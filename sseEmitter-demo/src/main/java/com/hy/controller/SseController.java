package com.hy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.TimeUnit;

@RestController
public class SseController {

    // 实现实时消息推送
    @GetMapping("/stream-messages")
    @CrossOrigin(origins = "http://localhost:63342")
    public SseEmitter streamMessages() {
        // 创建一个SseEmitter对象，设置超时时间
        SseEmitter emitter = new SseEmitter(0L);

        // 创建一个新的线程模拟实时消息推送
        new Thread(() -> {
            try {
                // 发送一条初始消息
                emitter.send("Server started, waiting for messages...", MediaType.TEXT_PLAIN);

                // 模拟推送实时消息
                for (int i = 1; i <= 5; i++) {
                    TimeUnit.SECONDS.sleep(1); // 每1秒发送一条消息
                    emitter.send("Message #" + i, MediaType.TEXT_PLAIN);
                }

                // 完成消息推送
                emitter.complete();
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        }).start();

        // 返回SseEmitter对象
        return emitter;
    }
}
