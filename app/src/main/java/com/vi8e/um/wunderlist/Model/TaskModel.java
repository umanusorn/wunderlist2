package com.vi8e.um.wunderlist.Model;
/**
 * Created by um.anusorn on 8/27/2015.
 */
public
class TaskModel extends ListModel {
public
TaskModel ( int id, String listTitle ) {
	super ( id, listTitle );
}

public
TaskModel ( String listTitle ) {
	super ( listTitle );
}

public
TaskModel ( String listTitle, String imgPath, int isDisturb, int isPinned, int folderId, int numCurrentTask, int numLateTask, int id ) {
	super ( listTitle, imgPath, isDisturb, isPinned, folderId, numCurrentTask, numLateTask, id );
}
}
