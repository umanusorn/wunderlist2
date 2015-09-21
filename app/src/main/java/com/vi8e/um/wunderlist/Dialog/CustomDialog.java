package com.vi8e.um.wunderlist.Dialog;
import android.app.Activity;
import android.util.Log;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.vi8e.um.wunderlist.Activity.LandingActivity;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.adater.LandingListAdapter;
import com.vi8e.um.wunderlist.util.QueryHelper;


/**
 * Created by um.anusorn on 8/17/2015.
 */
public
class CustomDialog {


public static
void showEditTextDialog ( final Activity thisContext, final LandingListAdapter landingListAdapter, final ListView listView ) {
	MaterialDialog scoreDialog = new MaterialDialog.Builder ( thisContext )
			//.customView ( R.layout.dialog_todo, true )
			.title ( "Add List" )
			.positiveText ( "ADD" )
			.input ( "Add List", "", new MaterialDialog.InputCallback () {
				@Override public
				void onInput ( MaterialDialog materialDialog, CharSequence charSequence ) {
					QueryHelper.addListToDB ( thisContext, String.valueOf ( charSequence ), landingListAdapter, listView );
					//landingListAdapter.addList ( new ListModel ( String.valueOf ( charSequence ) ), listView );
					//Utility.setTaskListViewHeight ( listView );
				}
			} )
			.negativeText ( "CANCEL" )
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
					LandingActivity.deleteSpecificList ( thisContext );
					LandingActivity.mLandingListAdapter.notifyDataSetChanged ();
					LandingActivity.setUpOnResume ();

				}
			} ).positiveColor ( thisContext.getResources ().getColor ( R.color.red_400 ) )
			.negativeText ( "NO" ).negativeColor ( thisContext.getResources ().getColor ( R.color.blue_400 ) )
			.show ();
}


}
