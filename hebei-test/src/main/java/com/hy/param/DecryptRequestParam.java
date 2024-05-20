package com.hy.param;

/**
 * Description: 解密接口请求参数
 *
 * @author: yhong
 * Date: 2024/5/15
 */
public class DecryptRequestParam {

    /**
     * version : 1
     * signAlgo : HmacSHA256
     * signature : NIuiI4sxjgBkOrSVDgcFLVqEzTSt3vexlgVkxGPVBHQ=
     * appId : {{appId}}
     * deviceId : {{deviceId}}
     * transId : 1234567890123456
     * keyId : 12c815b69e5e493288596643f69f1202
     * encData : MDoCAQEwEgIBATAKBggqgRzPVQFoAQoBAQMhADE03gkObklhZXLE9OBxoOAVtV/ChScbqs5NwDLQzipU
     */

    private String version;
    private String signAlgo;
    private String signature;
    private String appId;
    private String deviceId;
    private String transId;
    private String keyId;
    private String encData;

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

    public String getEncData() {
        return encData;
    }

    public void setEncData(String encData) {
        this.encData = encData;
    }
}
