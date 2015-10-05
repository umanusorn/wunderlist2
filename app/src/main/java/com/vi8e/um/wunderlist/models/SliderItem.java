package com.vi8e.um.wunderlist.models;

public class SliderItem {
private String image, url, targetType, targetName;
private long targetID;

public
String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public
	String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public
	String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public long getTargetID() {
		return targetID;
	}

	public void setTargetID(long targetID) {
		this.targetID = targetID;
	}

	public
	String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
}
