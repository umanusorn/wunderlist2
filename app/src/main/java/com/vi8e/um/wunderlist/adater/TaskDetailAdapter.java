package com.vi8e.um.wunderlist.adater;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vi8e.um.wunderlist.Activity.TaskActivity;
import com.vi8e.um.wunderlist.Activity.TaskDetailActivity;
import com.vi8e.um.wunderlist.Model.ListConst;
import com.vi8e.um.wunderlist.Model.TaskModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.util.Utility;

import java.util.ArrayList;


/**
 * Created by um.anusorn on 8/25/2015.
 */
public
class TaskDetailAdapter extends ArrayAdapter<TaskModel> {

Context   mContext;
Resources res;

int position;
//ListView             listViewIncomplete, listViewComplete;

public
TaskDetailAdapter ( Context context,
										ArrayList<TaskModel> listInComplete ) {

	super ( context, 0, listInComplete );
	mContext = context;
	res = context.getResources ();
}


@Override
public
View getView ( final int position, View convertView, final ViewGroup parent ) {
	// Get the data item for this position
	this.position = position;
	final TaskModel rowData;
	rowData = getItem ( position );
	// Check if an existing view is being reused, otherwise inflate the view
	/*if ( convertView == null ) {
		convertView = LayoutInflater.from ( getContext () ).inflate ( R.layout.list_row_list_activity, parent, false );
	}*/
	convertView = LayoutInflater.from ( getContext () ).inflate ( R.layout.list_row_list_activity, parent, false );
	// Lookup view for data population
	final TextView tvTitle = ( TextView ) convertView.findViewById ( R.id.listtitle );
	TextView tvLateTask = ( TextView ) convertView.findViewById ( R.id.latetask );
	TextView tvCurrentTask = ( TextView ) convertView.findViewById ( R.id.currentTask );
	final ImageView star = ( ImageView ) convertView.findViewById ( R.id.star );
	final ImageView chkBox = ( ImageView ) convertView.findViewById ( R.id.chkBox );
	RelativeLayout rowBg = ( RelativeLayout ) convertView.findViewById ( R.id.rowBg );
	CardView cardView = ( CardView ) convertView.findViewById ( R.id.card_view );

	// Populate the data into the template view using the data object
	tvTitle.setText ( rowData.getListTitle () );
	tvCurrentTask.setText ( String.valueOf ( rowData.getNumCurrentTask () ) );
	tvLateTask.setText ( String.valueOf ( rowData.getNumLateTask () ) );


	if ( rowData.isComplete () ) {
		rowBg.setAlpha ( ( float ) 0.5 );
		cardView.setAlpha ( ( float ) 0.5 );
	}

//	chkBox.setTag ( 1, position );

	chkBox.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			//TaskModel rowData=getItem ( position );
			rowData.setIsComplete ( String.valueOf ( ! rowData.isComplete () ) );
			if ( rowData.isComplete () ) {
				//todo don't know why cant use completeList to add element
				//completeList.add (rowData );
				TaskActivity.taskAdapterComplete.insert ( rowData, 0 );
				TaskActivity.taskAdapterInComplete.remove ( rowData );
//				inCompleteList.remove ( position );
			}
			else {
				TaskActivity.taskAdapterInComplete.insert ( rowData, 0 );
				TaskActivity.taskAdapterComplete.remove ( rowData );
			}
			Utility.setListViewHeightBasedOnChildren ( TaskActivity.listViewComplete );
			Utility.setListViewHeightBasedOnChildren ( TaskActivity.listViewIncomplete );
		}
	} );


	if ( rowData.isStar () ) {
		try {
			Log.d ( "Set Bg isStar=", "" + rowData.isStar () + ":" + rowData.getListTitle () );
			star.setBackground ( res.getDrawable ( R.mipmap.wl_task_detail_ribbon_selected ) );
		}
		catch ( NullPointerException e ) {
			Log.e ( "error on setBg Star", rowData.getListTitle () + ":" + e.toString () );
			Utility.setListViewHeightBasedOnChildren ( TaskActivity.listViewComplete );
			Utility.setListViewHeightBasedOnChildren ( TaskActivity.listViewIncomplete );
		}

	}
	else {
		try {
			Log.d ( "Set Bg unStar=", "" + rowData.isStar () + ":" + rowData.getListTitle () );
			star.setBackground ( res.getDrawable ( R.mipmap.wl_task_detail_ribbon ) );
		}
		catch ( NullPointerException e ) {
			Log.e ( "error on setBg unStar", rowData.getListTitle () + ":" + e.toString () );
			Utility.setListViewHeightBasedOnChildren ( TaskActivity.listViewComplete );
			Utility.setListViewHeightBasedOnChildren ( TaskActivity.listViewIncomplete );
		}
	}

	try {
		star.setOnClickListener ( new View.OnClickListener () {
			@Override public
			void onClick ( View v ) {
				Log.d ( "setOnClickStar ", "isComplete=" + ! rowData.isComplete () );
				if ( ! rowData.isComplete () ) {
					Log.d ( "setOnClickStar", "" + ! rowData.isComplete () );
					rowData.setIsStar ( ! rowData.isStar () );
					Utility.toggleImg ( v, res.getDrawable ( R.mipmap.wl_task_detail_ribbon ), res.getDrawable ( R.mipmap.wl_task_detail_ribbon_selected ) );
				}
			}
		} );

	}
	catch ( NullPointerException e ) {
		Log.e ( "error on setonClick", rowData.getListTitle () + ":" + e.toString () );
		Utility.setListViewHeightBasedOnChildren ( TaskActivity.listViewComplete );
		Utility.setListViewHeightBasedOnChildren ( TaskActivity.listViewIncomplete );
	}


	convertView.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			Intent intent = new Intent ( getContext (), TaskDetailActivity.class );
			intent.putExtra ( ListConst.KEY_TITLE, tvTitle.getText ().toString () );
			intent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK );
			getContext ().startActivity ( intent );

		}
	} );
	// Return the completed view to render on screen
	return convertView;
}

public
void addList ( TaskModel object, ListView listView ) {
	super.add ( object );
	Utility.setListViewHeightBasedOnChildren ( listView );
}

}