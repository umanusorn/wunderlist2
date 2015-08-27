package com.vi8e.um.wunderlist.adater;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vi8e.um.wunderlist.Activity.ListActivity;
import com.vi8e.um.wunderlist.Model.ListConst;
import com.vi8e.um.wunderlist.Model.TaskModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.util.Utility;

import java.util.ArrayList;


/**
 * Created by um.anusorn on 8/25/2015.
 */
public
class TaskAdapter extends ArrayAdapter<TaskModel> {

Context              mContext;
Resources            res;
TaskModel            rowData;
int                  position;
ArrayList<TaskModel> inCompleteList;
ArrayList<TaskModel> completeList;
ListView             listViewIncomplete, listViewComplete;

public
TaskAdapter ( Context context,
							ArrayList<TaskModel> listInComplete,
							ArrayList<TaskModel> listComplete,
							ListView listViewComplete,
							ListView listViewIncomplete ) {

	super ( context, 0, listInComplete );
	this.inCompleteList = listInComplete;
	this.completeList = listComplete;
	this.listViewComplete = listViewComplete;
	this.listViewIncomplete = listViewIncomplete;
	mContext = context;
	res = context.getResources ();
}

@Override
public
View getView ( final int position, View convertView, final ViewGroup parent ) {
	// Get the data item for this position
	this.position = position;
	rowData = getItem ( position );
	// Check if an existing view is being reused, otherwise inflate the view
	if ( convertView == null ) {
		convertView = LayoutInflater.from ( getContext () ).inflate ( R.layout.list_row_list_activity, parent, false );
	}

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


	chkBox.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {

			rowData.setIsComplete ( ! rowData.isComplete () );
			if ( rowData.isComplete () ) {
				//todo don't know why cant use completeList to add element
				//completeList.add (rowData );
				ListActivity.taskAdapterComplete.add ( rowData );
				ListActivity.taskAdapterInComplete.remove ( rowData );
//				inCompleteList.remove ( position );
			}
			else {
				ListActivity.taskAdapterInComplete.add ( rowData );
				ListActivity.taskAdapterComplete.remove ( rowData );
			}
			Utility.setListViewHeightBasedOnChildren ( ListActivity.listViewComplete );
			Utility.setListViewHeightBasedOnChildren ( ListActivity.listViewIncomplete );
		}
	} );

	if ( ! rowData.isComplete () ) {
		star.setOnClickListener ( new View.OnClickListener () {
			@Override public
			void onClick ( View v ) {
				Utility.toggleImg ( v, res.getDrawable ( R.mipmap.wl_task_detail_ribbon ), res.getDrawable ( R.mipmap.wl_task_detail_ribbon_selected ) );
				rowData.setIsStar ( ! rowData.isStar () );
			}
		} );
	}

	convertView.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			Intent intent = new Intent ( getContext (), ListActivity.class );
			intent.putExtra ( ListConst.KEY_TITLE, tvTitle.getText ().toString () );
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