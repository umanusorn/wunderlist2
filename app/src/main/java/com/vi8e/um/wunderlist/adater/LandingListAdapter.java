package com.vi8e.um.wunderlist.adater;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.nhaarman.listviewanimations.itemmanipulation.expandablelistitem.ExpandableListItemAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.undo.UndoAdapter;
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
class LandingListAdapter extends ExpandableListItemAdapter<ListModel> implements UndoAdapter {


ArrayList<ListModel> lists;
boolean              mIsLongClick;
ListModel listModel;
Context mContext;
public
LandingListAdapter ( Context context, ArrayList<ListModel> listModels ) {
	super (context);
	//super ( context, 0, listModels );
	this.lists = listModels;
	this.mContext=context;
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
		convertView = LayoutInflater.from ( mContext ).inflate ( R.layout.list_row_landing, parent, false );
	}

	final TextView tvTitle = ( TextView ) convertView.findViewById ( R.id.listtitle );
	TextView tvLateTask = ( TextView ) convertView.findViewById ( R.id.latetask );
	TextView tvCurrentTask = ( TextView ) convertView.findViewById ( R.id.currentTask );
	final ListView listView = ( ListView ) convertView.getParent ();

	tvTitle.setText ( listModel.getTitle () );

	tvCurrentTask.setText ( String.valueOf ( listModel.getNumCurrentTask () ) );
	tvLateTask.setText ( String.valueOf ( listModel.getNumLateTask () ) );
	convertView.setOnClickListener ( getOnClick ( listModel, mContext) );
	convertView.setOnLongClickListener ( getOnLongClick (listModel,position) );

	tvLateTask.setVisibility ( View.GONE );
	tvCurrentTask.setText ( String.valueOf ( getCurrentTaskCount ( listModel,mContext ) ));

	// Return the completed view to render on screen
	return convertView;
}

@NonNull @Override public
View getTitleView ( int i, View view, @NonNull ViewGroup viewGroup ) {
	TextView tv = (TextView) view;
	if (tv == null) {
		tv = new TextView(mContext );
	}
	//tv.setText(mContext.getString(R.string.expandorcollapsecard, (int) getItem(position)));
	return tv;
}

@NonNull @Override public
View getContentView ( int i, View view, @NonNull ViewGroup viewGroup ) {
	return null;
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


public void swapItems(){

}

@Override
public boolean hasStableIds(){
	return true;
}


@NonNull
@Override
public View getUndoView(final int position, final View convertView, @NonNull final ViewGroup parent) {
	View view = convertView;
	if (view == null) {
		//view = LayoutInflater.from(mContext).inflate(R.layout.undo_row, parent, false);
	}
	return view;
}

@Override
public void swapItems(int i, int i2) {
	super.swapItems(i,i2);
}

@NonNull @Override public
View getUndoClickView ( @NonNull View view ) {
	return null;
}

@Override
public long getItemId(final int position) {
	return getItem(position).hashCode();
}
/*


hasStableIds() to return true. For stable id's you could do something like
public long getItemId(int) { return getItem(i).hashCode(); }
*/


public
void addList ( ListModel object, ListView listView ) {

	super.add ( object );
	Utility.setTaskListViewHeight ( listView );
}
}