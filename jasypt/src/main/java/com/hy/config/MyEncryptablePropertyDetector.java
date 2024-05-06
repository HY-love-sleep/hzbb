package com.hy.config;

import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

public class MyEncryptablePropertyDetector implements EncryptablePropertyDetector {
    private String prefix = "Dsj(";
    private String suffix = ")";
    public MyEncryptablePropertyDetector() {
    }
    public MyEncryptablePropertyDetector(String prefix, String suffix) {
        Assert.notNull(prefix, "Prefix can't be Null");
        Assert.notNull(suffix, "Suffix can't be Null");
        this.prefix = prefix;
        this.suffix = suffix;
    }
    /**
    *判断配置文件中的数据是否是按这里指定前后缀组装的
    **/
    @Override
    public boolean isEncrypted(String message) {
        if (StringUtils.isBlank(message)) {
            return false;
        } else {
            String trimmedValue = message.trim();
            return trimmedValue.startsWith(this.prefix) && trimmedValue.endsWith(this.suffix);
        }
    }
    @Override
    public String unwrapEncryptedValue(String message) {
        /**
         *获取到 上面方法返回true的数据
         * 此处原数据返回，不作处理，统一在DefaultEncryptor处理
         */
        return message;
    }
    public String getPrefix() {
        return prefix;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    public String getSuffix() {
        return suffix;
    }
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
