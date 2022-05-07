package com.chinayin.wework.chatdata.model.messagetype;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author chianyin <whereismoney@qq.com>
 * 图文消息
 */
@Data
public class TypeNews {

    /**
     * 图文消息数组，每个item结构包含title、description、url、picurl等结构
     */
    private List<TypeNewsItem> item;

}

@Data
class TypeNewsItem {

    /**
     * 图文消息标题
     */
    private String title;

    /**
     * 图文消息描述
     */
    private String description;

    /**
     * 图文消息点击跳转地址
     */
    private String url;

    /**
     * 图文消息配图的url
     */
    @JSONField(name = "picurl")
    private String picUrl;

}
