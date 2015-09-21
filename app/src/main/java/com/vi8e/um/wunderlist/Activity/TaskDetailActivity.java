package com.vi8e.um.wunderlist.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vi8e.um.wunderlist.Dialog.CustomDialog;
import com.vi8e.um.wunderlist.Model.SubTaskModel;
import com.vi8e.um.wunderlist.Model.TaskModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.adater.TaskDetailAdapter;
import com.vi8e.um.wunderlist.provider.subtask.SubtaskColumns;
import com.vi8e.um.wunderlist.provider.subtask.SubtaskSelection;
import com.vi8e.um.wunderlist.provider.task.TaskColumns;
import com.vi8e.um.wunderlist.util.IntentCaller;
import com.vi8e.um.wunderlist.util.QueryHelper;
import com.vi8e.um.wunderlist.util.Utility;

import java.util.ArrayList;
import java.util.List;


public
class TaskDetailActivity extends AppCompatActivity {
private static final String TAG = LandingActivity.class.getSimpleName ();
public static ListView          listViewComplete;
private       Activity          thisActivity;
public static Context sContext;
public static TaskDetailAdapter taskAdapterComplete;

EditText editTextTitle;

Boolean isStar       = false;
Boolean showComplete = true;
TaskModel      mTaskModel;
RelativeLayout noteLayout;

ImageView star, checkBox;
TextView       noteEditText;
RelativeLayout addSubTask;


@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_task_detail );
sContext=getApplicationContext ();
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

	setView ();

	setViewValues ();


}

private
void setViewValues () {

	editTextTitle.setText ( TaskActivity.currentTask.getTitle () );
	mTaskModel.setIsStar ( String.valueOf ( ! mTaskModel.isStar () ) );
	noteEditText.setText ( String.valueOf ( mTaskModel.getNote () ) );
	taskAdapterComplete = setUpAdapterListView ( this, listViewComplete, taskAdapterComplete, getApplicationContext () );

	noteLayout.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			IntentCaller.taskNoteActivity ( getApplicationContext (), mTaskModel );
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


	addSubTask.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			CustomDialog.showAddSubTaskDialog ( thisActivity, taskAdapterComplete, listViewComplete );
		}
	} );
}

public static
TaskDetailAdapter setUpAdapterListView ( Activity activity, ListView listView, TaskDetailAdapter taskDetailAdapter, Context context ) {

	//listView = ( DynamicListView ) activity.findViewById ( R.id.listViewTaskInComplete );

	SubtaskSelection where = new SubtaskSelection ();
	where.taskid ( TaskActivity.currentTask.getId () );
	Cursor c = where.query ( context.getContentResolver () );
	c.moveToFirst ();
	Log.d ( TAG, "setUpAdapter" + String.valueOf ( c.getCount () ) );

	c.moveToFirst ();

	Log.d ( "setUpAdapter", String.valueOf ( c.getCount () ) );
	List<ContentValues> allListValues = QueryHelper.getValuesFromCursor ( c, SubtaskColumns.ALL_COLUMNS );
	ArrayList<SubTaskModel> arrayOfList = new ArrayList<> ();

	//landingListAdapter = new LandingListAdapter ( activity, arrayOfList );
	taskDetailAdapter = new TaskDetailAdapter ( context, arrayOfList );
  listView.setAdapter ( taskDetailAdapter );
	for ( int i = 0 ; i < allListValues.size () ; i++ ) {
		ContentValues values = allListValues.get ( i );
		taskDetailAdapter.add ( new SubTaskModel ( values.getAsString ( SubtaskColumns.SUBTASK_TITLE ),
		                                           values.getAsString ( SubtaskColumns.TASKID ),
		                                           values.getAsString ( SubtaskColumns._ID ),
		                                           values.getAsString ( SubtaskColumns.ISCOMPLETE ) ) );
	}

	Utility.setTaskListViewHeight ( listView );
	return taskDetailAdapter;
}

private
void setView () {
	listViewComplete = ( ListView ) findViewById ( R.id.listViewTaskInComplete );
	checkBox = ( ImageView ) findViewById ( R.id.chkBox );
	editTextTitle = ( EditText ) findViewById ( R.id.editTextTitle );
	star = ( ImageView ) findViewById ( R.id.star );
	noteEditText = ( TextView ) findViewById ( R.id.noteEdittext );
	noteLayout = ( RelativeLayout ) findViewById ( R.id.noteLayout );
	addSubTask = ( RelativeLayout ) findViewById ( R.id.addSubTask );

}

@Override
protected
void onPause () {
	super.onPause ();
	TaskModel currentTask = TaskActivity.currentTask;
	currentTask.setTitle ( editTextTitle.getText ().toString () );
	String id = currentTask.getId ();
	Uri uri = Uri.parse ( String.valueOf ( TaskColumns.CONTENT_URI ) + "/" + id );
	getContentResolver ().update ( uri, currentTask.getValues (), null, null );

}

@Override
protected
void
onResume () {
	super.onResume ();
	Log.d ( "OnResume", "" );
	setView ();
	setViewValues ();
}

@Override
public
boolean onCreateOptionsMenu ( Menu menu ) {
	getMenuInflater ().inflate ( R.menu.menu_task_detail, menu );
	return true;
}

@Override
public
boolean onOptionsItemSelected ( MenuItem item ) {
	int id = item.getItemId ();

	if ( id == R.id.action_settings ) {
		return true;
	}
	return super.onOptionsItemSelected ( item );
}
}
