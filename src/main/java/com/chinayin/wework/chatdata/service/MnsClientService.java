package com.chinayin.wework.chatdata.service;

import com.aliyun.mns.model.Message;

/**
 * @author chianyin <whereismoney@qq.com>
 */
public interface MnsClientService {
    /**
     * 发送消息到 mns
     *
     * @param msg
     * @return
     */
    String sendMessageToQueue(String msg);

    String sendMessageToQueue(Message msg);
}
