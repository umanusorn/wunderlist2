package com.vi8e.um.wunderlist.adater;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.vi8e.um.wunderlist.Activity.LandingActivity;
import com.vi8e.um.wunderlist.Model.ListModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.provider.list.ListSelection;
import com.vi8e.um.wunderlist.provider.task.TaskSelection;
import com.vi8e.um.wunderlist.util.IntentCaller;
import com.vi8e.um.wunderlist.util.Utility;

import java.util.ArrayList;


/**
 * Created by um.anusorn on 8/25/2015.
 */
public
class SubTaskAdapter extends ArrayAdapter<ListModel> {


ArrayList<ListModel> lists;
boolean              mIsLongClick;
ListModel listModel;

public
SubTaskAdapter ( Context context, ArrayList<ListModel> listModels ) {
	super ( context, 0, listModels );
	this.lists = listModels;
}

public
ArrayList<ListModel> getArrayList () {
	return lists;
}

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

	tvTitle.setText ( listModel.getTitle () );

	tvCurrentTask.setText ( String.valueOf ( listModel.getNumCurrentTask () ) );
	tvLateTask.setText ( String.valueOf ( listModel.getNumLateTask () ) );
	convertView.setOnClickListener ( getOnClick ( listModel, getContext () ) );
	convertView.setOnLongClickListener ( getOnLongClick (listModel,position) );

	tvLateTask.setVisibility ( View.GONE );
	tvCurrentTask.setText ( String.valueOf ( getCurrentTaskCount ( listModel,getContext () ) ));

	// Return the completed view to render on screen
	return convertView;
}


int getCurrentTaskCount(ListModel listModel,Context context){
	TaskSelection where = new TaskSelection ();

	where.listid ( listModel.getId ()  );
	int count = where.count ( context.getContentResolver () );
	//Log.d ( "getCurrentTaskCount", "listid=" + listModel.getId ()+" count=" +count);
	return count;
}

@NonNull private
View.OnLongClickListener getOnLongClick ( final ListModel listModel, final int position ) {
	return new View.OnLongClickListener () {
		@Override public
		boolean onLongClick ( View v ) {
			mIsLongClick =true;
			Log.d ( "onLongClick", "position="+ position );
			//remove ( listModel );
			LandingActivity.currentList=listModel;
			LandingActivity.setMenuList ();
			LandingActivity.currentListPosition=position;
			ListSelection where = new ListSelection ();
			//where.id ( Long.parseLong ( listModel.getId () ) );
			//where.delete ( context );
			return false;
		}
	};
}

@NonNull public
View.OnClickListener getOnClick ( final ListModel listModel, final Context context) {
	return new View.OnClickListener () {

		@Override public
		void onClick ( View v ) {

			Log.d ( "onClick","isLongClick="+mIsLongClick );
			if(!mIsLongClick){
				IntentCaller.taskActivity ( context, listModel );
			}
			mIsLongClick=false;
		}
	};
}


public
void addList ( ListModel object, ListView listView ) {

	super.add ( object );
	Utility.setTaskListViewHeight ( listView );
}
}