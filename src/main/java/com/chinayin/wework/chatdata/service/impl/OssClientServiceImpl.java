package com.chinayin.wework.chatdata.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.chinayin.wework.chatdata.config.OssClientConfig;
import com.chinayin.wework.chatdata.service.OssClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author chianyin <whereismoney@qq.com>
 * 阿里云oss
 */
@Service("OssClientService")
@Slf4j
public class OssClientServiceImpl implements OssClientService {

    @Resource
    private OssClientConfig config;

    /**
     * 获取媒体地址
     *
     * @param objectName
     * @return
     */
    public String getMediaOssUrl(String objectName) {
        return "https://" + config.getBucket() + "." + config.getEndpoint() + "/" + objectName;
    }

    /**
     * 上传资源到 oss
     *
     * @param objectName
     * @param file
     */
    public void upload(String objectName, String file) {
        File fs = new File(file);
        log.info("{} .size = {}", objectName, fs.length());
        if (!fs.exists() || fs.length() < 1) {
            log.error("本地 oss 文件不存在, {} ", objectName);
            return;
        }
        OSS ossClient = new OSSClientBuilder().build(
                config.getEndpoint(), config.getAccessKeyId(), config.getAccessKeySecret());
        try {
            // 优先判断文件是否存在
            boolean found = ossClient.doesObjectExist(config.getBucket(), objectName);
            if (found) {
                log.info("[流程]上传 oss 同名跳过, {}", objectName);
            } else {
                // 禁止覆盖同名文件 https://help.aliyun.com/document_detail/146172.html
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setHeader("x-oss-forbid-overwrite", "true");
                // 上传
                PutObjectRequest putObjectRequest = new PutObjectRequest(config.getBucket(), objectName, fs, metadata);
                PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
                //log.info("[流程]上传 oss 成功, {}", putObjectResult.getResponse().getRequestId());
            }
            // 上传完删除文件
            fs.delete();
        } catch (OSSException ex) {
            // 同名文件不需要上传
            if (ex.getErrorCode().equals("FileAlreadyExists")) {
                log.info("[流程]上传 oss 同名跳过, {}", objectName);
            } else {
                log.error("[异常]Caught an OSSException, which means your request made it to OSS, but was rejected with an error response for some reason.\n" +
                                "Error Message: {}\n" +
                                "Error Code: {}\n" +
                                "Request ID: {}\n" +
                                "Host ID: {}",
                        ex.getErrorMessage(), ex.getErrorCode(), ex.getRequestId(), ex.getHostId());
            }
            // 存在同名文件避免后续产生垃圾文件,删除文件
            fs.delete();
        } catch (ClientException ex) {
            log.error("[异常]Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.\nError Message: {}", ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[异常]上传 oss 失败, {}", e.getMessage());
        } finally {
            ossClient.shutdown();
        }
    }
}
