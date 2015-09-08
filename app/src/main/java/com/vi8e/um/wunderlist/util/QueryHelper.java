package com.vi8e.um.wunderlist.util;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.ListView;

import com.vi8e.um.wunderlist.Model.ListModel;
import com.vi8e.um.wunderlist.Model.TaskModel;
import com.vi8e.um.wunderlist.adater.LandingListAdapter;
import com.vi8e.um.wunderlist.adater.TaskAdapter;
import com.vi8e.um.wunderlist.provider.list.ListColumns;
import com.vi8e.um.wunderlist.provider.list.ListCursor;
import com.vi8e.um.wunderlist.provider.list.ListSelection;
import com.vi8e.um.wunderlist.provider.task.TaskColumns;
import com.vi8e.um.wunderlist.provider.task.TaskCursor;
import com.vi8e.um.wunderlist.provider.task.TaskSelection;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by um.anusorn on 8/31/2015.
 */
public
class QueryHelper {


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
void deleteListValue ( Context context ) {
	ListSelection personTeamSelection = new ListSelection ();
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
void addListToDB ( Context context, String title, LandingListAdapter landingListAdapter, ListView listView ) {

	Log.d ( "addListToDb", "" );
	ListModel listModel = new ListModel ( title );
	Uri uri = context.getContentResolver ().insert ( ListColumns.CONTENT_URI, listModel.getValues () );
	Log.d ( "ChkColumn ", "title" + title + "newId=" + uri.getPathSegments ().get ( 1 ) );
	landingListAdapter.insert ( new ListModel ( uri.getPathSegments ().get ( 1 ), title ), 0 );

	Utility.setTaskListViewHeight ( listView );
}

public static
void addTaskToDB ( Context context, TaskModel taskModel, TaskAdapter taskAdapter, ListView listView ) {

	Log.d ( "addTaskToDb", "" );
	Uri uri = context.getContentResolver ().insert ( TaskColumns.CONTENT_URI, taskModel.getValues () );
	Log.d ( "ChkColumn ", "title" + taskModel.getListTitle () + "taskId=" + uri.getPathSegments ().get ( 1 )+"listId"+taskModel.getListId () );
	taskModel.setId ( uri.getPathSegments ().get ( 1 ) );
	taskAdapter.insert ( new TaskModel ( uri.getPathSegments ().get ( 1 ), taskModel ), 0 );
	Utility.setTaskListViewHeight ( listView );
}




}
