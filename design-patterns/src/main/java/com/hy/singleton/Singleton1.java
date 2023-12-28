package com.hy.singleton;

/**
 * Description: 饿汉式
 * 线程安全效率高，但占用资源；
 * Author: yhong
 * Date: 2023/12/28
 */
public class Singleton1 {
    private static Singleton1 instance = new Singleton1();
    private Singleton1() {}

    public static Singleton1 getInstance() {
        return instance;
    }
}
