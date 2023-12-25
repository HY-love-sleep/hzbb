package com.hy.builder;

/**
 * Description: 指导者
 * Author: yhong
 * Date: 2023/12/25
 */
public class Director {
    private HouseBuilder houseBuilder;


    public Director(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    public House contructHouse() {
        houseBuilder.buildFoundation();
        houseBuilder.buildStructure();
        houseBuilder.buildRoof();
        houseBuilder.buildInterior();
        return houseBuilder.getHouse();
    }
}
