package com.vi8e.um.wunderlist.adapters;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vi8e.um.wunderlist.Activity.TaskDetailActivity;
import com.vi8e.um.wunderlist.Model.CommentModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.utils.Utility;

import java.util.ArrayList;


/**
 * Created by um.anusorn on 8/25/2015.
 */
public
class CommentAdapter extends ArrayAdapter<CommentModel> {

Context                 mContext;
Resources               res;
ArrayList<CommentModel> lists;
RelativeLayout          rootView;
TextView                tvTitle;
//ImageView chkBox;
private static final String TAG = CommentAdapter.class.getSimpleName ();

public
CommentAdapter ( Context context,
                 ArrayList<CommentModel> lists ) {

	super ( context, 0, lists );
	this.lists = lists;
	mContext = context;
	res = context.getResources ();
}

public
ArrayList<CommentModel> getArrayList () {
	return lists;
}

@Override
public
View getView ( final int position, View convertView, final ViewGroup parent ) {
	Log.d ( "", "getView" );
	final CommentModel rowData;
	rowData = getItem ( position );

	convertView = LayoutInflater.from ( getContext () ).inflate ( R.layout.list_row_comment, parent, false );
	// Lookup view for data population
	tvTitle = ( TextView ) convertView.findViewById ( R.id.commentTitle );
	//ImageView chkBox = ( ImageView ) convertView.findViewById ( R.id.chkBox );
	rootView = ( RelativeLayout ) convertView.findViewById ( R.id.row_list_comment_root_view );
	setView ( rowData );
	return convertView;
}

public
void setView ( final CommentModel rowData ) {
	tvTitle.setText ( rowData.getTitle () );
rootView.setOnLongClickListener ( new View.OnLongClickListener () {
	@Override public
	boolean onLongClick ( View view ) {



		return false;
	}
} );
}


@NonNull public static
View.OnClickListener onClickChkBox ( final CommentModel rowData ) {
	return new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {

			//	rowData.setDateTime ( String.valueOf ( ! rowData.getDateTime () ) );
			Log.d ( TAG,"onClickBox" );
			Utility.toggleImgCompleteData ( v, rowData, TaskDetailActivity.sContext );
		}
	};
}

private
void setUpCompletedBg ( CommentModel rowData, RelativeLayout rowBg, CardView cardView ) {
	if ( rowData.isComplete () ) {
		rowBg.setAlpha ( ( float ) 0.5 );
		cardView.setAlpha ( ( float ) 0.5 );
	}
}

}