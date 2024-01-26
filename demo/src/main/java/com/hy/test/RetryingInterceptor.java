package com.hy.test;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Description: okhttp接口请求失败重试拦截器
 * 请求失败后重试三次
 * Author: yhong
 * Date: 2024/1/24
 */
public class RetryingInterceptor implements Interceptor {

    private static final int MAX_RETRIES = 3; // 请求失败后重试3次
    private static final int RETRY_DELAY_MS = 1000; // 重试间隔

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = null;
        IOException lastException = null;

        for (int retry = 0; retry < MAX_RETRIES; retry++) {
            try {
                response = chain.proceed(request);
                if (response.isSuccessful()) {
                    return response;
                }
            } catch (IOException e) {
                lastException = e;
            }

            // 如果不是最后一次重试，等待一段时间
            if (retry < MAX_RETRIES - 1) {
                try {
                    Thread.sleep(RETRY_DELAY_MS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new IOException("Interrupted during retry", e);
                }
            }
        }

        // 如果所有重试都失败，则抛出最后一次异常
        if (lastException != null) {
            throw lastException;
        }

        // 如果没有异常，但所有重试都失败，返回空响应
        return new Response.Builder().request(request).protocol(response != null ? response.protocol() : null)
                .code(response != null ? response.code() : 0).message("Retries exhausted").build();
    }
}
