package com.vi8e.um.wunderlist.models;

public class ShopingItem {
	private long id,itemId;
	private int qty;
	private double price,total,save,priceori;
private String name, image, promo, brand;

public
long getId () {
	return id;
}

public
void setId ( long id ) {
	this.id = id;
}

public
int getQty () {
	return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public
	String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public
	String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public
	String getPromo() {
		return promo;
	}
	public void setPromo(String promo) {
		this.promo = promo;
	}
	public double getSave() {
		return save;
	}
	public void setSave(double save) {
		this.save = save;
	}
	public
	String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public double getPriceori() {
		return priceori;
	}
	public void setPriceori(double priceori) {
		this.priceori = priceori;
	}


}
