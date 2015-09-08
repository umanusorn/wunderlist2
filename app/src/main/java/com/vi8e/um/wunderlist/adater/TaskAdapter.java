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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vi8e.um.wunderlist.Activity.TaskActivity;
import com.vi8e.um.wunderlist.Model.TaskModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.util.IntentCaller;
import com.vi8e.um.wunderlist.util.Utility;

import java.util.ArrayList;


/**
 * Created by um.anusorn on 8/25/2015.
 */
public
class TaskAdapter extends ArrayAdapter<TaskModel> {

Context              mContext;
Resources            res;
ArrayList<TaskModel> lists;
boolean              mIsLongClick;

public
TaskAdapter ( Context context,
              ArrayList<TaskModel> lists ) {

	super ( context, 0, lists );
	this.lists = lists;
	mContext = context;
	res = context.getResources ();
}

public
ArrayList<TaskModel> getArrayList () {
	return lists;
}

@Override
public
View getView ( final int position, View convertView, final ViewGroup parent ) {

	final TaskModel rowData;
	// Get the data item for this position
	rowData = getItem ( position );

	convertView = LayoutInflater.from ( getContext () ).inflate ( R.layout.list_row_list_activity, parent, false );
	// Lookup view for data population
	final TextView tvTitle = ( TextView ) convertView.findViewById ( R.id.listtitle );
	TextView tvLateTask = ( TextView ) convertView.findViewById ( R.id.latetask );
	TextView tvCurrentTask = ( TextView ) convertView.findViewById ( R.id.currentTask );
	final ImageView star = ( ImageView ) convertView.findViewById ( R.id.star );
	final ImageView chkBox = ( ImageView ) convertView.findViewById ( R.id.chkBox );
	RelativeLayout rowBg = ( RelativeLayout ) convertView.findViewById ( R.id.rowBg );
	CardView cardView = ( CardView ) convertView.findViewById ( R.id.card_view );
	//ListView listView =(ListView)cardView.get

	// Populate the data into the template view using the data object
	tvTitle.setText ( rowData.getListTitle () );
	setUpCompletedBg ( rowData, rowBg, cardView );
	chkBox.setOnClickListener ( onClickChkBox ( rowData ) );
	setUpStar ( rowData, star, res );

	try {
		star.setOnClickListener ( new View.OnClickListener () {
			@Override public
			void onClick ( View v ) {
				onClickStar ( v, rowData );
			}
		} );

	}
	catch ( NullPointerException e ) {
		Log.e ( "error on setonClick", rowData.getListTitle () + ":" + e.toString () );
		Utility.setTaskListViewHeight ( TaskActivity.listViewComplete );
		Utility.setTaskListViewHeight ( TaskActivity.listViewIncomplete );
	}

	convertView.setOnClickListener ( onClickTask ( tvTitle, position ) );
	convertView.setOnLongClickListener ( onLongClickTask ( rowData, position ) );
	// Return the completed view to render on screen
	return convertView;
}

private
void setUpCompletedBg ( TaskModel rowData, RelativeLayout rowBg, CardView cardView ) {
	if ( rowData.isComplete () ) {
		rowBg.setAlpha ( ( float ) 0.5 );
		cardView.setAlpha ( ( float ) 0.5 );
	}
}

private static
void setUpStar ( TaskModel rowData, ImageView star, Resources res ) {
	if ( rowData.isStar () ) {
		try {
			Log.d ( "Set Bg isStar=", "" + rowData.isStar () + ":" + rowData.getListTitle () );
			star.setBackground ( res.getDrawable ( R.mipmap.wl_task_detail_ribbon_selected ) );
		}
		catch ( NullPointerException e ) {
			Log.e ( "error on setBg Star", rowData.getListTitle () + ":" + e.toString () );
			Utility.setTaskListViewHeight ( TaskActivity.listViewComplete );
			Utility.setTaskListViewHeight ( TaskActivity.listViewIncomplete );
		}

	}
	else {
		try {
			Log.d ( "Set Bg unStar=", "" + rowData.isStar () + ":" + rowData.getListTitle () );
			star.setBackground ( res.getDrawable ( R.mipmap.wl_task_detail_ribbon ) );
		}
		catch ( NullPointerException e ) {
			Log.e ( "error on setBg unStar", rowData.getListTitle () + ":" + e.toString () );
			Utility.setTaskListViewHeight ( TaskActivity.listViewComplete );
			Utility.setTaskListViewHeight ( TaskActivity.listViewIncomplete );
		}
	}
}

void onClickStar ( View v, TaskModel rowData ) {
	if ( ! rowData.isComplete () ) {
		Log.d ( "setOnClickStar", "" + ! rowData.isComplete () );
		Utility.toggleImgStarData ( v,
		                            rowData,
		                            res.getDrawable ( R.mipmap.wl_task_detail_ribbon ),
		                            res.getDrawable ( R.mipmap.wl_task_detail_ribbon_selected ) );
	}
}

@NonNull public
View.OnClickListener onClickTask ( final TextView tvTitle, final int position ) {
	return new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {

			TaskActivity.currentTask = getItem ( position );
			if ( ! mIsLongClick ) {

				IntentCaller.taskDetailActivity ( getContext (), tvTitle );
			}
			mIsLongClick = false;
		}
	};
}


@NonNull private
View.OnLongClickListener onLongClickTask ( TaskModel taskModel, final int position ) {
	return new View.OnLongClickListener () {
		@Override public
		boolean onLongClick ( View v ) {
			mIsLongClick = true;
			Log.d ( "onLongClick", "position=" + position );
			//remove ( listModel );
			TaskActivity.currentTask = getItem ( position );
			TaskActivity.setMenuList ();
			return false;
		}
	};
}

@NonNull public static
View.OnClickListener onClickChkBox ( final TaskModel rowData ) {
	return new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			//TaskModel rowData=getItem ( position );
			rowData.setIsComplete ( String.valueOf ( ! rowData.isComplete () ) );
			Log.d ( "TaskAdapter", "isComplete=" + rowData.getIsComplete () );
			if ( rowData.isComplete () ) {
				//todo don't know why cant use completeList to add element

				TaskActivity.taskAdapterComplete.insert ( rowData, 0 );
				TaskActivity.taskAdapterInComplete.remove ( rowData );
				//inCompleteList.remove ( position );
			}
			else {
				TaskActivity.taskAdapterInComplete.insert ( rowData, 0 );
				TaskActivity.taskAdapterComplete.remove ( rowData );
			}
			Utility.setTaskListViewHeight ( TaskActivity.listViewComplete );
			Utility.setTaskListViewHeight ( TaskActivity.listViewIncomplete );
		}
	};
}

public
void addList ( TaskModel object, ListView listView ) {
	super.add ( object );
	Utility.setTaskListViewHeight ( listView );
}

}