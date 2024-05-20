package com.hy.utils;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.*;
import org.apache.commons.codec.binary.Base64;

public class SignUtils {

    public static String generateRequestJson(Map<String, String> request, String key) throws Exception {
        // 排序
        String data = generateSignString(request);
        String signature = getHMAC(data.getBytes(), key.getBytes(), request.get("signAlgo"));
        // request.put("signature", signature);
        // return new GsonBuilder().disableHtmlEscaping().create().toJson(request);
        return signature;
    }

    //常量字符串
    private static final String AND = "&";
    private static final String EQUAL = "=";

    /**
     * 生成签名字符串，忽略掉转入的属性名
     *
     * param bean
     * param filterNames
     * @return
     */
    private static String generateSignString(Map<String, String> props) {
        StringBuilder sb = new StringBuilder();
        List<String> keys = new ArrayList<>(props.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            Object value = props.get(key);
            if (value == null || "signature".equals(key)) {
                continue;
            }
            if (i == props.size() - 1) {// 拼接时，不包括最后一个&字符
                sb.append(key).append(EQUAL).append(value);
            } else {
                sb.append(key).append(EQUAL).append(value).append(AND);
            }
        }
        return sb.toString();
    }

    private static String getHMAC(byte[] data, byte[] key, String HmacAlgo) throws Exception {
        SecretKeySpec signingKey = new SecretKeySpec(key, HmacAlgo);
        if ("HmacSHA256".equals(HmacAlgo)) {
            Mac mac = Mac.getInstance(HmacAlgo);
            mac.init(signingKey);
            return base64Encode(mac.doFinal(data));
        } else {
            KeyParameter keyParameter = new KeyParameter(key);
            SM3Digest digest = new SM3Digest();
            HMac mac = new HMac(digest);
            mac.init(keyParameter);
            mac.update(data, 0, data.length);
            byte[] hmac = new byte[0];
            hmac = new byte[mac.getMacSize()];
            mac.doFinal(hmac, 0);
            return Base64.encodeBase64String(hmac);
        }
    }

    public static String base64Encode(byte[] data){
        String base64Str = DatatypeConverter.printBase64Binary(data);
        return base64Str;
    }

    // signAlgo值仅支持为HmacSM3或者HmacSHA256，传递哪个用哪个算法
    // 测试用的main函数，更改map中的参数即可
    public static void main(String[] args) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("transId","e37b7f38-90ce-450b-bc40-0bd91cc7d884");
        map.put("appId","APP_4D332F85C4EC4478967BFF3A6FA16332");
        map.put("deviceId","DEV_787F742E32E0485D91C271095868A081");
        map.put("version","1.0");
        map.put("signAlgo","HmacSM3");
        map.put("origin","KMS");
        map.put("keyUsage","ENCRYPT/DECRYPT");
        map.put("keySpec","SM4_128");
        map.put("autoRotation","ENABLED");
        map.put("alias", "zkrmainkey");
        map.put("operateName", "zkr");
        map.put("rotationDays", "365");
        System.out.println(generateRequestJson(map, "RjrnZYFecGUBe2DYCHULRtrDupB9GE7u"));
    }
}
