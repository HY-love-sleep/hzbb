package com.hy.observer;

/**
 * Description: 观察者模式测试类
 * Author: yhong
 * Date: 2024/1/19
 */
public class ObserverPatternExample {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();
        subject.addObserver(new ConcreteObserver("hy"));
        subject.addObserver(new ConcreteObserver("hz"));

        subject.setState(1);
        subject.setState(2);
    }
}
