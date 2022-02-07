package com.chinayin.wework.chatdata.service.impl;

import com.alibaba.fastjson.JSON;
import com.chinayin.wework.chatdata.config.WeWorkConfig;
import com.chinayin.wework.chatdata.model.ChatDataDTO;
import com.chinayin.wework.chatdata.model.ChatDataDetailDTO;
import com.chinayin.wework.chatdata.model.MediaFileDTO;
import com.chinayin.wework.chatdata.model.MessageDTO;
import com.chinayin.wework.chatdata.model.messagetype.item.TypeChatRecordItem;
import com.chinayin.wework.chatdata.model.messagetype.item.TypeMixedItem;
import com.chinayin.wework.chatdata.service.*;
import com.tencent.wework.Finance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;

/**
 * @author chianyin <whereismoney@qq.com>
 */
@Service("FinanceService")
@Slf4j
public class ChatDataServiceImpl implements ChatDataService {

    @Resource
    private WeWorkConfig weworkConfig;

    @Resource
    private MnsClientService queueService;

    @Resource
    private OssClientService ossService;

    @Resource
    private FileOutputService fileService;

    @Resource
    private EncryptService encryptService;

    @Resource
    private MediaService mediaService;

    @Qualifier("threadPool")
    @Resource
    private ExecutorService executorService;

    @Override
    public long getChatData(long seq) {
        // 初始化sdk
        long sdk = Finance.NewSdk();
        int initStatus = Finance.Init(sdk, weworkConfig.getCorpId(), weworkConfig.getCorpSecret());
        if (initStatus != 0) {
            log.error("[异常]sdk初始化失败, {}", initStatus);
            Finance.DestroySdk(sdk);
            return seq;
        }
        // 拉取消息
        long slice = Finance.NewSlice();
        int ret = Finance.GetChatData(sdk, seq, weworkConfig.getLimit(), weworkConfig.getProxy(), weworkConfig.getPasswd(), weworkConfig.getTimeout(), slice);
        if (ret != 0) {
            log.error("[异常]微信会话获取数据失败, {}", ret);
            Finance.FreeSlice(slice);
            Finance.DestroySdk(sdk);
            return seq;
        }
        // 获取slice内容
        ChatDataDTO chatDataDTO = JSON.parseObject(Finance.GetContentFromSlice(slice), ChatDataDTO.class);
        if (chatDataDTO.getErrCode() != 0) {
            log.error("[异常]拉消息失败, {}", chatDataDTO);
            Finance.FreeSlice(slice);
            Finance.DestroySdk(sdk);
            return seq;
        }
        if (Objects.isNull(chatDataDTO.getChatData()) || chatDataDTO.getChatData().size() == 0) {
            log.info("[流程]暂无消息 seqNum {}, {}", seq, chatDataDTO);
            Finance.FreeSlice(slice);
            Finance.DestroySdk(sdk);
            return seq;
        }
        // 逐条处理
        for (ChatDataDetailDTO chatDataDetailDTO : chatDataDTO.getChatData()) {
            this.handleMessage(sdk, chatDataDetailDTO);
            seq = chatDataDetailDTO.getSeq();
        }
        //
        Finance.FreeSlice(slice);
        Finance.DestroySdk(sdk);
        return seq;
    }


    private String decryptData(long sdk, Integer publicKeyVer, String encryptKey, String encryptChatMsg) {
        // 解密key
        String decodeKey = encryptService.decodeEncryptRandomKey(publicKeyVer, encryptKey);
        if (Objects.isNull(decodeKey)) {
            return null;
        }
        long slice = Finance.NewSlice();
        int decryptStatus = Finance.DecryptData(sdk, decodeKey, encryptChatMsg, slice);
        if (decryptStatus != 0) {
            log.error("DecryptData, {}", decryptStatus);
            Finance.FreeSlice(slice);
            return null;
        }
        String msg = Finance.GetContentFromSlice(slice);
        Finance.FreeSlice(slice);
        return msg;
    }

