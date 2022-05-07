package com.chinayin.wework.chatdata.model.messagetype;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 表情消息
 */
@Data
public class TypeEmotion extends AbstractTypeFile {

    /**
     * 表情类型，png或者gif.1表示gif 2表示png。Uint32类型
     */
    private Integer type;

    /**
     * 表情图片宽度。Uint32类型
     */
    private Integer width;

    /**
     * 表情图片高度。Uint32类型
     */
    private Integer height;

    /**
     * 资源的文件大小。Uint32类型
     */
    @JSONField(name = "imagesize")
    private Integer imageSize;

}
