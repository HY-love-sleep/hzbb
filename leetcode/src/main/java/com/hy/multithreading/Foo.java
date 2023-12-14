package com.hy.multithreading;

import java.util.concurrent.CountDownLatch;

/**
 * Description: 按序打印
 * Author: yhong
 * Date: 2023/12/6
 */
public class Foo {
    private final CountDownLatch firstDone = new CountDownLatch(1);
    private final CountDownLatch secondDone = new CountDownLatch(1);
    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        firstDone.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        firstDone.await();
        printSecond.run();
        secondDone.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        secondDone.await();
        printThird.run();
    }
}
