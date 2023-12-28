package com.hy.singleton;

/**
 * Description: 懒汉式-加锁保证线程安全
 * Author: yhong
 * Date: 2023/12/28
 */
public class Singleton3 {
    private static Singleton3 instance;

    private Singleton3() {
    }

    public static synchronized Singleton3 getInstance() {
        if (instance == null) {
            instance = new Singleton3();
        }
        return instance;
    }
}
