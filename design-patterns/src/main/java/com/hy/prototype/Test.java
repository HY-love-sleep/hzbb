package com.hy.prototype;

/**
 * Description: Test
 * Author: yhong
 * Date: 2023/12/28
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Shape shape_ori = new Shape("circle");
        Shape shape_clone = shape_ori.clone();
        shape_clone.setShape("cloned circle");
        System.out.println(shape_ori);
        System.out.println(shape_clone);
    }
}
