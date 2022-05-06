package com.chinayin.wework.chatdata.model;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.annotation.JSONField;
import com.chinayin.wework.chatdata.model.messagetype.TypeEmotion;
import com.chinayin.wework.chatdata.model.messagetype.TypeFile;
import com.chinayin.wework.chatdata.model.messagetype.TypeImage;
import com.chinayin.wework.chatdata.model.messagetype.TypeVideo;
import com.chinayin.wework.chatdata.model.messagetype.item.TypeChatRecordItem;
import com.chinayin.wework.chatdata.model.messagetype.item.TypeMixedItem;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author chianyin <whereismoney@qq.com>
 */
@Data
@Slf4j
public class MediaFileDTO {

    /**
     * 固定值,队列服务时使用
     */
    @JSONField(ordinal = 1, name = "job")
    private String job = "ChatMessageMedia";

    @JSONField(ordinal = 2, name = "msgtype")
    private String msgType;

    @JSONField(ordinal = 3, name = "msgid")
    private String msgId;

    @JSONField(ordinal = 4, name = "sdkfileid")
    private String sdkFileId;

    @JSONField(ordinal = 5, name = "md5sum")
    private String md5sum;

    @JSONField(ordinal = 6, name = "file_ext")
    private String fileExt;

    @JSONField(ordinal = 7, name = "file_size")
    private long fileSize;

    /**
     * oss保存文件名
     */
    @JSONField(ordinal = 8, name = "oss_url")
    private String ossUrl;

    public static final String MSG_TYPE_IMAGE = "image";
    public static final String MSG_TYPE_VOICE = "voice";
    public static final String MSG_TYPE_VIDEO = "video";
    public static final String MSG_TYPE_EMOTION = "emotion";
    public static final String MSG_TYPE_FILE = "file";
    //
    public static final String MSG_TYPE_MIXED = "mixed";
    public static final String MSG_TYPE_CHAT_RECORD = "chatrecord";

    public static final List<String> NEED_MEDIA_FILE_TYPE = new ArrayList<String>() {{
        add(MSG_TYPE_IMAGE);
        add(MSG_TYPE_VOICE);
        add(MSG_TYPE_VIDEO);
        add(MSG_TYPE_EMOTION);
        add(MSG_TYPE_FILE);
    }};

    public MediaFileDTO() {

    }

    /**
     * 普通消息
     *
     * @param msgDTO
     */
    public MediaFileDTO(MessageDTO msgDTO) {
        this.msgType = msgDTO.getMsgType();
        this.msgId = msgDTO.getMsgId();
        switch (msgDTO.getMsgType()) {
            case MSG_TYPE_IMAGE:
                this.sdkFileId = msgDTO.getImage().getSdkFileId();
                this.md5sum = msgDTO.getImage().getMd5sum();
                this.fileSize = msgDTO.getImage().getFileSize();
                this.fileExt = "jpg";
                break;
            case MSG_TYPE_EMOTION:
                this.sdkFileId = msgDTO.getEmotion().getSdkFileId();
                this.md5sum = msgDTO.getEmotion().getMd5sum();
                this.fileSize = msgDTO.getEmotion().getImageSize();
                if (msgDTO.getEmotion().getType() == 1) {
                    this.fileExt = "gif";
                } else {
                    this.fileExt = "png";
                }
                break;
            case MSG_TYPE_VOICE:
                this.sdkFileId = msgDTO.getVoice().getSdkFileId();
                this.md5sum = msgDTO.getVoice().getMd5sum();
                this.fileSize = msgDTO.getVoice().getVoiceSize();
                this.fileExt = "amr";
                break;
            case MSG_TYPE_VIDEO:
                this.sdkFileId = msgDTO.getVideo().getSdkFileId();
                this.md5sum = msgDTO.getVideo().getMd5sum();
                this.fileSize = msgDTO.getVideo().getFileSize();
                this.fileExt = "mp4";
                break;
            case MSG_TYPE_FILE:
                this.sdkFileId = msgDTO.getFile().getSdkFileId();
                this.md5sum = msgDTO.getFile().getMd5sum();
                this.fileSize = msgDTO.getFile().getFileSize();
                this.fileExt = msgDTO.getFile().getFileExt();
                break;
        }
        // 发现oss上传时有文件md5是空的情况,特殊处理一下
        this.fixMsgMd5sum();
    }

