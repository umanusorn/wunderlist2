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


package com.vi8e.um.wunderlist.recyclerview;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.logger.Log;
import com.vi8e.um.wunderlist.logger.LogWrapper;
import com.vi8e.um.wunderlist.logger.MessageOnlyLogFilter;
import com.vi8e.um.wunderlist.utils.ActivityUi;
import com.vi8e.um.wunderlist.utils.UiMng;

/**
 * A simple launcher activity containing a summary sample description, sample log and a custom
 * {@link android.support.v4.app.Fragment} which can display a view.
 * <p>
 * For devices with displays with a width of 720dp or greater, the sample log is always visible,
 * on other devices it's visibility is controlled by an item on the Action Bar.
 */
public class RecycleActivityOriginal extends AppCompatActivity {

public static final String TAG = "RecycleActivityOriginal";

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

    if (savedInstanceState == null) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        transaction.replace(R.id.sample_content_fragment, fragment);
        transaction.commit();
    }

	ActivityUi.setToolBar(thisActivity,mToolbar, UiMng.getVersionName(getApplication()));
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    this.menu = menu;
    ActivityUi.setMenuNormal(thisActivity, menu);
    return true;
}
/*
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem logToggle = menu.findItem(R.id.menu_toggle_log);
        logToggle.setVisible(findViewById(R.id.sample_output) instanceof ViewAnimator);
        logToggle.setTitle(mLogShown ? R.string.sample_hide_log : R.string.sample_show_log);

        return super.onPrepareOptionsMenu(menu);
    }*/

@Override
public boolean onOptionsItemSelected(MenuItem item) {
        /*switch(item.getItemId()) {
            case R.id.menu_toggle_log:
                mLogShown = !mLogShown;
                ViewAnimator output = (ViewAnimator) findViewById(R.id.sample_output);
                if (mLogShown) {
                    output.setDisplayedChild(1);
                } else {
                    output.setDisplayedChild(0);
                }
                supportInvalidateOptionsMenu();
                return true;
        }*/
    return super.onOptionsItemSelected(item);
}

/** Create a chain of targets that will receive log data */

public void initializeLogging() {
    // Wraps Android's native log framework.
    LogWrapper logWrapper = new LogWrapper();
    // Using Log, front-end to the logging chain, emulates android.util.log method signatures.
    Log.setLogNode(logWrapper);

    // Filter strips out everything except the message text.
    MessageOnlyLogFilter msgFilter = new MessageOnlyLogFilter();
    logWrapper.setNext(msgFilter);

    // On screen logging via a fragment with a TextView.
       /* LogFragment logFragment = (LogFragment) getSupportFragmentManager()
                .findFragmentById(R.id.log_fragment);
        msgFilter.setNext(logFragment.getLogView());*/

    Log.i(TAG, "Ready");
}
}
