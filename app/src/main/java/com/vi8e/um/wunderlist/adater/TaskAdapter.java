package com.vi8e.um.wunderlist.adater;
import android.content.Context;
import android.content.Intent;
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
class TaskAdapter extends ArrayAdapter<TaskModel> {

Context              mContext;
Resources            res;
ArrayList<TaskModel> lists;
int                  position;
boolean              mIsLongClick;
TaskModel taskModel;
//ListView             listViewIncomplete, listViewComplete;

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
	// Get the data item for this position
	this.position = position;
	final TaskModel rowData;
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

	// Populate the data into the template view using the data object
	tvTitle.setText ( rowData.getListTitle () );
	tvCurrentTask.setText ( String.valueOf ( rowData.getNumCurrentTask () ) );
	tvLateTask.setText ( String.valueOf ( rowData.getNumLateTask () ) );

	if ( Boolean.valueOf ( rowData.isComplete () ) ) {
		rowBg.setAlpha ( ( float ) 0.5 );
		cardView.setAlpha ( ( float ) 0.5 );
	}

	chkBox.setOnClickListener ( onClickChkBox ( rowData ) );

	if ( Boolean.valueOf ( rowData.isStar () ) ) {
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
				//Log.d ( "setOnClickStar ", "isComplete=" + ! rowData.isComplete () );
				if ( ! rowData.isComplete () ) {
					Log.d ( "setOnClickStar", "" + ! rowData.isComplete () );
					rowData.setIsStar ( String.valueOf ( ! rowData.isStar () ) );
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


	convertView.setOnClickListener ( onClickTask ( tvTitle ) );
	convertView.setOnLongClickListener ( onLongClickTask () );
	// Return the completed view to render on screen
	return convertView;
}

@NonNull public
View.OnClickListener onClickTask ( final TextView tvTitle ) {
	return new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {

			if(!mIsLongClick){
				Intent intent = new Intent ( getContext (), TaskDetailActivity.class );
				intent.putExtra ( ListConst.KEY_TITLE, tvTitle.getText ().toString () );
				intent.addFlags ( Intent.FLAG_ACTIVITY_NEW_TASK );
				getContext ().startActivity ( intent );
			}
			mIsLongClick=false;


		}
	};
}


@NonNull private
View.OnLongClickListener onLongClickTask () {
	return new View.OnLongClickListener () {
		@Override public
		boolean onLongClick ( View v ) {
			mIsLongClick =true;
			Log.d ( "onLongClick", "" );
			//remove ( listModel );
			TaskActivity.currentTask =taskModel;
				TaskActivity.setMenuList ();
				//ListSelection where = new ListSelection ();
				//where.id ( Long.parseLong ( listModel.getId () ) );
				//where.delete ( context );
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
				Log.d ( "TaskAdapter","isComplete="+rowData.getIsComplete () );
				if ( rowData.isComplete () ) {
					//todo don't know why cant use completeList to add element

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
	};
}

public
void addList ( TaskModel object, ListView listView ) {
	super.add ( object );
	Utility.setListViewHeightBasedOnChildren ( listView );
}

}