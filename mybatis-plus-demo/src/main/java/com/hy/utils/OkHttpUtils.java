package com.hy.utils;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OkHttpUtils {

    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    private static final Gson gson = new Gson();

    /**
     * 发送 GET 请求
     *
     * @param url 请求的 URL
     * @return 响应字符串
     * @throws IOException 如果请求失败
     */
    public static String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        }
    }

    /**
     * 发送 POST 请求
     *
     * @param url 请求的 URL
     * @param params 请求参数
     * @return 响应字符串
     * @throws IOException 如果请求失败
     */
    public static String post(String url, Map<String, String> params) throws IOException {
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                formBuilder.add(entry.getKey(), entry.getValue());
            }
        }

        RequestBody requestBody = formBuilder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        }
    }

    /**
     * 发送 POST 请求（JSON 数据）
     *
     * @param url 请求的 URL
     * @param json 请求的 JSON 数据
     * @return 响应字符串
     * @throws IOException 如果请求失败
     */
    public static String postJson(String url, String json) throws IOException {
        RequestBody requestBody = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        }
    }

    /**
     * 发送 POST 请求（JSON 对象）
     *
     * @param url 请求的 URL
     * @param object 请求的 JSON 对象
     * @return 响应字符串
     * @throws IOException 如果请求失败
     */
    public static String postJson(String url, Object object) throws IOException {
        String json = gson.toJson(object);
        return postJson(url, json);
    }
}
