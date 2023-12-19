package com.hy.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: try-catch处理异常
 * Author: yhong
 * Date: 2023/12/19
 */
public class TryCatchThreadPoolExceptionTest {
    public static void main(String[] args) {
        /**
         * newFixedThreadPool是一个固定线程数的线程池（核心线程和最大线程一样）；
         * 存活时间为0；
         * LinkedBlockingQueue， 有OOM的风险
         */
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            threadPool.submit(() -> {
                System.out.print("Current Thread Name:" + Thread.currentThread().getName());
                try {
                    Object o = null;
                    System.out.println("result: " + o.toString());
                } catch (Exception e) {
                    System.out.println("程序报错");
                }
            });
        }
    }



}
