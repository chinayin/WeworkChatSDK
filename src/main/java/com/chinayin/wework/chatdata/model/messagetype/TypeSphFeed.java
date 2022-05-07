package com.chinayin.wework.chatdata.model.messagetype;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 视频号消息
 */
@Data
public class TypeSphFeed {

    /**
     * 视频号消息类型。2 图片、4 视频、9 直播
     */
    @JSONField(name = "feed_type")
    private Integer feedType;

    /**
     * 视频号账号名称
     */
    @JSONField(name = "sph_name")
    private String sphName;

    /**
     * 视频号消息描述
     */
    @JSONField(name = "feed_desc")
    private String feedDesc;

}
