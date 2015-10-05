package com.vi8e.um.wunderlist.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.models.NavDrawerItem;

import java.util.ArrayList;


public class MyAccountAdapter extends BaseAdapter implements Filterable {
private LayoutInflater           mInflater;
private Context                  context;
private ArrayList<NavDrawerItem> myaccountItems;
private ArrayList<NavDrawerItem> filteredItems;

public
MyAccountAdapter ( Context context, ArrayList<NavDrawerItem> items ) {
	this.context = context;
	this.myaccountItems = items;
	this.filteredItems = items;
	mInflater = ( LayoutInflater )
			context.getSystemService ( Activity.LAYOUT_INFLATER_SERVICE );
}

@Override
public
int getCount () {
	return filteredItems.size ();
}

@Override
public
Object getItem ( int position ) {
	return filteredItems.get ( position );
}

@Override
public
long getItemId ( int position ) {
	return position;
    }

	@Override
	public
	View getView(int position, View convertView, ViewGroup parent) {
    	ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listview_category_item, parent,false);
            holder=new ViewHolder();
            holder.textViewCategory=(TextView )convertView.findViewById( R.id.textViewCategory);
            convertView.setTag(holder);
        }else{
        	holder=(ViewHolder)convertView.getTag();
        }
        holder.textViewCategory.setText(filteredItems.get(position).getTitle());
		return convertView;
	}
	private static class ViewHolder{
		TextView textViewCategory;
	}

@Override
public
Filter getFilter () {
	return new Filter () {

		@Override
		protected
		void publishResults ( CharSequence charSequence, FilterResults filterResult ) {
			filteredItems = ( ArrayList<NavDrawerItem> ) filterResult.values;
			notifyDataSetChanged ();
		}

		@Override
		protected
		FilterResults performFiltering ( CharSequence charSequence ) {
			FilterResults results = new FilterResults ();

			if ( charSequence == null || charSequence.length () == 0 ) {
				results.values = myaccountItems;
				results.count = myaccountItems.size ();
			}
			else {
				ArrayList<NavDrawerItem> filterResultsData = new ArrayList<NavDrawerItem> ();
				for ( NavDrawerItem data : myaccountItems ) {
					if ( data.getTitle ().toLowerCase ().contains ( charSequence.toString ().toLowerCase () ) ) {
						filterResultsData.add ( data );
					}
				}
				results.values = filterResultsData;
				results.count = filterResultsData.size ();
			}

			return results;
		}
	};
}
}

