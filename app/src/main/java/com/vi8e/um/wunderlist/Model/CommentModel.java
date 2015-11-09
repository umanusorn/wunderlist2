package com.vi8e.um.wunderlist.Model;

import android.content.ContentValues;
import android.util.Log;

import com.vi8e.um.wunderlist.provider.taskcomment.TaskCommentColumns;


public
class CommentModel extends SubTaskModel {

String id;
String taskId;
String title;
String dateTime;
String userId;

public
CommentModel ( String title, String taskId, String id, String dateTime, String userId ) {
	super (title);
	//setDefault ();
	Log.d ( "newComment", "id=" + id );

	this.title = title;
	this.taskId = taskId;
	this.id = id;
	this.dateTime = dateTime;
	this.userId = userId;
}

public CommentModel(ContentValues listValues) {
	setValues(listValues);
}

public CommentModel setValues(ContentValues values) {
	id=values.getAsString(TaskCommentColumns._ID);
	title = values.getAsString(TaskCommentColumns.COMMENT_TITLE);
	taskId=values.getAsString(TaskCommentColumns.TASK_ID);
	userId=values.getAsString(TaskCommentColumns.USER_ID);
	dateTime=values.getAsString(TaskCommentColumns.DATETIME);

	return this;
}

public
CommentModel ( String title, String taskId ) {
	super (title);

	this.title = title;
	this.taskId = taskId;
	if ( getDateTime () == null ) {
		setDateTime ( String.valueOf ( false ) );
	}

}

public
String getDateTime () {
	return dateTime;
}

public
void setDateTime ( String dateTime ) {
	this.dateTime = dateTime;
}

public
String getUserId () {
	return userId;
}

public
void setUserId ( String userId ) {
	this.userId = userId;
}

public
boolean isComplete () {
	return Boolean.valueOf ( dateTime );
}

public
String getTaskId () {
	return taskId;
}

public
void setTaskId ( String taskId ) {
	this.taskId = taskId;
}

public
ContentValues getValues () {
	ContentValues values = new ContentValues ();
	values.put ( TaskCommentColumns.COMMENT_TITLE, title );
	values.put ( TaskCommentColumns.TASK_ID, taskId );
	values.put ( TaskCommentColumns.DATETIME, getComplete () );
	values.put ( TaskCommentColumns.USER_ID, getComplete () );
	values.put ( TaskCommentColumns._ID,id );
	return values;
}

public
String getComplete () {
	return dateTime;
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
