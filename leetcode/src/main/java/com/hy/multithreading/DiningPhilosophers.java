package com.hy.multithreading;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class DiningPhilosophers {

    private final Lock[] chopsticks = new ReentrantLock[5];

    // 最多允许四个人进餐
    private Semaphore eatCounts = new Semaphore(4);

    public DiningPhilosophers() {
        chopsticks[0] = new ReentrantLock();
        chopsticks[1] = new ReentrantLock();
        chopsticks[2] = new ReentrantLock();
        chopsticks[3] = new ReentrantLock();
        chopsticks[4] = new ReentrantLock();
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        int left = philosopher % 5;
        int right = (philosopher + 1) % 5;
        eatCounts.acquire();
        chopsticks[left].lock();
        chopsticks[right].lock();
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();
        chopsticks[left].unlock();
        chopsticks[right].unlock();
        eatCounts.release();
    }
}