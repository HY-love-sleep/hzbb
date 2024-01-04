package com.hy.decorator;

/**
 * Description: 具体的装饰器类
 * Author: yhong
 * Date: 2024/1/4
 */
public class SugarMilk extends CoffeeDecorator{

    public SugarMilk(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }

    @Override
    public double cost() {
        return super.cost() + 1.0;
    }

    @Override
    public String description() {
        return super.description() + " + 零卡糖";
    }
}
