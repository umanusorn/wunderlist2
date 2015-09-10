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
String createDate,DueToDate,ReminderDate;

public
TaskModel ( String id, TaskModel taskModel ) {
	super ( id, taskModel.title );
	setAll ( taskModel );
}

void setAll(TaskModel taskModel){

	this.setId ( taskModel.getId () );
	this.setIsStar ( taskModel.isStar );
	this.setIsComplete ( taskModel.isComplete );
	this.setListId ( taskModel.getListId () );
	this.setNote ( taskModel.getNote () );
	//this.setTaskId ( taskModel.getTaskId () );
	this.setTitle ( taskModel.getTitle () );
	this.setCreateDate ( taskModel.getCreateDate () );
	this.setReminderDate ( taskModel.getReminderDate () );
	this.setDueToDate ( taskModel.getDueToDate () );
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
TaskModel (  ContentValues values ){
	super (values.getAsString ( TaskColumns.TASK_TITLE ));
//	super ( id );
	setValues ( values );
}


public
TaskModel ( String id, ContentValues values ){
	super ( id );
	setValues ( values );
}

public
TaskModel ( String title, String isStar, String isComplete, String listID, long currentTime ) {
	super ( title );
	//setDefault ();
	this.setIsStar ( isStar );
	this.setIsComplete ( isComplete );
	this.setListId ( listID );
	this.setCreateDate ( String.valueOf ( currentTime ) );
}


public
ContentValues getValues (){
	ContentValues values = new ContentValues (  );
	values.put ( TaskColumns.TASK_TITLE, title );
	values.put ( TaskColumns.ISSTAR, isStar );
	values.put ( TaskColumns.ISCOMPLETE, isComplete );
	values.put ( TaskColumns.LISTID, listId );
	values.put ( TaskColumns.NOTE, note );
	values.put ( TaskColumns.CREATE_DATE,createDate );
	values.put ( TaskColumns.DUETO_DATE, getDueToDate () );
	values.put ( TaskColumns.REMINDER_DATE, getReminderDate () );
	return values;
}

public void setValues(ContentValues values){
	this.setTitle ( values.getAsString ( TaskColumns.TASK_TITLE ) );
	this.setIsStar ( values.getAsString ( TaskColumns.ISSTAR ) );
	this.setIsComplete ( values.getAsString ( TaskColumns.ISCOMPLETE ) );
	this.setListId ( values.getAsString ( TaskColumns.LISTID ) );
	this.setNote ( values.getAsString ( TaskColumns.NOTE ) );
	this.setId ( values.getAsString ( TaskColumns._ID ) );
	this.setCreateDate ( values.getAsString ( TaskColumns.CREATE_DATE ) );
	this.setDueToDate ( values.getAsString ( TaskColumns.DUETO_DATE ) );
	this.setReminderDate ( values.getAsString ( TaskColumns.REMINDER_DATE ) );

}






public
String getCreateDate () {
	return createDate;
}

public
void setCreateDate ( String createDate ) {
	this.createDate = createDate;
}

public
String getDueToDate () {
	return DueToDate;
}

public
void setDueToDate ( String dueToDate ) {
	DueToDate = dueToDate;
}

public
String getReminderDate () {
	return ReminderDate;
}

public
void setReminderDate ( String reminderDate ) {
	ReminderDate = reminderDate;
}
/*public
String isStar () {
	return Boolean.valueOf ( isStar );
}*/

public
boolean isStar () {
	return Boolean.valueOf ( isStar );
}

public String getIsStar(){
	return isStar;
}

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

}
