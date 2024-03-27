package com.hy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Description: CompletableFutureTest
 * Author: yhong
 * Date: 2024/3/27
 */
@SpringBootTest(classes = DemoApplication.class)
@Slf4j
public class CompletableFutureTest {
    @Autowired
    private ThreadPoolExecutor commonThreadPoolExecutor;
    /**
     * 调用supplyAsync执行CompletableFuture任务， 使用内置线程池
     */
    @Test
    public void defaultSupplyAsync() {
        long startTime = System.currentTimeMillis();
        CompletableFuture<Map<String, Object>> amountCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                log.error("异常：{}", e.getMessage());
            }
            Map<String, Object> amountMap = new HashMap<>(2);
            amountMap.put("amount", 60000);
            long endTime = System.currentTimeMillis();
            log.info("查询金额耗时：{} 秒", (endTime - startTime) / 1000);
            return amountMap;
        });

        CompletableFuture<Map<String, Object>> roleCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                log.error("异常：{}", e.getMessage());
            }
            Map<String, Object> roleMap = new HashMap<>(2);
            roleMap.put("role", "master");
            return roleMap;
        });

        log.info("金额查询结果：{}", amountCompletableFuture.join());
        log.info("角色查询结果：{}", roleCompletableFuture.join());

        log.info("总耗时：{} 秒", (System.currentTimeMillis() - startTime) / 1000);
    }

    /**
     * 调用supplyAsync执行CompletableFuture任务， 使用自定义线程池
     */
    @Test
    public void SupplyAsyncWithThreadPool() {
        long startTime = System.currentTimeMillis();
        CompletableFuture<Map<String, Object>> amountCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                log.info("current Thread : {}", Thread.currentThread().getName());
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                log.error("异常：{}", e.getMessage());
            }
            Map<String, Object> amountMap = new HashMap<>(2);
            amountMap.put("amount", 60000);
            long endTime = System.currentTimeMillis();
            log.info("查询金额耗时：{} 秒", (endTime - startTime) / 1000);
            return amountMap;
        }, commonThreadPoolExecutor);

        CompletableFuture<Map<String, Object>> roleCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                log.info("current Thread : {}", Thread.currentThread().getName());
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                log.error("异常：{}", e.getMessage());
            }
            Map<String, Object> roleMap = new HashMap<>(2);
            roleMap.put("role", "master");
            return roleMap;
        }, commonThreadPoolExecutor);

        log.info("金额查询结果：{}", amountCompletableFuture.join());
        log.info("角色查询结果：{}", roleCompletableFuture.join());

        log.info("总耗时：{} 秒", (System.currentTimeMillis() - startTime) / 1000);
    }

    @Test
// 执行第一个任务后 可以继续执行第二个任务 并携带第一个任务的返回值 第二个任务执行完有返回值
    public void defaultThenApply() throws ExecutionException, InterruptedException {
        long lordStartTime = System.currentTimeMillis();
        CompletableFuture<Map> amountCompletableFuture = CompletableFuture.supplyAsync(() -> {
            long startTime = System.currentTimeMillis();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Map<String, Object> amountMap = new HashMap<String, Object>();
            amountMap.put("amount", 90);
            log.info("执行金额查询操作用时：" + (System.currentTimeMillis() - startTime) / 1000 + "秒");
            return amountMap;
        });
        CompletableFuture<Integer> thenCompletableFuture = amountCompletableFuture.thenApply((map) -> {
            int number = 0;
            if (Integer.parseInt(map.get("amount").toString()) > 3) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 可口可乐3元一瓶 看金额一共能购买多少瓶
                number = Integer.parseInt(map.get("amount").toString()) / 3;
            }
            return number;
        });

        log.info("当前金额一共可以买" + thenCompletableFuture.get() + "瓶可口可乐！");
        Integer integer = thenCompletableFuture.get();
        log.info("总耗时:" + (System.currentTimeMillis() - lordStartTime) / 1000 + "秒");
    }
}
