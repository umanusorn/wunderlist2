package com.vi8e.um.wunderlist.util;
import android.content.Context;
import android.widget.TextView;

import com.vi8e.um.wunderlist.R;


/**
 * Created by um.anusorn on 9/23/2015.
 */
public
class UiMng {
public static void setRedText(Context context,TextView textView){

	textView.setTextColor ( context.getResources ().getColor ( R.color.red_400 ) );
}

public static void setBlueText(Context context,TextView textView){

	textView.setTextColor ( context.getResources ().getColor ( R.color.blue_400 ) );
}
}
