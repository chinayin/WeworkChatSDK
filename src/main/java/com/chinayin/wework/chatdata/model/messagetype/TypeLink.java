package com.chinayin.wework.chatdata.model.messagetype;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 链接消息
 */
@Data
public class TypeLink {

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息描述
     */
    private String description;

    /**
     * 链接url地址
     */
    @JSONField(name = "link_url")
    private String linkUrl;

    /**
     * 链接图片url
     */
    @JSONField(name = "image_url")
    private String imageUrl;


}
