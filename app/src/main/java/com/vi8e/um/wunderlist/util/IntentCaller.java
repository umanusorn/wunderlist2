package com.vi8e.um.wunderlist.util;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.vi8e.um.wunderlist.Activity.DeveloperActivity;
import com.vi8e.um.wunderlist.Activity.ListDetailActivity;
import com.vi8e.um.wunderlist.Activity.TaskActivity;
import com.vi8e.um.wunderlist.Activity.TaskDetailActivity;
import com.vi8e.um.wunderlist.Model.ListConst;
import com.vi8e.um.wunderlist.Model.ListModel;


/**
 * Created by um.anusorn on 9/3/2015.
 */
public
class IntentCaller {


public static
void developer ( Activity activity ) {
	Intent intent = new Intent ( activity, DeveloperActivity.class );
	intent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK );
	activity.startActivity ( intent );

}

public static
void taskActivity ( Context context, ListModel listModel ) {
	Intent intent = new Intent ( context, TaskActivity.class );
	intent.putExtra ( ListConst.KEY_TITLE, listModel.getTitle () );
	intent.putExtra ( ListConst.KEY_ID, listModel.getId () );
	context.startActivity ( intent );
}

public static
void taskDetailActivity ( Context context, TextView tvTitle ) {

	Intent intent = new Intent ( context, TaskDetailActivity.class );
	intent.putExtra ( ListConst.KEY_TITLE, tvTitle.getText ().toString () );
	intent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK );
	context.startActivity ( intent );
}

public static
void listDetailActivity ( Context context, ListModel listModel ) {
	Intent intent = new Intent ( context, ListDetailActivity.class );
	intent.putExtra ( ListConst.KEY_TITLE, listModel.getTitle () );
	intent.putExtra ( ListConst.KEY_ID, listModel.getId () );
	intent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK );
	context.startActivity ( intent );
}

}
