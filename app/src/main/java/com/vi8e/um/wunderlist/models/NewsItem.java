package com.vi8e.um.wunderlist.models;

public class NewsItem {
private long   id;
private int    image;
private String title, description;

public
long getId () {
	return id;
}

public
void setId(long id) {
		this.id = id;
	}
	public
	String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public
	String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getImage() {
		return image;
	}
	public void setImage(int image) {
		this.image = image;
	}

}
