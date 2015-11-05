package com.vi8e.um.wunderlist.utils;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.vi8e.um.wunderlist.Activity.Landing.LandingRecycleFragment;
import com.vi8e.um.wunderlist.R;
/**
 * Created by Fixer on 11/5/2015.
 */
public class RecycleUtil {
public static void setUpLandingRecycleFragment(Bundle savedInstanceState, AppCompatActivity activity) {
    if (savedInstanceState == null) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        LandingRecycleFragment fragment = new LandingRecycleFragment();
        transaction.replace(R.id.content_fragment, fragment);
        transaction.commit();
    }
}
}
