package com.chinayin.wework.chatdata.model.messagetype.item;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 会话记录消息 item 内容
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypeChatRecordItem {

    /**
     * 每条聊天记录的具体消息类型：ChatRecordText/ ChatRecordFile/ ChatRecordImage/ ChatRecordVideo/ ChatRecordLink/ ChatRecordLocation/ ChatRecordMixed ….
     */
    @JSONField(ordinal = 1)
    private String type;

    /**
     * 消息时间，utc时间，单位秒。
     */
    @JSONField(ordinal = 2, name = "msgtime")
    private long msgTime;

    /**
     * 消息内容。Json串，内容为对应类型的json。String类型
     */
    @JSONField(ordinal = 3)
    private String content;

    /**
     * 是否来自群会话。Bool类型
     */
    @JSONField(ordinal = 4, name = "from_chatroom")
    private boolean fromChatRoom;
}