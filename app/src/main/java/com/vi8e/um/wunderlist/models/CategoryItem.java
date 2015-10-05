package com.vi8e.um.wunderlist.models;

public class CategoryItem {
	private long id,parentId;
private String name, code, displayOrder, slug;
private int     productCount;
private boolean hasChild;

public long getId() {
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
	String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public
	String getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}
	public
	String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public boolean isHasChild() {
		return hasChild;
	}
	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	
}
