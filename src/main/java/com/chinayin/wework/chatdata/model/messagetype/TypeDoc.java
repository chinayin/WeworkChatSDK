package com.chinayin.wework.chatdata.model.messagetype;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 在线文档消息
 */
@Data
public class TypeDoc {

    /**
     * 在线文档名称
     */
    private String title;

    /**
     * 在线文档链接
     */
    @JSONField(name = "link_url")
    private String linkUrl;

    /**
     * 在线文档创建者。本企业成员创建为userid；外部企业成员创建为external_userid
     */
    @JSONField(name = "doc_creator")
    private String docCreator;

}
