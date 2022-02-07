package com.chinayin.wework.chatdata.model.messagetype.item;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 混合消息Item
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypeMixedItem {

    /**
     * 混合消息单条类型
     */
    @JSONField(ordinal = 1)
    private String type;

    /**
     * 混合消息内容
     */
    @JSONField(ordinal = 2)
    private String content;

}