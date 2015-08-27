package com.vi8e.um.wunderlist.Model;
/**
 * Created by um.anusorn on 8/27/2015.
 */
public
class TaskModel extends ListModel {

boolean isStar;
boolean isComplete;
int listId;
String note;

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
TaskModel ( int id, String listTitle ) {
	super ( id, listTitle );
}

public
TaskModel ( String listTitle,Boolean isStar ) {
	super ( listTitle );
	this.setIsStar ( isStar );
}

public
TaskModel ( String listTitle ) {
	super ( listTitle );
}

}
