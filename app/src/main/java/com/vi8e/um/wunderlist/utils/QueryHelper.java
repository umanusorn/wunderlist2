package com.vi8e.um.wunderlist.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.ListView;

import com.vi8e.um.wunderlist.Activity.LandingActivity;
import com.vi8e.um.wunderlist.Model.CommentModel;
import com.vi8e.um.wunderlist.Model.ListModel;
import com.vi8e.um.wunderlist.Model.SubTaskModel;
import com.vi8e.um.wunderlist.Model.TaskModel;
import com.vi8e.um.wunderlist.adapters.TaskAdapter;
import com.vi8e.um.wunderlist.provider.list.ListColumns;
import com.vi8e.um.wunderlist.provider.list.ListCursor;
import com.vi8e.um.wunderlist.provider.list.ListSelection;
import com.vi8e.um.wunderlist.provider.subtask.SubtaskColumns;
import com.vi8e.um.wunderlist.provider.subtask.SubtaskCursor;
import com.vi8e.um.wunderlist.provider.subtask.SubtaskSelection;
import com.vi8e.um.wunderlist.provider.task.TaskColumns;
import com.vi8e.um.wunderlist.provider.task.TaskCursor;
import com.vi8e.um.wunderlist.provider.task.TaskSelection;
import com.vi8e.um.wunderlist.provider.taskcomment.TaskCommentColumns;
import com.vi8e.um.wunderlist.provider.taskcomment.TaskCommentSelection;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by um.anusorn on 8/31/2015.
 */
