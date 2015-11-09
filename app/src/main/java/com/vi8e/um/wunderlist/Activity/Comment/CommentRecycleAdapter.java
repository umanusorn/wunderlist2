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

package com.vi8e.um.wunderlist.Activity.Comment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vi8e.um.wunderlist.Model.SubTaskModel;
import com.vi8e.um.wunderlist.R;

import java.util.ArrayList;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class CommentRecycleAdapter extends RecyclerView.Adapter<CommentRecycleAdapter.ViewHolder> {
private static final String TAG = "SubTaskRecycleAdapter";

private ArrayList<SubTaskModel> mDataSet;
private Context              mContext;

// BEGIN_INCLUDE(recyclerViewSampleViewHolder)

/**
 * Provide a reference to the type of views that you are using (custom ViewHolder)
 */
public static class ViewHolder extends RecyclerView.ViewHolder {
	final TextView tvTitle;
	public TextView getTvTitle() {
		return tvTitle;
	}

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

	}

}

/**
 * Initialize the dataset of the Adapter.
 *
 * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
 */
public CommentRecycleAdapter(ArrayList<SubTaskModel> dataSet, Context context) {
	mDataSet = dataSet;
	mContext = context;
}

// Create new views (invoked by the layout manager)
@Override
public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
	// Create a new view.
	View v = LayoutInflater.from(viewGroup.getContext())
	                       .inflate(R.layout.recycle_landing, viewGroup, false);
	return new ViewHolder(v);
}

// Replace the contents of a view (invoked by the layout manager)
@Override
public void onBindViewHolder(ViewHolder viewHolder, final int position) {
	Log.d(TAG, "Element " + position + " set.");
	SubTaskModel listModel = mDataSet.get(position);
	viewHolder.getTvTitle().setText(listModel.getTitle());
	viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {

		}
	});

}

@Override
public int getItemCount() {
	return mDataSet.size();
}
}
