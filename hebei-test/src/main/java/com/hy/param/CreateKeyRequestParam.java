package com.hy.param;

import java.util.Objects;

public class CreateKeyRequestParam {

    private String version;
    private String signAlgo;
    private String signature;
    private String appId;
    private String deviceId;
    private String transId;
    private String keyUsage;
    private String keySpec;

    // Getters and Setters

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

    public String getKeyUsage() {
        return keyUsage;
    }

    public void setKeyUsage(String keyUsage) {
        this.keyUsage = keyUsage;
    }

    public String getKeySpec() {
        return keySpec;
    }

    public void setKeySpec(String keySpec) {
        this.keySpec = keySpec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateKeyRequestParam that = (CreateKeyRequestParam) o;
        return Objects.equals(version, that.version) &&
                Objects.equals(signAlgo, that.signAlgo) &&
                Objects.equals(signature, that.signature) &&
                Objects.equals(appId, that.appId) &&
                Objects.equals(deviceId, that.deviceId) &&
                Objects.equals(transId, that.transId) &&
                Objects.equals(keyUsage, that.keyUsage) &&
                Objects.equals(keySpec, that.keySpec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, signAlgo, signature, appId, deviceId, transId, keyUsage, keySpec);
    }
}