public
class QueryHelper {

public static int getCurrentTaskCount ( ListModel listModel, Context context ) {
	//Log.d ( TAG, "getCurrentTaskCount" );
	TaskSelection where = new TaskSelection ();
	where.listid ( listModel.getId () ).and ().iscomplete ( String.valueOf ( Boolean.FALSE ) );
	int count = where.count ( context.getContentResolver () );
	Log.d ( "getCurrentTaskCount", "listid=" + listModel.getId ()+" count=" +count);
	return count;
}

public static
Cursor getSubTaskValueCursor ( Context context ) {
	SubtaskSelection selection = new SubtaskSelection ();
	String[] projection = SubtaskColumns.ALL_COLUMNS;
	SubtaskCursor cursor = selection.query ( context.getContentResolver (), projection );
	return cursor;
}

public static
Cursor getListValueCursor ( Context context ) {
	ListSelection recordValueSelection = new ListSelection ();
	String[] projection = ListColumns.ALL_COLUMNS;
	ListCursor cursor = recordValueSelection.query ( context.getContentResolver (), projection );
	return cursor;
}

public static
Cursor getTaskValueCursor ( Context context ) {
	TaskSelection selection = new TaskSelection ();
	String[] projection = TaskColumns.ALL_COLUMNS;
	TaskCursor recordValueCursor = selection.query ( context.getContentResolver (), projection );
	return recordValueCursor;
}



public static
void deleteAllListValues ( Context context ) {
	ListSelection listSelection = new ListSelection ();
	listSelection.delete ( context.getContentResolver () );
}

public static
void deleteAllSubTaskValues ( Context context ) {
	SubtaskSelection subtaskSelection = new SubtaskSelection();
	subtaskSelection.delete ( context.getContentResolver () );
}

public static
void deleteAllCommentValues ( Context context ) {
	TaskCommentSelection taskCommentSelection = new TaskCommentSelection();
	taskCommentSelection.delete ( context.getContentResolver () );
}

public static
void deleteAllTaskValues ( Context context ) {
	TaskSelection personTeamSelection = new TaskSelection ();
	personTeamSelection.delete ( context.getContentResolver () );
}

public static
List<ContentValues> getValuesFromCursor ( Cursor c, String[] ALL_COLUMNS ) {
	List<ContentValues> values = new ArrayList<ContentValues> ();
	int i = 0;
	String key;
	int index;
	c.moveToFirst ();
	if ( c.getCount () > 0 ) {
		do {
			ContentValues value = new ContentValues ();
			//Log.d ( "InWhile cursor=", c.getCount () + "  Values=" + values.size () );
			for ( int j = 0 ; j < c.getColumnCount () ; j++ ) {
				key = ALL_COLUMNS[ j ];
				index = c.getColumnIndex ( key );
				//Log.d ( "InFor",key+">>"+index );
				value.put ( key, c.getString ( index ) );
			}
			i++;
			values.add ( value );
		}
		while ( c.moveToNext () );
	}
	Log.d ( "getCount cursor=", c.getCount () + "  Values=" + values.size () );

	return values;
}

public static
 void addListToDB ( Context context, String title,ListView listView ) {

	Log.d ( "addListToDb", "" );
	Uri uri = addListToDB ( context, title );
	Log.d ( "ChkColumn ", "title" + title + "newId=" + getIdFromUri ( uri ) );
	updateListAdapter ( title, listView, uri );
}

public static
void addSubTaskToDB ( Context context, String title,String taskId,ListView listView ) {

	Log.d ( "addListToDb", "" );
	Uri uri = addSubTaskToDB ( context, title,taskId );
	Log.d ( "ChkColumn ", "title" + title + "newId=" + getIdFromUri ( uri ) );
	//updateListAdapter ( title, listView, uri );
}



public static
 void updateListAdapter ( String title, ListView listView, Uri uri ) {
	LandingActivity.mLandingListAdapter.add ( 0, new ListModel ( getIdFromUri ( uri ), title ) );
	UiMng.setTaskListViewHeight(listView);
}


public static
String getIdFromUri ( Uri uri ) {
	return uri.getPathSegments ().get ( 1 );
}

public static
Uri addListToDB ( Context context, String title ) {
	ListModel listModel = new ListModel ( title );
	return context.getContentResolver ().insert ( ListColumns.CONTENT_URI, listModel.getValues () );
}

public static
Uri addSubTaskToDB ( Context context, String title, String taskId ) {
	SubTaskModel subTaskModel = new SubTaskModel ( title,taskId );
	return context.getContentResolver ().insert ( SubtaskColumns.CONTENT_URI, subTaskModel.getValues () );
}

public static
Uri addCommentToDB ( Context context, String title, String taskId ) {
	CommentModel model = new CommentModel ( title,taskId );
	return context.getContentResolver ().insert ( TaskCommentColumns.CONTENT_URI, model.getValues () );
}

public static
void updateListAdapter ( ListModel listModel, ListView listView) {

	LandingActivity.mLandingListAdapter.add ( 0,listModel );
	UiMng.setTaskListViewHeight(listView);
}

public static
Uri addListToDB ( Context context, ListModel listModel ) {
	return context.getContentResolver ().insert ( ListColumns.CONTENT_URI, listModel.getValues () );
}

public static
void addTaskToDB ( Context context, TaskModel taskModel, TaskAdapter taskAdapter, ListView listView ) {

	Uri uri = addTaskToDB ( context, taskModel );
	taskModel.setId ( getIdFromUri ( uri ) );
	taskAdapter.insert ( new TaskModel ( getIdFromUri ( uri ), taskModel ), 0 );
	UiMng.setTaskListViewHeight(listView);
}

public static
Uri addTaskToDB ( Context context, TaskModel taskModel ) {
	Log.d ( "addTaskToDb", "" );
	Uri uri = context.getContentResolver ().insert ( TaskColumns.CONTENT_URI, taskModel.getValues () );
	Log.d ( "ChkColumn ", "title" + taskModel.getTitle () + "taskId=" + getIdFromUri ( uri ) + "listId" + taskModel.getListId () );
return uri;
}

public static
void genListAndTask ( Context context ) {
	for ( int i = 0 ; i < 10 ; i++ ) {
		Uri uri = addListToDB ( context, "Category " + ( i + 1 ) );

		for ( int j = 0 ; j < i ; j++ ) {
			boolean isStar, isComplete = false;
			if ( j % 2 == 0 ) {
				isStar = true;
				if ( j % 3 != 0 ) {
					isComplete = false;
				}
				else
				{
					isComplete = true;
				}
			}
			else {
				isStar = false;
			}

			TaskModel taskModel = new TaskModel ( "Task "+( i + 1 )+"-"+(j+1),
			                                      String.valueOf ( isStar ),
			                                      String.valueOf ( isComplete ),
			                                      uri.getPathSegments ().get ( 1 ),
			                                      System.currentTimeMillis () );
			if(j>2)
					taskModel.setNote ( "GeneratedNote"+( i + 1 )+"-"+(j-2) );
			context.getContentResolver ().insert ( TaskColumns.CONTENT_URI, taskModel.getValues () );
		}

	}
}
}
