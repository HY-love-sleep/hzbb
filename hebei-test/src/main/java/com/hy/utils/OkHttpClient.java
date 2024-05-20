package com.hy.utils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

/**
 * Description: 调用加密机接口工具类
 *
 * @author: yhong
 * Date: 2024/5/15
 */
@Slf4j
public class OkHttpClient {
    public enum RequestType {
        CREATE_KEY("创建主秘钥"),
        ENCRYPT("加密"),
        DECRYPT("解密"),
        HMAC("获取hmac值");

        private String description;

        RequestType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    private static final okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();

    public static JSONObject callApi(String apiUrl, RequestType requestType, Map<String, String> params) throws IOException {
        RequestBody requestBody = createRequestBody(params);
        Request request = new Request.Builder()
                .url(apiUrl)
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                log.error("请求{}接口失败， 请求url：{}， 请求参数：{}", requestType.getDescription(), apiUrl, params);
                throw new IOException("Unexpected code " + response);
            }
            return JSONObject.parseObject(response.body().string());
        }
    }

    private static RequestBody createRequestBody(Map<String, String> params) {
        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            jsonObject.put(entry.getKey(), entry.getValue());
        }
        String jsonStr = jsonObject.toJSONString();
        return RequestBody.create(MediaType.get("application/json; charset=utf-8"), jsonStr);
    }
}