    /**
     * 混合消息
     *
     * @param item
     * @param message
     */
    public MediaFileDTO(TypeMixedItem item, MessageDTO message) {
        this.msgType = item.getType();
        this.msgId = message.getMsgId();
        switch (this.msgType) {
            case MSG_TYPE_IMAGE:
                TypeImage image = JSON.parseObject(item.getContent(), TypeImage.class);
                this.sdkFileId = image.getSdkFileId();
                this.md5sum = image.getMd5sum();
                this.fileSize = image.getFileSize();
                this.fileExt = "jpg";
                break;
            case MSG_TYPE_EMOTION:
                TypeEmotion emo = JSON.parseObject(item.getContent(), TypeEmotion.class);
                this.sdkFileId = emo.getSdkFileId();
                this.md5sum = emo.getMd5sum();
                this.fileSize = emo.getImageSize();
                if (emo.getType() == 1) {
                    this.fileExt = "gif";
                } else {
                    this.fileExt = "png";
                }
                break;
            case MSG_TYPE_VOICE:
                // mixed消息无法同时有语音
                break;
            case MSG_TYPE_VIDEO:
                TypeVideo video = JSON.parseObject(item.getContent(), TypeVideo.class);
                this.sdkFileId = video.getSdkFileId();
                this.md5sum = video.getMd5sum();
                this.fileSize = video.getFileSize();
                this.fileExt = "mp4";
                break;
            case MSG_TYPE_FILE:
                TypeFile file = JSON.parseObject(item.getContent(), TypeFile.class);
                this.sdkFileId = file.getSdkFileId();
                this.md5sum = file.getMd5sum();
                this.fileSize = file.getFileSize();
                this.fileExt = file.getFileExt();
                break;
        }
        // 发现oss上传时有文件md5是空的情况,特殊处理一下
        this.fixMsgMd5sum();
    }

    /**
     * 转发消息
     *
     * @param item
     * @param message
     */
    public MediaFileDTO(TypeChatRecordItem item, MessageDTO message) {
        this.msgType = this.parseChatRecordType(item.getType());
        this.msgId = message.getMsgId();
        switch (this.msgType) {
            case MSG_TYPE_IMAGE:
                TypeImage image = JSON.parseObject(item.getContent(), TypeImage.class);
                this.sdkFileId = image.getSdkFileId();
                this.md5sum = image.getMd5sum();
                this.fileSize = image.getFileSize();
                this.fileExt = "jpg";
                break;
            case MSG_TYPE_EMOTION:
                TypeEmotion emo = JSON.parseObject(item.getContent(), TypeEmotion.class);
                this.sdkFileId = emo.getSdkFileId();
                this.md5sum = emo.getMd5sum();
                this.fileSize = emo.getImageSize();
                if (emo.getType() == 1) {
                    this.fileExt = "gif";
                } else {
                    this.fileExt = "png";
                }
                break;
            case MSG_TYPE_VOICE:
                // mixed消息无法同时有语音
                break;
            case MSG_TYPE_VIDEO:
                TypeVideo video = JSON.parseObject(item.getContent(), TypeVideo.class);
                this.sdkFileId = video.getSdkFileId();
                this.md5sum = video.getMd5sum();
                this.fileSize = video.getFileSize();
                this.fileExt = "mp4";
                break;
            case MSG_TYPE_FILE:
                TypeFile file = JSON.parseObject(item.getContent(), TypeFile.class);
                this.sdkFileId = file.getSdkFileId();
                this.md5sum = file.getMd5sum();
                this.fileSize = file.getFileSize();
                this.fileExt = file.getFileExt();
                break;
        }
        // 发现oss上传时有文件md5是空的情况,特殊处理一下
        this.fixMsgMd5sum();
    }

    /**
     * 格式化为普通type值
     *
     * @param type
     * @return
     */
    private String parseChatRecordType(String type) {
        return type.replace("ChatRecord", "").toLowerCase();
    }

    /**
     * 修复md5sum为空情况
     */
    private void fixMsgMd5sum() {
        if (Objects.nonNull(this.sdkFileId) && (
                Objects.isNull(this.md5sum) || this.md5sum.length() == 0
        )) {
            log.error("md5sum empty, msgId: %s", this.msgId);
            // 随机生成一个
            String md5sum = "md5sum-" + System.currentTimeMillis();
            this.md5sum = md5sum;
        }
    }

}
