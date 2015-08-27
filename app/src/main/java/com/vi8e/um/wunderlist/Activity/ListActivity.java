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
import android.widget.EditText;
import android.widget.ListView;

import com.vi8e.um.wunderlist.Model.ListConst;
import com.vi8e.um.wunderlist.Model.TaskModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.adater.TaskAdapter;
import com.vi8e.um.wunderlist.util.Utility;

import java.util.ArrayList;


public
class ListActivity extends AppCompatActivity {

String title;


Toolbar               toolbar;
DrawerLayout          drawerLayout;
ActionBarDrawerToggle drawerToggle;
TaskAdapter           mTaskAdapter;
CoordinatorLayout     rootLayout;
FloatingActionButton  fabBtn;
ListView listView;

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_list );
	setUpContent ();
	setView ();
	listView = ( ListView ) findViewById ( R.id.listViewLanding );
	mTaskAdapter = setUpAdapterListView ( this, getApplication (), listView, mTaskAdapter );

}

void setView () {
	final EditText editText = ( EditText ) findViewById ( R.id.editText );
	editText.setHint ( "Add a to-do in \"" + title + "\"" );
	editText.setImeActionLabel ( "ADD", KeyEvent.KEYCODE_ENTER );
	editText.setOnKeyListener ( new View.OnKeyListener () {
		@Override public
		boolean onKey ( View v, int keyCode, KeyEvent event ) {
			if(keyCode==KeyEvent.KEYCODE_ENTER){
				mTaskAdapter.addList ( new TaskModel ( editText.getText ().toString () ), listView);
			}
			return false;
		}
	} );

}

public static
TaskAdapter setUpAdapterListView ( Activity activity, Context context, ListView listView, TaskAdapter taskAdapter ) {

	ArrayList<TaskModel> arrayOfList = new ArrayList<TaskModel> ();
// Create the adapter to convert the array to views
	taskAdapter = new TaskAdapter ( activity, arrayOfList );
// Attach the adapter to a ListView

	listView.setAdapter ( taskAdapter );
	for ( int i = 0 ; i < 3 ; i++ ) {
		Log.d ( "loop", "" + i );
		taskAdapter.add ( new TaskModel ( i, "Dummy Task" + i ) );
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
