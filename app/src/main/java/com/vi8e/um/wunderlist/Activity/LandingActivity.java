package com.vi8e.um.wunderlist.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.nhaarman.listviewanimations.ArrayAdapter;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.OnItemMovedListener;
import com.nhaarman.listviewanimations.itemmanipulation.dragdrop.TouchViewDraggableManager;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.OnDismissCallback;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.undo.SimpleSwipeUndoAdapter;
import com.vi8e.um.wunderlist.Dialog.CustomDialog;
import com.vi8e.um.wunderlist.Model.ListModel;
import com.vi8e.um.wunderlist.Model.TaskModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.adater.LandingListAdapter;
import com.vi8e.um.wunderlist.provider.list.ListColumns;
import com.vi8e.um.wunderlist.provider.list.ListSelection;
import com.vi8e.um.wunderlist.provider.task.TaskColumns;
import com.vi8e.um.wunderlist.provider.task.TaskCursor;
import com.vi8e.um.wunderlist.provider.task.TaskSelection;
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
ActionBarDrawerToggle   drawerToggle;
CoordinatorLayout       rootLayout;
FloatingActionButton    fabBtn;
static DynamicListView listView;
NestedScrollView nestedScrollView;
public static ListModel          currentList;
static        ActionBar          mActionBar;
public static LandingListAdapter mLandingListAdapter;
static        Activity           thisActivity;
static        Menu               menu;
public static int                currentListPosition;
public static boolean isLongClick;

public static
boolean isDragging () {
	return isDragging;
}

private static boolean isDragging;
private static final int INITIAL_DELAY_MILLIS = 100;

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	Fabric.with ( this, new Crashlytics () );
	setContentView ( R.layout.activity_landing );
	thisActivity = this;
	sContext = getApplicationContext ();
	nestedScrollView = ( NestedScrollView ) findViewById ( R.id.nested_scroll_view );
	initToolbar ();
	initInstances ();

	mLandingListAdapter = setUpAdapterListView ( thisActivity, getApplication (), mLandingListAdapter );
	setListView ();
	setFloatingActionBtnClickListener ( getWindow ().getDecorView ().findViewById ( android.R.id.content ), listView, mLandingListAdapter );

	int minHeight = ( int ) Utility.getListHeight ( thisActivity );
	Log.d ( TAG, "minHeight Of NestedScroll= " + minHeight );
	nestedScrollView.setMinimumHeight ( minHeight );

//	mLandingListAdapter.onClickList ( 0, thisActivity, mLandingListAdapter.getItem ( 0 ));

}

public static
void setActiveToolBar () {
	toolbar.setBackgroundDrawable ( new ColorDrawable ( sContext.getResources ().getColor ( R.color.blue_400 ) ) );
}

public static
void setInActiveToolBar () {
	toolbar.setBackgroundDrawable ( new ColorDrawable ( sContext.getResources ().getColor ( R.color.transparent ) ) );
}

private
void setListView () {
	listView = ( DynamicListView ) findViewById ( R.id.listViewTaskInComplete );
	ArrayAdapter<ListModel> adapter = mLandingListAdapter;

	SimpleSwipeUndoAdapter simpleSwipeUndoAdapter = new SimpleSwipeUndoAdapter ( adapter, this, new OnSwipeDismissCallBack ( adapter ) );
	AlphaInAnimationAdapter animAdapter = new AlphaInAnimationAdapter ( simpleSwipeUndoAdapter );
	animAdapter.setAbsListView ( listView );

	assert animAdapter.getViewAnimator () != null;
	animAdapter.getViewAnimator ().setInitialDelayMillis ( INITIAL_DELAY_MILLIS );

	listView.setAdapter ( animAdapter );

	listView.enableDragAndDrop ();
	listView.setDraggableManager ( new TouchViewDraggableManager ( R.id.list_row_draganddrop_touchview ) );
	listView.setOnItemMovedListener ( new MyOnItemMovedListener () );
	listView.setOnItemLongClickListener ( new MyOnItemLongClickListener ( listView ) );

	//listView.enableSimpleSwipeUndo ();
	listView.enableSwipeToDismiss ( simpleSwipeUndoAdapter );
	listView.setOnItemClickListener ( new MyOnItemClickListener ( listView ) );

}

public static
void setCurrentList ( final int position ) {
	//currentList = listModel;
	currentListPosition = position;
	currentList = mLandingListAdapter.getItem ( position );
}


