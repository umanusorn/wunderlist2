package com.vi8e.um.wunderlist.models;

public class DepartmentItem {
	private long id,parentId,deptId;
	private int image;
	public long getDeptId() {
		return deptId;
	}
	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}

private String departmentName;
private int    count, level, isOpen;
private String allParentIds;

public
String getAllParentIds () {
	return allParentIds;
}

public
void setAllParentIds ( String allParentIds ) {
	this.allParentIds = allParentIds;
}

public
int getIsOpen () {
	return isOpen;
}

public
void setIsOpen ( int isOpen ) {
	this.isOpen = isOpen;
}

public
int getLevel () {
	return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	private boolean isHasChildren;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public
	String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public boolean isHasChildren() {
		return isHasChildren;
	}
	public void setHasChildren(boolean isHasChildren) {
		this.isHasChildren = isHasChildren;
	}
	public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}

	
	
	
}
