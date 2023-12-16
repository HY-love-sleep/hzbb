package com.hy.factory;

/**
 * Description: 圆形生产工厂
 * Author: yhong
 * Date: 2023/12/16
 */
public class CircleFactory extends AbsShapeFactory{

    @Override
    public Shape getShape() {
        return new Circle();
    }
}
