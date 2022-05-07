package com.chinayin.wework.chatdata.model.messagetype;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 视频消息
 */
@Data
public class TypeVideo extends AbstractTypeFile {

    /**
     * 视频播放长度。Uint32类型
     */
    @JSONField(name = "play_length")
    private Integer playLength;

}
