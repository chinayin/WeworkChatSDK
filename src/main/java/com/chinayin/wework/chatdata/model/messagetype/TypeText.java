package com.chinayin.wework.chatdata.model.messagetype;

import lombok.Data;

/**
 * @author chianyin <whereismoney@qq.com>
 * 文本消息
 */
@Data
public class TypeText {

    /**
     * type 为 text 的时候文本内容
     */
    private String content;

}
