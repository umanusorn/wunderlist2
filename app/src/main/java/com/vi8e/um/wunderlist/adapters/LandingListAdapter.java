package com.vi8e.um.wunderlist.adapters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nhaarman.listviewanimations.itemmanipulation.expandablelistitem.ExpandableListItemAdapter;
import com.nhaarman.listviewanimations.itemmanipulation.swipedismiss.undo.UndoAdapter;
import com.vi8e.um.wunderlist.Activity.LandingActivity;
import com.vi8e.um.wunderlist.Model.ListModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.provider.task.TaskSelection;
import com.vi8e.um.wunderlist.utils.IntentCaller;
import com.vi8e.um.wunderlist.utils.Utility;

import java.util.ArrayList;

/**
 * Created by um.anusorn on 8/25/2015.
 */
public
class LandingListAdapter extends ExpandableListItemAdapter<ListModel> implements UndoAdapter {

static ArrayList<ListModel> lists;
ListModel listModel;
Context   mContext;
LandingListAdapter thisAdapter;

private static final String TAG = LandingActivity.class.getSimpleName ();

public
LandingListAdapter ( Context context, ArrayList<ListModel> listModels ) {
	super ( context );
	this.lists = listModels;
	this.mContext = context;
	thisAdapter=this;

}

public
ArrayList<ListModel> getArrayList () {
	return lists;
}

@Override
public
View getView ( final int position, View convertView, ViewGroup parent ) {

	listModel = getItem ( position );
	Log.d ( TAG, "getView title=" + listModel.getTitle () );

	// Check if an existing view is being reused, otherwise inflate the view
	if ( convertView == null ) {
		convertView = LayoutInflater.from ( mContext ).inflate ( R.layout.list_row_landing, parent, false );
	}
	listModel.setRootView ( convertView );
	final TextView tvTitle = ( TextView ) convertView.findViewById ( R.id.listtitle );
	TextView tvLateTask = ( TextView ) convertView.findViewById ( R.id.latetask );
	TextView tvCurrentTask = ( TextView ) convertView.findViewById ( R.id.currentTask );
	final ListView listView = ( ListView ) convertView.getParent ();

	tvTitle.setText ( listModel.getTitle () );

	tvCurrentTask.setText ( String.valueOf ( listModel.getNumCurrentTask () ) );
	tvLateTask.setText ( String.valueOf ( listModel.getNumLateTask () ) );
	tvLateTask.setVisibility ( View.GONE );
	tvCurrentTask.setText ( String.valueOf ( getCurrentTaskCount ( listModel, mContext ) ) );

	convertView.setOnClickListener ( getOnClick ( listModel, mContext, position ) );
	convertView.setOnLongClickListener ( getOnLongClick ( listModel, position ) );


	convertView.setOnTouchListener ( new View.OnTouchListener () {
		@Override public
		boolean onTouch ( View v, MotionEvent event ) {
			//Log.d ( TAG, "onTouch" );
			//RelativeLayout rowListRootView = listModel.getRowListRootView ();
			if ( ! LandingActivity.isDragging () ) {
				try {
					//setActiveListBgColor ( position );
				}
				catch ( IndexOutOfBoundsException e ) {

					Log.e ( TAG, e.getMessage () );
					listView.deferNotifyDataSetChanged ();
					LandingActivity.setUpOnResume ();
					//setActiveListBgColor ( position );
				}
			}
			else {
				try {

					setInActiveListBgColor ( position,thisAdapter,mContext );
				}
				catch ( IndexOutOfBoundsException e ) {

					Log.e ( TAG, e.getMessage () );
					listView.deferNotifyDataSetChanged ();
					LandingActivity.setUpOnResume ();
					setInActiveListBgColor ( position,thisAdapter,mContext );
				}
			}

			return false;
		}
	} );

	// Return the completed view to render on screen
	return convertView;
}

public
void setActiveListBgColor ( int position ) {
	RelativeLayout rowListRootView = getItem ( position ).getRowListRootView ();
	rowListRootView.setBackgroundColor ( mContext.getResources ().getColor ( R.color.red_400 ) );
}

public static
void setInActiveListBgColor ( int position ,LandingListAdapter landingListAdapter,Context context) {
	RelativeLayout rowListRootView = landingListAdapter.getItem ( position ).getRowListRootView ();
	rowListRootView.setBackgroundColor ( context.getResources ().getColor ( R.color.white ) );
	rowListRootView.setAlpha ( ( float ) 1.0 );
}

@NonNull @Override public
View getTitleView ( int i, View view, @NonNull ViewGroup viewGroup ) {
	TextView tv = ( TextView ) view;
	if ( tv == null ) {
		tv = new TextView ( mContext );
	}
	//tv.setText(mContext.getString(R.string.expandorcollapsecard, (int) getItem(position)));
	return tv;
}

@NonNull @Override public
View getContentView ( int i, View view, @NonNull ViewGroup viewGroup ) {
	return null;
}

int getCurrentTaskCount ( ListModel listModel, Context context ) {
	Log.d ( TAG, "getCurrentTaskCount" );
	TaskSelection where = new TaskSelection ();
	where.listid ( listModel.getId () ).and ().iscomplete ( String.valueOf ( Boolean.FALSE ) );
	int count = where.count ( context.getContentResolver () );
	//Log.d ( "getCurrentTaskCount", "listid=" + listModel.getId ()+" count=" +count);
	return count;
}

@NonNull private
View.OnLongClickListener getOnLongClick ( final ListModel listModel, final int position ) {
	return new View.OnLongClickListener () {
		@Override public
		boolean onLongClick ( View v ) {
			LandingActivity.isLongClick = true;
			Log.d ( "onLongClick", "position=" + position + "List title= " + listModel.getTitle () );
			return false;
		}
	};
}

@NonNull public
View.OnClickListener getOnClick ( final ListModel listModel, final Context context, final int position ) {
	return new View.OnClickListener () {

		@Override public
		void onClick ( View v ) {

			Log.d ( "onClick", "isLongClick=" + LandingActivity.isLongClick );

			onClickList ( position, context, listModel );
		}
	};
}

public
void onClickList ( int position, Context context, ListModel listModel ) {
	LandingActivity.setCurrentList ( position );
	//if ( ! isLongClick ) {
	IntentCaller.taskActivity ( context, listModel );
	//}
	LandingActivity.isLongClick = false;
}

public
void moveItem ( ListModel item, int newIndex ) {

	if ( lists != null ) {
		this.remove ( item );
		this.add ( newIndex, item );

		Log.d ( "moveItem", " newIndex=" + newIndex );
		notifyDataSetChanged ();
	}
}


@Override
public
boolean hasStableIds () {
	return true;
}


@NonNull
@Override
public
View getUndoView ( final int position, final View convertView, @NonNull final ViewGroup parent ) {
	View view = convertView;
	if ( view == null ) {
		//view = LayoutInflater.from(mContext).inflate(R.layout.undo_row, parent, false);
	}
	return view;
}

@NonNull @Override public
View getUndoClickView ( @NonNull View view ) {
	return null;
}

@Override
public
long getItemId ( final int position ) {
	return getItem ( position ).hashCode ();
}

/*@Override
public
void swapItems ( int i, int i2 ) {
	super.swapItems ( i, i2 );
}*/
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