package com.chinayin.wework.chatdata.model.messagetype;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 小程序消息
 */
@Data
public class TypeWeapp {

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息描述
     */
    private String description;

    /**
     * 用户名称
     */
    @JSONField(name = "username")
    private String userName;

    /**
     * 小程序名称
     */
    @JSONField(name = "displayname")
    private String displayName;

}
