package com.hy.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: 为工作线程设置UncaughtExceptionHandler
 * Author: yhong
 * Date: 2023/12/19
 */
public class UncaughtExceptionHandlerTest {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5, r -> {
            Thread t = new Thread(r);
            t.setUncaughtExceptionHandler((t1, e) -> {
                System.out.println(t1.getName() + "抛出异常：" + e.getMessage());
            });
            return t;
        });

        for (int i = 0; i < 5; i++) {
            /**
             * execute()提交一个Runnable对象；
             * Runnable对象没有返回值， 适用于不需要返回值的任务；
             * Runnable对象无法抛出异常， 只能手动用try-catch捕获异常；
             * submit()可以提交Runnable对象也可以提交Callable对象；
             * Callable对象的call()方法有一个泛型的返回对象， 可以通过 future.get()来获取执行结果；
             * Callable对象可以向上抛出异常；
             */
            threadPool.execute(() -> {
                Object o = null;
                System.out.println("result: " + o.toString());
            });
        }

    }

}
