package com.vi8e.um.wunderlist;
import android.app.Activity;

import com.afollestad.materialdialogs.MaterialDialog;


/**
 * Created by um.anusorn on 8/17/2015.
 */
public
class CustomDialog {


public static
void showPassCodeChangeOrTurnOff (final Activity thisContext ) {
	MaterialDialog scoreDialog = new MaterialDialog.Builder ( thisContext )
			.title ( "Please choose Change/Turn off" )
			.positiveText ( "Change" )
			.negativeText ( "Turn off" )
			.neutralText ( "natural" )
			.show ();
}


}
