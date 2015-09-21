package com.vi8e.um.wunderlist.adater;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vi8e.um.wunderlist.Activity.TaskDetailActivity;
import com.vi8e.um.wunderlist.Model.SubTaskModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.util.Utility;

import java.util.ArrayList;


/**
 * Created by um.anusorn on 8/25/2015.
 */
public
class TaskDetailAdapter extends ArrayAdapter<SubTaskModel> {

Context              mContext;
Resources            res;
ArrayList<SubTaskModel> lists;
boolean              mIsLongClick;

private static final String TAG = TaskDetailAdapter.class.getSimpleName ();
public
TaskDetailAdapter ( Context context,
                    ArrayList<SubTaskModel> lists ) {

	super ( context, 0, lists );
	this.lists = lists;
	mContext = context;
	res = context.getResources ();
}

public
ArrayList<SubTaskModel> getArrayList () {
	return lists;
}

@Override
public
View getView ( final int position, View convertView, final ViewGroup parent ) {

	final SubTaskModel rowData;
	// Get the data item for this position
	rowData = getItem ( position );

	convertView = LayoutInflater.from ( getContext () ).inflate ( R.layout.list_row_sub_task, parent, false );
	// Lookup view for data population
	final TextView tvTitle = ( TextView ) convertView.findViewById ( R.id.listtitle );
	final ImageView chkBox = ( ImageView ) convertView.findViewById ( R.id.chkBox );
	RelativeLayout rowBg = ( RelativeLayout ) convertView.findViewById ( R.id.rowBg );

	setView ( rowData, tvTitle, chkBox );
	return convertView;
}

public
void setView ( SubTaskModel rowData, TextView tvTitle, ImageView chkBox ) {
	tvTitle.setText ( rowData.getTitle () );
	chkBox.setOnClickListener ( onClickChkBox ( rowData ) );
}


@NonNull public static
View.OnClickListener onClickChkBox ( final SubTaskModel rowData ) {
	return new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {

		//	rowData.setIsComplete ( String.valueOf ( ! rowData.getIsComplete () ) );
			Log.d ( TAG, "getIsComplete= " + rowData.getIsComplete () );
			Utility.toggleImgCompleteData ( v, rowData, TaskDetailActivity.sContext);
			Utility.setTaskListViewHeight ( TaskDetailActivity.listViewSubTask );
		}
	};
}

private
void setUpCompletedBg ( SubTaskModel rowData, RelativeLayout rowBg, CardView cardView ) {
	if ( rowData.isComplete () ) {
		rowBg.setAlpha ( ( float ) 0.5 );
		cardView.setAlpha ( ( float ) 0.5 );
	}
}


}