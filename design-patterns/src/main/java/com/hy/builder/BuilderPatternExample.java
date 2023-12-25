package com.hy.builder;

/**
 * Description: 建造者模式示例
 * Author: yhong
 * Date: 2023/12/25
 */
public class BuilderPatternExample {
    public static void main(String[] args) {
        HouseBuilder builder = new LuxuryHouseBuilder();
        Director director = new Director(builder);
        House house = director.contructHouse();
        System.out.println(house);
    }
}
