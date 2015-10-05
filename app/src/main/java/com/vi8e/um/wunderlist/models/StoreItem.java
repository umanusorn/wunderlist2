package com.vi8e.um.wunderlist.models;

public class StoreItem {
	private long id,cityId;
private String name, address, phone;
private double latitude, longitude;

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
	String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public
	String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public long getCityId() {
		return cityId;
	}
	public void setCityId(long cityId) {
		this.cityId = cityId;
	}
	
}
