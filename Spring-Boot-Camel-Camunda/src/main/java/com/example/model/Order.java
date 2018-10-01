package com.example.model;

import java.io.Serializable;

public class Order implements Serializable{

	private String orderId;
	private String name;
	private int price;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", name=" + name + ", price=" + price + "]";
	}
	
	
}
