package com.chinayin.wework.chatdata.service.impl;

import com.chinayin.wework.chatdata.config.WeWorkEncryptConfig;
import com.chinayin.wework.chatdata.service.EncryptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;

/**
 * @author chianyin <whereismoney@qq.com>
 * 加解密处理
 */
@Slf4j
@Service("EncryptService")
public class EncryptServiceImpl implements EncryptService {

    @Resource
    private WeWorkEncryptConfig encryptConfig;

    private String getPriKey(Integer publicKeyVer) {
        return encryptConfig.getPrivateKey().get(String.valueOf(publicKeyVer));
    }

    public String decodeEncryptRandomKey(Integer publicKeyVer, String encryptRandomKey) {
        String key = getPriKey(publicKeyVer);
        if (Objects.isNull(key)) {
            log.error("decodeEncryptRandomKey failed, privateKey(keyVer={}) is null", publicKeyVer);
            return null;
        }
        Base64.Decoder decoder = Base64.getMimeDecoder();
        byte[] keyBytes;
        try {
            keyBytes = decoder.decode(key);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            Cipher c1 = Cipher.getInstance("RSA");
            c1.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] temp = c1.doFinal(Base64.getMimeDecoder().decode(encryptRandomKey));
            return new String(temp);
        } catch (Exception e) {
            log.error("decodeEncryptRandomKey failed, {}", e.getMessage());
        }
        return null;
    }

}
