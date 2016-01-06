/*
* Copyright 2013 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/


package com.vi8e.um.wunderlist.Activity.Landing;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.vi8e.um.wunderlist.Model.ListModel;
import com.vi8e.um.wunderlist.Model.ModelType;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.adapters.LandingListAdapter;
import com.vi8e.um.wunderlist.dialogs.CustomDialog;
import com.vi8e.um.wunderlist.utils.ActivityUi;
import com.vi8e.um.wunderlist.utils.IntentCaller;
import com.vi8e.um.wunderlist.utils.RecycleUtil;

/**
 * A simple launcher activity containing a summary sample description, sample log and a custom
 * {@link android.support.v4.app.Fragment} which can display a view.
 * <p>
 * For devices with displays with a width of 720dp or greater, the sample log is always visible,
 * on other devices it's visibility is controlled by an item on the Action Bar.
 */
public class LandingRecycleActivity extends AppCompatActivity {

public static final String TAG = "LandingRecycleActivity";

private AppCompatActivity thisActivity;
private Menu              menu;
Toolbar mToolbar;

public static ListModel          currentList;
static        ActionBar          mActionBar;
public static LandingListAdapter mLandingListAdapter;
public static int                currentListPosition;
public static boolean            isLongClick;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    thisActivity = this;
    setContentView(R.layout.activity_landing_recycle);
    RecycleUtil.setUpRecycleFragment(savedInstanceState, thisActivity, ModelType.LIST);
    // ActivityUi.setToolBar(thisActivity, mToolbar, UiMng.getVersionName(getApplication()));
    ActivityUi.setToolBar(thisActivity, mToolbar, "MyTask");
  /*  setFloatingActionBtnClickListener ( getWindow ().getDecorView ().findViewById ( android.R.id.content ), listView, mLandingListAdapter );*/
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    this.menu = menu;
    ActivityUi.setMenuNormal(thisActivity, menu);
    return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    //noinspection SimplifiableIfStatement
    if (id == R.id.menu_setting) {
        IntentCaller.developer(thisActivity);
        return true;
    }
    if (id == R.id.delete) {
        // deleteSpecificList ( getApplicationContext (), currentList.getId () );
    }

    if (id == R.id.duplicateList) {
        //duplicateSpecificList();
    }

    if (id == R.id.menu_edit) {
        //IntentCaller.listDetailActivity ( getApplicationContext (), currentList );
    }

    ActivityUi.setMenuNormal(thisActivity, menu);
    return super.onOptionsItemSelected(item);
}

private
void setFloatingActionBtnClickListener ( View view, final ListView listView, final LandingListAdapter landingListAdapter ) {
    com.getbase.floatingactionbutton.FloatingActionButton newListBtn
        = ( com.getbase.floatingactionbutton.FloatingActionButton ) view.findViewById ( R.id.action_a );
    com.getbase.floatingactionbutton.FloatingActionButton toDoBtn = ( com.getbase.floatingactionbutton.FloatingActionButton ) view.findViewById ( R.id.action_b );

    newListBtn.setOnClickListener ( new View.OnClickListener () {
        @Override public
        void onClick ( View v ) {
            CustomDialog.showAddListDialog(thisActivity, landingListAdapter, listView);
        }
    } );
    toDoBtn.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
            CustomDialog.showAddListDialog(thisActivity, landingListAdapter, listView);
        }
    });
}


/*
private void duplicateSpecificList() {
    ListModel newListModel = new ListModel(currentList);
    newListModel.setTitle(newListModel.getTitle() + " Copy");
    Uri uri = QueryHelper.addListToDB(getApplicationContext(), newListModel);
    //listSelection.delete ( getApplicationContext () );

    TaskSelection taskSelection = new TaskSelection();
    taskSelection.listid(currentList.getId());

    TaskCursor taskCursor = taskSelection.query(getApplicationContext());

    taskCursor.moveToFirst();

    List<ContentValues> allListValues = QueryHelper.getValuesFromCursor(taskCursor, TaskColumns.ALL_COLUMNS);
    for (int i = 0; i < allListValues.size(); i++) {
        ContentValues values = allListValues.get ( i );
        Log.d(TAG, "duplicating " + values.getAsString(TaskColumns.TASK_TITLE));
        values.put ( TaskColumns.LISTID, uri.getPathSegments ().get ( 1 ) );
        QueryHelper.addTaskToDB ( getApplicationContext (), new TaskModel( values ) );
    }
    QueryHelper.updateListAdapter(newListModel, listView);
}
*/


}
