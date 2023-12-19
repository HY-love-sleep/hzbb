package com.hy.multithreading;

import java.util.concurrent.Semaphore;

class H2O {

    public H2O() {
        
    }
    private volatile int flag = 0;
    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
		synchronized (this) {
            while (flag == 2) {
                this.wait();
            }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            flag++;
            this.notifyAll();
        }

    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        synchronized (this) {
            while (flag != 2) {
                this.wait();
            }
            // releaseOxygen.run() outputs "O". Do not change or remove this line.
            releaseOxygen.run();
            flag = 0;
            this.notifyAll();
        }
    }
}

class H2O2 {
    public H2O2() {}

    Semaphore s_H = new Semaphore(2);
    Semaphore s_O = new Semaphore(0);
    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        s_H.acquire();
        releaseHydrogen.run();
        s_O.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        s_O.acquire(2);
        releaseOxygen.run();
        s_H.release(2);
    }
}