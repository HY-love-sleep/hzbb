package com.hy.entity;

/**
 * 定义一个Order实体类，这也是disruptor中的一个event
 */
public class Order {

	private String id;
	private String name;
	private double price;

	public Order() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}