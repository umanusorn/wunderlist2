package com.vi8e.um.wunderlist.adater;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.vi8e.um.wunderlist.Activity.DeveloperActivity;
import com.vi8e.um.wunderlist.Model.ListConst;
import com.vi8e.um.wunderlist.Model.ListModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.provider.list.ListSelection;
import com.vi8e.um.wunderlist.util.Utility;

import java.util.ArrayList;


/**
 * Created by um.anusorn on 8/25/2015.
 */
public
class LandingListAdapter extends ArrayAdapter<ListModel> {


ArrayList<ListModel> lists;

public
LandingListAdapter ( Context context, ArrayList<ListModel> listModels ) {
	super ( context, 0, listModels );
	this.lists = listModels;
}

public
ArrayList<ListModel> getArrayList () {
	return lists;
}
ListModel listModel;

@Override
public
View getView ( final int position, View convertView, ViewGroup parent ) {

	listModel = getItem ( position );
	// Check if an existing view is being reused, otherwise inflate the view
	if ( convertView == null ) {
		convertView = LayoutInflater.from ( getContext () ).inflate ( R.layout.list_row_landing, parent, false );
	}

	final TextView tvTitle = ( TextView ) convertView.findViewById ( R.id.listtitle );
	TextView tvLateTask = ( TextView ) convertView.findViewById ( R.id.latetask );
	TextView tvCurrentTask = ( TextView ) convertView.findViewById ( R.id.currentTask );
	final ListView listView = ( ListView ) convertView.getParent ();

	tvTitle.setText ( listModel.getListTitle () );
	tvCurrentTask.setText ( String.valueOf ( listModel.getNumCurrentTask () ) );
	tvLateTask.setText ( String.valueOf ( listModel.getNumLateTask () ) );

	convertView.setOnClickListener ( getOnClick ( tvTitle,getContext () ) );
	convertView.setOnLongClickListener ( getOnLongClick () );

	// Return the completed view to render on screen
	return convertView;
}

@NonNull public static
View.OnClickListener getOnClick ( final TextView tvTitle, final Context context ) {
	return new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			//Intent  intent = new Intent ( getContext (), TaskActivity.class );
			Intent intent = new Intent (context, DeveloperActivity.class );
			intent.putExtra ( ListConst.KEY_TITLE, tvTitle.getText ().toString () );
			context.startActivity ( intent );

		}
	};
}

@NonNull private
View.OnLongClickListener getOnLongClick () {
	return new View.OnLongClickListener () {
		@Override public
		boolean onLongClick ( View v ) {

			Log.d ( "onLongClick", "" );
			Context context = getContext ();
			remove ( listModel );
			ListSelection where = new ListSelection ();
			where.id ( Long.parseLong ( listModel.getId () ) );
			where.delete ( context );
			return false;
		}
	};
}


public
void addList ( ListModel object, ListView listView ) {

	super.add ( object );
	Utility.setListViewHeightBasedOnChildren ( listView );
}
}