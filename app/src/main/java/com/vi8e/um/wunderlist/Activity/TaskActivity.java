package com.vi8e.um.wunderlist.Activity;

import android.app.Activity;
import android.content.Context;
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
import com.vi8e.um.wunderlist.util.Utility;

import java.util.ArrayList;


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
	setUpContent ();
	setView ();
	thisActivity = this;
	listViewComplete = ( ListView ) findViewById ( R.id.listViewTaskComplete );
	completeList = new ArrayList<TaskModel> ();
	//completeList = new ArrayList<TaskModel> ();
// Create the adapter to convert the array to views
	taskAdapterComplete = new TaskAdapter ( getApplication (), completeList );
	taskAdapterComplete = setUpAdapterListView ( this, listViewComplete, taskAdapterComplete, true );

	inCompleteList = new ArrayList<TaskModel> ();
	//completeList = new ArrayList<TaskModel> ();
// Create the adapter to convert the array to views
	taskAdapterInComplete = new TaskAdapter ( getApplication (), inCompleteList );
	listViewIncomplete = ( ListView ) findViewById ( R.id.listViewTaskInComplete );
	taskAdapterInComplete = setUpAdapterListView ( this, listViewIncomplete, taskAdapterInComplete,false );

	toggleShowCompleteListView ();

}

void toggleShowCompleteListView(){
	showComplete=!showComplete;
	if(showComplete){
		listViewComplete.setVisibility ( View.VISIBLE );
	}
	else {
		listViewComplete.setVisibility ( View.GONE );
	}
}



void setView () {

	TextView tvComplete = (TextView)findViewById ( R.id.tvComplete );
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
			if(keyCode==KeyEvent.KEYCODE_ENTER && event.getAction ()!=KeyEvent.ACTION_DOWN){
				taskAdapterInComplete.addList ( new TaskModel ( editText.getText ().toString (), isStar ), listViewIncomplete );
				editText.setText ( "" );
				View view = thisActivity.getCurrentFocus ();
				if (view != null) {
					InputMethodManager imm = (InputMethodManager )getSystemService( Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
				}
			}
			return false;
		}
	} );

}

public static
TaskAdapter setUpAdapterListView ( Activity activity, ListView listView, TaskAdapter taskAdapter, boolean isComplete ) {

/*	list= new ArrayList<TaskModel> ();
	//completeList = new ArrayList<TaskModel> ();
// Create the adapter to convert the array to views
	taskAdapter = new TaskAdapter ( activity, list );
// Attach the adapter to a ListView*/

	listView.setAdapter ( taskAdapter );
	for ( int i = 0 ; i < 3 ; i++ ) {
		Log.d ( "loop", "" + i );
		TaskModel taskModel = new TaskModel ( "Dummy Task " + i +" "+isComplete);
		taskModel.setIsComplete ( isComplete );
		taskAdapter.insert ( taskModel, 0 );
	}
	Utility.setListViewHeightBasedOnChildren ( listView );
// Or even append an entire new collection
// Fetching some data, data has now returned
// If data was JSON, convert to ArrayList of User objects.
	/*JSONArray jsonArray = ...;
	ArrayList<User> newUsers = User.fromJson(jsonArray)
	adapter.addAll(newUsers);*/
	return taskAdapter;
}


void setUpContent(){
	Bundle extras = getIntent().getExtras();
	if(extras !=null)
	{
		title= extras.getString( ListConst.KEY_TITLE);
	}
}

@Override
public
boolean onCreateOptionsMenu ( Menu menu ) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater ().inflate ( R.menu.menu_list, menu );
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
