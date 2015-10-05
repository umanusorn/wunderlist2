package com.vi8e.um.wunderlist.models;

public class ShoppingItemsItem {
	private long id,productid;
private String name, notes, brand, price, weight, priceDisc, imageUrl;
private int    qty;
private double row_total;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getProductid() {
		return productid;
	}
	public void setProductid(long productid) {
		this.productid = productid;
	}
	public
	String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public
	String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public
	String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public
	String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public
	String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getRow_total() {
		return row_total;
	}
	public void setRow_total(double row_total) {
		this.row_total = row_total;
	}
	public
	String getPriceDisc() {
		return priceDisc;
	}
	public void setPriceDisc(String priceDisc) {
		this.priceDisc = priceDisc;
	}
	public
	String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}

