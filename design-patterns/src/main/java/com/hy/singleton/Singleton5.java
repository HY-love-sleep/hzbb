package com.hy.singleton;

/**
 * Description: 静态内部类-最优秀的方式
 * 没有加锁的消耗， 也实现了懒加载【内部类只有在调用时才会被加载！】
 * Author: yhong
 * Date: 2023/12/28
 */
public class Singleton5 {
    private Singleton5() {}

    private static class SingletonHolder {
        public static Singleton5 instance = new Singleton5();
    }

    public Singleton5 getInstance() {
        return SingletonHolder.instance;
    }
}
