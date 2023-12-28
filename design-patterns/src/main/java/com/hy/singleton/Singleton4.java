package com.hy.singleton;

/**
 * Description: 懒汉式-双重校验保证线程安全
 * Author: yhong
 * Date: 2023/12/28
 */
public class Singleton4 {
    // 禁止指令重排，保证线程间的可见性；
    private static volatile Singleton4 instance;

    private Singleton4() {}

    public static Singleton4 getInstance() {
        if (instance == null) {
            synchronized (Singleton4.class) {
                if (instance == null) {
                    // new 这个操作并不是一个原子操作， 有1、分配空间 2、初始化对象 3、将instance引用指向该对象
                    // 由于JVM的指令重排，可能存在1-3-2的情况，此时返回了instance引用不为null, 但对象并没有完成初始化；
                    instance = new Singleton4();
                }
            }
        }
        return instance;
    }
}
