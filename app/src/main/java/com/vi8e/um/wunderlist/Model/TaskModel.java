package com.vi8e.um.wunderlist.Model;
import android.content.ContentValues;

import com.vi8e.um.wunderlist.provider.task.TaskColumns;


/**
 * Created by um.anusorn on 8/27/2015.
 */
public
class TaskModel extends ListModel {

String isStar;
String isComplete;
String listId;
String note;
String taskTitle;
String taskId;

public
TaskModel ( String id, TaskModel taskModel ) {
	super ( id, taskModel.listTitle );
	this.setIsStar ( taskModel.isStar );
	this.setIsComplete ( taskModel.isComplete );
}

public
TaskModel ( String id, ContentValues values ){
	super ( id );
	setValues ( values );
}

public
TaskModel ( String title, String isStar, String b, String listID ) {
	super ( title );
	this.setIsStar ( isStar );
	this.setIsComplete ( isComplete );
	//this.setTaskTitle ( title );
	this.setListId ( listID );

}

public void setValues(ContentValues values){
	this.setListTitle ( values.getAsString ( TaskColumns.TASK_TITLE ) );
	this.setIsStar ( values.getAsString ( TaskColumns.ISSTAR ) );
	this.setIsComplete ( values.getAsString ( TaskColumns.ISCOMPLETE ) );
	this.setListId ( values.getAsString ( TaskColumns.LISTID ) );
	this.setNote ( values.getAsString ( TaskColumns.NOTE ) );

}

public
ContentValues getValues (){
	ContentValues values = new ContentValues (  );
	values.put ( TaskColumns.TASK_TITLE, listTitle );
	values.put ( TaskColumns.ISSTAR, isStar );
	values.put ( TaskColumns.ISCOMPLETE, isComplete );
	values.put ( TaskColumns.LISTID, listId );
	values.put ( TaskColumns.NOTE, note );
		return values;
}

public
boolean isStar () {
	return Boolean.valueOf ( isStar );
}
/*public
String isStar () {
	return Boolean.valueOf ( isStar );
}*/

public
void setIsStar ( String isStar ) {
	this.isStar = isStar;
}

public boolean isComplete(){
	return Boolean.valueOf ( isComplete );
}

public
String getIsComplete () {
	return isComplete;
}

public
void setIsComplete ( String isComplete ) {
	this.isComplete = isComplete;
}

public
String getListId () {
	return listId;
}

public
void setListId ( String listId ) {
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
String getTaskTitle () {
	return taskTitle;
}

public
void setTaskTitle ( String taskTitle ) {
	this.taskTitle = taskTitle;
}

public
String getTaskId () {
	return taskId;
}

public
void setTaskId ( String taskId ) {
	this.taskId = taskId;
}
}
