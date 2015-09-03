package com.vi8e.um.wunderlist.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
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
import com.vi8e.um.wunderlist.provider.list.ListColumns;
import com.vi8e.um.wunderlist.provider.task.TaskColumns;
import com.vi8e.um.wunderlist.util.QueryHelper;
import com.vi8e.um.wunderlist.util.Utility;

import java.util.ArrayList;
import java.util.List;


public
class TaskActivity extends AppCompatActivity {

String title;


Toolbar               toolbar;
DrawerLayout          drawerLayout;
ActionBarDrawerToggle drawerToggle;
public static TaskAdapter taskAdapterInComplete;
public static TaskAdapter taskAdapterComplete;
CoordinatorLayout    rootLayout;
FloatingActionButton fabBtn;
public static ListView listViewIncomplete, listViewComplete;
Boolean isStar       = false;
Boolean showComplete = false;
static ArrayList<TaskModel> inCompleteList;// = new ArrayList<TaskModel> ();
static ArrayList<TaskModel> completeList;
Activity thisActivity;

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_task );

	Intent intent = getIntent ();
	Bundle bundle = intent.getExtras ();
	String title = bundle.getString ( ListConst.KEY_TITLE );

	setToolBar ( toolbar, title );
	setUpContent ();
	setView ();
	thisActivity = this;
	listViewComplete = ( ListView ) findViewById ( R.id.listViewTaskComplete );
	completeList = new ArrayList<TaskModel> ();
	//completeList = new ArrayList<TaskModel> ();
// Create the adapter to convert the array to views
	taskAdapterComplete = new TaskAdapter ( getApplication (), completeList );
	taskAdapterComplete = setUpAdapterListView ( this,getApplicationContext (), listViewComplete, taskAdapterComplete, true );

	inCompleteList = new ArrayList<TaskModel> ();
	//completeList = new ArrayList<TaskModel> ();
// Create the adapter to convert the array to views
	taskAdapterInComplete = new TaskAdapter ( getApplication (), inCompleteList );
	listViewIncomplete = ( ListView ) findViewById ( R.id.listViewTaskInComplete );
	taskAdapterInComplete = setUpAdapterListView ( this,getApplicationContext (), listViewIncomplete, taskAdapterInComplete, false );

	toggleShowCompleteListView ();

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
	ImageView editTextStar = ( ImageView ) findViewById ( R.id.editTextStar );

	editTextStar.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			isStar = Utility.toggleImg ( v,
																	 getResources ().getDrawable ( R.mipmap.wl_task_detail_ribbon ),
																	 getResources ().getDrawable ( R.mipmap.wl_task_detail_ribbon_selected ) );

		}
	} );

	final EditText editText = ( EditText ) findViewById ( R.id.editText );
	editText.setHint ( "Add a to-do in \"" + title + "\"" );
	editText.setImeActionLabel ( "ADD", KeyEvent.KEYCODE_ENTER );
	editText.setOnKeyListener ( new View.OnKeyListener () {
		@Override public
		boolean onKey ( View v, int keyCode, KeyEvent event ) {
			if ( keyCode == KeyEvent.KEYCODE_ENTER && event.getAction () != KeyEvent.ACTION_DOWN ) {
				String title = editText.getText ().toString ();
				taskAdapterInComplete.addList ( new TaskModel ( title, isStar ), listViewIncomplete );
				QueryHelper.addTaskToDB ( getApplicationContext (),title,taskAdapterComplete,listViewComplete );
				editText.setText ( "" );
				View view = thisActivity.getCurrentFocus ();
				if ( view != null ) {
					InputMethodManager imm = ( InputMethodManager ) getSystemService ( Context.INPUT_METHOD_SERVICE );
					imm.hideSoftInputFromWindow ( view.getWindowToken (), 0 );
				}
			}
			return false;
		}
	} );

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

public static
TaskAdapter setUpAdapterListView ( Activity activity,Context context, ListView listView, TaskAdapter taskAdapter, boolean isComplete ) {

	listView.setAdapter ( taskAdapter );
	/*for ( int i = 0 ; i < 3 ; i++ ) {
		Log.d ( "loop", "" + i );
		TaskModel taskModel = new TaskModel ( "Dummy Task " + i + " " + isComplete );
		taskModel.setIsComplete ( isComplete );
		taskAdapter.insert ( taskModel, 0 );
	}*/

	Cursor c = QueryHelper.getTaskValueCursor ( context );
	c.moveToFirst ();

	Log.d ( "setUpAdapter", String.valueOf ( c.getCount () ) );
	List<ContentValues> allListValues = QueryHelper.getValuesFromCursor ( c, TaskColumns.ALL_COLUMNS );

// Attach the adapter to a ListView

	listView.setAdapter ( taskAdapter );
	for ( int i = 0 ; i < allListValues.size () ; i++ ) {

		ContentValues values = allListValues.get ( i );
		taskAdapter.add ( new TaskModel ( values.getAsString ( ListColumns._ID ), values.getAsString ( ListColumns.LIST_TITLE ) ) );
		Log.d ( "loop", " id=" + values.getAsInteger ( ListColumns._ID ) );
	}

	Utility.setListViewHeightBasedOnChildren ( listView );

	return taskAdapter;
}


void setUpContent () {
	Bundle extras = getIntent ().getExtras ();
	if ( extras != null ) {
		title = extras.getString ( ListConst.KEY_TITLE );
	}
}

@Override
public
boolean onCreateOptionsMenu ( Menu menu ) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater ().inflate ( R.menu.menu_task_detail, menu );
	return true;
}


@Override
public
boolean onOptionsItemSelected ( MenuItem item ) {
	// Handle action bar item clicks here. The action bar will
	// automatically handle clicks on the Home/Up button, so long
	// as you specify a parent activity in AndroidManifest.xml.
	int id = item.getItemId ();

	//noinspection SimplifiableIfStatement
	if ( id == R.id.action_settings ) {
		return true;
	}

	return super.onOptionsItemSelected ( item );
}
}
