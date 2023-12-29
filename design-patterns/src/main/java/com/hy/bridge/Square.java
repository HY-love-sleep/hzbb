package com.hy.bridge;

/**
 * Description: 正方形
 * Author: yhong
 * Date: 2023/12/29
 */
public class Square extends Shape {

    // 实现类需要定义构造方法来指示对象应该如何创建；
    // 并且如果继承的抽象类有构造方法的话， 需要调用抽象类的构造方法，完成抽象类中定义的初始化工作！
    public Square(Color color) {
        super(color);
    }

    @Override
    void draw() {
        System.out.print("我在画一个  ");
        color.applyColor();
        System.out.println("的正方形");
    }
}
