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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vi8e.um.wunderlist.Model.SubTaskModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.provider.subtask.SubtaskColumns;
import com.vi8e.um.wunderlist.utils.QueryHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates the use of {@link RecyclerView} with a {@link LinearLayoutManager} and a
 * {@link GridLayoutManager}.
 */
public class SubTaskRecycleFragment extends Fragment {

private static final String TAG                = "SubTaskRecycleFragment";
protected RecyclerView               mRecyclerView;
protected SubTaskRecycleAdapter      mAdapter;
protected RecyclerView.LayoutManager mLayoutManager;
protected ArrayList<SubTaskModel>       mDataSet;

@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	initDataSet(getContext());
}

/**
 * Generates Strings for RecyclerView's adapter. This data would usually come
 * from a local content provider or remote server.
 */
private void initDataSet(Context context) {
	//todo change
	Cursor c = QueryHelper.getSubTaskValueCursor(context);
	c.moveToFirst();
	Log.d("setUpAdapter", String.valueOf(c.getCount()));

	//todo change
	List<ContentValues> allSubTaskValues = QueryHelper.getValuesFromCursor(c, SubtaskColumns.ALL_COLUMNS);
	mDataSet = new ArrayList<>();
	for (ContentValues SubTaskValues : allSubTaskValues) {
		//todo change
		mDataSet.add(new SubTaskModel(SubTaskValues));
	}
}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState)
{
	View rootView = inflater.inflate(R.layout.recycler_view_frag, container, false);
	rootView.setTag(TAG);
	setView(savedInstanceState, rootView);

	//todo change
	mAdapter = new SubTaskRecycleAdapter(mDataSet, getContext());
	// Set SubTaskRecycleAdapter as the adapter for RecyclerView.
	mRecyclerView.setAdapter(mAdapter);

	return rootView;
}

private void setView(Bundle savedInstanceState, View rootView) {
// BEGIN_INCLUDE(initializeRecyclerView)
	mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

	// LinearLayoutManager is used here, this will layout the elements in a similar fashion
	// to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
	// elements are laid out.
	mLayoutManager = new LinearLayoutManager(getActivity());

	if (savedInstanceState != null) {
		// Restore saved layout manager type.
	}
	setRecyclerViewLayoutManager();
}

public void setRecyclerViewLayoutManager() {
	int scrollPosition = 0;
	// If a layout manager has already been set, get current scroll position.
	if (mRecyclerView.getLayoutManager() != null) {
		scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
				.findFirstCompletelyVisibleItemPosition();
	}

	mLayoutManager = new LinearLayoutManager(getActivity());
	mRecyclerView.setLayoutManager(mLayoutManager);
	mRecyclerView.scrollToPosition(scrollPosition);
}

@Override
public void onSaveInstanceState(Bundle savedInstanceState) {
	// Save currently selected layout manager.
	super.onSaveInstanceState(savedInstanceState);
}
}
