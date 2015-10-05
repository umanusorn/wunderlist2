package com.vi8e.um.wunderlist.models;

public class ScheduleListItem {
private String sku, name, brand;
private int qty;

public
String getSku () {
	return sku;
}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public
	String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public
	String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
}
