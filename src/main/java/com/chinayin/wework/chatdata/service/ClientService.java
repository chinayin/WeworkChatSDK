package com.chinayin.wework.chatdata.service;

/**
 * @author chianyin <whereismoney@qq.com>
 */
public interface ClientService {
    long getSdk();

    void downloadMediaFile(String sdkFileId, String file, long fileSize);

    String decryptData(Integer publicKeyVer, String encryptKey, String encryptChatMsg);

}
