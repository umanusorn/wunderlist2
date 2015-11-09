package com.vi8e.um.wunderlist.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.vi8e.um.wunderlist.Activity.Comment.CommentRecycleFragment;
import com.vi8e.um.wunderlist.Activity.Landing.LandingRecycleFragment;
import com.vi8e.um.wunderlist.Activity.TaskDetail.SubTaskRecycleFragment;
import com.vi8e.um.wunderlist.Model.ModelType;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.logger.Log;
/**
 * Created by Fixer on 11/5/2015.
 */
public class RecycleUtil {

private static final String TAG = RecycleUtil.class.getSimpleName ();
public static void setUpRecycleFragment(Bundle savedInstanceState, AppCompatActivity activity, String
		modelType)
{
	if (savedInstanceState == null) {
		FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

		Fragment fragment = null;
		switch (modelType) {
			case ModelType.LIST:
				fragment = new LandingRecycleFragment();
				break;
			case ModelType.SUB_TASK:
				fragment = new SubTaskRecycleFragment();
				break;
			case ModelType.TASK:

				break;
			case ModelType.COMMENT:
				fragment = new CommentRecycleFragment();
				break;
			default:
				Log.e(TAG,"ModelType ERROR:"+modelType);
		}
		transaction.replace(R.id.recycle_content_fragment, fragment);
		transaction.commit();
	}
}
}
