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

import com.vi8e.um.wunderlist.Model.SubTaskModel;
import com.vi8e.um.wunderlist.Model.TaskModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.adapters.SubTaskAdapter;
import com.vi8e.um.wunderlist.dialogs.CustomDialog;
import com.vi8e.um.wunderlist.dialogs.ReminderDialog;
import com.vi8e.um.wunderlist.provider.subtask.SubtaskColumns;
import com.vi8e.um.wunderlist.provider.subtask.SubtaskSelection;
import com.vi8e.um.wunderlist.provider.task.TaskColumns;
import com.vi8e.um.wunderlist.utils.IntentCaller;
import com.vi8e.um.wunderlist.utils.QueryHelper;
import com.vi8e.um.wunderlist.utils.UiMng;
import com.vi8e.um.wunderlist.utils.Utility;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public
class TaskDetailActivity extends AppCompatActivity {
private static final String TAG = TaskDetailActivity.class.getSimpleName ();
public static ListView       listViewSubTask;
public static Activity       thisActivity;
public static Context        sContext;
public static SubTaskAdapter subTaskAdapter;
public static
android.support.v4.app.FragmentManager sFragmentManager;

Boolean isStar       = false;
Boolean showComplete = true;

EditText editTextTitle,editTextBottom;
public static TaskModel currentTask;
RelativeLayout noteLayout;
ImageView      star, checkBoxTask;
TextView       noteEditText;
RelativeLayout addSubTask;
RelativeLayout calendarLayout;
RelativeLayout bottomRoot;
public static TextView reminderText;


@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_task_detail );
	sContext = getApplicationContext ();
	thisActivity = this;
	currentTask = TaskActivity.currentTask;

	sFragmentManager = getSupportFragmentManager ();

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
	subTaskAdapter.setNotifyOnChange ( true );
	Utility.setTaskListViewHeight ( listViewSubTask );
}


public static
void setTextViewReminderFromTaskDB ( TaskModel taskModel, TextView reminderText, Context context ) {
	String reminderTime = taskModel.getReminderDate ();
	Log.d ( TAG, "reminderTime= " + reminderTime );
	if ( reminderTime != null && ! reminderTime.isEmpty () ) {
		Date date = new Date ();
		date.setTime ( Long.parseLong ( reminderTime ) );
		ReminderDialog.setTextViewReminder ( date, reminderText, context );
	}
	else {
		reminderText.setText ( context.getResources ().getString ( R.string.reminder_text_task_detail ));
		UiMng.setBlackText ( context,reminderText );
	}
}

public static
SubTaskAdapter setUpAdapterListView ( Activity activity, ListView listView, SubTaskAdapter subTaskAdapter, Context context ) {
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
	subTaskAdapter = new SubTaskAdapter ( context, arrayOfList );
	listView.setAdapter ( subTaskAdapter );
	for ( int i = 0 ; i < allListValues.size () ; i++ ) {
		ContentValues values = allListValues.get ( i );
		subTaskAdapter.add ( new SubTaskModel ( values.getAsString ( SubtaskColumns.SUBTASK_TITLE ),
		                                           values.getAsString ( SubtaskColumns.TASKID ),
		                                           values.getAsString ( SubtaskColumns._ID ),
		                                           values.getAsString ( SubtaskColumns.ISCOMPLETE ) ) );
	}

	Utility.setTaskListViewHeight ( listViewSubTask );

	return subTaskAdapter;
}

private
void setView () {
	Log.d ( "","setView" );
	listViewSubTask = ( ListView ) findViewById ( R.id.listViewTaskInComplete );
	checkBoxTask = ( ImageView ) findViewById ( R.id.chkBox );
	editTextTitle = ( EditText ) findViewById ( R.id.editTextTitle );
	star = ( ImageView ) findViewById ( R.id.star );
	noteEditText = ( TextView ) findViewById ( R.id.noteEdittext );
	noteLayout = ( RelativeLayout ) findViewById ( R.id.noteLayout );
	addSubTask = ( RelativeLayout ) findViewById ( R.id.addSubTask );
	calendarLayout = ( RelativeLayout ) findViewById ( R.id.calendatLayout );
	reminderText = (TextView)findViewById ( R.id.reminder_text_taskDetail );
  bottomRoot =(RelativeLayout)findViewById ( R.id.bottomBarRoot );
	editTextBottom=(EditText)findViewById ( R.id.editTextBottom );
}

private
void setViewValues () {

	editTextTitle.setText ( TaskActivity.currentTask.getTitle () );
	currentTask.setIsStar ( String.valueOf ( ! currentTask.isStar () ) );
	noteEditText.setText ( String.valueOf ( currentTask.getNote () ) );
	noteLayout.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			IntentCaller.taskNoteActivity ( getApplicationContext (), currentTask );
		}
	} );
	currentTask.setIsComplete ( String.valueOf ( ! currentTask.isComplete () ) );

	setTextViewReminderFromTaskDB ( currentTask, reminderText, sContext );

	calendarLayout.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			ReminderDialog.showReminderDialog ( thisActivity, listViewSubTask, sContext );
		}
	} );

	Utility.toggleImgCompleteData ( checkBoxTask, currentTask, getApplicationContext () );
	checkBoxTask.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			Utility.toggleImgCompleteData ( v, currentTask, getApplicationContext () );
		}
	} );
	Utility.toggleImgStarData ( star, currentTask, getApplicationContext () );
	star.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			Utility.toggleImgStarData ( v, currentTask, getApplicationContext () );
		}
	} );

	addSubTask.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			Log.d ( "","addSubTask" );
			CustomDialog.showAddSubTaskDialog ( thisActivity, subTaskAdapter, listViewSubTask );
		}
	} );
	subTaskAdapter = setUpAdapterListView ( this, listViewSubTask, subTaskAdapter, getApplicationContext () );

	editTextBottom.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			IntentCaller.commentActivity ( TaskDetailActivity.this.getApplicationContext () );
		}
	} );


	bottomRoot.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View view ) {
			IntentCaller.commentActivity ( TaskDetailActivity.this.getApplicationContext () );
		}
	} );


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
	saveSubTaskAdapterToDb ();

}

private
void saveSubTaskAdapterToDb () {
	for ( int i = 0 ; i < subTaskAdapter.getCount () ; i++ ) {
		//ListModel recordData = mLandingListAdapter.getArrayList ().get ( i );
		SubTaskModel recordData = subTaskAdapter.getItem ( i );
		String id = recordData.getId ();
		Uri uri = Uri.parse ( String.valueOf ( SubtaskColumns.CONTENT_URI ) + "/" + id );
		//try {
		getContentResolver ().update ( uri, recordData.getValues (), null, null );

		/*catch ( IllegalArgumentException e ) {
			Log.e ( "errorOnUpdateData", e.getMessage () );
			uri = getContentResolver ().insert ( ListColumns.CONTENT_URI, recordData.getValues () );
			Log.d ( "ChkColumn ", "title" + recordData.getTitle () + "newId=" + uri.getPathSegments ().get ( 1 ) );
		}*/
	}
}

@Override
protected
void
onResume () {
	super.onResume ();
	Log.d ( "OnResume", "" );
	setView ();
	setViewValues ();
	setUpAdapterListView ();
}

public static
SubTaskAdapter setUpAdapterListView () {
	return setUpAdapterListView ( thisActivity, listViewSubTask, subTaskAdapter, sContext );
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
