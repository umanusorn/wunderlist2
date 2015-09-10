package com.vi8e.um.wunderlist.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.vi8e.um.wunderlist.Model.ListConst;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.util.ActivityUi;


public
class TaskNoteActivity extends AppCompatActivity {
Toolbar  toolBar;
String   listId;
EditText listName;

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_task_note );
	ActivityUi.setToolBar ( this, toolBar, "" );

	Intent intent = getIntent ();
	Bundle bundle = intent.getExtras ();
	String title = bundle.getString ( ListConst.KEY_TITLE );
	listId = bundle.getString ( ListConst.KEY_ID );
	listName = (EditText)findViewById ( R.id.listName );

	listName.setText ( title );
}

@Override
public
boolean onCreateOptionsMenu ( Menu menu ) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater ().inflate ( R.menu.menu_task_note, menu );
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
