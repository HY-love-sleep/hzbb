package com.hy.config;

import com.hy.utils.KaiserUtil;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Value;

public class DefaultEncryptor implements StringEncryptor {
    /**
     * 获取写在配置文件中的参数，这里是解密的密码
     **/
    @Value("${jasypt.encryptor.password}")
    private int decryptPassword;

    private MyEncryptablePropertyDetector propertyDetector;

    public DefaultEncryptor() {
    }

    public DefaultEncryptor(MyEncryptablePropertyDetector propertyDetector) {
        this.propertyDetector = propertyDetector;
    }

    /**
     * 这里是加密方法，不在这里加密，原参数返回
     **/
    @Override
    public String encrypt(String encryptMessage) {
        return encryptMessage;
    }

    /**
     * 解密
     **/
    @Override
    public String decrypt(String decryptMessage) {
        /**
         * 从MyEncryptablePropertyDetector的 unwrapEncryptedValue方法返回的数据在这里处理
         */
        String prefix = propertyDetector.getPrefix();
        String suffix = propertyDetector.getSuffix();
        int prefixIndex = decryptMessage.indexOf(prefix);
        int suffixIndex = decryptMessage.indexOf(suffix);
        /**
         * 截取括号中间部分，例如：Dsj(root) 里面的：root
         */
        decryptMessage = decryptMessage.substring(prefixIndex + prefix.length(), suffixIndex);
        /**
         * 做解密：加解密公共方法
         */
        String result = KaiserUtil.decryptKaiser(decryptMessage, decryptPassword);
        return result;
    }
}
