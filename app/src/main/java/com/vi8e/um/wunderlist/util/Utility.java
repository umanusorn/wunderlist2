package com.vi8e.um.wunderlist.util;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.vi8e.um.wunderlist.Model.TaskModel;
import com.vi8e.um.wunderlist.R;


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


public static boolean toggleImg (View v,Drawable normal,Drawable clicked){
	if ( v.getId () == 0 ) {

		v.setBackground ( normal );
		v.setId ( 1 );
		return false;
	}
	else {
		v.setBackground ( clicked );
		v.setId ( 0 );
		return true;
	}
}

public static boolean toggleImgStarData ( View v, TaskModel rowData,Context context){

	Resources res = context.getResources ();
  Drawable normal =  res.getDrawable ( R.mipmap.wl_task_detail_ribbon );
	Drawable clicked= res.getDrawable ( R.mipmap.wl_task_detail_ribbon_selected );
	return toggleImgStarData ( v, rowData, normal, clicked );
}

public static boolean toggleImgStarData ( View v, TaskModel rowData, Drawable normal, Drawable clicked ){

	Log.d ( "toggleImgData","isStar="+rowData.getIsStar () );

	if ( rowData.isStar ()) {
		v.setBackground ( normal );
		rowData.setIsStar ( String.valueOf ( ! rowData.isStar () ) );
		return false;
	}
	else {
		v.setBackground ( clicked );
		rowData.setIsStar ( String.valueOf ( ! rowData.isStar () ) );
		return true;
	}
}


public static boolean toggleImgCompleteData ( View v, TaskModel rowData,Context context){

	Resources res = context.getResources ();
	Drawable normal = res.getDrawable ( R.mipmap.wl_task_detail_ribbon_selected );
	Drawable clicked= res.getDrawable ( R.mipmap.wl_icon_task_detail_checkbox);
	return toggleImgCompleteData ( v, rowData, normal, clicked );
}

public static boolean toggleImgCompleteData ( View v, TaskModel rowData, Drawable normal, Drawable clicked ){

	Log.d ( "toggleImgData","isStar="+rowData.getIsStar () );

	if ( rowData.isStar ()) {
		v.setBackground ( normal );
		rowData.setIsComplete ( String.valueOf ( ! rowData.isComplete () ) );
		return false;
	}
	else {
		v.setBackground ( clicked );
		rowData.setIsComplete ( String.valueOf ( ! rowData.isComplete () ) );
		return true;
	}
}


}