private static
class MyOnItemLongClickListener implements AdapterView.OnItemLongClickListener {
	//private final DynamicListView mListView;
	MyOnItemLongClickListener ( final DynamicListView listView ) {

	}

	@Override
	public
	boolean onItemLongClick ( final AdapterView<?> parent, final View view, final int position, final long id ) {
		Log.d ( TAG, "onItemLongClick" );

		setCurrentList ( position );
		setMenuList ();
		setActiveList ();
		setActiveToolBar ();

		if ( listView != null ) {
			Log.d ( TAG, "StartDrag position="+position );
			try {
				listView .startDragging ( position - listView.getHeaderViewsCount () );
				LandingListAdapter.setInActiveListBgColor ( position, mLandingListAdapter, thisActivity );
				isDragging = true;
			}
			catch ( ClassCastException e ) {
				Log.e ( TAG, e.getMessage () );
			}
			catch ( IllegalStateException e ) {
				Log.e ( TAG, e.getMessage () );
			}
		}
		return true;
	}
}

public static
void setActiveList () {
	RelativeLayout rowListRootView = currentList.getRowListRootView ();
	rowListRootView.setBackgroundColor ( sContext.getResources ().getColor ( R.color.blue_400_trans50 ) );
}

private
class MyOnItemClickListener implements AdapterView.OnItemClickListener {

	private final DynamicListView mListView;
	MyOnItemClickListener ( final DynamicListView listView ) {
		mListView = listView;
	}
	@Override
	public
	void onItemClick ( final AdapterView<?> parent, final View view, final int position, final long id ) {
		//mListView.insert(position, getString(R.string.newly_added_item, mNewItemCount));
		//mNewItemCount++;
	}
}

private
class MyOnItemMovedListener implements OnItemMovedListener {

	//private final LandingListAdapter mAdapter;

	private Toast mToast;

	MyOnItemMovedListener ( /*final LandingListAdapter adapter */ ) {
		//mAdapter = adapter;
	}

	@Override
	public
	void onItemMoved ( final int originalPosition, final int newPosition ) {

		Log.d ( TAG, "onItemMoved originalPos="+originalPosition+" newPos="+newPosition );
		isDragging=false;
		//updateListPosition ( originalPosition, newPosition);
		setInActiveToolBar ();
	}

	public
	void updateListPosition ( int originalPosition, int newPosition ) {
		if ( originalPosition != newPosition ) {
			ListModel item = mLandingListAdapter.getItem ( originalPosition );
			mLandingListAdapter.moveItem ( item, newPosition );
		}
	}
}

public static
void setUpOnResume () {
//	mLandingListAdapter.clear ();
	mLandingListAdapter = setUpAdapterListView ( thisActivity, thisActivity.getApplication (), mLandingListAdapter );
}

public static
LandingListAdapter setUpAdapterListView ( Activity activity, Context context, LandingListAdapter landingListAdapter ) {

	listView = ( DynamicListView ) activity.findViewById ( R.id.listViewTaskInComplete );
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
	}

	Utility.setTaskListViewHeight ( listView );

// Or even append an entire new collection
// Fetching some data, data has now returned
// If data was JSON, convert to ArrayList of User objects.
	/*JSONArray jsonArray = ...;
	ArrayList<User> newUsers = User.fromJson(jsonArray)
	adapter.addAll(newUsers);*/

	/*AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter ( landingListAdapter );
	animationAdapter.setAbsListView ( listView );*/

	listView.setAdapter ( landingListAdapter );

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
			CustomDialog.showAddListDialog ( thisActivity, landingListAdapter, listView );
		}
	} );
	toDoBtn.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			CustomDialog.showAddListDialog ( thisActivity, landingListAdapter, listView );
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
	rootLayout = ( CoordinatorLayout ) findViewById ( R.id.rootLayout );
	collapsingToolbarLayout = ( CollapsingToolbarLayout ) findViewById ( R.id.collapsingToolbarLayout );
	collapsingToolbarLayout.setTitle ( "" + Utility.getVersionName ( getApplication () ) );

}

@Override
public
void onPostCreate ( Bundle savedInstanceState ) {
	super.onPostCreate ( savedInstanceState );
//	drawerToggle.syncState ();
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
	//setMenuNormal ();

	Log.d ( "Main", "EnterOnPause dataCount" + mLandingListAdapter.getCount () );
	saveListAdapterToDb ();
}