    private void handleMessage(long sdk, ChatDataDetailDTO chatDataDetailDTO) {
        String originMsg = this.decryptData(sdk, chatDataDetailDTO.getPublicKeyVer(), chatDataDetailDTO.getEncryptRandomKey(), chatDataDetailDTO.getEncryptChatMsg());
        if (Objects.isNull(originMsg)) {
            return;
        }

        // 保存原始数据
        fileService.saveOriginMessage(originMsg);

        // 解析数据
        MessageDTO messageDTO = null;
        try {
            messageDTO = JSON.parseObject(originMsg, MessageDTO.class);
        } catch (Exception e) {
            log.error("[异常]数据解析失败, {}", originMsg);
        }
        if (Objects.isNull(messageDTO)) {
            return;
        }
        log.info("msgId: {}, seq: {}", chatDataDetailDTO.getMsgId(), chatDataDetailDTO.getSeq());

        // 发送去消息队列处理,队列中高优先级
        String msg = JSON.toJSONString(messageDTO);
        log.debug("msg: {}", msg);
        com.aliyun.mns.model.Message mns = new com.aliyun.mns.model.Message(msg);
        mns.setPriority(3);
        queueService.sendMessageToQueue(mns);

        // 非发送类是没有type的,跳过处理
        if (Objects.isNull(messageDTO.getMsgType())) {
            log.debug("msg_type is null, {}, {}", chatDataDetailDTO.getMsgId(), msg);
            return;
        }

        // 上传媒体文件
        try {
            List<MediaFileDTO> mediaList = new ArrayList<>();
            // mixed消息,需要判断后拆开处理
            if (Objects.nonNull(messageDTO.getMixed()) && Objects.nonNull(messageDTO.getMixed().getItem())) {
                for (TypeMixedItem item : messageDTO.getMixed().getItem()) {
                    if (Objects.isNull(item))
                        continue;
                    MediaFileDTO media = new MediaFileDTO(item, messageDTO);
                    log.debug("mixed.media, {}", JSON.toJSONString(media));
                    mediaList.add(media);
                }
            } else if (Objects.nonNull(messageDTO.getChatRecord()) && Objects.nonNull(messageDTO.getChatRecord().getItem())) {
                for (TypeChatRecordItem item : messageDTO.getChatRecord().getItem()) {
                    if (Objects.isNull(item))
                        continue;
                    MediaFileDTO media = new MediaFileDTO(item, messageDTO);
                    log.debug("chatrecord.media, {}", JSON.toJSONString(media));
                    mediaList.add(media);
                }
            } else {
                mediaList.add(new MediaFileDTO(messageDTO));
            }
            for (MediaFileDTO media : mediaList) {
                if (Objects.nonNull(media.getSdkFileId())) {
                    executorService.submit(() -> handleMedia(media));
                }
            }
        } catch (Exception ex) {
            log.error("[异常]媒体文件解析或处理失败, {}", ex.getMessage());
        }

    }

    private void handleMedia(MediaFileDTO media) {
        String sdkFileId = media.getSdkFileId();
        String md5 = media.getMd5sum();
        String day = (new SimpleDateFormat("yyyy/MM")).format(new Date());
        String file = "wework/" + day + "/" + md5 + "." + media.getFileExt();
        String objectName = "wework/" + day + "/" + md5 + "." + media.getFileExt();
        try {
            // 下载
            mediaService.downloadMediaFile(sdkFileId, file, media.getFileSize());
            // 上传
            ossService.upload(objectName, file);
            //
            String ossUrl = ossService.getMediaOssUrl(objectName);
            media.setOssUrl(ossUrl);
            // 发送去消息队列处理,低优先级+延迟
            String msg = JSON.toJSONString(media);
            log.debug("media: {}", msg);
            com.aliyun.mns.model.Message mns = new com.aliyun.mns.model.Message(msg);
            mns.setPriority(10);
            mns.setDelaySeconds(10);
            queueService.sendMessageToQueue(mns);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[异常]处理媒体文件, {}, {}", e.getMessage(), sdkFileId);
        }
    }

}
