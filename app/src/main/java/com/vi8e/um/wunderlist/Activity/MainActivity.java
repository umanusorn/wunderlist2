package com.vi8e.um.wunderlist.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
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
import com.vi8e.um.wunderlist.Model.QueryHelper;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.adater.LandingListAdapter;
import com.vi8e.um.wunderlist.provider.list.ListColumns;
import com.vi8e.um.wunderlist.provider.list.ListContentValues;
import com.vi8e.um.wunderlist.util.CustomDialog;
import com.vi8e.um.wunderlist.util.Utility;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;


public
class MainActivity extends AppCompatActivity {


Toolbar                 toolbar;
CollapsingToolbarLayout collapsingToolbarLayout;

DrawerLayout          drawerLayout;
ActionBarDrawerToggle drawerToggle;
LandingListAdapter    mLandingListAdapter;
CoordinatorLayout     rootLayout;
FloatingActionButton  fabBtn;


Activity thisActivity;

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	Fabric.with ( this, new Crashlytics () );
	setContentView ( R.layout.activity_main );
	thisActivity = this;
	ListView listView = ( ListView ) findViewById ( R.id.listViewTaskInComplete );
	mLandingListAdapter = setUpAdapterListView ( thisActivity, getApplication (), listView, mLandingListAdapter );

	initToolbar ();
	initInstances ();

	setFloatingActionBtnClickListener ( getWindow ().getDecorView ().findViewById ( android.R.id.content ), listView, mLandingListAdapter );

	//Utility.setDrawbleColorFilter ();

}

public static
LandingListAdapter setUpAdapterListView ( Activity activity, Context context, ListView listView, LandingListAdapter landingListAdapter ) {


	Cursor c = QueryHelper.getListValueCursor ( context);
	List<ContentValues> allListValues = QueryHelper.getListValuesFromCursor ( c );

	ArrayList<ListModel> arrayOfList = new ArrayList<ListModel> ();
// Create the adapter to convert the array to views
	landingListAdapter = new LandingListAdapter ( activity, arrayOfList );
// Attach the adapter to a ListView

	listView.setAdapter ( landingListAdapter );
	for ( int i = 0 ; i < allListValues.size () ; i++ ) {
		Log.d ( "loop", "" + i );
		landingListAdapter.add ( new ListModel ( i, allListValues.get ( i ).getAsString ( ListColumns.LIST_TITLE )) );
	}

	Utility.setListViewHeightBasedOnChildren ( listView );

// Or even append an entire new collection
// Fetching some data, data has now returned
// If data was JSON, convert to ArrayList of User objects.
	/*JSONArray jsonArray = ...;
	ArrayList<User> newUsers = User.fromJson(jsonArray)
	adapter.addAll(newUsers);*/
	return landingListAdapter;
}

private
void setFloatingActionBtnClickListener ( View view, final ListView listView, final LandingListAdapter landingListAdapter ) {
	com.getbase.floatingactionbutton.FloatingActionButton
			newListBtn
			= ( com.getbase.floatingactionbutton.FloatingActionButton ) view.findViewById ( R.id.action_a );
	com.getbase.floatingactionbutton.FloatingActionButton toDoBtn = ( com.getbase.floatingactionbutton.FloatingActionButton ) view.findViewById ( R.id
																																																																										.action_b );
	newListBtn.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			CustomDialog.showPassCodeChangeOrTurnOff ( thisActivity, landingListAdapter, listView );
		}
	} );
	toDoBtn.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			CustomDialog.showPassCodeChangeOrTurnOff ( thisActivity, landingListAdapter, listView );
		}
	} );
}

private
void initToolbar () {
	//toolbar = (Toolbar) findViewById(R.id.toolbar);
//	setSupportActionBar(toolbar);
}

private
void initInstances () {
	drawerLayout = ( DrawerLayout ) findViewById ( R.id.drawerLayout );
	drawerToggle = new ActionBarDrawerToggle ( MainActivity.this, drawerLayout, R.string.hello_world, R.string.hello_world );
	drawerLayout.setDrawerListener ( drawerToggle );

	toolbar = ( Toolbar ) findViewById ( R.id.toolbar );
	setSupportActionBar ( toolbar );
	toolbar.setVisibility ( View.VISIBLE );
	//	getSupportActionBar().setHomeButtonEnabled(true);
	//getSupportActionBar().setDisplayHomeAsUpEnabled(true);


	rootLayout = ( CoordinatorLayout ) findViewById ( R.id.rootLayout );

	collapsingToolbarLayout = ( CollapsingToolbarLayout ) findViewById ( R.id.collapsingToolbarLayout );

	collapsingToolbarLayout.setTitle ( "" + Utility.getVersionName ( getApplication () ) );

	//ContentValues values = new ContentValues (  );

	addToDB ( getApplication () );
	Intent intent = new Intent ( thisActivity, ViewDBActivity.class );
	startActivity ( intent );

}


public static
void addToDB ( Context context ) {

	Log.d ( "addToDb", "" );
	ListContentValues values = new ListContentValues ();
	values.putListTitle ( "Tes title" );
	context.getContentResolver ().insert ( ListColumns.CONTENT_URI, values.values () );
}

@Override
public
void onPostCreate ( Bundle savedInstanceState ) {
	super.onPostCreate ( savedInstanceState );
	drawerToggle.syncState ();
}

@Override
public
void onConfigurationChanged ( Configuration newConfig ) {
	super.onConfigurationChanged ( newConfig );
	drawerToggle.onConfigurationChanged ( newConfig );
}

@Override
public
boolean onCreateOptionsMenu ( Menu menu ) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater ().inflate ( R.menu.menu_main, menu );
	return true;
}

@Override
public
boolean onOptionsItemSelected ( MenuItem item ) {
	if ( drawerToggle.onOptionsItemSelected ( item ) ) {
		return true;
	}

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
