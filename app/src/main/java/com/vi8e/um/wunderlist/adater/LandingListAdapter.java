package com.vi8e.um.wunderlist.adater;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
class LandingListAdapter extends ArrayAdapter<ListModel> {
public
LandingListAdapter ( Context context, ArrayList<ListModel> users ) {
	super ( context, 0, users );
}

@Override
public
View getView ( int position, View convertView, ViewGroup parent ) {
	// Get the data item for this position
	ListModel listModel = getItem ( position );
	// Check if an existing view is being reused, otherwise inflate the view
	if ( convertView == null ) {
		convertView = LayoutInflater.from ( getContext () ).inflate ( R.layout.list_row_landing, parent, false );
	}
	// Lookup view for data population
	final TextView tvTitle = ( TextView ) convertView.findViewById ( R.id.listtitle );
	TextView tvLateTask = ( TextView ) convertView.findViewById ( R.id.latetask );
	TextView tvCurrentTask = ( TextView ) convertView.findViewById ( R.id.currentTask );


	// Populate the data into the template view using the data object
	tvTitle.setText ( listModel.getListTitle () );
	tvCurrentTask.setText ( String.valueOf ( listModel.getNumCurrentTask () ) );
	tvLateTask.setText ( String.valueOf ( listModel.getNumLateTask () ) );

	convertView.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			Intent  intent = new Intent ( getContext (), ListActivity.class );
			intent.putExtra ( ListConst.KEY_TITLE,tvTitle.getText ().toString () );
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