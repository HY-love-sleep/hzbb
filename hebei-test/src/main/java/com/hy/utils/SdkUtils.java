package com.hy.utils;

import com.alibaba.fastjson.JSONObject;
import com.hy.conf.SDKProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 调用加密机接口工具类
 *
 * @author: yhong
 * Date: 2024/5/15
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SdkUtils {

    public static String version;
    public static String signAlgo;
    public static String signature;
    public static String appId;
    public static String transId;
    public static String keyUsage;
    public static String keyId;
    public static String key;
    public static String deviceId;
    public static String keySpec;
    public static String mode;
    public static String iv;
    public static String padding;
    public static String createKeyUrl;
    public static String encryptUrl;
    public static String decryptUrl;
    public static String getHmacUrl;

    @Value("${hb.version}")
    public void setVersion(String version) {
        SdkUtils.version = version;
    }

    @Value("${hb.signAlgo}")
    public void setSignAlgo(String signAlgo) {
        SdkUtils.signAlgo = signAlgo;
    }

    @Value("${hb.signature}")
    public void setSignature(String signature) {
        SdkUtils.signature = signature;
    }

    @Value("${hb.appId}")
    public void setAppId(String appId) {
        SdkUtils.appId = appId;
    }

    @Value("${hb.transId}")
    public void setTransId(String transId) {
        SdkUtils.transId = transId;
    }

    @Value("${hb.keyUsage}")
    public void setKeyUsage(String keyUsage) {
        SdkUtils.keyUsage = keyUsage;
    }

    @Value("${hb.keyId}")
    public void setKeyId(String keyId) {
        SdkUtils.keyId = keyId;
    }
    @Value("${hb.key}")
    public void setKey(String key) {
        SdkUtils.key = key;
    }

    @Value("${hb.deviceId}")
    public void setDeviceId(String deviceId) {
        SdkUtils.deviceId = deviceId;
    }

    @Value("${hb.keySpec}")
    public void setKeySpec(String keySpec) {
        SdkUtils.keySpec = keySpec;
    }

    @Value("${hb.mode}")
    public void setMode(String mode) {
        SdkUtils.mode = mode;
    }

    @Value("${hb.iv}")
    public void setIv(String iv) {
        SdkUtils.iv = iv;
    }

    @Value("${hb.padding}")
    public void setPadding(String padding) {
        SdkUtils.padding = padding;
    }

    @Value("${hb.createKeyUrl}")
    public void setCreateKeyUtl(String createKeyUrl) {
        SdkUtils.createKeyUrl = createKeyUrl;
    }

    @Value("${hb.encryptUrl}")
    public void setEncryptUrl(String encryptUrl) {
        SdkUtils.encryptUrl = encryptUrl;
    }

    @Value("${hb.decryptUrl}")
    public void setDecryptUrl(String decryptUrl) {
        SdkUtils.decryptUrl = decryptUrl;
    }

    @Value("${hb.getHmacUrl}")
    public void setGetHmacUrl(String getHmacUrl) {
        SdkUtils.getHmacUrl = getHmacUrl;
    }


    /**
     * 获取主密钥ID
     * @return
     * @throws Exception
     */
    public static String getKeyId() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("version", version);
        params.put("signAlgo", signAlgo);
        params.put("signature", signature);
        params.put("appId", appId);
        params.put("transId", transId);
        params.put("keyUsage", keyUsage);
        params.put("deviceId", deviceId);
        params.put("keySpec", keySpec);
        String sign = SignUtils.generateRequestJson(params, key);
        params.put("signature", sign);

        JSONObject jsonObject = OkHttpClient.callApi(createKeyUrl, OkHttpClient.RequestType.CREATE_KEY, params);
        return jsonObject.getJSONObject("data").getString("keyId");
    }

    /**
     * 加密数据
     * @param data
     * @return
     * @throws Exception
     */
    public static String encryptByApi(String data) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("version", version);
        params.put("signAlgo", signAlgo);
        params.put("signature", signature);
        params.put("appId", appId);
        params.put("transId", transId);
        params.put("keyId", keyId);
        params.put("deviceId", deviceId);
        params.put("mode", mode);
        params.put("iv", encodeToBase64(iv));
        params.put("padding", padding);
        params.put("plainText", encodeToBase64(data));
        String sign = SignUtils.generateRequestJson(params, key);
        params.put("signature", sign);

        JSONObject jsonObject = OkHttpClient.callApi(encryptUrl, OkHttpClient.RequestType.ENCRYPT, params);
        return jsonObject.getJSONObject("data").getString("cipherTextBlob");
    }

    /**
     * 解密数据
     * @param data
     * @return
     * @throws Exception
     */
    public static String decryptByApi(String data) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("version", version);
        params.put("signAlgo", signAlgo);
        params.put("signature", signature);
        params.put("appId", appId);
        params.put("transId", transId);
        params.put("keyId", keyId);
        params.put("deviceId", deviceId);
        // 存储的是加密后的base64编码， 因此此处无需进行base64编码
        params.put("encData", data);
        String sign = SignUtils.generateRequestJson(params, key);
        params.put("signature", sign);

        JSONObject jsonObject = OkHttpClient.callApi(decryptUrl, OkHttpClient.RequestType.DECRYPT, params);
        return jsonObject.getJSONObject("data").getString("plainText");
    }

    public static String getHmacByApi(String data) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("version", version);
        params.put("signAlgo", signAlgo);
        params.put("signature", signature);
        params.put("appId", appId);
        params.put("transId", transId);
        params.put("keyId", keyId);
        params.put("deviceId", deviceId);
        params.put("source", encodeToBase64(data));
        String sign = SignUtils.generateRequestJson(params, key);
        params.put("signature", sign);

        JSONObject jsonObject = OkHttpClient.callApi(getHmacUrl, OkHttpClient.RequestType.HMAC, params);
        return jsonObject.getJSONObject("data").getString("hmac");
    }

    private static String encodeToBase64(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data must not be null.");
        }
        // 将字符串转换为字节数组，Base64.encodeToString接受字节输入
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
        // 使用Base64.Encoder进行编码
        return Base64.getEncoder().encodeToString(dataBytes);
    }

    /**
     * 解码Base64编码的字符串
     *
     * @param encodedString 要解码的Base64字符串
     * @return 解码后的字节数组
     */
    public static byte[] decodeBase64(String encodedString) {
        // 使用Java 8的Base64Decoder进行解码
        Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(encodedString);
    }

    /**
     * 将Base64解码后的字节数组转换为字符串（假设原数据是UTF-8编码）
     *
     * @param decodedBytes 解码后的字节数组
     * @return 解码并转换后的字符串
     * @throws UnsupportedEncodingException 如果指定的字符编码不受支持
     */
    public static String decodeToString(byte[] decodedBytes) throws UnsupportedEncodingException {
        return new String(decodedBytes, "UTF-8");
    }

    public static void main(String[] args) {
        System.out.println(encodeToBase64("我爱数科"));
    }

}
