package com.vi8e.um.wunderlist.util;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;


/**
 * Created by um007 on 6/14/13.
 * This Class Initialize view and return those view
 * Also generate id which contain TypeId,SubTypeId,subTypeNumId and uniqueId
 * Also get some part of id
 */
public class Init {

public static
CheckBox checkBox(Context context,
                                int type,
                                int subType,
                                int subSubType,
                                int UNIQUE_ID)
{
	CheckBox checkBox = new CheckBox (context);
	checkBox.setLayoutParams(
			new ViewGroup.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT,
			                           ViewGroup.LayoutParams.WRAP_CONTENT)
	                        );
	return checkBox;
}

public static
EditText editText(Context context, Boolean isMATCH_PARENT) {
	EditText editText = new EditText (context);
	int width;
	int height = ViewGroup.LayoutParams.WRAP_CONTENT;
	if (isMATCH_PARENT) {
		width = ViewGroup.LayoutParams.MATCH_PARENT;
	}
	else {
		width = ViewGroup.LayoutParams.WRAP_CONTENT;
	}
	editText.setLayoutParams(new ViewGroup.LayoutParams(width, height));
	return editText;
}

public static boolean[][] getBoolean(int MAX_SIZE1, int MAX_SIZE2, boolean value) {
	boolean[][] booleans = new boolean[MAX_SIZE1][MAX_SIZE2];
	for (int i = 0; i < MAX_SIZE1; i++) {
		for (int j = 0; j < MAX_SIZE2; j++) {
			booleans[i][j] = value;
		}
	}
	return booleans;
}
public static LinearLayout.LayoutParams layoutParamLinear() {
	return new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

}


public static
LinearLayout linearLayout(int isVerticle,
                                        int layoutParamWidth_ViewGroup_LayoutParam,
                                        int layoutParamHeight,
                                        Context context)
{
	LinearLayout linearLayout = new LinearLayout (context);
	linearLayout.setOrientation(isVerticle);
	linearLayout.setLayoutParams(new ViewGroup.LayoutParams(
			layoutParamWidth_ViewGroup_LayoutParam, layoutParamHeight));
	return linearLayout;
}


public static
RelativeLayout relativeLayout(final int isVerticle,
                                            final int layoutParamWidth_ViewGroup_LayoutParam,
                                            final int layoutParamHeight,
                                            final Context context)
{
	RelativeLayout relativeLayout = new RelativeLayout (context);
	//relativeLayout.setOrientation(isVerticle);
	relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(
			layoutParamWidth_ViewGroup_LayoutParam, layoutParamHeight));
	return relativeLayout;
}

public static
View setLayoutParamTableRow(View view) {
	view.setLayoutParams(new TableRow.LayoutParams( TableRow.LayoutParams.WRAP_CONTENT,
	                                               TableRow.LayoutParams.WRAP_CONTENT));
	return view;
}



public static
TableRow tableRow(Context context) {
	TableRow tableRow = new TableRow (context);
	tableRow.setLayoutParams(new TableRow.LayoutParams( TableRow.LayoutParams.WRAP_CONTENT,
	                                                   TableRow.LayoutParams.WRAP_CONTENT));
	tableRow.setGravity( Gravity.CENTER);
	return tableRow;
}

public static
TextView textView(Context context,
                                String text,
                                final int TYPE,
                                final int SUB_TYPE,
                                final int UNIQUE_ID)
{
	TextView textView = textView(context, text);
	textView.setTextAppearance(context, android.R.style.TextAppearance_Large);


	return textView;
}

public static
TextView textView(Context context, String text) {
	TextView textView = new TextView (context);
	textView.setLayoutParams(
			new ViewGroup.LayoutParams( ViewGroup.LayoutParams.WRAP_CONTENT,
			                           ViewGroup.LayoutParams.WRAP_CONTENT)
	                        );
	textView.setText(text);
	textView.setTextColor( Color.BLACK);
	return textView;
}

}