private
void saveListAdapterToDb () {
	for ( int i = 0 ; i < mLandingListAdapter.getCount () ; i++ ) {
		//ListModel recordData = mLandingListAdapter.getArrayList ().get ( i );
		ListModel recordData = mLandingListAdapter.getItem ( i );
		String id = recordData.getId ();
		Uri uri = Uri.parse ( String.valueOf ( ListColumns.CONTENT_URI ) + "/" + id );
		try {
			getContentResolver ().update ( uri, recordData.getValues (), null, null );
		}
		catch ( IllegalArgumentException e ) {
			Log.e ( "errorOnUpdateData", e.getMessage () );
			uri = getContentResolver ().insert ( ListColumns.CONTENT_URI, recordData.getValues () );
			Log.d ( "ChkColumn ", "title" + recordData.getTitle () + "newId=" + uri.getPathSegments ().get ( 1 ) );
		}
	}
}

@Override
protected
void
onResume () {
	super.onResume ();
	Log.d ( "OnResume", "" );
	setUpOnResume ();
	//mLandingListAdapter = setUpAdapterListView ( thisActivity, getApplication (), listView, mLandingListAdapter );

}

public static
void setMenuNormal () {
	menu.clear ();
	thisActivity.getMenuInflater ().inflate ( R.menu.menu_main_normal, menu );
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

private
class OnSwipeDismissCallBack implements OnDismissCallback {


	@Nullable
	private Toast mToast;

	OnSwipeDismissCallBack ( final ArrayAdapter<ListModel> adapter ) {

	}

	@Override
	public
	void onDismiss ( @NonNull final ViewGroup viewGroup_ListView, @NonNull final int[] reverseSortedPositions ) {

//todo bad code T^T
		for ( int position : reverseSortedPositions ) {
			setCurrentList ( position );
		}
		Log.d ( TAG, "onDismiss" );
		CustomDialog.showDialogDelete ( thisActivity, mLandingListAdapter, listView );
		Utility.toastOneInstance ( mToast, getApplicationContext () );
	}

}


@Override
public
boolean onOptionsItemSelected ( MenuItem item ) {

	int id = item.getItemId ();
	//noinspection SimplifiableIfStatement
	if ( id == R.id.menu_setting ) {
		IntentCaller.developer ( thisActivity );
		return true;
	}
	if ( id == R.id.delete ) {

		deleteSpecificList ( getApplicationContext () );
	}

	if ( id == R.id.duplicateList ) {

		duplicateSpecificList ();
	}

	if ( id == R.id.menu_edit ) {
		IntentCaller.listDetailActivity ( getApplicationContext (), currentList );
	}

	setMenuNormal ();

	return super.onOptionsItemSelected ( item );
}

private
void duplicateSpecificList () {
	ListModel newListModel = new ListModel ( currentList );
	newListModel.setTitle ( newListModel.getTitle () + " Copy" );
	Uri uri = QueryHelper.addListToDB ( getApplicationContext (), newListModel );
	//listSelection.delete ( getApplicationContext () );

	TaskSelection taskSelection = new TaskSelection ();
	taskSelection.listid ( currentList.getId () );

	TaskCursor taskCursor = taskSelection.query ( getApplicationContext () );

	taskCursor.moveToFirst ();

	List<ContentValues> allListValues = QueryHelper.getValuesFromCursor ( taskCursor, TaskColumns.ALL_COLUMNS );
	for ( int i = 0 ; i < allListValues.size () ; i++ ) {
		ContentValues values = allListValues.get ( i );
		Log.d ( TAG, "duplicating " + values.getAsString ( TaskColumns.TASK_TITLE ) );
		values.put ( TaskColumns.LISTID, uri.getPathSegments ().get ( 1 ) );
		QueryHelper.addTaskToDB ( getApplicationContext (), new TaskModel ( values ) );
	}
	QueryHelper.updateListAdapter ( newListModel, listView );

}

public static
void deleteSpecificList ( Context context ) {
	Log.d ( TAG, "CurrentList title= " + currentList.getTitle () );

	TaskSelection taskSelection = new TaskSelection ();
	taskSelection.listid ( currentList.getId () );
	taskSelection.delete ( context );

	ListSelection listSelection = new ListSelection ();
	listSelection.id ( Long.parseLong ( currentList.getId () ) );
	listSelection.delete ( context );

	//todo dont know why cannot delete via .remove(currentList)
	//mLandingListAdapter.remove ( currentList );
	mLandingListAdapter.remove ( currentListPosition );

}

}
