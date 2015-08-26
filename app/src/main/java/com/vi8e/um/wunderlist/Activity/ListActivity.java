package com.vi8e.um.wunderlist.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.vi8e.um.wunderlist.Model.ListConst;
import com.vi8e.um.wunderlist.R;


public
class ListActivity extends AppCompatActivity {

String title;

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_list );
	setUpContent ();
	setView ();
}

void setView(){
	EditText editText =(EditText)findViewById ( R.id.editText );
	editText.setHint ( "Add a to-do in \""+title+"\"" );
}

void setUpContent(){
	Bundle extras = getIntent().getExtras();
	if(extras !=null)
	{
		title= extras.getString( ListConst.KEY_TITLE);
	}
}

@Override
public
boolean onCreateOptionsMenu ( Menu menu ) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater ().inflate ( R.menu.menu_list, menu );
	return true;
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
