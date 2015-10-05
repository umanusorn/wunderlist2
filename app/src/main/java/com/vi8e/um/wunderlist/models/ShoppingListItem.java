package com.vi8e.um.wunderlist.models;

public class ShoppingListItem {
private long   id;
private String name, createdon;
private int qty_counts;

public
long getId () {
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
	String getCreatedon() {
		return createdon;
	}
	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}
	public int getQty_counts() {
		return qty_counts;
	}
	public void setQty_counts(int qty_counts) {
		this.qty_counts = qty_counts;
	}
	
}
