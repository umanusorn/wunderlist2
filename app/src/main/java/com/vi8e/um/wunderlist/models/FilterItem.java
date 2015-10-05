package com.vi8e.um.wunderlist.models;

public class FilterItem {
private long   id;
private String name;
private int    selected;

public
long getId () {
	return id;
}

public
void setId ( long id) {
		this.id = id;
	}
	public
	String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSelected() {
		return selected;
	}
	public void setSelected(int selected) {
		this.selected = selected;
	}
	
}
