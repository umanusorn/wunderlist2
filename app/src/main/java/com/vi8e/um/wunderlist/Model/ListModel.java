package com.vi8e.um.wunderlist.Model;

import android.content.ContentValues;
import android.util.Log;

import com.vi8e.um.wunderlist.provider.list.ListColumns;


public
class ListModel {


String id;
int numLateTask;
int    numCurrentTask;
int    folderId;
boolean    isPinned;
boolean   isDisturb;
String imgPath;
String listTitle;
String type;
public
ListModel ( String id, String listTitle ) {
	setDefault ();

	Log.d ("NewListModel","id="+id);
	this.id = id;
	this.listTitle = listTitle;
}

void setDefault () {
	this.listTitle = ""; //
	this.imgPath = "";
	this.isDisturb = false;

	this.isPinned = false;
	this.folderId = 0;
	this.numCurrentTask = 0;

	this.numLateTask = 0;
	this.id = "0";

}

public
ContentValues getValues(){
	ContentValues values = new ContentValues (  );
	values.put ( ListColumns.LIST_TITLE,listTitle );
	values.put ( ListColumns.IMG_PATH,imgPath );
	values.put ( ListColumns.ISDISTURB,isDisturb );

	values.put ( ListColumns.ISPINNED,isPinned );
	values.put ( ListColumns.FOLDER_ID,folderId );
	values.put ( ListColumns.NUM_CURRENT_TASK,numCurrentTask );

	values.put ( ListColumns.NUM_LATE_TASK,numLateTask );
	//values.put ( ListColumns._ID,id );

	return values;
}

public
ListModel ( String listTitle ) {
	setDefault ();
	this.listTitle = listTitle;

}

public
ListModel ( String listTitle, String imgPath, boolean isDisturb, boolean isPinned, int folderId, int numCurrentTask, int numLateTask, String id ) {
	this.listTitle = listTitle; //
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
String getListTitle () {
	return listTitle;
}

public
void setListTitle ( String listTitle ) {
	this.listTitle = listTitle;
}

public
String getId () {
	return id;
}

public
void setId ( String id ) {
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
boolean isPinned () {
	return isPinned;
}

public
void setIsPinned ( boolean isPinned ) {
	this.isPinned = isPinned;
}

public
boolean isDisturb () {
	return isDisturb;
}

public
void setIsDisturb ( boolean isDisturb ) {
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
