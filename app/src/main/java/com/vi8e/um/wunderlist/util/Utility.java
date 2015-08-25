package com.vi8e.um.wunderlist.util;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;


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

	int totalHeight = 0;
	int desiredWidth = View.MeasureSpec.makeMeasureSpec ( listView.getWidth (), View.MeasureSpec.AT_MOST );
	for (int i = 0; i < listAdapter.getCount(); i++) {
		View listItem = listAdapter.getView(i, null, listView);
		listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
		totalHeight += listItem.getMeasuredHeight();
	}

	ViewGroup.LayoutParams params = listView.getLayoutParams();
	params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
	listView.setLayoutParams(params);
	listView.requestLayout();
}


public static
void setDrawbleColorFilter (Context context, Drawable drawable,int color ) {

	//int COLOR2 = Color.parseColor ( "#FF" + getColor () );
	PorterDuff.Mode mMode = PorterDuff.Mode.SRC_ATOP;
	drawable.setColorFilter ( color, mMode );
}
}
