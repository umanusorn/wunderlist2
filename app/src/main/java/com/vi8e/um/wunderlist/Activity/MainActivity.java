package com.vi8e.um.wunderlist.Activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.crashlytics.android.Crashlytics;
import com.vi8e.um.wunderlist.Model.ListModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.adater.ListAdapter;
import com.vi8e.um.wunderlist.util.CustomDialog;
import com.vi8e.um.wunderlist.util.Utility;

import java.util.ArrayList;

import io.fabric.sdk.android.Fabric;


public
class MainActivity extends AppCompatActivity {


Toolbar toolbar;
CollapsingToolbarLayout collapsingToolbarLayout;

DrawerLayout drawerLayout;
ActionBarDrawerToggle drawerToggle;

CoordinatorLayout rootLayout;
FloatingActionButton fabBtn;
ListAdapter listAdapter;

Activity thisActivity;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate ( savedInstanceState );
	//toolbar.setBackgroundColor ( Color.alpha ( 0 ) );
	Fabric.with ( this, new Crashlytics () );
	setContentView ( R.layout.activity_main );
	thisActivity=this;
	ListView listView = ( ListView ) findViewById(R.id.listViewLanding );
	initToolbar ();
	initInstances ();
	setFloatingActionBtnClickListener ( getWindow ().getDecorView ().findViewById ( android.R.id.content ),listView );


	//Utility.setDrawbleColorFilter ();

	ArrayList<ListModel> arrayOfList = new ArrayList<ListModel> ();
// Create the adapter to convert the array to views
	listAdapter = new ListAdapter (this, arrayOfList);
// Attach the adapter to a ListView

	listView.setAdapter ( listAdapter );
arrayOfList.add ( new ListModel ( "dssdf" ) );

	for ( int i = 0 ; i < 3 ; i++ ) {
		Log.d ("loop",""+i);
		listAdapter.add ( new ListModel ( i, "TestingListViews" + i ) );
	}
	listAdapter.add ( new ListModel ( "dssdf" ) );
	Utility.setListViewHeightBasedOnChildren ( listView );


// Or even append an entire new collection
// Fetching some data, data has now returned
// If data was JSON, convert to ArrayList of User objects.
	/*JSONArray jsonArray = ...;
	ArrayList<User> newUsers = User.fromJson(jsonArray)
	adapter.addAll(newUsers);*/

}

private void setFloatingActionBtnClickListener ( View view, final ListView listView ){
	com.getbase.floatingactionbutton.FloatingActionButton newListBtn = ( com.getbase.floatingactionbutton.FloatingActionButton ) view.findViewById(R.id.action_a);
	com.getbase.floatingactionbutton.FloatingActionButton toDoBtn = ( com.getbase.floatingactionbutton.FloatingActionButton ) view.findViewById(R.id.action_b);
	newListBtn.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			CustomDialog.showPassCodeChangeOrTurnOff ( thisActivity,listAdapter,listView );
		}
	} );
	toDoBtn.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			CustomDialog.showPassCodeChangeOrTurnOff (thisActivity,listAdapter,listView );
		}
	} );
}

private void initToolbar() {
	//toolbar = (Toolbar) findViewById(R.id.toolbar);
//	setSupportActionBar(toolbar);
}

private void initInstances() {
	drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
	drawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.hello_world, R.string.hello_world);
	drawerLayout.setDrawerListener ( drawerToggle );

	/*toolbar = (Toolbar) findViewById(R.id.toolbar);
	setSupportActionBar(toolbar);
	toolbar.setVisibility ( View.VISIBLE );*/
	//	getSupportActionBar().setHomeButtonEnabled(true);
	//getSupportActionBar().setDisplayHomeAsUpEnabled(true);


	rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout);

	collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
	collapsingToolbarLayout.setTitle ( "User Profile" );


}

@Override
public void onPostCreate(Bundle savedInstanceState) {
	super.onPostCreate(savedInstanceState);
	drawerToggle.syncState ();
}

@Override
public void onConfigurationChanged(Configuration newConfig) {
	super.onConfigurationChanged(newConfig);
	drawerToggle.onConfigurationChanged ( newConfig );
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.menu_main, menu);
	return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
	if (drawerToggle.onOptionsItemSelected(item))
		return true;

	// Handle action bar item clicks here. The action bar will
	// automatically handle clicks on the Home/Up button, so long
	// as you specify a parent activity in AndroidManifest.xml.
	int id = item.getItemId();

	//noinspection SimplifiableIfStatement
	if (id == R.id.action_settings) {
		return true;
	}

	return super.onOptionsItemSelected(item);
}
}
