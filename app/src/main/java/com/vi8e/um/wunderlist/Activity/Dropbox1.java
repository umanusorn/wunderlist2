package com.vi8e.um.wunderlist.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vi8e.um.wunderlist.R;


public
class Dropbox1 extends AppCompatActivity {

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_dropbox1 );
	Toolbar toolbar = ( Toolbar ) findViewById ( R.id.toolbar );
	setSupportActionBar ( toolbar );

	FloatingActionButton fab = ( FloatingActionButton ) findViewById ( R.id.fab );
	fab.setOnClickListener ( new View.OnClickListener () {
		@Override
		public
		void onClick ( View view ) {
			Snackbar.make ( view, "Replace with your own action", Snackbar.LENGTH_LONG )
			        .setAction ( "Action", null ).show ();
		}
	} );
}

}
