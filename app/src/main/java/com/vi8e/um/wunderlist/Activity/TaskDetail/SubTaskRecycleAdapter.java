/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.vi8e.um.wunderlist.Activity.TaskDetail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vi8e.um.wunderlist.Model.SubTaskModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.dialogs.CustomDialog;
import com.vi8e.um.wunderlist.utils.UiMng;

import java.util.ArrayList;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class SubTaskRecycleAdapter extends RecyclerView.Adapter<SubTaskRecycleAdapter.ViewHolder> {
private static final String TAG = "SubTaskRecycleAdapter";

private ArrayList<SubTaskModel> mDataSet;
private Context                 mContext;

@NonNull public static View.OnClickListener onClickChkBox(final SubTaskModel rowData) {
	return new View.OnClickListener() {
		@Override public void onClick(View v) {

			//	rowData.setDateTime ( String.valueOf ( ! rowData.getDateTime () ) );
			Log.d(TAG, "onClickBox");
			UiMng.toggleImgCompleteData(v, rowData, TaskDetailActivity.sContext);
			//Utility.setTaskListViewHeight ( TaskDetailActivity.listViewSubTask );
		}
	};
}

// BEGIN_INCLUDE(recyclerViewSampleViewHolder)

/**
 * Provide a reference to the type of views that you are using (custom ViewHolder)
 */
public static class ViewHolder extends RecyclerView.ViewHolder {
	final TextView tvTitle;

	public TextView getTvTitle() {
		return tvTitle;
	}

	ImageView chkBox;

	public ViewHolder(View view) {
		super(view);
		// Define click listener for the ViewHolder's View.
		view.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(TAG, "Element " + getPosition() + " clicked.");
			}
		});
		tvTitle = (TextView) view.findViewById(R.id.subTaskTitle);
		chkBox = (ImageView) view.findViewById(R.id.chkBox);

	}
}

/**
 * Initialize the dataset of the Adapter.
 *
 * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
 */
public SubTaskRecycleAdapter(ArrayList<SubTaskModel> dataSet, Context context) {
	mDataSet = dataSet;
	mContext = context;
}

// Create new views (invoked by the layout manager)
@Override
public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
	// Create a new view.
	View v = LayoutInflater.from(viewGroup.getContext())
	                       .inflate(R.layout.recycle_subtask, viewGroup, false);
	return new ViewHolder(v);
}

// Replace the contents of a view (invoked by the layout manager)
@Override
public void onBindViewHolder(ViewHolder viewHolder, final int position) {
	Log.d(TAG, "Element " + position + " set.");
	final SubTaskModel rowData = mDataSet.get(position);
	viewHolder.getTvTitle().setText(rowData.getTitle());
	UiMng.toggleImgCompleteData(viewHolder.chkBox, rowData, TaskDetailActivity.sContext);
	viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			CustomDialog.showUpdateSubTaskDialog(rowData,
			                                     TaskDetailActivity.thisActivity,
			                                     TaskDetailActivity.subTaskAdapter,
			                                     TaskDetailActivity.listViewSubTask);
		}
	});
	viewHolder.chkBox.setOnClickListener(onClickChkBox(rowData));
}

@Override
public int getItemCount() {
	return mDataSet.size();
}
}
