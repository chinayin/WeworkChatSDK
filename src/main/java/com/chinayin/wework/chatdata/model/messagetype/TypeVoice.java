package com.chinayin.wework.chatdata.model.messagetype;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 语音消息
 */
@Data
public class TypeVoice extends AbstractTypeFile {

    /**
     * 语音消息大小。Uint32类型
     */
    @JSONField(name = "voice_size")
    private Integer voiceSize;

    /**
     * 播放长度。Uint32类型
     */
    @JSONField(name = "play_length")
    private Integer playLength;

}
