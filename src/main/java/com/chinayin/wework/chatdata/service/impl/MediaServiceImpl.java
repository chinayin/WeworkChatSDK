package com.chinayin.wework.chatdata.service.impl;

import com.chinayin.wework.chatdata.config.WeWorkConfig;
import com.chinayin.wework.chatdata.service.MediaService;
import com.tencent.wework.Finance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author chianyin <whereismoney@qq.com>
 */
@Service("MediaService")
@Slf4j
public class MediaServiceImpl implements MediaService {

    @Resource
    private WeWorkConfig weworkConfig;

    private long init() {
        // 初始化sdk
        long sdk = Finance.NewSdk();
        int status = Finance.Init(sdk, weworkConfig.getCorpId(), weworkConfig.getCorpSecret());
        if (status != 0) {
            log.error("[异常]MediaServiceImpl.sdk.init失败, {}", status);
            Finance.DestroySdk(sdk);
            throw new RuntimeException(String.format("[异常]MediaServiceImpl.sdk.init失败, %s", status));
        }
        return sdk;
    }

    @Override
    public void downloadMediaFile(String sdkFileId, String file, long fileSize) {
        log.info("下载媒体文件 size: {}, {}", fileSize, sdkFileId);
        // 判断文件夹是否存在
        File fs = new File(file);
        if (!fs.getParentFile().exists()) {
            fs.getParentFile().mkdirs();
        }
        // 判断本地是否存在文件并且文件大小与远程一致,防止多次下载
        if (fs.exists() && fs.length() == fileSize) {
            log.debug("媒体文件本地已存在 {},{}", file, sdkFileId);
            return;
        }
        // 初始化sdk
        long sdk;
        try {
            sdk = this.init();
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
        // 下载媒体文件
        String buf = "";
        long index = 0;
        int retry = 0;
        while (true) {
            if (retry >= 3) {
                log.debug("媒体文件下载重试次数超过3次, {}", sdkFileId);
                break;
            }
            long mediaData = Finance.NewMediaData();
            int ret = Finance.GetMediaData(sdk, buf, sdkFileId, weworkConfig.getProxy(), weworkConfig.getPasswd(), weworkConfig.getTimeout(), mediaData);
            if (ret != 0) {
                log.error("获取媒体文件异常, {}", ret);
                Finance.FreeMediaData(mediaData);
                retry++;
                continue;
            }
            log.debug("media_data_long: {}, len: {}, data_len: {}, is_finish: {}", index, Finance.GetIndexLen(mediaData), Finance.GetDataLen(mediaData), Finance.IsMediaDataFinish(mediaData));
            try {
                //大于512k的文件会分片拉取，此处需要使用追加写，避免后面的分片覆盖之前的数据。
                FileOutputStream outputStream = new FileOutputStream(fs, true);
                outputStream.write(Finance.GetData(mediaData));
                outputStream.close();
            } catch (Exception e) {
                log.error("[异常]写本地文件失败, {}", e.getMessage());
                Finance.FreeMediaData(mediaData);
                retry++;
                continue;
            }
            if (Finance.IsMediaDataFinish(mediaData) == 1) {
                Finance.FreeMediaData(mediaData);
                break;
            }
            index++;
            buf = Finance.GetOutIndexBuf(mediaData);
            Finance.FreeMediaData(mediaData);
        }
        Finance.DestroySdk(sdk);
        log.info("[流程]媒体文件 size: {}, {}, {}", fs.length(), file, sdkFileId);
    }
}
