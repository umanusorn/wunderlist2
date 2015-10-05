package com.vi8e.um.wunderlist.models;

public class GalleryItem {
private long   id;
private String title, type, sku, brand, image, inventory_status, attribute;
private double price,promo_price,lowest_price,highest_price,lowest_promo_price,highest_promo_price;
	private boolean isKVD,isBulkSpecial,isWeightItem,isAllowInstallment,credit_card_promo_allowed,isExclusiveBrand;
private String average_weight, size, extra_note;
private int    itemOrder;
private int    promoQty;
private int    stamps;
private String deliveryType;

public
boolean isKVD () {
	return isKVD;
}

public
void setKVD ( boolean isKVD ) {
	this.isKVD = isKVD;
}

public
boolean isBulkSpecial () {
	return isBulkSpecial;
}

public
void setBulkSpecial ( boolean isBulkSpecial ) {
	this.isBulkSpecial = isBulkSpecial;
}

public
boolean isWeightItem () {
	return isWeightItem;
}

public
void setWeightItem ( boolean isWeightItem ) {
	this.isWeightItem = isWeightItem;
	}
	public boolean isAllowInstallment() {
		return isAllowInstallment;
	}
	public void setAllowInstallment(boolean isAllowInstallment) {
		this.isAllowInstallment = isAllowInstallment;
	}
	public boolean isCredit_card_promo_allowed() {
		return credit_card_promo_allowed;
	}
	public void setCredit_card_promo_allowed(boolean credit_card_promo_allowed) {
		this.credit_card_promo_allowed = credit_card_promo_allowed;
	}
	public
	String getAverage_weight() {
		return average_weight;
	}
	public void setAverage_weight(String average_weight) {
		this.average_weight = average_weight;
	}
	public
	String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public
	String getExtra_note() {
		return extra_note;
	}
	public void setExtra_note(String extra_note) {
		this.extra_note = extra_note;
	}
	public int getStamps() {
		return stamps;
	}
	public void setStamps(int stamps) {
		this.stamps = stamps;
	}
	public double getPrice() { 	
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getLowest_price() {
		return lowest_price;
	}
	public void setLowest_price(double lowest_price) {
		this.lowest_price = lowest_price;
	}
	public double getHighest_price() {
		return highest_price;
	}
	public void setHighest_price(double highest_price) {
		this.highest_price = highest_price;
	}
	public double getLowest_promo_price() {
		return lowest_promo_price;
	}
	public void setLowest_promo_price(double lowest_promo_price) {
		this.lowest_promo_price = lowest_promo_price;
	}
	public double getHighest_promo_price() {
		return highest_promo_price;
	}
	public void setHighest_promo_price(double highest_promo_price) {
		this.highest_promo_price = highest_promo_price;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public
	String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public
	String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public
	String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public
	String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public
	String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPromo_price() {
		return promo_price;
	}
	public void setPromo_price(double promo_price) {
		this.promo_price = promo_price;
	}
	public
	String getInventory_status() {
		return inventory_status;
	}
	public void setInventory_status(String inventory_status) {
		this.inventory_status = inventory_status;
	}
	public
	String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public int getPromoQty() {
		return promoQty;
	}
	public void setPromoQty(int promoQty) {
		this.promoQty = promoQty;
	}
	public int getItemOrder() {
		return itemOrder;
	}
	public void setItemOrder(int itemOrder) {
		this.itemOrder = itemOrder;
	}
	public boolean isExclusiveBrand() {
		return isExclusiveBrand;
	}
	public void setExclusiveBrand(boolean isExclusiveBrand) {
		this.isExclusiveBrand = isExclusiveBrand;
	}
	public
	String getDeliveryType() {
		return deliveryType;
	}
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	
	
	
}
