package com.hy.decorator;

/**
 * Description: 最基础的coffee类， 实现了Coffee接口
 * Author: yhong
 * Date: 2024/1/4
 */
public class SimpleCoffee implements Coffee{

    @Override
    public double cost() {
        return 13.0;
    }

    @Override
    public String description() {
        return "基础款";
    }
}
