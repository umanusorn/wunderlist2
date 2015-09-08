package com.vi8e.um.wunderlist.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.vi8e.um.wunderlist.Model.ListConst;
import com.vi8e.um.wunderlist.Model.TaskModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.adater.TaskAdapter;
import com.vi8e.um.wunderlist.provider.task.TaskColumns;
import com.vi8e.um.wunderlist.provider.task.TaskSelection;
import com.vi8e.um.wunderlist.util.QueryHelper;
import com.vi8e.um.wunderlist.util.Utility;

import java.util.ArrayList;
import java.util.List;


public
class TaskActivity extends AppCompatActivity {

String title;
private static final String TAG = TaskActivity.class.getSimpleName ();

Toolbar               toolbar;
DrawerLayout          drawerLayout;
ActionBarDrawerToggle drawerToggle;

public static TaskAdapter taskAdapterInComplete;
public static TaskAdapter taskAdapterComplete;
public static ListView    listViewIncomplete, listViewComplete;
Boolean isStar       = false;
Boolean showComplete = true;
static        ArrayList<TaskModel> inCompleteList;// = new ArrayList<TaskModel> ();
static        ArrayList<TaskModel> completeList;
static        Activity             thisActivity;
static        String               listId;
public static TaskModel            currentTask;
static        Menu                 menu;


@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_task );

	Intent intent = getIntent ();
	Bundle bundle = intent.getExtras ();
	String title = bundle.getString ( ListConst.KEY_TITLE );
	listId = bundle.getString ( ListConst.KEY_ID );

	setToolBar ( toolbar, title );
	setUpContent ();
	setView ();
	thisActivity = this;
	listViewComplete = ( ListView ) findViewById ( R.id.listViewTaskComplete );
	setUpAdater ();
	toggleShowCompleteListView ();

}

private
void setUpAdater () {
	completeList = new ArrayList<TaskModel> ();
	taskAdapterComplete = new TaskAdapter ( getApplication (), completeList );
	taskAdapterComplete = setUpAdapterListView ( getApplicationContext (), listViewComplete, taskAdapterComplete, true );

	inCompleteList = new ArrayList<TaskModel> ();
	taskAdapterInComplete = new TaskAdapter ( getApplication (), inCompleteList );
	listViewIncomplete = ( ListView ) findViewById ( R.id.listViewTaskInComplete );
	taskAdapterInComplete = setUpAdapterListView ( getApplicationContext (), listViewIncomplete, taskAdapterInComplete, false );
}

public static
TaskAdapter setUpAdapterListView ( Context context, ListView listView, TaskAdapter taskAdapter, boolean isComplete ) {

	listView.setAdapter ( taskAdapter );
	TaskSelection where = new TaskSelection ();

	Log.d ( TAG, "listId=" + listId + " isComplete=" + isComplete );
	where.iscomplete ( String.valueOf ( isComplete ) ).and ().listid ( listId );
	Cursor c = where.query ( context.getContentResolver () );
	c.moveToFirst ();
	Log.d ( TAG, "setUpAdapter" + String.valueOf ( c.getCount () ) );
	List<ContentValues> allListValues = QueryHelper.getValuesFromCursor ( c, TaskColumns.ALL_COLUMNS );

	for ( int i = 0 ; i < allListValues.size () ; i++ ) {
		ContentValues values = allListValues.get ( i );
		Log.d ( TAG, values.toString () );
		taskAdapter.add ( new TaskModel ( values.getAsString ( TaskColumns._ID ), values ) );
		//Log.d ( TAG, " id=" + values.getAsInteger ( TaskColumns._ID ) );
	}
	listView.setAdapter ( taskAdapter );
	Utility.setTaskListViewHeight ( listView );
	return taskAdapter;
}

private
void setToolBar ( Toolbar toolbar, String title ) {
	toolbar = ( Toolbar ) findViewById ( R.id.toolbar );
	setSupportActionBar ( toolbar );
	toolbar.setVisibility ( View.VISIBLE );
	getSupportActionBar ().setTitle ( title );
	toolbar.setTitle ( title );
	getSupportActionBar ().setDisplayShowTitleEnabled ( true );
	getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
	getSupportActionBar ().setDisplayShowHomeEnabled ( true );
	toolbar.setNavigationOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			finish ();
		}
	} );
}

void setView () {
	TextView tvComplete = ( TextView ) findViewById ( R.id.tvComplete );
	tvComplete.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			toggleShowCompleteListView ();
		}
	} );
	ImageView editTextStar = ( ImageView ) findViewById ( R.id.star );

	editTextStar.setOnClickListener ( onCLickStar () );

	final EditText editText = ( EditText ) findViewById ( R.id.editText );
	editText.setHint ( "Add a to-do in \"" + title + "\"" );
	editText.setImeActionLabel ( "ADD", KeyEvent.KEYCODE_ENTER );
	editText.setOnKeyListener ( onAddViaEditText ( editText ) );

}

