package com.chinayin.wework.chatdata.service;

/**
 * @author chianyin <whereismoney@qq.com>
 */
public interface ChatDataService {

    /**
     * 拉微信消息
     *
     * @param seq 起始位置
     * @return 拉取位置
     */
    long getChatData(long seq);
}
