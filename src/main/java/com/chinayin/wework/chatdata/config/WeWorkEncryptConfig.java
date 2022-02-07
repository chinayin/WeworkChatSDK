package com.chinayin.wework.chatdata.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.TreeMap;


/**
 * @author chianyin <whereismoney@qq.com>
 * 企业微信私钥
 * key格式 : publicKeyVer,privateKey
 * 这个是由 pkcs1 转来的 pkcs8.key
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "encrypt")
public class WeWorkEncryptConfig {

    /**
     * 加密私钥  <版本号,私钥内容>
     */
    private Map<String, String> privateKey = new TreeMap<>();

}
