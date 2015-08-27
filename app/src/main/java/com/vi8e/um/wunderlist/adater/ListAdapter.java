package com.vi8e.um.wunderlist.adater;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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
class ListAdapter extends ArrayAdapter<TaskModel> {

Context              mContext;
Resources            res;
TaskModel            rowData;
int                  position;
ArrayList<TaskModel > mArrayList;

public
ListAdapter ( Context context, ArrayList<TaskModel> arrayList ) {

	super ( context, 0, arrayList );
	mArrayList = arrayList;
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



	// Populate the data into the template view using the data object
	tvTitle.setText ( rowData.getListTitle () );
	tvCurrentTask.setText ( String.valueOf ( rowData.getNumCurrentTask () ) );
	tvLateTask.setText ( String.valueOf ( rowData.getNumLateTask () ) );

	final View finalConvertView = convertView;
	final View finalConvertView1 = convertView;
	chkBox.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			//finalConvertView.setVisibility ( View.GONE );
			mArrayList.remove ( position );
			Utility.setListViewHeightBasedOnChildren ( ( ListView ) parent );
		}
	} );

	star.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			Utility.toggleImg ( v, res.getDrawable ( R.mipmap.wl_task_detail_ribbon ), res.getDrawable ( R.mipmap.wl_task_detail_ribbon_selected ) );
			rowData.setIsStar ( !rowData.isStar () );

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
void addList ( TaskModel object, ListView listView ) {
	super.add ( object );
	Utility.setListViewHeightBasedOnChildren ( listView );
}

}