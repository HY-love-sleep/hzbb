package com.hy.singleton;

/**
 * Description: 懒汉式-线程不安全
 * Author: yhong
 * Date: 2023/12/28
 */
public class Singleton2 {
    // 私有静态成员变量， 用于存储这个单例实例；
    private static Singleton2 instance;

    // 构造方法声明为私有的
    private Singleton2() {}

    public static Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}
