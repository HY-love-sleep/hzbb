package com.hy.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author yHong
 * @version 1.0
 * @date 2025/3/26 11:29
 * @description
 */
@RestController
public class StreamController {

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @CrossOrigin(origins = "http://localhost:63342")
    public SseEmitter streamData() {
        // 创建 SSE 发射器，设置超时时间（例如 1 分钟）
        SseEmitter emitter = new SseEmitter(60_000L);

        // 创建新线程，防止主程序阻塞
        new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    Thread.sleep(1000); // 模拟延迟
                    // 发送数据
                    emitter.send("time=" + System.currentTimeMillis());
                }
                // 发送完毕
                emitter.complete();
            } catch (Exception e) {
                // 处理异常并完成发射器
                emitter.completeWithError(e);
            }
        }).start();

        return emitter;
    }
}