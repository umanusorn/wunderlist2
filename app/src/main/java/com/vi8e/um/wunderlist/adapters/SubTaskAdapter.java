package com.vi8e.um.wunderlist.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vi8e.um.wunderlist.Activity.TaskDetail.SubTaskRecycleAdapter;
import com.vi8e.um.wunderlist.Activity.TaskDetail.TaskDetailActivity;
import com.vi8e.um.wunderlist.Model.SubTaskModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.dialogs.CustomDialog;

import java.util.ArrayList;


/**
 * Created by um.anusorn on 8/25/2015.
 */
public class SubTaskAdapter extends ArrayAdapter<SubTaskModel> {

Context                 mContext;
Resources               res;
ArrayList<SubTaskModel> lists;
RelativeLayout          rootView;
TextView                tvTitle;
//ImageView chkBox;
private static final String TAG = SubTaskAdapter.class.getSimpleName();

public SubTaskAdapter(Context context,
                      ArrayList<SubTaskModel> lists)
{

	super(context, 0, lists);
	this.lists = lists;
	mContext = context;
	res = context.getResources();
}

public ArrayList<SubTaskModel> getArrayList() {
	return lists;
}

@Override
public View getView(final int position, View convertView, final ViewGroup parent) {
	Log.d("", "getView");
	final SubTaskModel rowData;
	rowData = getItem(position);

	convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_sub_task, parent, false);
	// Lookup view for data population
	tvTitle = (TextView) convertView.findViewById(R.id.subTaskTitle);
	ImageView chkBox = (ImageView) convertView.findViewById(R.id.chkBox);
	rootView = (RelativeLayout) convertView.findViewById(R.id.subTaskRootRow);
	setView(rowData, chkBox);
	return convertView;
}

public void setView(final SubTaskModel rowData, ImageView chkBox) {
	tvTitle.setText(rowData.getTitle());
	//UiMng.toggleImgCompleteData(chkBox, rowData, TaskDetailActivity.sContext);
	//Utility.toggleImgCompleteData ( chkBox, rowData, TaskDetailActivity.sContext );
	chkBox.setOnClickListener(SubTaskRecycleAdapter.onClickChkBox(rowData,TaskDetailActivity.thisActivity));
	rootView.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			CustomDialog.showUpdateSubTaskDialog(rowData,
			                                     TaskDetailActivity.thisActivity,
			                                     TaskDetailActivity.subTaskAdapter,
			                                     TaskDetailActivity.listViewSubTask);
		}
	});
}


private void setUpCompletedBg(SubTaskModel rowData, RelativeLayout rowBg, CardView cardView) {
	if (rowData.isComplete()) {
		rowBg.setAlpha((float) 0.5);
		cardView.setAlpha((float) 0.5);
	}
}


}