@NonNull private
View.OnKeyListener onAddViaEditText ( final EditText editText ) {
	return new View.OnKeyListener () {
		@Override public
		boolean onKey ( View v, int keyCode, KeyEvent event ) {
			if ( keyCode == KeyEvent.KEYCODE_ENTER && event.getAction () != KeyEvent.ACTION_DOWN ) {

				String title = editText.getText ().toString ();
				TaskModel taskModel = new TaskModel ( title, String.valueOf ( isStar ), String.valueOf ( false ), listId,System.currentTimeMillis () );
				QueryHelper.addTaskToDB ( getApplicationContext (), taskModel, taskAdapterInComplete, listViewIncomplete );

				editText.setText ( "" );
				View view = thisActivity.getCurrentFocus ();
				if ( view != null ) {
					InputMethodManager imm = ( InputMethodManager ) getSystemService ( Context.INPUT_METHOD_SERVICE );
					imm.hideSoftInputFromWindow ( view.getWindowToken (), 0 );
				}
			}
			return false;
		}
	};
}

@NonNull private
View.OnClickListener onCLickStar () {
	return new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			isStar = Utility.toggleImg ( v,
			                             getResources ().getDrawable ( R.mipmap.wl_task_detail_ribbon ),
			                             getResources ().getDrawable ( R.mipmap.wl_task_detail_ribbon_selected ) );

		}
	};
}

void toggleShowCompleteListView () {
	showComplete = ! showComplete;
	if ( showComplete ) {
		listViewComplete.setVisibility ( View.VISIBLE );
	}
	else {
		listViewComplete.setVisibility ( View.GONE );
	}
}

void setUpContent () {
	Bundle extras = getIntent ().getExtras ();
	if ( extras != null ) {
		title = extras.getString ( ListConst.KEY_TITLE );
	}
}

@Override
protected
void onPause () {
	super.onPause ();
	//setMenuNormal ();
	adapterToDb ( taskAdapterComplete );
	adapterToDb ( taskAdapterInComplete );
}

void adapterToDb ( TaskAdapter taskAdapter ) {

	Log.d ( TAG, "EnterOnPause dataCount" + taskAdapter.getCount () );
	for ( int i = 0 ; i < taskAdapter.getCount () ; i++ ) {
		TaskModel data = taskAdapter.getArrayList ().get ( i );
		String id = data.getId ();
		Uri uri = Uri.parse ( String.valueOf ( TaskColumns.CONTENT_URI ) + "/" + id );
		//Log.d ( TAG, "isStar=" + data.getIsStar () + " listId=" + data.getListId () + " OwnId" + data.getId () );
		try {
			getContentResolver ().update ( uri, data.getValues (), null, null );
		}
		catch ( IllegalArgumentException e ) {
			Log.e ( TAG, "errorOnAddData" + e.getMessage () );

			String title = data.getTitle ();
			TaskModel taskModel = new TaskModel ( id, data );
			uri = getContentResolver ().insert ( TaskColumns.CONTENT_URI, taskModel.getValues () );
			Log.d ( TAG, "title" + title + "newId=" + uri.getPathSegments ().get ( 1 ) );
		}
	}
}

@Override
protected
void
onResume () {
	super.onResume ();
	Log.d ( "OnResume", "" );
	setUpAdater ();

}

@Override
public
boolean onCreateOptionsMenu ( Menu menu ) {
	getMenuInflater ().inflate ( R.menu.menu_task_detail, menu );
	TaskActivity.menu = menu;
	return true;
}

@Override
public
boolean onOptionsItemSelected ( MenuItem item ) {
	int id = item.getItemId ();

	if ( id == R.id.action_settings ) {
		return true;
	}

	if ( id == R.id.delete ) {

		Log.d ( TAG, "delete id=" + currentTask.getId () );

		TaskSelection where = new TaskSelection ();
		where.id ( Long.parseLong ( currentTask.getId () ) );
		where.delete ( getApplicationContext () );
		if ( currentTask.isComplete () ) {
			taskAdapterComplete.remove ( currentTask );
		}
		else {
			taskAdapterInComplete.remove ( currentTask );
		}

		Utility.setTaskListViewHeight ( listViewIncomplete );
	}
	setMenuNormal ();

	return super.onOptionsItemSelected ( item );
}

public static
void setMenuNormal () {
	menu.clear ();
	thisActivity.getMenuInflater ().inflate ( R.menu.menu_main_normal, menu );

}

public static
void setMenuList () {
	menu.clear ();
	thisActivity.getMenuInflater ().inflate ( R.menu.menu_main_list_toggle, menu );

}
}
