package com.vi8e.um.wunderlist.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vi8e.um.wunderlist.R;

import java.util.ArrayList;


public class MyArrayAdapter extends BaseAdapter {
private LayoutInflater mInflater;
private Context        context;

private String[] galleryItems;
private String[] filteredItems;

public
MyArrayAdapter ( Context context, String[] items ) {
	this.context = context;
	this.galleryItems = items;
	this.filteredItems = items;
	mInflater = ( LayoutInflater )
			context.getSystemService ( Activity.LAYOUT_INFLATER_SERVICE );
}

public
MyArrayAdapter ( Context context, ArrayList<String> items ) {
	this.context = context;
    	this.galleryItems=(String[]) items.toArray();
        this.filteredItems=(String[]) items.toArray();
        mInflater = (LayoutInflater )
                context.getSystemService( Activity.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return filteredItems.length;
    }
 
    @Override
    public
    Object getItem(int position) {
        return filteredItems[position];
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }

	@Override
	public
	View getView(int position, View convertView, ViewGroup parent) {
    	ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listview_children_item, parent,false);
            holder=new ViewHolder();
            holder.textViewName=(TextView )convertView.findViewById( R.id.textViewName);
            convertView.setTag(holder);
        }else{
        	holder=(ViewHolder)convertView.getTag();
        }
        holder.textViewName.setText(filteredItems[position]);
		return convertView;
	}
	private static class ViewHolder{
		TextView textViewName;
	}

}