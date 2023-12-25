package com.hy.builder;

class LuxuryHouseBuilder extends HouseBuilder {
    @Override
    public void buildFoundation() {
        house.setFoundation("Strong Foundation");
    }

    @Override
    public void buildStructure() {
        house.setStructure("Reinforced Structure");
    }

    @Override
    public void buildRoof() {
        house.setRoof("Elegant Roof");
    }

    @Override
    public void buildInterior() {
        house.setInterior("Luxury Interior");
    }
}