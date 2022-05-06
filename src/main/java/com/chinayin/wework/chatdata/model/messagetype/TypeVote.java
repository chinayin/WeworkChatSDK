package com.chinayin.wework.chatdata.model.messagetype;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 投票消息
 */
@Data
public class TypeVote {

    /**
     * 投票主题
     */
    @JSONField(name = "votetitle")
    private String title;

    /**
     * 投票选项，可能多个内容
     */
    @JSONField(name = "voteitem")
    private String[] item;

    /**
     * 投票类型.101发起投票、102参与投票
     */
    @JSONField(name = "votetype")
    private Integer type;

    /**
     * 投票id，方便将参与投票消息与发起投票消息进行前后对照
     */
    @JSONField(name = "voteid")
    private String id;

}
