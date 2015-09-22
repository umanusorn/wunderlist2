package com.vi8e.um.wunderlist.Dialog;
import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.vi8e.um.wunderlist.Activity.LandingActivity;
import com.vi8e.um.wunderlist.Activity.TaskActivity;
import com.vi8e.um.wunderlist.Activity.TaskDetailActivity;
import com.vi8e.um.wunderlist.Model.SubTaskModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.adater.LandingListAdapter;
import com.vi8e.um.wunderlist.adater.TaskDetailAdapter;
import com.vi8e.um.wunderlist.provider.subtask.SubtaskColumns;
import com.vi8e.um.wunderlist.util.QueryHelper;


/**
 * Created by um.anusorn on 8/17/2015.
 */
public
class CustomDialog {


public static
void showReminderDialog ( final Activity thisContext, final ListView listView ) {
	MaterialDialog reminderDialog = new MaterialDialog.Builder ( thisContext )
			.customView ( R.layout.dialog_reminder, true )
			.show ();

	View view =reminderDialog.getCustomView ();

}

public static
void showAddListDialog ( final Activity thisContext, final LandingListAdapter landingListAdapter, final ListView listView ) {
	MaterialDialog scoreDialog = new MaterialDialog.Builder ( thisContext )
			//.customView ( R.layout.dialog_todo, true )
			.title ( "Add List" )
			.positiveText ( "ADD" )
			.input ( "Add List", "", new MaterialDialog.InputCallback () {
				@Override public
				void onInput ( MaterialDialog materialDialog, CharSequence charSequence ) {
					QueryHelper.addListToDB ( thisContext, String.valueOf ( charSequence ), listView );
					//landingListAdapter.addList ( new ListModel ( String.valueOf ( charSequence ) ), listView );
					//Utility.setTaskListViewHeight ( listView );
				}
			} )
			.negativeText ( "CANCEL" )
			.show ();
}

public static
void showAddSubTaskDialog ( final Activity thisContext, final TaskDetailAdapter taskDetailAdapter, final ListView listView ) {
	MaterialDialog scoreDialog = new MaterialDialog.Builder ( thisContext )
			//.customView ( R.layout.dialog_todo, true )
			.title ( "Add subTask" )
			.positiveText ( "ADD" )
			.input ( "Add subTask", "", new MaterialDialog.InputCallback () {
				@Override public
				void onInput ( MaterialDialog materialDialog, CharSequence charSequence ) {
					QueryHelper.addSubTaskToDB ( thisContext, String.valueOf ( charSequence ), TaskActivity.currentTask.getId (), listView );
					TaskDetailActivity.setUpAdapterListView ();
					//landingListAdapter.addList ( new ListModel ( String.valueOf ( charSequence ) ), listView );
					//Utility.setTaskListViewHeight ( listView );
				}
			} )
			.negativeText ( "CANCEL" )
			.show ();
}


public static
void showUpdateSubTaskDialog ( final SubTaskModel rowData, final Activity activity, final TaskDetailAdapter taskDetailAdapter, final ListView listView ) {
	new MaterialDialog.Builder ( activity )
			.title ( "Edit" )
			.positiveText ( "ACCEPT" )
			.input ( rowData.getTitle (), rowData.getTitle (), new MaterialDialog.InputCallback () {
				         @Override public
				         void onInput ( MaterialDialog materialDialog, CharSequence charSequence ) {

					         rowData.setTitle ( String.valueOf ( charSequence ));
					         String id = rowData.getId ();
					         Uri uri = Uri.parse ( String.valueOf ( SubtaskColumns.CONTENT_URI ) + "/" + id );
					         activity.getContentResolver ().update ( uri, rowData.getValues (), null, null );
					         TaskDetailActivity.setUpAdapterListView ();
				         }
			         }
			       )
			.

					negativeText ( "CANCEL" )

			.

					show ();
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
					LandingActivity.deleteSpecificList ( thisContext );
					LandingActivity.mLandingListAdapter.notifyDataSetChanged ();
					LandingActivity.setUpOnResume ();

				}
			} ).positiveColor ( thisContext.getResources ().getColor ( R.color.red_400 ) )
			.negativeText ( "NO" ).negativeColor ( thisContext.getResources ().getColor ( R.color.blue_400 ) )
			.show ();
}


}
