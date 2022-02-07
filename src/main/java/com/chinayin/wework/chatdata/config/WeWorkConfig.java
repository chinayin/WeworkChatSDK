package com.chinayin.wework.chatdata.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * @author chianyin <whereismoney@qq.com>
 * 企业微信配置文件
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "app")
public class WeWorkConfig {

    private Integer limit = 100;

    private Integer timeout = 10;

    private String corpId;

    private String corpSecret;

    private String env;

    private String proxy;

    private String passwd;

}
