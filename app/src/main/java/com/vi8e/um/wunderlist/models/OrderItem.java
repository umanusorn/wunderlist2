package com.vi8e.um.wunderlist.models;

public class OrderItem {
String orderId, orderIdPrinted, status, orderDate;
double grandTotal;

public
String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public
	String getOrderIdPrinted() {
		return orderIdPrinted;
	}
	public void setOrderIdPrinted(String orderIdPrinted) {
		this.orderIdPrinted = orderIdPrinted;
	}
	public
	String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public
	String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public double getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
	
}
