package com.vi8e.um.wunderlist.Activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.vi8e.um.wunderlist.Model.CommentModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.adapters.CommentAdapter;
import com.vi8e.um.wunderlist.provider.taskcomment.TaskCommentColumns;
import com.vi8e.um.wunderlist.provider.taskcomment.TaskCommentSelection;
import com.vi8e.um.wunderlist.utils.ActivityUi;
import com.vi8e.um.wunderlist.utils.QueryHelper;
import com.vi8e.um.wunderlist.utils.Utility;

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
static        int            currentListPosition;
public static CommentModel   currentList;
static        Activity       thisActivity;
public static CommentAdapter sCommentAdapter;
public static ListView       listViewSubTask;

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
	setUpAdapterListView ( this, listViewSubTask, sCommentAdapter, thisContext );
	listViewSubTask.setOnItemLongClickListener ( new AdapterView.OnItemLongClickListener () {
		@Override public
		boolean onItemLongClick ( AdapterView<?> adapterView, View view, int position, long id ) {
			//deleteCurrentComment ( position ,thisContext);
					view.setVisibility ( View.GONE );
			adapterView.setVisibility ( View.GONE );
			return false;
		}
	} );
}

public
void deleteCurrentComment ( int position ,Context context) {
		Log.d ( TAG,"pos="+position );
		TaskCommentSelection selection = new TaskCommentSelection ();

	selection.id ( Long.parseLong ( sCommentAdapter.getItem( position ).getId()));
		selection.delete ( context );
}


public static
void setCurrentList ( final int position ) {
	//currentList = listModel;
	currentListPosition = position;
	currentList = sCommentAdapter.getItem ( position );
}

void onClickSend(String title){
	QueryHelper.addCommentToDB ( thisContext, title, TaskActivity.currentTask.getId () );
	setUpAdapterListView ( thisActivity, listViewSubTask, sCommentAdapter, thisContext );
}

public static
CommentAdapter setUpAdapterListView ( Activity activity, ListView listView, CommentAdapter commentAdapter, Context context ) {
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
	commentAdapter = new CommentAdapter ( context, arrayOfList );
	listView.setAdapter ( commentAdapter );
	for ( int i = 0 ; i < allListValues.size () ; i++ ) {
		ContentValues values = allListValues.get ( i );
		commentAdapter.add ( new CommentModel ( values.getAsString ( TaskCommentColumns.COMMENT_TITLE ),
		                                        values.getAsString ( TaskCommentColumns.TASK_ID ),
		                                        values.getAsString ( TaskCommentColumns._ID ),
		                                        values.getAsString ( TaskCommentColumns.DATETIME ),
		                                        values.getAsString ( TaskCommentColumns.USER_ID ) )
		                   );
	}

	Utility.setTaskListViewHeight ( listViewSubTask );

	return commentAdapter;
}

}
