package com.hy.multithreading;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * 1 2 3 4 5 6 7 8 9 10
 * 0 1 0 2 0 3 0 4 0 5
 */
class ZeroEvenOdd {
    private int n;
    private volatile int flag = 1;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }


    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            synchronized (this) {
                while (flag % 2 == 0) {
                    wait();
                }
                printNumber.accept(0);
                flag++;
                notifyAll();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            synchronized (this) {
                while (flag % 4 != 0) {
                    wait();
                }
                printNumber.accept(i);
                flag++;
                notifyAll();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            synchronized (this) {
                // 2 6 10
                while (flag % 4 != 2) {
                    wait();
                }
                printNumber.accept(i);
                flag++;
                notifyAll();
            }
        }
    }
}

class ZeroEvenOdd2 {
    private int n;

    private Semaphore[] semaphores = new Semaphore[3];


    public ZeroEvenOdd2(int n) {
        this.n = n;
        semaphores[0] = new Semaphore(1);
        semaphores[1] = new Semaphore(0);
        semaphores[2] = new Semaphore(0);
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            semaphores[0].acquire();
            printNumber.accept(0);
            if (i % 2 == 0) {
                semaphores[1].release();
            } else {
                semaphores[2].release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            semaphores[1].acquire();
            printNumber.accept(i);
            semaphores[0].release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            semaphores[2].acquire();
            printNumber.accept(i);
            semaphores[0].release();
        }
    }
}

class ZeroEvenOdd3 {
    private int n;
    private LinkedBlockingQueue<Integer>[] queues = new LinkedBlockingQueue[3];

    public ZeroEvenOdd3(int n) throws InterruptedException {
        this.n = n;
        for (int i = 0; i < queues.length; i++) {
            queues[i] = new LinkedBlockingQueue<>(1);
        }
        queues[0].put(1);
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            queues[0].take();
            printNumber.accept(0);
            if (i % 2 == 0) {
                queues[2].put(1);
            } else {
                queues[1].put(1);
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            queues[1].take();
            printNumber.accept(i);
            queues[0].put(1);
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            queues[2].take();
            printNumber.accept(i);
            queues[0].put(1);
        }
    }
}