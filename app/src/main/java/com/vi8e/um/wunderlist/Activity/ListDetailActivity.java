package com.vi8e.um.wunderlist.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.vi8e.um.wunderlist.Model.ListConst;
import com.vi8e.um.wunderlist.Model.ListModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.provider.list.ListColumns;
import com.vi8e.um.wunderlist.util.ActivityUi;


public
class ListDetailActivity extends AppCompatActivity {

Toolbar toolBar;
String listId;
EditText listName;
@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_list_detail );
	ActivityUi.setToolBar ( this, toolBar, "" );

	Intent intent = getIntent ();
	Bundle bundle = intent.getExtras ();
	String title = bundle.getString ( ListConst.KEY_TITLE );
	listId = bundle.getString ( ListConst.KEY_ID );
	listName = (EditText)findViewById ( R.id.listName );
	listName.setText ( title );
	listName.setHint ( "set list name" );
	listName.setImeActionLabel ( "ADD", KeyEvent.KEYCODE_ENTER );
	listName.setOnKeyListener ( onAddViaEditText ( listName ) );
}

@NonNull private
View.OnKeyListener onAddViaEditText ( final EditText editText ) {
	return new View.OnKeyListener () {
		@Override public
		boolean onKey ( View v, int keyCode, KeyEvent event ) {
			if ( keyCode == KeyEvent.KEYCODE_ENTER && event.getAction () != KeyEvent.ACTION_DOWN ) {
				LandingActivity.currentList.setTitle ( editText.getText ().toString () );
finish ();
			}
			return false;
		}
	};
}

@Override
public
boolean onCreateOptionsMenu ( Menu menu ) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater ().inflate ( R.menu.menu_list_detail, menu );
	return true;
}

@Override
protected
void onPause () {
	super.onPause ();
	//setMenuNormal ();
	ListModel currentList= LandingActivity.currentList;
	currentList.setTitle ( listName.getText ().toString () );
	String id = currentList.getId ();
	Uri uri = Uri.parse ( String.valueOf ( ListColumns.CONTENT_URI ) + "/" + id );
	getContentResolver ().update ( uri, currentList.getValues (), null, null );
}

@Override
public
boolean onOptionsItemSelected ( MenuItem item ) {
	// Handle action bar item clicks here. The action bar will
	// automatically handle clicks on the Home/Up button, so long
	// as you specify a parent activity in AndroidManifest.xml.
	int id = item.getItemId ();

	//noinspection SimplifiableIfStatement
	if ( id == R.id.action_settings ) {
		return true;
	}

	return super.onOptionsItemSelected ( item );
}
}
