package com.chinayin.wework.chatdata.config;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.MNSClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chianyin <whereismoney@qq.com>
 * 阿里云MNS队列配置
 */
@Configuration
@Slf4j
@Data
@ConfigurationProperties(prefix = "mns")
public class MnsClientConfig {

    private String accessKeyId;

    private String accessKeySecret;

    private String endpoint;

    private String queue;

    @Bean
    public MNSClient mnsClient() {
        log.info(endpoint);
        return (new CloudAccount(accessKeyId, accessKeySecret, endpoint)).getMNSClient();
    }
}
