package com.vi8e.um.wunderlist.Model;
import android.content.ContentValues;
import android.util.Log;

import com.vi8e.um.wunderlist.provider.task.TaskColumns;


/**
 * Created by um.anusorn on 8/27/2015.
 */
public
class TaskModel extends ListModel {

boolean isStar;
boolean isComplete;
int listId;
String note;
String taskTitle;
String taskId;


public
ContentValues getValues (){
	ContentValues values = new ContentValues (  );
	values.put ( TaskColumns.TASK_TITLE,taskTitle );
	values.put ( TaskColumns.ISSTAR,isStar );
	values.put ( TaskColumns.ISCOMPLETE,isComplete );
	values.put ( TaskColumns.LISTID,listId );
	values.put ( TaskColumns.NOTE,note );
		return values;
}


public
boolean isStar () {
	return isStar;
}

public
boolean isComplete () {
	return isComplete;
}

public
void setIsComplete ( boolean isComplete ) {
	this.isComplete = isComplete;
}

public
void setIsStar ( boolean isStar ) {
	Log.d ( "isStar=", "" + isStar );
	this.isStar = isStar;
}

public
int getListId () {
	return listId;
}

public
void setListId ( int listId ) {
	this.listId = listId;
}

public
String getNote () {
	return note;
}

public
void setNote ( String note ) {
	this.note = note;
}

public
TaskModel ( String id, String listTitle ) {
	super ( id, listTitle );
}

public
TaskModel ( String listTitle,Boolean isStar ) {
	super ( listTitle );
	this.setIsStar ( isStar );
}

public
TaskModel ( String listTitle,Boolean isStar ,boolean isComplete ) {
	super ( listTitle );
	this.setIsStar ( isStar );
}

public
void setTaskTitle ( String taskTitle ) {
	this.taskTitle = taskTitle;
}

public
void setTaskId ( String taskId ) {
	this.taskId = taskId;
}

public
TaskModel ( String listTitle ) {
	super ( listTitle );
	this.setTaskTitle ( listTitle );

}

}
