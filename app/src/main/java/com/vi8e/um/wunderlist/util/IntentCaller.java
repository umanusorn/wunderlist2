package com.vi8e.um.wunderlist.util;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.vi8e.um.wunderlist.Activity.DeveloperActivity;
import com.vi8e.um.wunderlist.Activity.TaskActivity;
import com.vi8e.um.wunderlist.Model.ListConst;


/**
 * Created by um.anusorn on 9/3/2015.
 */
public
class IntentCaller {


public static
void developer (Context context) {
	Intent intent = new Intent (context, DeveloperActivity.class );
	context.startActivity ( intent );
}

public static
void taskActivity ( Context context, TextView tvTitle ) {
	Intent intent = new Intent ( context, TaskActivity.class );
	intent.putExtra ( ListConst.KEY_TITLE, tvTitle.getText ().toString () );
	context.startActivity ( intent );
}
}
