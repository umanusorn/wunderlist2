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
import com.vi8e.um.wunderlist.adater.TaskDetailAdapter2;
import com.vi8e.um.wunderlist.provider.task.TaskColumns;
import com.vi8e.um.wunderlist.util.Utility;

import java.util.ArrayList;


public
class TaskDetailActivity extends AppCompatActivity {

private ListView           listViewComplete;
private Activity           thisActivity;
private TaskDetailAdapter2 taskAdapterComplete;

EditText editTextTitle;

Boolean isStar       = false;
Boolean showComplete = true;
TaskModel mTaskModel;

ImageView star, checkBox;

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_task_detail );

	thisActivity = this;
	mTaskModel = TaskActivity.currentTask;

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
	taskAdapterComplete = new TaskDetailAdapter2 ( getApplication (), completeList );
	taskAdapterComplete = setUpAdapterListView ( this, listViewComplete, taskAdapterComplete, false );


	checkBox = ( ImageView ) findViewById ( R.id.chkBox );
	editTextTitle = ( EditText ) findViewById ( R.id.editTextTitle );
	star = ( ImageView ) findViewById ( R.id.star );

	mTaskModel.setIsStar ( String.valueOf ( !mTaskModel.isStar () ) );
	Utility.toggleImgStarData ( star, mTaskModel, getApplicationContext () );
	star.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			Utility.toggleImgStarData ( v, mTaskModel, getApplicationContext () );
		}
	} );

	mTaskModel.setIsComplete ( String.valueOf ( ! mTaskModel.isComplete () ) );
	Utility.toggleImgCompleteData ( checkBox, mTaskModel, getApplicationContext () );
	checkBox.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			Utility.toggleImgCompleteData ( v, mTaskModel, getApplicationContext () );
		}
	} );


	editTextTitle.setText ( TaskActivity.currentTask.getTitle () );





}

@Override
protected
void onPause () {
	super.onPause ();
	//setMenuNormal ();

	//ListModel currentList= LandingActivity.currentList;
	TaskModel currentTask = TaskActivity.currentTask;
	currentTask.setTitle ( editTextTitle.getText ().toString () );
	String id = currentTask.getId ();
	Uri uri = Uri.parse ( String.valueOf ( TaskColumns.CONTENT_URI ) + "/" + id );
	getContentResolver ().update ( uri, currentTask.getValues (), null, null );

}

public static
TaskDetailAdapter2 setUpAdapterListView ( Activity activity, ListView listView, TaskDetailAdapter2 taskDetailAdapter, boolean isComplete ) {

	listView.setAdapter ( taskDetailAdapter );
	for ( int i = 0 ; i < 3 ; i++ ) {
		Log.d ( "loop", "" + i );
		TaskModel taskModel = new TaskModel ( "Dummy", String.valueOf ( false ), String.valueOf ( false ), "0",System.currentTimeMillis () );
		taskModel.setIsComplete ( String.valueOf ( isComplete ) );
		taskDetailAdapter.insert ( taskModel, 0 );
	}
	Utility.setTaskListViewHeight ( listView );
	return taskDetailAdapter;
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
