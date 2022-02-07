package com.chinayin.wework.chatdata.model.messagetype;

import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 小程序消息
 */
@Data
public class TypeTodo {

    /**
     * 待办的来源文本
     */
    private String title;

    /**
     * 待办的具体内容
     */
    private String content;

}
