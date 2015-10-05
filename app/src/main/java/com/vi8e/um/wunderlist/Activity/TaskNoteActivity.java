package com.vi8e.um.wunderlist.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.vi8e.um.wunderlist.Model.TaskModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.provider.task.TaskColumns;
import com.vi8e.um.wunderlist.utils.ActivityUi;


public
class TaskNoteActivity extends AppCompatActivity {
Toolbar  toolBar;
String   taskId;
String   note;
EditText listName;

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_task_note );


	Intent intent = getIntent ();
	Bundle bundle = intent.getExtras ();
	String title = bundle.getString ( TaskColumns.TASK_TITLE );
	taskId = bundle.getString ( TaskColumns._ID );
	note = bundle.getString ( TaskColumns.NOTE );

	listName = ( EditText ) findViewById ( R.id.listName );
	//listName.setImeActionLabel ( "NOTED", KeyEvent.KEYCODE_ENTER );
	ActivityUi.setToolBar ( this, toolBar, title  );
	listName.setText ( note );
}

@Override
public
boolean onCreateOptionsMenu ( Menu menu ) {
	getMenuInflater ().inflate ( R.menu.menu_task_note, menu );
	return true;
}

@Override
protected
void onPause () {
	super.onPause ();
	TaskModel currentTask=TaskActivity.currentTask;
	currentTask.setNote ( listName.getText ().toString () );
	String id = currentTask.getId ();
	Uri uri = Uri.parse ( String.valueOf ( TaskColumns.CONTENT_URI ) + "/" + id );
	getContentResolver ().update ( uri, currentTask.getValues (), null, null );
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
