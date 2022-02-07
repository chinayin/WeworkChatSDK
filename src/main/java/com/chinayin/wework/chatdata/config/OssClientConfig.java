package com.chinayin.wework.chatdata.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chianyin <whereismoney@qq.com>
 * 阿里云OSS上传配置
 */
@Configuration
@Slf4j
@Data
@ConfigurationProperties(prefix = "oss")
public class OssClientConfig {

    private String accessKeyId;

    private String accessKeySecret;

    private String endpoint;

    private String bucket;

    @Bean
    public OSS ossClient() {
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}
