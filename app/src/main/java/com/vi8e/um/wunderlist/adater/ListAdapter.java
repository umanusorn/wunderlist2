package com.vi8e.um.wunderlist.adater;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.vi8e.um.wunderlist.Activity.ListActivity;
import com.vi8e.um.wunderlist.Model.ListConst;
import com.vi8e.um.wunderlist.Model.ListModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.util.Utility;

import java.util.ArrayList;


/**
 * Created by um.anusorn on 8/25/2015.
 */
public
class ListAdapter extends ArrayAdapter<ListModel> {

Context mContext;

public
ListAdapter ( Context context, ArrayList<ListModel> users ) {

	super ( context, 0, users );
	mContext = context;
}

@Override
public
View getView ( int position, View convertView, final ViewGroup parent ) {
	// Get the data item for this position
	ListModel listModel = getItem ( position );
	// Check if an existing view is being reused, otherwise inflate the view
	if ( convertView == null ) {
		convertView = LayoutInflater.from ( getContext () ).inflate ( R.layout.list_row_list_activity, parent, false );
	}
	// Lookup view for data population
	final TextView tvTitle = ( TextView ) convertView.findViewById ( R.id.listtitle );
	TextView tvLateTask = ( TextView ) convertView.findViewById ( R.id.latetask );
	TextView tvCurrentTask = ( TextView ) convertView.findViewById ( R.id.currentTask );
	final ImageView star = ( ImageView ) convertView.findViewById ( R.id.star );
	// Populate the data into the template view using the data object
	tvTitle.setText ( listModel.getListTitle () );
	tvCurrentTask.setText ( String.valueOf ( listModel.getNumCurrentTask () ) );
	tvLateTask.setText ( String.valueOf ( listModel.getNumLateTask () ) );

	star.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			if ( star.getId () == 0 ) {

				star.setBackground ( mContext.getResources ().getDrawable ( R.mipmap.wl_task_detail_ribbon ) );
				star.setId ( 1 );
			}
			else {
				star.setBackground ( mContext.getResources ().getDrawable ( R.mipmap.wl_task_detail_ribbon_selected ) );
				star.setId ( 0 );
			}
		}
	} );


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
void addList ( ListModel object, ListView listView ) {

	super.add ( object );
	Utility.setListViewHeightBasedOnChildren ( listView );
}
}