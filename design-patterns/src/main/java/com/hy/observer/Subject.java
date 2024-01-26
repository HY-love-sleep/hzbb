package com.hy.observer;

/**
 * Description: 主题接口
 * Author: yhong
 * Date: 2024/1/19
 */
public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

