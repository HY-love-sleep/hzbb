package com.hy.bridge;

/**
 * Description: 测试类
 * 桥接模式允许我们在不改变 颜色类 和 形状类的基础上，独立地对他们进行扩展和变化
 * 组合优于继承！
 * Author: yhong
 * Date: 2023/12/29
 */
public class Test {
    public static void main(String[] args) {
        Color red = new Red();
        Color blue = new Blue();
        Shape circle = new Circle(red);
        Shape square = new Square(blue);
        circle.draw();
        square.draw();
    }

}
