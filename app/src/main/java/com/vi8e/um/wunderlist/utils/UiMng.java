package com.vi8e.um.wunderlist.utils;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vi8e.um.wunderlist.R;


/**
 * Created by um.anusorn on 9/23/2015.
 */
public
class UiMng {
public static void setRedText(Context context,TextView textView){
	int color = ( context.getResources ().getColor ( R.color.red_400 ) );
	textView.setTextColor ( color);
	View view=(View)textView.getParent ();
	try {
		ImageView imageView = ( ImageView ) view.findViewById ( R.id.reminderImg );
		imageView.setColorFilter  ( color );
	}catch ( Exception e ){
		Log.d ("","Error on try to color the image");
	}
}

public static void setBlueText(Context context,TextView textView){
	int color = ( context.getResources ().getColor ( R.color.blue_400 ) );
	textView.setTextColor ( color);
	View view=(View)textView.getParent ();
	try {
		ImageView imageView = ( ImageView ) view.findViewById ( R.id.reminderImg );
		imageView.setColorFilter ( color );
	}catch ( Exception e ){
		Log.d ("","Error on try to color the image");
	}

}

public static void setBlackText(Context context,TextView textView){
	textView.setTextColor ( context.getResources ().getColor ( R.color.grey_800 ) );
}

}
