package com.vi8e.um.wunderlist.util;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.vi8e.um.wunderlist.Model.TaskModel;


/**
 * Created by um.anusorn on 8/25/2015.
 */
public class Utility {
public static void setListViewHeightBasedOnChildren(ListView listView) {

	ListAdapter listAdapter = listView.getAdapter();
	if (listAdapter == null) {
		// pre-condition
		return;
	}

	Log.d ( "setListHeight ","                          \n" );

	int totalHeight = 0;
	int desiredWidth = View.MeasureSpec.makeMeasureSpec ( listView.getWidth (), View.MeasureSpec.AT_MOST );
	for (int i = 0; i < listAdapter.getCount(); i++) {
		View listItem = listAdapter.getView(i, null, listView);
		try {
			TaskModel taskModel = (TaskModel)listAdapter.getItem ( i );
			Log.d ( "Data",taskModel.getListTitle ()+"isStar ["+taskModel.isStar()+"] isComplete"+taskModel.isComplete () );
		}catch ( Exception e ){

		}

		listItem.measure ( desiredWidth, View.MeasureSpec.UNSPECIFIED );
		totalHeight += listItem.getMeasuredHeight();
	}
	ViewGroup.LayoutParams params = listView.getLayoutParams();
	params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
	listView.setLayoutParams ( params );
	listView.requestLayout();
}

public static
void setDrawbleColorFilter (Context context, Drawable drawable,int color ) {

	//int COLOR2 = Color.parseColor ( "#FF" + getColor () );
	PorterDuff.Mode mMode = PorterDuff.Mode.SRC_ATOP;
	drawable.setColorFilter ( color, mMode );
}

public static String getVersionName ( Context context ){
	try {
		return String.valueOf ( context.getPackageManager().getPackageInfo ( context.getPackageName (), 0 ).versionName );
	}
	catch ( PackageManager.NameNotFoundException e ) {
		e.printStackTrace ();
	}
	return "null";
}

public static String getVersionCode ( Context context ){
	try {
		return String.valueOf ( context.getPackageManager().getPackageInfo ( context.getPackageName (), 0 ).versionCode );
	}
	catch ( PackageManager.NameNotFoundException e ) {
		e.printStackTrace ();
	}
	return "null";
}


public static boolean toggleImg (View v,Drawable normal,Drawable click){
	if ( v.getId () == 0 ) {

		v.setBackground ( normal );
		v.setId ( 1 );
		return false;
	}
	else {
		v.setBackground ( click );
		v.setId ( 0 );
		return true;
	}
}

public static boolean toggleImgStarData ( View v, TaskModel rowData, Drawable normal, Drawable click ){

	Log.d ( "toggleImgData","isStar="+rowData.getIsStar () );

	if ( rowData.isStar ()) {
		v.setBackground ( normal );
		rowData.setIsStar ( String.valueOf ( ! rowData.isStar () ) );
		return false;
	}
	else {
		v.setBackground ( click );
		rowData.setIsStar ( String.valueOf ( ! rowData.isStar () ) );
		return true;
	}
}


}
