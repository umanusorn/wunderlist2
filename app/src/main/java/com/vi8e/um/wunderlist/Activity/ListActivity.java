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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.vi8e.um.wunderlist.Model.ListConst;
import com.vi8e.um.wunderlist.Model.ListModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.adater.ListAdapter;
import com.vi8e.um.wunderlist.util.Utility;

import java.util.ArrayList;


public
class ListActivity extends AppCompatActivity {

String title;


Toolbar                 toolbar;
DrawerLayout          drawerLayout;
ActionBarDrawerToggle drawerToggle;
ListAdapter    listAdapter;
CoordinatorLayout     rootLayout;
FloatingActionButton  fabBtn;

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_list );
	setUpContent ();
	setView ();
	ListView listView = ( ListView ) findViewById ( R.id.listViewLanding );
	listAdapter=setUpAdapterListView ( this, getApplication (),listView, listAdapter);
}

void setView () {
	EditText editText = ( EditText ) findViewById ( R.id.editText );
	editText.setHint ( "Add a to-do in \"" + title + "\"" );


}

public static
ListAdapter setUpAdapterListView ( Activity activity, Context context, ListView listView, ListAdapter listAdapter ) {

	ArrayList<ListModel> arrayOfList = new ArrayList<ListModel> ();
// Create the adapter to convert the array to views
	listAdapter = new ListAdapter ( activity, arrayOfList );
// Attach the adapter to a ListView

	listView.setAdapter ( listAdapter );
	for ( int i = 0 ; i < 3 ; i++ ) {
		Log.d ( "loop", "" + i );
		listAdapter.add ( new ListModel ( i, "Dummy Task" + i ) );
	}
	Utility.setListViewHeightBasedOnChildren ( listView );

// Or even append an entire new collection
// Fetching some data, data has now returned
// If data was JSON, convert to ArrayList of User objects.
	/*JSONArray jsonArray = ...;
	ArrayList<User> newUsers = User.fromJson(jsonArray)
	adapter.addAll(newUsers);*/
	return listAdapter;
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
