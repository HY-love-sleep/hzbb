package com.hy.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Description: 使用future的get方法来处理异常
 * Author: yhong
 * Date: 2023/12/19
 */
public class FutureGetThreadPoolExceptionTest {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            Future<?> future = threadPool.submit(() -> {
                System.out.print("Current Thread Name:" + Thread.currentThread().getName() + "  ");
                Object o = null;
                System.out.println("result:  " + o.toString());
            });
            try{
                future.get();
            } catch (ExecutionException | InterruptedException e) {
                System.out.println(e.getMessage());;
            }
        }
    }
}
