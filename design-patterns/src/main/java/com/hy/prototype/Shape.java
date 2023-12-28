package com.hy.prototype;

import lombok.ToString;

/**
 * Description: shape类实现cloneable接口
 * Author: yhong
 * Date: 2023/12/28
 */
@ToString
public class Shape implements Cloneable{
    private String shape;


    public Shape(String shape) {
        this.shape = shape;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    @Override
    public Shape clone() throws CloneNotSupportedException {
        return (Shape) super.clone();
    }
}
