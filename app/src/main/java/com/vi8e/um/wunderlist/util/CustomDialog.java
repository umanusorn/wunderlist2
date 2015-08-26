package com.vi8e.um.wunderlist.util;
import android.app.Activity;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.vi8e.um.wunderlist.Model.ListModel;
import com.vi8e.um.wunderlist.adater.LandingListAdapter;


/**
 * Created by um.anusorn on 8/17/2015.
 */
public
class CustomDialog {


public static
void showPassCodeChangeOrTurnOff ( final Activity thisContext, final LandingListAdapter landingListAdapter, final ListView listView ) {
	MaterialDialog scoreDialog = new MaterialDialog.Builder ( thisContext )
			//.customView ( R.layout.dialog_todo, true )
			.title ( "Add to-do" )
			.positiveText ( "ADD" )
			.input ( "Add to-do", "", new MaterialDialog.InputCallback () {
				@Override public
				void onInput ( MaterialDialog materialDialog, CharSequence charSequence ) {
					landingListAdapter.addList ( new ListModel ( String.valueOf ( charSequence )),listView );
				}
			} )
			.negativeText ( "CANCEL" )
			.show ();

}


}
