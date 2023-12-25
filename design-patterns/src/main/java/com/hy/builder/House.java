package com.hy.builder;

import lombok.ToString;

/**
 * Description: 房屋类
 * Author: yhong
 * Date: 2023/12/25
 */
@ToString
public class House {
    private String foundation;
    private String structure;
    private String roof;
    private String interior;


    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }
}
