package com.hy.observer;

/**
 * Description: 具体观察者类
 * Author: yhong
 * Date: 2024/1/19
 */
public class ConcreteObserver implements Observer{
    private String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(int state) {
        System.out.println(name + "收到更新， state为" + state);
    }
}
