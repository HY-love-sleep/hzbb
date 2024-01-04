package com.hy.decorator;

/**
 * Description: 装饰器抽象类
 * Author: yhong
 * Date: 2024/1/4
 */
public abstract class CoffeeDecorator implements Coffee{

    // 持有一个被装饰对象的引用
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee decoratedCoffee) {
        this.decoratedCoffee = decoratedCoffee;
    }

    @Override
    public double cost() {
        return decoratedCoffee.cost();
    }

    @Override
    public String description() {
        return decoratedCoffee.description();
    }
}
