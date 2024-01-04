package com.hy.decorator;

/**
 * Description: 装饰器模式测试类
 * Author: yhong
 * Date: 2024/1/4
 */
public class DecoratorExample {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println("Cost : " + coffee.cost() + "  Desc : " + coffee.description());

        MilkCoffee milkCoffee = new MilkCoffee(coffee);
        System.out.println("Cost : " + milkCoffee.cost() + "  Desc : " + milkCoffee.description());

        SugarMilk sugarMilk = new SugarMilk(coffee);
        System.out.println("Cost : " + sugarMilk.cost() + "  Desc : " + sugarMilk.description());
    }
}
