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

package com.vi8e.um.wunderlist.Activity.Landing;

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

import com.vi8e.um.wunderlist.Model.ListModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.provider.list.ListColumns;
import com.vi8e.um.wunderlist.utils.QueryHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates the use of {@link RecyclerView} with a {@link LinearLayoutManager} and a
 * {@link GridLayoutManager}.
 */
public class BasedRecycleFragment extends Fragment {

private static final String TAG = "SubTaskRecycleFragment";
protected static final String KEY_LAYOUT_MANAGER = "layoutManager";
private static final int SPAN_COUNT = 2;

private enum LayoutManagerType {
	GRID_LAYOUT_MANAGER,
	LINEAR_LAYOUT_MANAGER
}

protected LayoutManagerType          mCurrentLayoutManagerType;
protected RecyclerView               mRecyclerView;
protected LandingRecycleAdapter      mAdapter;
protected RecyclerView.LayoutManager mLayoutManager;
protected ArrayList<ListModel>       mDataset;

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
	Cursor c = QueryHelper.getListValueCursor(context);
	c.moveToFirst();
	Log.d("setUpAdapter", String.valueOf(c.getCount()));
	List<ContentValues> allListValues = QueryHelper.getValuesFromCursor(c, ListColumns.ALL_COLUMNS);
	mDataset = new ArrayList<>();
	for (ContentValues listValues : allListValues) {
		mDataset.add(new ListModel(listValues));
	}
}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState)
{
	View rootView = inflater.inflate(R.layout.recycler_view_frag, container, false);
	rootView.setTag(TAG);
	setView(savedInstanceState, rootView);
	mAdapter = new LandingRecycleAdapter(mDataset, getContext());
	// Set LandingRecycleAdapter as the adapter for RecyclerView.
	mRecyclerView.setAdapter(mAdapter);
	// END_INCLUDE(initializeRecyclerView)

	return rootView;
}

private void setView(Bundle savedInstanceState, View rootView) {
// BEGIN_INCLUDE(initializeRecyclerView)
	mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

	// LinearLayoutManager is used here, this will layout the elements in a similar fashion
	// to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
	// elements are laid out.
	mLayoutManager = new LinearLayoutManager(getActivity());
	mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

	if (savedInstanceState != null) {
		// Restore saved layout manager type.
		mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
				.getSerializable(KEY_LAYOUT_MANAGER);
	}
	setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
}

/**
 * Set RecyclerView's LayoutManager to the one given.
 *
 * @param layoutManagerType Type of layout manager to switch to.
 */
public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
	int scrollPosition = 0;

	// If a layout manager has already been set, get current scroll position.
	if (mRecyclerView.getLayoutManager() != null) {
		scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
				.findFirstCompletelyVisibleItemPosition();
	}

	switch (layoutManagerType) {
		case GRID_LAYOUT_MANAGER:
			mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
			mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
			break;
		case LINEAR_LAYOUT_MANAGER:
			mLayoutManager = new LinearLayoutManager(getActivity());
			mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
			break;
		default:
			mLayoutManager = new LinearLayoutManager(getActivity());
			mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
	}

	mRecyclerView.setLayoutManager(mLayoutManager);
	mRecyclerView.scrollToPosition(scrollPosition);
}

@Override
public void onSaveInstanceState(Bundle savedInstanceState) {
	// Save currently selected layout manager.
	savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
	super.onSaveInstanceState(savedInstanceState);
}


}
