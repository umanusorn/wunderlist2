package com.vi8e.um.wunderlist.dialogs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.vi8e.um.wunderlist.Activity.Comment.CommentActivity;
import com.vi8e.um.wunderlist.Activity.LandingActivity;
import com.vi8e.um.wunderlist.Activity.TaskActivity;
import com.vi8e.um.wunderlist.Activity.TaskDetail.TaskDetailActivity;
import com.vi8e.um.wunderlist.Model.ModelType;
import com.vi8e.um.wunderlist.Model.SubTaskModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.adapters.CommentAdapter;
import com.vi8e.um.wunderlist.adapters.LandingListAdapter;
import com.vi8e.um.wunderlist.provider.subtask.SubtaskColumns;
import com.vi8e.um.wunderlist.utils.QueryHelper;
import com.vi8e.um.wunderlist.utils.RecycleUtil;
import com.vi8e.um.wunderlist.utils.dropbox.UploadMultiPictures;

import java.io.File;


/**
 * Created by um.anusorn on 8/17/2015.
 */
public
class CustomDialog {

public static final String TAG = "CustomDialog";

public static
void showAddListDialog ( final Activity thisContext, final LandingListAdapter landingListAdapter, final ListView listView ) {
	MaterialDialog scoreDialog = new MaterialDialog.Builder ( thisContext )
			//.customView ( R.layout.dialog_todo, true )
			.title ( thisContext.getString ( R.string.add_list ) )
			.positiveText ( "ADD" )
			.input ( thisContext.getString ( R.string.add_list ), "", new MaterialDialog.InputCallback () {
				@Override public
				void onInput ( MaterialDialog materialDialog, CharSequence charSequence ) {
					QueryHelper.addListToDB ( thisContext, String.valueOf ( charSequence ), listView );
				}
			} )
			.negativeText ( "CANCEL" )
			.show ();
}

public static
void showAddSubTaskDialog ( final AppCompatActivity thisActivity, final ListView listView ) {
	MaterialDialog scoreDialog = new MaterialDialog.Builder ( thisActivity )
			//.customView ( R.layout.dialog_todo, true )
			.title ( thisActivity.getString(R.string.add_sub_task) )
			.positiveText ( "ADD" )
			.input ( thisActivity.getString(R.string.add_sub_task), "", new MaterialDialog.InputCallback () {
				@Override public
				void onInput ( MaterialDialog materialDialog, CharSequence charSequence ) {
					QueryHelper.addSubTaskToDB(thisActivity,
					                           String.valueOf(charSequence),
					                           TaskActivity.currentTask.getId(),
					                           listView);
					//TaskDetailActivity.setUpAdapterListView();
					RecycleUtil.setUpRecycleFragment(TaskDetailActivity.thisSavedInstanceState, thisActivity, ModelType.SUB_TASK);
					//landingListAdapter.addList ( new ListModel ( String.valueOf ( charSequence ) ), listView );
					//Utility.setTaskListViewHeight ( listView );
				}
			} )
			.negativeText("CANCEL")
			.show();
}


public static
void showUpdateSubTaskDialog ( final SubTaskModel rowData, final AppCompatActivity activity, final ListView listView ) {
	new MaterialDialog.Builder ( activity )
			.title ( "Edit" )
			.positiveText ( "ACCEPT" )
			.input ( rowData.getTitle (), rowData.getTitle (), new MaterialDialog.InputCallback () {
				         @Override public
				         void onInput ( MaterialDialog materialDialog, CharSequence charSequence ) {

					         rowData.setTitle ( String.valueOf ( charSequence ) );
					         String id = rowData.getId ();
					         Uri uri = Uri.parse ( String.valueOf ( SubtaskColumns.CONTENT_URI ) + "/" + id );
					         activity.getContentResolver ().update(uri, rowData.getValues(), null, null);
					         //TaskDetailActivity.setUpAdapterListView();
					         RecycleUtil.setUpRecycleFragment(TaskDetailActivity.thisSavedInstanceState,activity,ModelType.SUB_TASK);
				         }
			         }
			       )
			.

					negativeText ( "CANCEL" )

			.

					show ();
}

public static
void showCancelUploadDialog ( final Activity thisContext, final Intent intent, final UploadMultiPictures uploadMultiPictures  ) {

	Log.d ( TAG,"showCancelUplaod Dialog ");
	MaterialDialog diallogDelete = new MaterialDialog.Builder ( thisContext )
			//.customView ( R.layout.dialog_todo, true )
			.title ( "Uploading in progress" )
			//.content ( "\"" + title + "\" " + thisContext.getString ( R.string.content_delete_list ) )
			.positiveText ( "Cancel the upload" ).callback ( new MaterialDialog.ButtonCallback () {
				@Override public
				void onPositive ( MaterialDialog dialog ) {
					super.onPositive ( dialog );

					if ( uploadMultiPictures != null ) {
						uploadMultiPictures.cancelUpload ();
						intent.putExtra ( TaskDetailActivity.CANCEL_UPLOAD, TaskDetailActivity.FALSE );
						Log.d ( TAG, "canceled uploadBtn" );
					}
				}
			} ).positiveColor ( thisContext.getResources ().getColor ( R.color.red_400 ) )
			.negativeText ( "Ok" ).negativeColor ( thisContext.getResources ().getColor ( R.color.blue_400 ) )
			.show ();
}

public static
void showDialogDelete ( final Activity thisContext, final LandingListAdapter landingListAdapter, final ListView listView ) {


	String title = landingListAdapter.getItem ( LandingActivity.currentListPosition ).getTitle ();
	Log.d ( "CustomDialog ", title );
	MaterialDialog diallogDelete = new MaterialDialog.Builder ( thisContext )
			//.customView ( R.layout.dialog_todo, true )
			.title ( "Delete List" )
			.content ( "\"" + title + "\" " + thisContext.getString ( R.string.content_delete_list ) )
			.positiveText ( "Delete" ).callback ( new MaterialDialog.ButtonCallback () {
				@Override public
				void onPositive ( MaterialDialog dialog ) {
					super.onPositive ( dialog );
					LandingActivity.deleteSpecificList ( thisContext, LandingActivity.currentList.getId () );
					LandingActivity.mLandingListAdapter.notifyDataSetChanged ();
					LandingActivity.setUpOnResume ();

				}
			} ).positiveColor ( thisContext.getResources ().getColor ( R.color.red_400 ) )
			.negativeText ( "NO" ).negativeColor ( thisContext.getResources ().getColor ( R.color.blue_400 ) )
			.show ();
}

public static
void showDialogDeleteComment ( final Activity thisContext, final CommentAdapter sCommentAdapter, final int currentPosition) {

	String title = sCommentAdapter.getItem (currentPosition).getTitle ();
	Log.d ( "CustomDialog ", title );
	MaterialDialog diallogDelete = new MaterialDialog.Builder ( thisContext )
			//.customView ( R.layout.dialog_todo, true )
			.title ( "Delete Comment" )
			.content ( "\"" + title + "\" " + thisContext.getString ( R.string.content_delete_list ) )
			.positiveText ( "Delete" ).callback ( new MaterialDialog.ButtonCallback () {
				@Override public
				void onPositive ( MaterialDialog dialog ) {
					super.onPositive ( dialog );
					CommentActivity.deleteCurrentComment ( currentPosition, thisContext, sCommentAdapter );

				}
			} ).positiveColor ( thisContext.getResources ().getColor ( R.color.red_400 ) )
			.negativeText ( "NO" ).negativeColor ( thisContext.getResources ().getColor ( R.color.blue_400 ) )
			.show ();
}


public static
void showUploadProgressDialog ( final UploadMultiPictures uploadMultiPictures, ProgressDialog mDialog, File[] filesToUpload ) {
	mDialog = new ProgressDialog ( TaskDetailActivity.thisActivity );
	mDialog.setMax ( 100 );
	mDialog.setMessage ( "Uploading file 1 / " + filesToUpload.length );
	mDialog.setProgressStyle ( ProgressDialog.STYLE_HORIZONTAL );
	mDialog.setProgress ( 0 );
	mDialog.setButton ( "Cancel", new DialogInterface.OnClickListener () {
		public
		void onClick ( DialogInterface dialog, int which ) {
			uploadMultiPictures.cancel ( true );
		}
	} );
	mDialog.show ();
}
}
