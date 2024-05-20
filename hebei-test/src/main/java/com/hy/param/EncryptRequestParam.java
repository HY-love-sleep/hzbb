package com.hy.param;

/**
 * Description: 加密接口请求参数
 *
 * @author: yhong
 * Date: 2024/5/15
 */
public class EncryptRequestParam {

    /**
     * version : 1
     * signAlgo : HmacSHA256
     * signature : s9MSSGcaLexKk9tVom2ETFN4WzKnZz4HVuTYGceZb0g=
     * appId : {{appId}}
     * deviceId : {{deviceId}}
     * transId : 1234567890123456
     * keyId : 12c815b69e5e493288596643f69f1202
     * mode : ECB
     * iv : 1234567812345678
     * padding : PKCS7Padding
     * plainText : 8o3nymQLL5OQKuyAUpjRUg==
     */

    private String version;
    private String signAlgo;
    private String signature;
    private String appId;
    private String deviceId;
    private String transId;
    private String keyId;
    private String mode;
    private String iv;
    private String padding;
    private String plainText;

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

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
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

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getPadding() {
        return padding;
    }

    public void setPadding(String padding) {
        this.padding = padding;
    }

    public String getPlainText() {
        return plainText;
    }

    public void setPlainText(String plainText) {
        this.plainText = plainText;
    }
}
