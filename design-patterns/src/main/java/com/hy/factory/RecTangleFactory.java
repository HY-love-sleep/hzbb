package com.hy.factory;

/**
 * Description: 三角形生产工厂
 * Author: yhong
 * Date: 2023/12/16
 */
public class RecTangleFactory extends AbsShapeFactory{

    @Override
    public Shape getShape() {
        return new RecTangle();
    }
}
