package com.hy.multithreading;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

/**
 * synchronized + 标志位
 * 结果超时
 */
class FizzBuzz {
    private int n;

    private AtomicInteger flag = new AtomicInteger(1);

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            synchronized (this) {
                if (!(flag.get() % 3 == 0 && flag.get() % 5 != 0)) {
                    this.wait();
                }
                if (i % 3 == 0 && i % 5 != 0) {
                    printFizz.run();
                    flag.incrementAndGet();
                    this.notifyAll();
                }
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            synchronized (this) {
                if (!(flag.get() % 5 == 0 && flag.get() % 3 != 0)) {
                    this.wait();
                }
                if (i % 5 == 0 && i % 3 != 0) {
                    printBuzz.run();
                    flag.incrementAndGet();
                    this.notifyAll();
                }
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            synchronized (this) {
                if (!(flag.get() % 3 == 0 && flag.get() % 5 == 0)) {
                    this.wait();
                }
                if (i % 3 == 0 && i % 5 == 0) {
                    printFizzBuzz.run();
                    flag.incrementAndGet();
                    this.notifyAll();
                }
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            synchronized (this) {
                if (!(flag.get() % 3 != 0 && flag.get() % 5 != 0)) {
                    this.wait();
                }
                if (i % 3 != 0 && i % 5 != 0) {
                    printNumber.accept(i);
                    flag.incrementAndGet();
                    this.notifyAll();
                }
            }
        }
    }
}

/**
 * CyclicBarrier
 */
class FizzBuzz2 {
    private int n;
    private CyclicBarrier cb = new CyclicBarrier(4);

    public FizzBuzz2(int n) {
        this.n = n;
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                printFizz.run();
            }
            try {
                cb.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 5 == 0 && i % 3 != 0) {
                printBuzz.run();
            }
            try {
                cb.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void fizzbuzz(Runnable printFizzbuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                printFizzbuzz.run();
            }
            try {
                cb.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
                printNumber.accept(i);
            }
            try {
                cb.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * Semaphore
 */
class FizzBuzz3 {
    private int n;
    private Semaphore s_fizz = new Semaphore(0);
    private Semaphore s_buzz = new Semaphore(0);
    private Semaphore s_fizzbuzz = new Semaphore(0);
    private Semaphore s_number = new Semaphore(1);

    public FizzBuzz3(int n) {
        this.n = n;
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                s_fizz.acquire();
                printFizz.run();
                s_number.release();
            }
        }
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 == 0) {
                s_buzz.acquire();
                printBuzz.run();
                s_number.release();
            }
        }
    }

    public void fizzbuzz(Runnable printFizzbuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                s_fizzbuzz.acquire();
                printFizzbuzz.run();
                s_number.release();
            }
        }
    }

    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            s_number.acquire();
            if (i % 3 != 0 && i % 5 != 0) {
                printNumber.accept(i);
                s_number.release();
            } else if (i % 3 == 0 && i % 5 != 0) {
                s_fizz.release();
            } else if (i % 3 != 0) {
                s_buzz.release();
            } else {
                s_fizzbuzz.release();
            }

        }
    }
}

/**
 * BlockedQueue
 * 和信号量是一个思路
 */
class FizzBuzz4 {
    private int n;
    private BlockingQueue<Integer>[] queues = new LinkedBlockingQueue[4];

    public FizzBuzz4() {}
    public FizzBuzz4(int n) throws InterruptedException {
        this.n = n;
        for (int i = 0; i < queues.length; i++) {
            queues[i] = new LinkedBlockingQueue<>(1);
        }
        queues[3].put(1);
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                queues[0].take();
                printFizz.run();
                queues[3].put(1);
            }
        }
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 == 0) {
                queues[1].take();
                printBuzz.run();
                queues[3].put(1);
            }
        }
    }

    public void fizzbuzz(Runnable printFizzbuzz) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                queues[2].take();
                printFizzbuzz.run();
                queues[3].put(1);
            }
        }
    }

    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            queues[3].take();
            if (i % 3 != 0 && i % 5 != 0) {
                printNumber.accept(i);
                queues[3].put(1);
            } else if (i % 3 == 0 && i % 5 != 0) {
                queues[0].put(1);
            } else if (i % 3 != 0) {
                queues[1].put(1);
            } else {
                queues[2].put(1);
            }
        }
    }
}