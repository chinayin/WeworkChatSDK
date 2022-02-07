package com.chinayin.wework.chatdata.service.impl;

import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.Message;
import com.chinayin.wework.chatdata.config.MnsClientConfig;
import com.chinayin.wework.chatdata.service.MnsClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author chianyin <whereismoney@qq.com>
 * 阿里云mns
 */
@Service("MnsClientService")
@Slf4j
public class MnsClientServiceImpl implements MnsClientService {

    @Resource
    private MNSClient client;

    @Resource
    private MnsClientConfig config;

    @Override
    public String sendMessageToQueue(String msg) {
        return this.sendMessageToQueue(new Message(msg));
    }

    /**
     * priority 发送优先级(取值范围1~16,其中1为最高优先级,队列默认优先级为8)
     *
     * @param msg
     * @return
     */
    @Override
    public String sendMessageToQueue(Message msg) {
        try {
            CloudQueue queue = client.getQueueRef(config.getQueue());
            Message message = queue.putMessage(msg);
            String msgId = message.getMessageId();
            log.info("[流程]投递 mns 成功, {}, {}", msgId, message.getRequestId());
            return msgId;
        } catch (ClientException e) {
            log.error("[异常]投递 mns 失败, Caught an ClientException: {}", e.toString());
        } catch (ServiceException e) {
            log.error("[异常]投递 mns 失败, Caught an ServiceException: {}", e.toString());
        } catch (Exception e) {
            log.error("[异常]投递 mns 失败, {}", e.getMessage());
        }
        return null;
    }

}
