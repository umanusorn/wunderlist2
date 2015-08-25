package com.vi8e.um.wunderlist;
import android.app.Activity;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;


/**
 * Created by um.anusorn on 8/17/2015.
 */
public
class CustomDialog {


public static
void showPassCodeChangeOrTurnOff (final Activity thisContext ) {
	MaterialDialog scoreDialog = new MaterialDialog.Builder ( thisContext )
			//.customView ( R.layout.dialog_todo, true )
			.title ( "Add to-do" )
			.positiveText ( "ADD" )
			.input ( "Add to-do", "", new MaterialDialog.InputCallback () {
				@Override public
				void onInput ( MaterialDialog materialDialog, CharSequence charSequence ) {
					showPassCodeChangeOrTurnOff ( thisContext );
				}
			} )
			.negativeText ( "CANCEL" )
			.show ();


	View view = scoreDialog.getCustomView ();
}


}
