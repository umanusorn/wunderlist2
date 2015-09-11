package com.vi8e.um.wunderlist.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.util.QueryHelper;


public
class DeveloperActivity extends Activity {
Activity mActivity;

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_dev_database );


	Button genRecord = ( Button ) findViewById ( R.id.GenRecordBtn );
	Button removeLists = ( Button ) findViewById ( R.id.removeListBtn );
	Button viewDbBtn = ( Button ) findViewById ( R.id.viewDbBtn );
	Button genListTask = ( Button ) findViewById ( R.id.genListTask );
	Button viewDB2Btn = ( Button ) findViewById ( R.id.viewDB2Btn );
	Button removeTask = ( Button ) findViewById ( R.id.removeTaskBtn );


	genListTask.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			QueryHelper.genListAndTask ( getApplicationContext () );
		}
	} );

	viewDbBtn.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View view ) {
			Intent callIntent = new Intent ( getApplication (), ViewListDBActivity.class );
			startActivity ( callIntent );
		}
	} );

	viewDB2Btn.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View view ) {
			Intent callIntent = new Intent ( getApplication (), ViewTaskDBActivity.class );
			startActivity ( callIntent );
		}
	} );

	removeTask.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			QueryHelper.deleteTaskValue ( getApplicationContext () );
		}
	} );
	removeLists.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			QueryHelper.deleteListValue ( getApplicationContext () );
		}
	} );

}

@Override
public
boolean onCreateOptionsMenu ( Menu menu ) {
	return true;
}

@Override
public
boolean onOptionsItemSelected ( MenuItem item ) {
	int id = item.getItemId ();

	//noinspection SimplifiableIfStatement
	if ( id == android.R.id.home ) {
		super.onBackPressed ();
	}
	if ( id == R.id.action_settings ) {
		return true;
	}

	return super.onOptionsItemSelected ( item );
}
}
