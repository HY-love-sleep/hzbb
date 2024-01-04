package com.hy.decorator;

/**
 * Description: 具体的装饰器类
 * 继承了咖啡装饰器类，在原始的咖啡上添加新的功能；
 * Author: yhong
 * Date: 2024/1/4
 */
public class MilkCoffee extends CoffeeDecorator{

    public MilkCoffee(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double cost() {
        return super.cost() + 3.0;
    }

    @Override
    public String description() {
        return super.description() + " + 牛奶";
    }

}
