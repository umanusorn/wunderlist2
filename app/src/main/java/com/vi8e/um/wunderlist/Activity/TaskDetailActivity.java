package com.vi8e.um.wunderlist.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.adater.TaskAdapter;


public
class TaskDetailActivity extends AppCompatActivity {

private ListView       listViewComplete;
private Activity       thisActivity;
private TaskAdapter taskAdapterComplete;

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_task_detail );


	thisActivity = this;
/*	listViewComplete = ( ListView ) findViewById ( R.id.listViewTaskComplete );
	ArrayList<TaskModel> completeList = new ArrayList<TaskModel> ();
	taskAdapterComplete = new TaskAdapter ( getApplication (), completeList );
	taskAdapterComplete = TaskActivity.setUpAdapterListView ( this, listViewComplete, taskAdapterComplete, true );*/
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
