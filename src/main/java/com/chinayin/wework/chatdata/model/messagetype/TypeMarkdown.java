package com.chinayin.wework.chatdata.model.messagetype;

import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * MarkDown格式消息
 */
@Data
public class TypeMarkdown {

    /**
     * markdown消息内容，目前为机器人发出的消息
     */
    private String content;

}
