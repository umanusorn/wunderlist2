package com.vi8e.um.wunderlist.Activity;

import android.content.ContentValues;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.vi8e.um.wunderlist.Model.QueryHelper;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.provider.list.ListColumns;
import com.vi8e.um.wunderlist.util.ConfirmDialog;
import com.vi8e.um.wunderlist.util.Init;

import java.util.List;


public
class ViewDBActivity extends AppCompatActivity {
TableLayout tableLayout;

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_view_db );
	tableLayout = (TableLayout ) findViewById(R.id.layoutHealthTable);
	setRequestedOrientation( ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE );
	genTableUi ();
}


public void genTableUi() {
	Cursor c = QueryHelper.getListValueCursor ( getApplicationContext () );
	List<ContentValues> allListValues = QueryHelper.getListValuesFromCursor ( c );
	genColName();
	if (allListValues.isEmpty()) {
		ConfirmDialog.show(this, "No data in TableHealth", new ConfirmDialog.ConfirmListener() {
			@Override public void onConfirm(String key) {
				finish();
			}
		}, "Exit App");
		return;
	}
//fill data each row
	final int ROW_SIZE = c.getCount();
	for (ContentValues value : allListValues) {
		TableRow tableRow = Init.tableRow ( getApplicationContext () );
//fill data each column
		for (int j = 0; j < ListColumns.ALL_COLUMNS.length; j++) {
			String tvString = "NO DATA";
			tvString = value.getAsString(ListColumns.ALL_COLUMNS[j]);
			tvString = "  " + tvString + "  ";

			TextView textView = Init.textView(getApplicationContext(),
																				tvString,
																				0,
																				0,
																				0);
			textView = (TextView) Init.setLayoutParamTableRow(textView);
			textView.setGravity( Gravity.CENTER);
			tableRow.addView(textView);

		}
		tableLayout.addView(tableRow,
												new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
																										 TableLayout.LayoutParams.WRAP_CONTENT));
	}
}

public void genColName() {
	TableRow tableRow = Init.tableRow(getApplicationContext());
	for (int j = 0; j < ListColumns.ALL_COLUMNS.length; j++) {
		TextView textView = Init.textView(getApplicationContext(),
																			"   " +
																			(ListColumns.ALL_COLUMNS[j]) + "   ",
																			0,
																			0,
																			0);
		textView = (TextView) Init.setLayoutParamTableRow(textView);
		tableRow.addView(textView);
	}
	tableRow.setBackgroundColor ( getResources ().getColor ( R.color.green_400 ) );
	tableLayout.addView ( tableRow,
												new TableLayout.LayoutParams ( TableLayout.LayoutParams.WRAP_CONTENT,
																											 TableLayout.LayoutParams.WRAP_CONTENT ) );
}




@Override
public
boolean onCreateOptionsMenu ( Menu menu ) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater ().inflate ( R.menu.menu_view_db, menu );
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