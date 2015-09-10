package com.vi8e.um.wunderlist.util;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

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
}
