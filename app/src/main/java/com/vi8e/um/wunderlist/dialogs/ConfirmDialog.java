package com.vi8e.um.wunderlist.dialogs;

/**
 * Created by um2013 on 2/14/2014.
 */
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;

import com.vi8e.um.wunderlist.R;


public class ConfirmDialog {

public static void show(Context context,String title,final ConfirmListener listener,final String key ){
//context using getActivity or this

	Builder builder = new AlertDialog.Builder(context);
	//builder.setIcon( R.drawable.ic_launcher);
	builder.setTitle(context.getString(R.string.app_name));
	builder.setMessage(title);
	builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

		public void onClick(DialogInterface dialog, int id) {
			// HomeFragmentActivity.this.finish();
			if(listener != null){
				listener.onConfirm(key);
			}
		}
	});
	builder.setNegativeButton("NO", null);
	builder.show();
}


public interface ConfirmListener {
	void onConfirm ( String key );
}

}
