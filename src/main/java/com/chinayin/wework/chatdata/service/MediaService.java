package com.chinayin.wework.chatdata.service;

/**
 * @author chianyin <whereismoney@qq.com>
 */
public interface MediaService {

    /**
     * 下载企业微信媒体文件
     *
     * @param sdkFileId
     * @param file
     * @param fileSize
     */
    void downloadMediaFile(String sdkFileId, String file, long fileSize);

}
