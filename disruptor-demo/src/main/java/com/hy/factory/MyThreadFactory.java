package com.hy.factory;


import java.util.concurrent.ThreadFactory;
 
public class MyThreadFactory implements ThreadFactory {
    private int counter;
    private String prefix;
 
    public MyThreadFactory(String prefix) {
        this.prefix = prefix;
        this.counter = 0;
    }
 
    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName(prefix + "-" + counter++); // 自定义线程名称
        return thread;
    }
}