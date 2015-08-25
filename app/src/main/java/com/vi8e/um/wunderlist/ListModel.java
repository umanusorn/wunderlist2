package com.vi8e.um.wunderlist;

public class ListModel {


int id,numLateTask,numCurrentTask,folderId,isPinned, isDisturb;
String imgPath;
String name;
String type;




public
ListModel ( String name, String imgPath, int isDisturb, int isPinned, int folderId, int numCurrentTask, int numLateTask, int id ) {
	this.name = name; //
	this.imgPath = imgPath;
	this.isDisturb = isDisturb;
	this.isPinned = isPinned;
	this.folderId = folderId;
	this.numCurrentTask = numCurrentTask;
	this.numLateTask = numLateTask;
	this.id = id;
}

public
String getType () {

	return type;
}

public
void setType ( String type ) {
	this.type = type;
}

public
String getName () {
	return name;
}

public
void setName ( String name ) {
	this.name = name;
}

public
int getId () {
	return id;
}

public
void setId ( int id ) {
	this.id = id;
}

public
int getNumLateTask () {
	return numLateTask;
}

public
void setNumLateTask ( int numLateTask ) {
	this.numLateTask = numLateTask;
}

public
int getNumCurrentTask () {
	return numCurrentTask;
}

public
void setNumCurrentTask ( int numCurrentTask ) {
	this.numCurrentTask = numCurrentTask;
}

public
int getFolderId () {
	return folderId;
}

public
void setFolderId ( int folderId ) {
	this.folderId = folderId;
}

public
int getIsPinned () {
	return isPinned;
}

public
void setIsPinned ( int isPinned ) {
	this.isPinned = isPinned;
}

public
int getIsDisturb () {
	return isDisturb;
}

public
void setIsDisturb ( int isDisturb ) {
	this.isDisturb = isDisturb;
}

public
String getImgPath () {
	return imgPath;
}

public
void setImgPath ( String imgPath ) {
	this.imgPath = imgPath;
}
}
