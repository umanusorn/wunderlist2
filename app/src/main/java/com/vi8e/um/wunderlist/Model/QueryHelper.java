package com.vi8e.um.wunderlist.Model;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.vi8e.um.wunderlist.provider.list.ListColumns;
import com.vi8e.um.wunderlist.provider.list.ListCursor;
import com.vi8e.um.wunderlist.provider.list.ListSelection;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by um.anusorn on 8/31/2015.
 */
public
class QueryHelper {


public static
Cursor getListValueCursor ( Context context ) {
	ListSelection recordValueSelection = new ListSelection ();
	String[] projection = ListColumns.ALL_COLUMNS;
	ListCursor recordValueCursor = recordValueSelection.query ( context.getContentResolver (), projection );
	return recordValueCursor;
}

public static
List<ContentValues> getListValuesFromCursor ( Cursor c ) {
	List<ContentValues> values = new ArrayList<ContentValues> ();
	int i = 0;
	String key;
	int index;
	c.moveToFirst ();
	while ( c.moveToNext () ) {
		ContentValues value = new ContentValues ();
		for ( int j = 1 ; j < c.getColumnCount () ; j++ ) {
			key = ListColumns.ALL_COLUMNS[ j ];
			index = c.getColumnIndex ( key );
			value.put ( key, c.getString ( index ) );
		}
		i++;
		values.add ( value );
	}
	Log.d ( "getCount",c.getCount () + "  " + values.size () );

	return values;
}


}
