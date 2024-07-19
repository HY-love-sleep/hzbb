package com.hy.crypto.impl;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.hy.crypto.EncryptionStrategy;
import com.hy.crypto.enums.EncryptionAlgorithm;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * Description: AES实现加解密
 *
 * @author: yhong
 * Date: 2024/7/19
 */
@Component
public class AesEncryptionStrategy implements EncryptionStrategy {

    private static final String KEY = "YourSecureKey123";
    private final AES aes;
    public AesEncryptionStrategy() {
        aes = SecureUtil.aes(KEY.getBytes());
    }
    @Override
    public String encrypt(String data) {
        return aes.encryptHex(data);
    }

    @Override
    public String decrypt(String encryptedData) {
        return aes.decryptStr(encryptedData, StandardCharsets.UTF_8);
    }

    @Override
    public String support() {
        return EncryptionAlgorithm.AES.getAlgorithmName();
    }
}
