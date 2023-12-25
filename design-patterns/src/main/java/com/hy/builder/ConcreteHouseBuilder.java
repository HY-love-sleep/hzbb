package com.hy.builder;

class ConcreteHouseBuilder extends HouseBuilder {

        @Override
        public void buildFoundation() {
            house.setFoundation("Standard Foundation");
        }

        @Override
        public void buildStructure() {
            house.setStructure("Standard Structure");
        }

        @Override
        public void buildRoof() {
            house.setRoof("Standard Roof");
        }

        @Override
        public void buildInterior() {
            house.setInterior("Standard Interior");
        }
    }