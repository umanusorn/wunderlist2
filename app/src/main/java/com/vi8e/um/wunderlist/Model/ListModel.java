package com.vi8e.um.wunderlist.Model;

import android.content.ContentValues;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.provider.list.ListColumns;


public
class ListModel {


String  id;
int     numLateTask;
int     numCurrentTask;
int     folderId;
boolean isPinned;
boolean isDisturb;
String  imgPath;
String  title;
String  type;
View rootView;

public RelativeLayout getRowListRootView(){
	return  (RelativeLayout)getRootView ().findViewById ( R.id.row_list_root_view );
}
public
ListModel ( String id, String title ) {
	//setDefault ();
	Log.d ( "NewListModel", "id=" + id+"title= "+title );
	this.id = id;
	this.title = title;
}

public
ListModel ( String title ) {
	setDefault ();
	this.title = title;

}

void setDefault () {
	//this.title = ""; //
	this.imgPath = "";
	this.isDisturb = false;

	this.isPinned = false;
	this.folderId = 0;
	this.numCurrentTask = 0;

	this.numLateTask = 0;
	this.id = "0";

}

public
ListModel ( ListModel listModel) {
	this.title = listModel.getTitle ();
	this.imgPath = listModel.getImgPath ();
	this.isDisturb = listModel.isDisturb ();
	this.isPinned = listModel.isPinned ();
	this.folderId = listModel.getFolderId ();
	this.numCurrentTask = listModel.getNumCurrentTask ();
	this.numLateTask = listModel.getNumLateTask ();
	this.id = listModel.getId ();

}

public
String getTitle () {
	return title;
}

public
void setTitle ( String title ) {
	this.title = title;
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
boolean isDisturb () {
	return isDisturb;
}

public
String getImgPath () {
	return imgPath;
}

public
void setImgPath ( String imgPath ) {
	this.imgPath = imgPath;
}

public
ListModel ( String title, String imgPath, boolean isDisturb, boolean isPinned, int folderId, int numCurrentTask, int numLateTask, String id ) {
	this.title = title; //
	this.imgPath = imgPath;
	this.isDisturb = isDisturb;
	this.isPinned = isPinned;
	this.folderId = folderId;
	this.numCurrentTask = numCurrentTask;
	this.numLateTask = numLateTask;
	this.id = id;

}

public
View getRootView () {
	return rootView;
}

public
void setRootView ( View rootView ) {
	this.rootView = rootView;
}

public
ContentValues getValues () {
	ContentValues values = new ContentValues ();
	values.put ( ListColumns.LIST_TITLE, title );
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
String getType () {

	return type;
}

public
void setType ( String type ) {
	this.type = type;
}

public
void setIsPinned ( boolean isPinned ) {
	this.isPinned = isPinned;
}

public
void setIsDisturb ( boolean isDisturb ) {
	this.isDisturb = isDisturb;
}
}
