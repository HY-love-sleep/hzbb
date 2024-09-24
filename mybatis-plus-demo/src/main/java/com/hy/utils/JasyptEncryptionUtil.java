// package com.hy.utils;
//
// /**
//  * Description: Jasypt工具类
//  *
//  * @Author: yhong
//  * Date: 2024/5/6
//  */
// import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
//
// public class JasyptEncryptionUtil {
//
//     private static final String ENCRYPTION_ALGORITHM = "PBEWithMD5AndDES";
//     private static final String ENCRYPTION_PASSWORD = "hongy25";
//
//     private static StandardPBEStringEncryptor encryptor;
//
//     private JasyptEncryptionUtil() {
//         // 防止实例化
//     }
//
//     private static StandardPBEStringEncryptor getEncryptor() {
//         if (encryptor == null) {
//             encryptor = new StandardPBEStringEncryptor();
//             encryptor.setAlgorithm(ENCRYPTION_ALGORITHM);
//             encryptor.setPassword(ENCRYPTION_PASSWORD);
//         }
//         return encryptor;
//     }
//
//     /**
//      * 加密字符串
//      * @param value 待加密的明文
//      * @return 加密后的密文
//      */
//     public static String encrypt(String value) {
//         return getEncryptor().encrypt(value);
//     }
//
//     /**
//      * 解密字符串
//      * @param encryptedValue 已加密的密文
//      * @return 解密后的明文
//      */
//     public static String decrypt(String encryptedValue) {
//         return getEncryptor().decrypt(encryptedValue);
//     }
//
//     public static void main(String[] args) {
//         String originalPassword = "root";
//         String encryptedPassword = JasyptEncryptionUtil.encrypt(originalPassword);
//         System.out.println("Encrypted password: " + encryptedPassword);
//
//         String decryptedPassword = JasyptEncryptionUtil.decrypt(encryptedPassword);
//         System.out.println("Decrypted password: " + decryptedPassword);
//     }
// }
//
