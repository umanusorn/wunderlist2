package com.vi8e.um.wunderlist.models;

public class CityItem {
	private long id;
	private double latitude,longitude;
private float  altitude;
private String name;

public
long getId () {
	return id;
}

public
void setId ( long id ) {
	this.id = id;
}

public
double getLatitude() {
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
	public float getAltitude() {
		return altitude;
	}
	public void setAltitude(float altitude) {
		this.altitude = altitude;
	}
	public
	String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
