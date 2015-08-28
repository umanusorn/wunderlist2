package com.vi8e.um.wunderlist.adater;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.vi8e.um.wunderlist.Model.TaskModel;

import java.util.ArrayList;


/**
 * Created by um.anusorn on 8/27/2015.
 */
public abstract
class TaskDetailAdapter extends TaskAdapter {


public
TaskDetailAdapter ( Context context, ArrayList<TaskModel> listInComplete ) {
	super ( context, listInComplete );
}

@Override
public
View getView ( int position, View convertView, final ViewGroup parent ) {


	return convertView;
}

}