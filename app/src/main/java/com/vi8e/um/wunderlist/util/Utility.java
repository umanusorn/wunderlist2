package com.vi8e.um.wunderlist.util;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.vi8e.um.wunderlist.Model.SubTaskModel;
import com.vi8e.um.wunderlist.Model.TaskModel;
import com.vi8e.um.wunderlist.R;


/**
 * Created by um.anusorn on 8/25/2015.
 */
public
class Utility {
public static
void setTaskListViewHeight ( ListView listView ) {

	ListAdapter listAdapter = listView.getAdapter ();
	if ( listAdapter == null ) {
		// pre-condition
		return;
	}

	int totalHeight = 0;
	int desiredWidth = View.MeasureSpec.makeMeasureSpec ( listView.getWidth (), View.MeasureSpec.AT_MOST );
	for ( int i = 0 ; i < listAdapter.getCount () ; i++ ) {
		View listItem = listAdapter.getView ( i, null, listView );
		listItem.measure ( desiredWidth, View.MeasureSpec.UNSPECIFIED );//UNSPECIFIED

		totalHeight += listItem.getMeasuredHeight ();
		Log.d ( "setTaskListViewHeight","height= "+totalHeight );
	}
	ViewGroup.LayoutParams params = listView.getLayoutParams ();
	totalHeight+=( listView.getDividerHeight () * ( listAdapter.getCount () - 1 ) );

	Log.d ( "setTaskListViewHeight","height= "+totalHeight );
	params.height = totalHeight ;
	listView.setLayoutParams ( params );
	listView.requestLayout ();
}

public static
void setDrawbleColorFilter ( Context context, Drawable drawable, int color ) {

	//int COLOR2 = Color.parseColor ( "#FF" + getColor () );
	PorterDuff.Mode mMode = PorterDuff.Mode.SRC_ATOP;
	drawable.setColorFilter ( color, mMode );
}

public static
String getVersionName ( Context context ) {
	try {
		return String.valueOf ( context.getPackageManager ().getPackageInfo ( context.getPackageName (), 0 ).versionName );
	}
	catch ( PackageManager.NameNotFoundException e ) {
		e.printStackTrace ();
	}
	return "null";
}

public static
String getVersionCode ( Context context ) {
	try {
		return String.valueOf ( context.getPackageManager ().getPackageInfo ( context.getPackageName (), 0 ).versionCode );
	}
	catch ( PackageManager.NameNotFoundException e ) {
		e.printStackTrace ();
	}
	return "null";
}


public static
boolean toggleImg ( View v, Drawable normal, Drawable clicked ) {
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

public static
boolean toggleImgStarData ( View v, TaskModel rowData, Context context ) {

	Resources res = context.getResources ();
	Drawable normal = res.getDrawable ( R.mipmap.wl_task_detail_ribbon );
	Drawable clicked = res.getDrawable ( R.mipmap.wl_task_detail_ribbon_selected );
	return toggleImgStarData ( v, rowData, normal, clicked );
}

public static
boolean toggleImgStarData ( View v, TaskModel rowData, Drawable normal, Drawable clicked ) {

	Log.d ( "toggleImgData", "isStar=" + rowData.getIsStar () );

	ImageView imageView =(ImageView)v;
	imageView.setBackground ( null );
	if ( rowData.isStar () ) {
		imageView.setImageDrawable ( normal );
		rowData.setIsStar ( String.valueOf ( ! rowData.isStar () ) );
		return false;
	}
	else {
		imageView.setImageDrawable ( clicked );
		rowData.setIsStar ( String.valueOf ( ! rowData.isStar () ) );
		return true;
	}
}


public static
boolean toggleImgCompleteData ( View v, SubTaskModel rowData, Context context ) {

	Resources res = context.getResources ();
	Drawable normal = res.getDrawable ( R.mipmap.wl_icon_task_detail_checkbox );
	Drawable clicked = res.getDrawable ( R.mipmap.wl_icon_task_detail_checkedbox );
	RelativeLayout rootView =(RelativeLayout)v.getParent ();
	return toggleImgCompleteData ( v, rowData, normal, clicked );
}


public static
boolean toggleImgCompleteData ( View v, SubTaskModel rowData, Drawable normal, Drawable clicked ) {
	ImageView imageView =(ImageView)v;
	if ( rowData.isComplete () ) {
		imageView.setImageDrawable ( normal );
		rowData.setIsComplete ( String.valueOf ( ! rowData.isComplete () ) );
		return false;
	}
	else {
		imageView.setImageDrawable ( clicked );
		rowData.setIsComplete ( String.valueOf ( ! rowData.isComplete () ) );
		return true;
	}
}

public static
float getListHeight ( Activity activity ) {
	DisplayMetrics displayMetrics = getScreenSize ( activity );
	float height = displayMetrics.heightPixels;
	float density = activity.getResources ().getDisplayMetrics ().density;
	float headerHeight = activity.getResources ().getDimension ( R.dimen.landing_header_max_height )/density;
	float screenHeight = height / density;
	int actonBarHeight= ( int ) (getActionBarHeight ( activity )/density);
	//Log.d ( "Utility", "ScreenHeight" + screenHeight + "headerHeight=" + headerHeight +"dense="+density+"actionBarHeight"+actonBarHeight);
	return ( screenHeight - headerHeight )*density-actonBarHeight;
}

public static int getActionBarHeight(Activity activity){
	TypedValue tv = new TypedValue ();
	if (activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
	{
		return  TypedValue.complexToDimensionPixelSize(tv.data,activity.getResources().getDisplayMetrics());
	}
	return -1;
}

public static
DisplayMetrics getScreenSize ( Activity activity ) {
	Display display = activity.getWindowManager ().getDefaultDisplay ();
	DisplayMetrics outMetrics = new DisplayMetrics ();
	display.getMetrics ( outMetrics );

	return outMetrics;
}

public static
void toastOneInstance (Toast mToast,Context context) {
	if ( mToast != null ) {
		mToast.cancel ();
	}
	mToast = Toast.makeText (
			context,
			"String",
			Toast.LENGTH_LONG
	                        );
	mToast.show ();
}
}
