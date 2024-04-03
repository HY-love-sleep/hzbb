package com.hy.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: 线程池， 用于处理流量抓取程序
 * Author: yhong
 * Date: 2024/4/3
 */
// @Configuration
@Slf4j
public class PacketCaptureThreadPool {
    @Bean("packetCaptureThreadPoolExecutor")
    public ThreadPoolExecutor packetCaptureThreadPoolExecutor() {

        return new ThreadPoolExecutor(10, 10, 5000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(100), r -> {
            AtomicInteger threadNumber = new AtomicInteger(1);
            Thread thread = new Thread(r);
            thread.setUncaughtExceptionHandler((t, e) -> log.error("{} 抛出异常： {}", t.getName(), e.getMessage()));
            thread.setName("packetCapture-thread-" + threadNumber.getAndIncrement());
            return thread;
        }, new ThreadPoolExecutor.CallerRunsPolicy());
    }
}

