package com.vi8e.um.wunderlist.Activity.Landing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.vi8e.um.wunderlist.Activity.LandingActivity;
import com.vi8e.um.wunderlist.Model.ListModel;
import com.vi8e.um.wunderlist.utils.IntentCaller;
/**
 * Created by Fixer on 11/4/2015.
 */
public class ListAction {
@NonNull public static
View.OnClickListener getOnClick ( final ListModel listModel, final Context context, final int position ) {
	return new View.OnClickListener () {

		@Override public
		void onClick ( View v ) {

			Log.d ("onClick", "isLongClick=" + LandingActivity.isLongClick);

			onClickList ( position, context, listModel );
		}
	};
}

public static
void onClickList ( int position, Context context, ListModel listModel ) {

	//LandingActivity.setCurrentList ( position, LandingActivity.mLandingListAdapter );
	//if ( ! isLongClick ) {
	IntentCaller.taskActivity(context, listModel);
	//}
	LandingActivity.isLongClick = false;
}
}
