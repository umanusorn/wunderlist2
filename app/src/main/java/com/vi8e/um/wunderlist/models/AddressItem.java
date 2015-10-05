package com.vi8e.um.wunderlist.models;

public class AddressItem {
private long   id;
private String firstName, lastName, address, address2, postalCode, phoneNumber, home, office, addressType;
	private boolean isPrimary;
	private int require_halal;
	
	public int getRequire_halal() {
		return require_halal;
	}
	public void setRequire_halal(int require_halal) {
		this.require_halal = require_halal;
	}
	public
	String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public
	String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	public
	String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public
	String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public
	String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public
	String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public
	String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public
	String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public
	String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean isPrimary() {
		return isPrimary;
	}
	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
}
