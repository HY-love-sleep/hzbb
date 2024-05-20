package com.hy.conf;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description: 密码机接口配置类
 *
 * @author: yhong
 * Date: 2024/5/15
 */
@ConfigurationProperties(prefix = "hb", ignoreUnknownFields = false)
@Component
public class SDKProperties {
    private String version;
    private String signAlgo;
    private String signature;
    private String appId;
    private String transId;
    private String keyUsage;
    private String keyId;
    private String key;
    private String deviceId;
    private String keySpec;
    private String mode;
    private String iv;
    private String padding;
    private String createKeyUrl;
    private String encryptUrl;
    private String decryptUrl;
    private String getHmacUrl;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSignAlgo() {
        return signAlgo;
    }

    public void setSignAlgo(String signAlgo) {
        this.signAlgo = signAlgo;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getKeyUsage() {
        return keyUsage;
    }

    public void setKeyUsage(String keyUsage) {
        this.keyUsage = keyUsage;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getKeySpec() {
        return keySpec;
    }

    public void setKeySpec(String keySpec) {
        this.keySpec = keySpec;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getIv() {
        return iv;
    }

    /**
     * IV 值，mode 为 CBC 时有效。
     * 注意：如果 mode 为 CBC，iv 值填写 16 位 的 base64 码。
     * @param iv
     */
    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getPadding() {
        return padding;
    }

    public void setPadding(String padding) {
        this.padding = padding;
    }

    public String getCreateKeyUrl() {
        return createKeyUrl;
    }

    public void setCreateKeyUrl(String createKeyUrl) {
        this.createKeyUrl = createKeyUrl;
    }

    public String getEncryptUrl() {
        return encryptUrl;
    }

    public void setEncryptUrl(String encryptUrl) {
        this.encryptUrl = encryptUrl;
    }

    public String getDecryptUrl() {
        return decryptUrl;
    }

    public void setDecryptUrl(String decryptUrl) {
        this.decryptUrl = decryptUrl;
    }

    public String getGetHmacUrl() {
        return getHmacUrl;
    }

    public void setGetHmacUrl(String getHmacUrl) {
        this.getHmacUrl = getHmacUrl;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
