package com.vi8e.um.wunderlist.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.utils.ActivityUi;


public
class CommentActivity extends AppCompatActivity {
Toolbar mToolbar;
String title ="Comment";
@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_comment );
	mToolbar = ( Toolbar ) findViewById ( R.id.toolbar );
	setSupportActionBar ( mToolbar );
	ActivityUi.setToolBar ( this, mToolbar, title );
	mToolbar.setBackgroundColor ( getResources ().getColor( R.color.blue_500 ) );
}


}
