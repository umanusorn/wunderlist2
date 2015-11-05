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
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.utils.ActivityUi;
import com.vi8e.um.wunderlist.utils.IntentCaller;
import com.vi8e.um.wunderlist.utils.RecycleUtil;
import com.vi8e.um.wunderlist.utils.UiMng;

/**
 * A simple launcher activity containing a summary sample description, sample log and a custom
 * {@link android.support.v4.app.Fragment} which can display a view.
 * <p>
 * For devices with displays with a width of 720dp or greater, the sample log is always visible,
 * on other devices it's visibility is controlled by an item on the Action Bar.
 */
public class LandingRecycleActivity extends AppCompatActivity {

public static final String TAG = "LandingRecycleActivity";

// Whether the Log Fragment is currently shown
private boolean           mLogShown;
private AppCompatActivity thisActivity;
private Menu              menu;
Toolbar mToolbar;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    thisActivity = this;
    setContentView(R.layout.activity_landing_recycle);

    RecycleUtil.setUpLandingRecycleFragment(savedInstanceState, thisActivity);
    ActivityUi.setToolBar(thisActivity, mToolbar, UiMng.getVersionName(getApplication()));
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
    int id = item.getItemId ();
    //noinspection SimplifiableIfStatement
    if ( id == R.id.menu_setting ) {
        IntentCaller.developer(thisActivity);
        return true;
    }
    if ( id == R.id.delete ) {
       // deleteSpecificList ( getApplicationContext (), currentList.getId () );
    }

    if ( id == R.id.duplicateList ) {
       // duplicateSpecificList ();
    }

    if ( id == R.id.menu_edit ) {
        //IntentCaller.listDetailActivity ( getApplicationContext (), currentList );
    }

    ActivityUi.setMenuNormal(thisActivity, menu);
    return super.onOptionsItemSelected(item);
}

}
