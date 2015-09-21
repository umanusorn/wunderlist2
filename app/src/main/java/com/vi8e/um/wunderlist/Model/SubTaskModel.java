package com.vi8e.um.wunderlist.Model;

import android.content.ContentValues;
import android.util.Log;

import com.vi8e.um.wunderlist.provider.subtask.SubtaskColumns;


public
class SubTaskModel {


String id;
String taskId;
String title;
String Complete;

public
String getTaskId () {
	return taskId;
}

public
void setTaskId ( String taskId ) {
	this.taskId = taskId;
}

public
String getComplete () {
	return Complete;
}

public
void setComplete ( String complete ) {
	Complete = complete;
}

public
SubTaskModel ( String title, String taskId, String id ) {
	//setDefault ();
	Log.d ( "newSubTask", "id=" + id );

	this.title = title;
	this.taskId = taskId;
	this.id = id;
}
public
SubTaskModel ( String title, String taskId ) {

	this.title = title;
	this.taskId = taskId;
}

public
ContentValues getValues () {
	ContentValues values = new ContentValues ();
	values.put ( SubtaskColumns.SUBTASK_TITLE, title );
	values.put ( SubtaskColumns.TASKID, taskId );
	values.put ( SubtaskColumns.COMPLETE, getComplete ());
	//values.put ( ListColumns._ID,id );
	return values;
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
}