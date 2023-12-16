package com.hy.factory;

/**
 * Description: 测试类
 * Author: yhong
 * Date: 2023/12/16
 */
public class Test {
    public static void main(String[] args) {
        CircleFactory circleFactory = new CircleFactory();
        Shape circle = circleFactory.getShape();
        circle.draw();

        RecTangleFactory recTangleFactory = new RecTangleFactory();
        Shape rectangle = recTangleFactory.getShape();
        rectangle.draw();
    }
}
