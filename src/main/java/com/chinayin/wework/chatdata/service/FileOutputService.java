package com.chinayin.wework.chatdata.service;

/**
 * @author chianyin <whereismoney@qq.com>
 */
public interface FileOutputService {

    /**
     * 把消息保存到文件
     *
     * @param msg 消息
     */
    void saveMessage(String msg);

    /**
     * 记录未处理的消息
     *
     * @param msg 消息
     */
    void saveOriginMessage(String msg);
}
