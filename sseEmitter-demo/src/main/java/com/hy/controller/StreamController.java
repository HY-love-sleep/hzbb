package com.hy.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;

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
        // 1. 创建 SseEmitter（超时时间设为无限或较长，例如 30 分钟）
        SseEmitter emitter = new SseEmitter(1800000L);

        // 2. 生成 Flux 数据流（示例：每秒推送一个消息）
        Flux<String> messageFlux = Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "Message " + sequence + " at " + System.currentTimeMillis());

        // 3. 订阅 Flux 并发送 SSE 事件
        messageFlux.subscribe(
                message -> {
                    try {
                        emitter.send(SseEmitter.event()
                                .data(message)
                                .id(String.valueOf(System.currentTimeMillis()))
                                .name("message-event"));
                    } catch (IOException e) {
                        emitter.completeWithError(e);
                    }
                },
                emitter::completeWithError,
                emitter::complete
        );

        // 4. 返回 SseEmitter
        return emitter;
    }
}