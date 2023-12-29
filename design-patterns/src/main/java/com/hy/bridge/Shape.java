package com.hy.bridge;

/**
 * Description: 抽象-形状类
 * Author: yhong
 * Date: 2023/12/29
 */
public abstract class Shape {
    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    abstract void draw();
}
