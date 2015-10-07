package com.vi8e.um.wunderlist.utils;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
void setToolBar ( final AppCompatActivity listDetailActivity, Toolbar toolbar, String title ) {
	toolbar = ( Toolbar ) listDetailActivity.findViewById ( R.id.toolbar );
	listDetailActivity.setSupportActionBar ( toolbar );
	toolbar.setVisibility ( View.VISIBLE );
	listDetailActivity.getSupportActionBar ().setTitle ( title );
	toolbar.setTitle ( title );
	listDetailActivity.getSupportActionBar ().setDisplayShowTitleEnabled ( true );
	listDetailActivity.getSupportActionBar ().setDisplayHomeAsUpEnabled ( true );
	listDetailActivity.getSupportActionBar ().setDisplayShowHomeEnabled ( true );
	toolbar.setNavigationOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			listDetailActivity.finish ();
		}
	} );
}

public static
void setActiveToolBar (AppCompatActivity thisActivity,Toolbar toolbar,String title,Context context) {

	//toolbar
	thisActivity.getSupportActionBar ().setTitle ( title);
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
void setActiveList ( RelativeLayout rowListRootView, Context context ) {
	rowListRootView.setBackgroundColor (
			context.getResources ().getColor (
					R.color.blue_400_trans50 ) );
}
}
