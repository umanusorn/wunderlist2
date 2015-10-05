package com.vi8e.um.wunderlist.models;

public class PWPItem {
	private long id,productid;
private String name, brand, sku, promo_text, image_url;
private double price, promo_price;

public
long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public
	String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public
	String getPromo_text() {
		return promo_text;
	}
	public void setPromo_text(String promo_text) {
		this.promo_text = promo_text;
	}
	public
	String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPromo_price() {
		return promo_price;
	}
	public void setPromo_price(double promo_price) {
		this.promo_price = promo_price;
	}
	public long getProductid() {
		return productid;
	}
	public void setProductid(long productid) {
		this.productid = productid;
	}
	
}
