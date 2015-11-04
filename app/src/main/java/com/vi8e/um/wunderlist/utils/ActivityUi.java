package com.vi8e.um.wunderlist.utils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;

import com.vi8e.um.wunderlist.R;


/**
 * Created by um.anusorn on 9/10/2015.
 */
public
class ActivityUi {
public static
Toolbar initToolbar (Toolbar toolbar,AppCompatActivity appCompatActivity,ActionBar actionBar) {
	toolbar = ( Toolbar ) appCompatActivity.findViewById ( R.id.toolbar );
	appCompatActivity.setSupportActionBar(toolbar);
	actionBar = appCompatActivity.getSupportActionBar();
	toolbar.setVisibility ( View.VISIBLE );
	return toolbar;
}

public static
void setMenuNormal ( AppCompatActivity thisActivity, Menu menu ) {
	Log.d("", "setMenuNormal");
	menu.clear ();
	thisActivity.getMenuInflater ().inflate ( R.menu.menu_main_normal, menu );
}

public static
void setToolBar ( final AppCompatActivity activity, Toolbar toolbar, String title ) {
	toolbar = ( Toolbar ) activity.findViewById ( R.id.toolbar );
	activity.setSupportActionBar(toolbar);

	toolbar.setVisibility(View.VISIBLE);
	ActionBar actionBar = activity.getSupportActionBar();
	toolbar.setTitle ( title );
	actionBar.setTitle(title);
	actionBar.setDisplayShowTitleEnabled(true);
	actionBar.setDisplayHomeAsUpEnabled(true);
	actionBar.setDisplayShowHomeEnabled(true);
	toolbar.setNavigationOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			activity.finish();
		}
	} );
}

public static
void setActiveToolBar (AppCompatActivity thisActivity,Toolbar toolbar,String title,Context context) {

	//toolbar
	thisActivity.getSupportActionBar ().setTitle ( title );
	toolbar.setBackgroundDrawable ( new ColorDrawable ( context.getResources ().getColor ( R.color.blue_400 ) ) );
}

public static
void setInActiveToolBar (Toolbar toolbar,Context context) {

	toolbar.setTitle ( "" );
	toolbar.setBackgroundDrawable ( new ColorDrawable ( context.getResources ().getColor ( R.color.transparent ) ) );
}

public static
void setMenuList (AppCompatActivity thisActivity,Menu menu) {
	menu.clear ();
	thisActivity.getMenuInflater ().inflate ( R.menu.menu_main_list_toggle, menu );
//	mActionBar.setBackgroundDrawable ( new ColorDrawable (sContext.getResources ().getColor ( R.color.blue_300 )) );

}

public static
void setMenuComment (AppCompatActivity thisActivity,Menu menu) {
	menu.clear ();
	thisActivity.getMenuInflater ().inflate ( R.menu.menu_comment_toggle, menu );
//	mActionBar.setBackgroundDrawable ( new ColorDrawable (sContext.getResources ().getColor ( R.color.blue_300 )) );

}

public static
void setActiveList ( RelativeLayout rowListRootView, Context context ) {
	rowListRootView.setBackgroundColor (
			context.getResources ().getColor (
					R.color.blue_400_trans50 ) );
}
}
