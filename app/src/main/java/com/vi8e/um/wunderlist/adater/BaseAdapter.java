package com.vi8e.um.wunderlist.adater;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.vi8e.um.wunderlist.Model.ListModel;

import java.util.ArrayList;


/**
 * Created by um.anusorn on 8/27/2015.
 */
public abstract
class BaseAdapter<T> extends ArrayAdapter<ListModel> {


@Override
public
View getView ( int position, View convertView, final ViewGroup parent ){


	return convertView;
}

public
BaseAdapter ( Context context, int resource, ArrayList<ListModel> users ) {
	super ( context, resource );

}

public
BaseAdapter ( Context context, int resource, int textViewResourceId ) {
	super ( context, resource, textViewResourceId );
}

}