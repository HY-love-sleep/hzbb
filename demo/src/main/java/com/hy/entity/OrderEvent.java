package com.hy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class OrderEvent {
    
    private Order order;

    private String addOrUpdate;

    public OrderEvent(Order order, String addOrUpdate) {
        this.order = order;
        this.addOrUpdate = addOrUpdate;
    }
}