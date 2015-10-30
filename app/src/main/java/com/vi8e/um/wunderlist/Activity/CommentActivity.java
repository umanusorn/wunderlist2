package com.vi8e.um.wunderlist.Activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vi8e.um.wunderlist.Model.CommentModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.adapters.CommentAdapter;
import com.vi8e.um.wunderlist.provider.taskcomment.TaskCommentColumns;
import com.vi8e.um.wunderlist.provider.taskcomment.TaskCommentSelection;
import com.vi8e.um.wunderlist.utils.ActivityUi;
import com.vi8e.um.wunderlist.utils.QueryHelper;
import com.vi8e.um.wunderlist.utils.UiMng;

import java.util.ArrayList;
import java.util.List;


public
class CommentActivity extends AppCompatActivity {

private static final String TAG = TaskDetailActivity.class.getSimpleName ();
Toolbar mToolbar;
String title = "Comment";
Context  thisContext;
TextView sendComment;
EditText editTextComment;
static        int               currentListPosition;
public static CommentModel      currentList;
static        AppCompatActivity thisActivity;
public static CommentAdapter    sCommentAdapter;
public static ListView          listViewSubTask;
private       Menu              menu;

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_comment );
	thisContext = getApplicationContext ();
	thisActivity = this;
	mToolbar = ( Toolbar ) findViewById ( R.id.toolbar );
	setSupportActionBar ( mToolbar );
	ActivityUi.setToolBar ( this, mToolbar, title );

	mToolbar.setBackgroundColor ( getResources ().getColor ( R.color.blue_500 ) );
	editTextComment = ( EditText ) findViewById ( R.id.editTextComment );
	sendComment = ( TextView ) findViewById ( R.id.sendComment );
	sendComment.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View view ) {
			onClickSend ( editTextComment.getText ().toString () );
		}
	} );
	listViewSubTask = ( ListView ) findViewById ( R.id.listViewComment );
	/*for ( int i = 0 ; i < 5 ; i++ ) {
		onClickSend ( "test"+i );
	}*/
	sCommentAdapter = setUpAdapterListView ( listViewSubTask, thisContext );
	listViewSubTask.setOnItemLongClickListener ( new AdapterView.OnItemLongClickListener () {
		@Override public
		boolean onItemLongClick ( AdapterView<?> adapterView, View view, int position, long id ) {

			ActivityUi.setMenuComment ( thisActivity, menu );
			ActivityUi.setActiveList ( ( RelativeLayout ) view, thisContext );
			//ActivityUi.setActiveToolBar ( thisActivity, toolbar, currentList.getTitle (), thisContext);

			//CustomDialog.showDialogDeleteComment ( thisActivity,sCommentAdapter,position );
			setCurrentList ( position );
			return false;
		}
	} );
}

@Override
public
boolean onOptionsItemSelected ( MenuItem item ) {

	int id = item.getItemId ();
	if ( id == R.id.delete ) {

		deleteCurrentComment ( currentListPosition, thisContext, sCommentAdapter );
	}

	setMenuNormal ( thisActivity, menu );

	return super.onOptionsItemSelected ( item );
}


@Override
public
boolean onCreateOptionsMenu ( Menu menu ) {
	// Inflate the menu; this adds items to the action bar if it is present.
	this.menu = menu;
	setMenuNormal ( thisActivity, menu );
	return true;
}

public static
void setMenuNormal ( AppCompatActivity thisActivity, Menu menu ) {
	menu.clear ();
	thisActivity.getMenuInflater ().inflate ( R.menu.menu_main_normal, LandingActivity.menu );
}

public static
void deleteCurrentComment ( int position, Context context, CommentAdapter commentAdapter ) {
	Log.d ( TAG, "pos=" + position );
	TaskCommentSelection selection = new TaskCommentSelection ();
	CommentModel commentModel = commentAdapter.getItem ( position );
	String id = commentModel.getId ();
	Log.d ( TAG, "id=" + id );
	selection.id ( Long.parseLong ( id ) );
	selection.delete ( context );

	commentAdapter.remove ( commentModel );
	commentAdapter.notifyDataSetChanged ();

}


public static
void setCurrentList ( final int position ) {
	//currentList = listModel;
	currentListPosition = position;
	currentList = sCommentAdapter.getItem ( position );
}

void onClickSend ( String title ) {
	QueryHelper.addCommentToDB ( thisContext, title, TaskActivity.currentTask.getId () );
	sCommentAdapter = setUpAdapterListView ( listViewSubTask, thisContext );
}

public static
CommentAdapter setUpAdapterListView ( ListView listView, Context context ) {
	TaskCommentSelection where = new TaskCommentSelection ();
	where.taskId ( TaskActivity.currentTask.getId () );
	Cursor c = where.query ( context.getContentResolver () );
	c.moveToFirst ();
	Log.d ( TAG, "setUpAdapter" + String.valueOf ( c.getCount () ) );

	c.moveToFirst ();

	Log.d ( "setUpAdapter", String.valueOf ( c.getCount () ) );
	List<ContentValues> allListValues = QueryHelper.getValuesFromCursor ( c, TaskCommentColumns.ALL_COLUMNS );
	ArrayList<CommentModel> arrayOfList = new ArrayList<> ();

	//landingListAdapter = new LandingListAdapter ( activity, arrayOfList );
	CommentAdapter commentAdapter = new CommentAdapter ( context, arrayOfList );
	listView.setAdapter ( commentAdapter );
	for ( int i = 0 ; i < allListValues.size () ; i++ ) {
		ContentValues values = allListValues.get ( i );

		Log.d ( TAG, "comment id=" + values.getAsString ( TaskCommentColumns._ID ) );
		commentAdapter.add ( new CommentModel ( values.getAsString ( TaskCommentColumns.COMMENT_TITLE ),
		                                        values.getAsString ( TaskCommentColumns.TASK_ID ),
		                                        values.getAsString ( TaskCommentColumns._ID ),
		                                        values.getAsString ( TaskCommentColumns.DATETIME ),
		                                        values.getAsString ( TaskCommentColumns.USER_ID ) )
		                   );
	}
	UiMng.setTaskListViewHeight(listViewSubTask);
	return commentAdapter;
}

}
