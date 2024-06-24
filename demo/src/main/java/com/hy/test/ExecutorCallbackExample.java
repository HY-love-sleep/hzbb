package com.hy.test;

import java.util.concurrent.*;

public class ExecutorCallbackExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(5);
        // 使用 CompletionService
        CompletionService<Integer> completionService = new ExecutorCompletionService<>(executor);

        // 提交10个任务
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep((int) (Math.random() * 1000)); // 模拟耗时任务
                    return taskId;
                }
            });
        }

        // 获取并打印已完成任务的结果及执行时间
        executor.execute(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    Future<Integer> future = completionService.take();
                    long startTime = future.get().longValue(); // 假设任务开始时记录了时间戳
                    long endTime = System.nanoTime();
                    long executionTime = endTime - startTime;
                    System.out.println("Task completed in " + TimeUnit.NANOSECONDS.toMillis(executionTime) + " ms");
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        // 关闭线程池
        executor.shutdown();
    }
}
