package com.hy.crypto.enums;

public enum EncryptionAlgorithm {
    AES("AES"),
    RSA("RSA"),
    BLOWFISH("Blowfish"),
    DES("DES"),
    API("API_ENCRYPTION");

    private final String algorithmName;

    EncryptionAlgorithm(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    // 通过算法名称获取枚举实例
    public static EncryptionAlgorithm fromString(String name) {
        for (EncryptionAlgorithm alg : EncryptionAlgorithm.values()) {
            if (alg.getAlgorithmName().equalsIgnoreCase(name)) {
                return alg;
            }
        }
        throw new IllegalArgumentException("Unknown algorithm name: " + name);
    }
}
