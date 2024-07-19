package com.hy.crypto;

/**
 * Description: 加解密接口
 *
 * @author: yhong
 * Date: 2024/7/17
 */
public interface EncryptionStrategy {
    String encrypt(String data);
    String decrypt(String encryptedData);
    String support();
}


