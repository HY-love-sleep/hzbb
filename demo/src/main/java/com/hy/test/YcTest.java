package com.hy.test;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Description:
 * Author: yhong
 * Date: 2024/1/25
 */
@Slf4j
public class YcTest {
    private static final String API_URL = "http://172.23.39.13:8981/yc-api/screen/getLoginUserByAuthCode";
    public static void main(String[] args) throws IOException {
        UserInfoByAuthCode userInfoByAuthCode = getUserInfoByAuthCode("test");
        System.out.println(userInfoByAuthCode);
    }

    public static UserInfoByAuthCode getUserInfoByAuthCode(String authCode) throws IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new RetryingInterceptor())  // 失败重试三次
                .build();

        Request request = new Request.Builder()
                .url(API_URL + "?authCode=" + authCode)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseBody = response.body().string();
                JSONObject jsonResponse = JSONObject.parseObject(responseBody);
                JSONObject data = jsonResponse.getJSONObject("data");

                if (data != null) {
                    UserInfoByAuthCode userData = data.toJavaObject(UserInfoByAuthCode.class);
                    if (userData != null) {
                        return userData;
                    } else {
                        log.error("json转Entity失败, data_json:{}", data.toJSONString());
                    }
                } else {
                    log.info("返回结果不包含data, response:{}", jsonResponse.toJSONString());
                }
            } else {
                log.error("请求结果错误， 错误码 code:{}", response.code());
            }
        } catch (IOException e) {
            log.error("发送请求失败, exception:{}", e.getMessage());
        }
        return null;
    }

}
