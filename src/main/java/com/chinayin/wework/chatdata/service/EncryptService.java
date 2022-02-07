package com.chinayin.wework.chatdata.service;

/**
 * @author chianyin <whereismoney@qq.com>
 */
public interface EncryptService {

    /**
     * 解密内容
     *
     * @param encryptRandomKey
     */
    String decodeEncryptRandomKey(Integer publicKeyVer, String encryptRandomKey);

}
