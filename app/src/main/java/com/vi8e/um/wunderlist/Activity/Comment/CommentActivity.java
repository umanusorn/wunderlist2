package com.vi8e.um.wunderlist.Activity.Comment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vi8e.um.wunderlist.Activity.TaskActivity;
import com.vi8e.um.wunderlist.Model.CommentModel;
import com.vi8e.um.wunderlist.Model.ModelType;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.utils.ActivityUi;
import com.vi8e.um.wunderlist.utils.QueryHelper;
import com.vi8e.um.wunderlist.utils.RecycleUtil;


public
class CommentActivity extends AppCompatActivity {

private final String TAG = this.getClass().getSimpleName();
Toolbar mToolbar;
String title = "Comment";
Context  thisContext;
TextView sendComment;
EditText editTextComment;
static        int               currentListPosition;
public static CommentModel      currentList;
static        AppCompatActivity thisActivity;
public static ListView          listViewSubTask;
private       Menu              menu;
public static        Bundle            thisSavedInstanceState;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_comment);
	thisContext = getApplicationContext();
	thisActivity = this;
	mToolbar = (Toolbar) findViewById(R.id.toolbar);
	setSupportActionBar(mToolbar);
	ActivityUi.setToolBar(this, mToolbar, title);

	thisSavedInstanceState = savedInstanceState;
	RecycleUtil.setUpRecycleFragment(savedInstanceState, thisActivity, ModelType.COMMENT);

	mToolbar.setBackgroundColor(getResources().getColor(R.color.blue_500));
	editTextComment = (EditText) findViewById(R.id.editTextComment);
	sendComment = (TextView) findViewById(R.id.sendComment);
	sendComment.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View view) {
			onClickSend(editTextComment.getText().toString());
		}
	});
	listViewSubTask = (ListView) findViewById(R.id.listViewComment);
	/*for ( int i = 0 ; i < 5 ; i++ ) {
		onClickSend ( "test"+i );
	}*/
	RecycleUtil.setUpRecycleFragment(savedInstanceState, thisActivity, ModelType.COMMENT);
	listViewSubTask.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
		@Override public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

			ActivityUi.setMenuComment(thisActivity, menu);
			ActivityUi.setActiveList ( ( RelativeLayout ) view, thisContext );
			//ActivityUi.setActiveToolBar ( thisActivity, toolbar, currentList.getTitle (), thisContext);

			//CustomDialog.showDialogDeleteComment ( thisActivity,sCommentAdapter,position );
			//todo
			//setCurrentList ( position );
			return false;
		}
	} );
}

@Override
public
boolean onOptionsItemSelected ( MenuItem item ) {

	int id = item.getItemId ();
	if ( id == R.id.delete ) {

		//todo
		//deleteCurrentComment ( currentListPosition, thisContext, sCommentAdapter );
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

	if(menu==null)
		thisActivity.invalidateOptionsMenu();

	/*menu.clear ();
	thisActivity.getMenuInflater ().inflate ( R.menu.menu_main_normal, LandingActivity.menu );*/

}

//todo
/*public static
void deleteCurrentComment ( int position, Context context, CommentRecycleAdapter commentAdapter ) {
	Log.d ( TAG, "pos=" + position );
	TaskCommentSelection selection = new TaskCommentSelection ();
	CommentModel commentModel = commentAdapter.getItem ( position );
	String id = commentModel.getId ();
	Log.d ( TAG, "id=" + id );
	selection.id ( Long.parseLong ( id ) );
	selection.delete ( context );
	commentAdapter.remove ( commentModel );
	commentAdapter.notifyDataSetChanged ();

}*/

//todo
/*public static
void setCurrentList ( final int position ) {
	//currentList = listModel;
	currentListPosition = position;
	currentList = sCommentAdapter.getItem(position);
}*/

void onClickSend ( String title ) {
	QueryHelper.addCommentToDB ( thisContext, title, TaskActivity.currentTask.getId () );
	RecycleUtil.setUpRecycleFragment(thisSavedInstanceState, thisActivity, ModelType.COMMENT);
}


}
