package com.chinayin.wework.chatdata.service;

/**
 * @author chianyin <whereismoney@qq.com>
 */
public interface OssClientService {

    /**
     * 获取媒体地址
     *
     * @param objectName
     * @return
     */
    String getMediaOssUrl(String objectName);

    /**
     * 上传资源到 oss
     *
     * @param objectName
     * @param file
     */
    void upload(String objectName, String file);

}
