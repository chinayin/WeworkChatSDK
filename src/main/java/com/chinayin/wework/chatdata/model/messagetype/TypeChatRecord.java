package com.chinayin.wework.chatdata.model.messagetype;

import com.alibaba.fastjson.annotation.JSONField;
import com.chinayin.wework.chatdata.model.messagetype.item.TypeChatRecordItem;
import lombok.Data;

import java.util.List;

/**
 * @author chianyin <whereismoney@qq.com>
 * 会话记录消息
 */
@Data
public class TypeChatRecord {

    /**
     * 聊天记录标题。String类型
     */
    @JSONField(ordinal = 1)
    private String title;

    /**
     * 消息记录内的消息内容，批量数据
     */
    @JSONField(ordinal = 2)
    private List<TypeChatRecordItem> item;
}

