package com.vi8e.um.wunderlist.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
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
import com.vi8e.um.wunderlist.adater.LandingListAdapter;
import com.vi8e.um.wunderlist.provider.list.ListColumns;
import com.vi8e.um.wunderlist.provider.list.ListContentValues;
import com.vi8e.um.wunderlist.util.CustomDialog;
import com.vi8e.um.wunderlist.util.QueryHelper;
import com.vi8e.um.wunderlist.util.Utility;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;


public
class LandingActivity extends AppCompatActivity {


private static final String TAG = LandingActivity.class.getSimpleName ();
Toolbar                 toolbar;
CollapsingToolbarLayout collapsingToolbarLayout;

DrawerLayout          drawerLayout;
ActionBarDrawerToggle drawerToggle;
LandingListAdapter    mLandingListAdapter;
CoordinatorLayout     rootLayout;
FloatingActionButton  fabBtn;
ListView              listView;

Activity thisActivity;

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	Fabric.with ( this, new Crashlytics () );
	setContentView ( R.layout.activity_main );
	thisActivity = this;
	listView = ( ListView ) findViewById ( R.id.listViewTaskInComplete );


	initToolbar ();
	initInstances ();

	mLandingListAdapter = setUpAdapterListView ( thisActivity, getApplication (), listView, mLandingListAdapter );
	setFloatingActionBtnClickListener ( getWindow ().getDecorView ().findViewById ( android.R.id.content ), listView, mLandingListAdapter );

	/*for ( int i = 0 ; i < 1 ; i++ ) {
		addToDB ( getApplication (),"tssd",mLandingListAdapter, listView );
	}*/
}

@Override
protected
void onPause () {
	super.onPause ();
	Log.d ( "Main", "EnterOnPause dataCount" + mLandingListAdapter.getCount () );
	for ( int i = 0 ; i < mLandingListAdapter.getCount () ; i++ ) {
		ListModel recordData = mLandingListAdapter.getArrayList ().get ( i );
		String id = recordData.getId ();
		Uri uri = Uri.parse ( String.valueOf ( ListColumns.CONTENT_URI ) + "/" + id );
		try {
			getContentResolver ().update ( uri, recordData.getValues (), null, null );
		}
		catch ( IllegalArgumentException e ) {
			Log.e ( "errorOnAddData", e.getMessage () );

			String title = recordData.getListTitle ();
			ListContentValues values = new ListContentValues ();
			values.putListTitle ( title );
			ListModel listModel = new ListModel ( title );
			uri = getContentResolver ().insert ( ListColumns.CONTENT_URI, listModel.getValues () );
			Log.d ( "ChkColumn ", "title" + title + "newId=" + uri.getPathSegments ().get ( 1 ) );
			//getContentResolver().insert ( ListColumns.CONTENT_URI, recordData.getValues () );
		}
	}
}

public static
LandingListAdapter setUpAdapterListView ( Activity activity, Context context, ListView listView, LandingListAdapter landingListAdapter ) {


	Cursor c = QueryHelper.getListValueCursor ( context );
	c.moveToFirst ();

	Log.d ( "setUpAdapter", String.valueOf ( c.getCount () ) );
	List<ContentValues> allListValues = QueryHelper.getListValuesFromCursor ( c );

	ArrayList<ListModel> arrayOfList = new ArrayList<ListModel> ();
	landingListAdapter = new LandingListAdapter ( activity, arrayOfList );
// Attach the adapter to a ListView

	listView.setAdapter ( landingListAdapter );
	for ( int i = 0 ; i < allListValues.size () ; i++ ) {

		ContentValues values = allListValues.get ( i );
		landingListAdapter.add ( new ListModel ( values.getAsString ( ListColumns._ID ), values.getAsString ( ListColumns.LIST_TITLE ) ) );
		Log.d ( "loop", " id=" + values.getAsInteger ( ListColumns._ID ) );
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
	com.getbase.floatingactionbutton.FloatingActionButton toDoBtn = ( com.getbase.floatingactionbutton.FloatingActionButton ) view.findViewById ( R.id.action_b );

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
	drawerToggle = new ActionBarDrawerToggle ( LandingActivity.this, drawerLayout, R.string.hello_world, R.string.hello_world );
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

	int id = item.getItemId ();

	//noinspection SimplifiableIfStatement
	if ( id == R.id.action_settings ) {
		return true;
	}

	return super.onOptionsItemSelected ( item );
}
}
