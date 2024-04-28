package com.hy.multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Description: 交替打印
 *
 * @Author: yhong
 * Date: 2024/4/28
 */
public class AlternatePrint {
    private volatile static int shardCOunt = 0;
    private final static String str = "ABC";
    private static CyclicBarrier cb = new CyclicBarrier(3);
    private static Runnable task = new Runnable() {

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                synchronized (this) {
                    shardCOunt = shardCOunt > 2 ? 0 : shardCOunt;
                    System.out.print(str.charAt(shardCOunt++));
                    if (shardCOunt % 3 == 0) {
                        System.out.println();
                    }
                }

                try {
                    cb.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    };

    public static void main(String[] args) {
        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();
    }

}
