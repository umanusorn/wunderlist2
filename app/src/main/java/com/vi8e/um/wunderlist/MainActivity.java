package com.vi8e.um.wunderlist;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.crashlytics.android.Crashlytics;
import com.vi8e.um.wunderlist.Model.ListModel;
import com.vi8e.um.wunderlist.adater.ListAdapter;
import com.vi8e.um.wunderlist.util.CustomDialog;

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

Activity thisActivity;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	Fabric.with ( this, new Crashlytics () );
	setContentView ( R.layout.activity_main );
	thisActivity=this;
	initToolbar ();
	initInstances ();
	setFloatingActionBtnClickListener ( getWindow ().getDecorView ().findViewById ( android.R.id.content ) );


	ArrayList<ListModel> arrayOfUsers = new ArrayList<ListModel> ();
// Create the adapter to convert the array to views
	ListAdapter adapter = new ListAdapter (this, arrayOfUsers);
// Attach the adapter to a ListView
	ListView listView = (ListView ) findViewById(R.id.listViewLanding);
	listView.setAdapter ( adapter );



	ListModel newUser = new ListModel ("Nathan");
	adapter.add(newUser);

// Or even append an entire new collection
// Fetching some data, data has now returned
// If data was JSON, convert to ArrayList of User objects.
	/*JSONArray jsonArray = ...;
	ArrayList<User> newUsers = User.fromJson(jsonArray)
	adapter.addAll(newUsers);*/

}

private void setFloatingActionBtnClickListener ( View view ){
	com.getbase.floatingactionbutton.FloatingActionButton newListBtn = ( com.getbase.floatingactionbutton.FloatingActionButton ) view.findViewById(R.id.action_a);
	com.getbase.floatingactionbutton.FloatingActionButton toDoBtn = ( com.getbase.floatingactionbutton.FloatingActionButton ) view.findViewById(R.id.action_b);

	newListBtn.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			CustomDialog.showPassCodeChangeOrTurnOff ( thisActivity );
		}
	} );
	toDoBtn.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			CustomDialog.showPassCodeChangeOrTurnOff (thisActivity );
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

	getSupportActionBar().setHomeButtonEnabled(true);
	getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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
