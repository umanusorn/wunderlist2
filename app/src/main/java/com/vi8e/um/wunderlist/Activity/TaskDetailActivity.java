package com.vi8e.um.wunderlist.Activity;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.vi8e.um.wunderlist.Model.TaskModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.adater.TaskDetailAdapter;
import com.vi8e.um.wunderlist.provider.task.TaskColumns;
import com.vi8e.um.wunderlist.util.Utility;

import java.util.ArrayList;


public
class TaskDetailActivity extends AppCompatActivity {

private ListView          listViewComplete;
private Activity          thisActivity;
private TaskDetailAdapter taskAdapterComplete;

EditText editTextTitle;

ImageView star,checkBox;

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_task_detail );





	thisActivity = this;

	getWindow ().setSoftInputMode (
			WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
																);
	View view = this.getCurrentFocus ();
	if ( view != null ) {
		InputMethodManager imm = ( InputMethodManager ) getSystemService ( Context.INPUT_METHOD_SERVICE );
		imm.hideSoftInputFromWindow ( view.getWindowToken (), 0 );
	}

	listViewComplete = ( ListView ) findViewById ( R.id.listViewTaskInComplete );
	ArrayList<TaskModel> completeList = new ArrayList<TaskModel> ();
	taskAdapterComplete = new TaskDetailAdapter ( getApplication (), completeList );
	taskAdapterComplete = setUpAdapterListView ( this, listViewComplete,taskAdapterComplete, false );


	checkBox =(ImageView)findViewById ( R.id.chkBox );
	editTextTitle =(EditText)findViewById ( R.id.editTextTitle );
	star = (ImageView)findViewById ( R.id.star );

	editTextTitle.setText ( TaskActivity.currentTask.getListTitle () );



}

@Override
protected
void onPause () {
	super.onPause ();
	//setMenuNormal ();

	//ListModel currentList= LandingActivity.currentList;
	TaskModel currentTask = TaskActivity.currentTask;
	currentTask.setListTitle ( editTextTitle.getText ().toString () );
	String id = currentTask.getId ();
	Uri uri = Uri.parse ( String.valueOf ( TaskColumns.CONTENT_URI ) + "/" + id );
	getContentResolver ().update ( uri, currentTask.getValues (), null, null );

}

public static
TaskDetailAdapter setUpAdapterListView ( Activity activity, ListView listView, TaskDetailAdapter taskAdapter, boolean isComplete ) {

/*	list= new ArrayList<TaskModel> ();
	//completeList = new ArrayList<TaskModel> ();
// Create the adapter to convert the array to views
	taskAdapter = new TaskAdapter ( activity, list );
// Attach the adapter to a ListView*/

	listView.setAdapter ( taskAdapter );
	for ( int i = 0 ; i < 3 ; i++ ) {
		Log.d ( "loop", "" + i );
		TaskModel taskModel = new TaskModel ( "Dummy", String.valueOf (false ), String.valueOf ( false),"0" );
		taskModel.setIsComplete ( String.valueOf ( isComplete ) );
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
