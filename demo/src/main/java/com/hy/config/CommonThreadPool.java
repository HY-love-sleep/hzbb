package com.hy.config;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: 通用线程池
 * Author: yhong
 * Date: 2024/3/27
 */
@Configuration
@Slf4j
public class CommonThreadPool {
    @Bean("commonThreadPoolExecutor")
    public ThreadPoolExecutor commonThreadPoolExecutor() {

        return new ThreadPoolExecutor(17, 30, 5000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(50), r -> {
            AtomicInteger threadNumber = new AtomicInteger(1);
            Thread thread = new Thread(r);
            thread.setUncaughtExceptionHandler((t, e) -> {
                log.error("{} 抛出异常： {}", t.getName(), e.getMessage());
            });
            thread.setName("common-thread-" + threadNumber.getAndIncrement());
            return thread;
        }, new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
