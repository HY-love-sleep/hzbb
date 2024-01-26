package com.hy.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 具体主题类
 * Author: yhong
 * Date: 2024/1/19
 */
public class ConcreteSubject implements Subject{
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(observer -> {
            observer.update(state);
        });
    }
}
