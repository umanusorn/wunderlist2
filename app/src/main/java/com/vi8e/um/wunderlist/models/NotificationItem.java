package com.vi8e.um.wunderlist.models;

public class NotificationItem {
private long   _id;
private String message, datetime;
private boolean read;

public
long get_id () {
	return _id;
	}
	public void set_id(long _id) {
		this._id = _id;
	}
	public
	String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public
	String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public boolean isRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
	
	
}
