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
import android.support.v7.app.ActionBar;
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
import com.vi8e.um.wunderlist.provider.list.ListSelection;
import com.vi8e.um.wunderlist.util.CustomDialog;
import com.vi8e.um.wunderlist.util.IntentCaller;
import com.vi8e.um.wunderlist.util.QueryHelper;
import com.vi8e.um.wunderlist.util.Utility;

import java.util.ArrayList;
import java.util.List;

import io.fabric.sdk.android.Fabric;


public
class LandingActivity extends AppCompatActivity {


private static final String TAG = LandingActivity.class.getSimpleName ();
static Toolbar toolbar;
static Context sContext;
CollapsingToolbarLayout collapsingToolbarLayout;

public static ListModel currentList;
DrawerLayout          drawerLayout;
ActionBarDrawerToggle drawerToggle;
static ActionBar          mActionBar;
public static LandingListAdapter mLandingListAdapter;
CoordinatorLayout    rootLayout;
FloatingActionButton fabBtn;
ListView             listView;
static long listId;


static        Activity thisActivity;
static        Menu     menu;
public static int      currentListPosition;
private boolean mIsLongClick;

public
boolean isLongClick () {
	return mIsLongClick;
}

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	Fabric.with ( this, new Crashlytics () );
	setContentView ( R.layout.activity_landing );
	thisActivity = this;
	listView = ( ListView ) findViewById ( R.id.listViewTaskInComplete );

	initToolbar ();
	initInstances ();

	mLandingListAdapter = setUpAdapterListView ( thisActivity, getApplication (), listView, mLandingListAdapter );
	setFloatingActionBtnClickListener ( getWindow ().getDecorView ().findViewById ( android.R.id.content ), listView, mLandingListAdapter );

}

public static
LandingListAdapter setUpAdapterListView ( Activity activity, Context context, ListView listView, LandingListAdapter landingListAdapter ) {

	Cursor c = QueryHelper.getListValueCursor ( context );
	c.moveToFirst ();

	Log.d ( "setUpAdapter", String.valueOf ( c.getCount () ) );
	List<ContentValues> allListValues = QueryHelper.getValuesFromCursor ( c, ListColumns.ALL_COLUMNS );
	ArrayList<ListModel> arrayOfList = new ArrayList<ListModel> ();

	landingListAdapter = new LandingListAdapter ( activity, arrayOfList );

// Attach the adapter to a ListView
	listView.setAdapter ( landingListAdapter );
	for ( int i = 0 ; i < allListValues.size () ; i++ ) {
		ContentValues values = allListValues.get ( i );
		landingListAdapter.add ( new ListModel ( values.getAsString ( ListColumns._ID ), values.getAsString ( ListColumns.LIST_TITLE ) ) );
		Log.d ( "loop", " id=" + values.getAsInteger ( ListColumns._ID ) );
	}

	Utility.setTaskListViewHeight ( listView );

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
	com.getbase.floatingactionbutton.FloatingActionButton newListBtn
			= ( com.getbase.floatingactionbutton.FloatingActionButton ) view.findViewById ( R.id.action_a );
	com.getbase.floatingactionbutton.FloatingActionButton toDoBtn = ( com.getbase.floatingactionbutton.FloatingActionButton ) view.findViewById ( R.id
			                                                                                                                                              .action_b );

	newListBtn.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			CustomDialog.showEditTextDialog ( thisActivity, landingListAdapter, listView );
		}
	} );
	toDoBtn.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			CustomDialog.showEditTextDialog ( thisActivity, landingListAdapter, listView );
		}
	} );
}

private
void initToolbar () {
	toolbar = ( Toolbar ) thisActivity.findViewById ( R.id.toolbar );
	setSupportActionBar ( toolbar );
	mActionBar = getSupportActionBar ();
	toolbar.setVisibility ( View.VISIBLE );
}

private
void initInstances () {
	drawerLayout = ( DrawerLayout ) findViewById ( R.id.drawerLayout );
	drawerToggle = new ActionBarDrawerToggle ( LandingActivity.this, drawerLayout, R.string.hello_world, R.string.hello_world );
	drawerLayout.setDrawerListener ( drawerToggle );

	//	getSupportActionBar().setHomeButtonEnabled(true);
	//getSupportActionBar().setDisplayHomeAsUpEnabled(true);

	rootLayout = ( CoordinatorLayout ) findViewById ( R.id.rootLayout );
	collapsingToolbarLayout = ( CollapsingToolbarLayout ) findViewById ( R.id.collapsingToolbarLayout );
	collapsingToolbarLayout.setTitle ( "" + Utility.getVersionName ( getApplication () ) );

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
protected
void onPause () {
	super.onPause ();
	setMenuNormal ();

	Log.d ( "Main", "EnterOnPause dataCount" + mLandingListAdapter.getCount () );
	adapterToDb ();
}

private
void adapterToDb () {
	for ( int i = 0 ; i < mLandingListAdapter.getCount () ; i++ ) {
		ListModel recordData = mLandingListAdapter.getArrayList ().get ( i );
		String id = recordData.getId ();
		Uri uri = Uri.parse ( String.valueOf ( ListColumns.CONTENT_URI ) + "/" + id );
		try {
			getContentResolver ().update ( uri, recordData.getValues (), null, null );
		}
		catch ( IllegalArgumentException e ) {
			Log.e ( "errorOnUpdateData", e.getMessage () );
			uri = getContentResolver ().insert ( ListColumns.CONTENT_URI, recordData.getValues () );
			Log.d ( "ChkColumn ", "title" + recordData.getListTitle () + "newId=" + uri.getPathSegments ().get ( 1 ) );
		}
	}
}

@Override
		protected void
onResume(){
super.onResume ();
	Log.d ( "OnResume","" );
	mLandingListAdapter = setUpAdapterListView ( thisActivity, getApplication (), listView, mLandingListAdapter );

}

public static
void setMenuNormal () {
	menu.clear ();
	thisActivity.getMenuInflater ().inflate ( R.menu.menu_main_normal, menu );

//	getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//	mActionBar.setBackgroundDrawable ( new ColorDrawable ( sContext.getResources ().getColor ( R.color.transparent ) ) );

}

public static
void setMenuList () {
	menu.clear ();
	thisActivity.getMenuInflater ().inflate ( R.menu.menu_main_list_toggle, menu );
//	mActionBar.setBackgroundDrawable ( new ColorDrawable (sContext.getResources ().getColor ( R.color.blue_300 )) );

}

@Override
public
boolean onCreateOptionsMenu ( Menu menu ) {
	// Inflate the menu; this adds items to the action bar if it is present.
	this.menu = menu;
	setMenuNormal ();

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
	if ( id == R.id.menu_setting ) {
		IntentCaller.developer ( thisActivity );
		return true;
	}
	if ( id == R.id.delete ) {
		ListSelection where = new ListSelection ();
		where.id ( Long.parseLong ( currentList.getId () ) );
		where.delete ( getApplicationContext () );
		mLandingListAdapter.remove ( currentList );
	}

	if ( id == R.id.menu_edit ) {
		/*ListSelection where = new ListSelection ();
		where.id ( Long.parseLong ( currentList.getId () ) );*/
		IntentCaller.listDetailActivity ( getApplicationContext (),currentList );
	}

	setMenuNormal ();

	return super.onOptionsItemSelected ( item );
}

}
