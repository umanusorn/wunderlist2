package com.vi8e.um.wunderlist.Dialog;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.vi8e.um.wunderlist.Activity.TaskDetailActivity;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.util.UiMng;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * Created by um.anusorn on 9/23/2015.
 */
public
class ReminderDialog {

static RelativeLayout save, remove, addReminder;
static
TextView reminderText;
static Activity mActivity;
static Context mContext;
static private SlideDateTimeListener listener = getSlideDateTimeListener ();

@NonNull private static
SlideDateTimeListener getSlideDateTimeListener () {
	return new SlideDateTimeListener () {

			@Override
			public
			void onDateTimeSet ( Date date ) {
				// Do something with the date. This Date object contains
				// the date and time that the user has selected.
				long currentTime = Calendar.getInstance ().getTimeInMillis ();
				;

				reminderText.setText ("Reminder "+ getTimeHHmm ( date ));

				if(!date.before ( Calendar.getInstance ().getTime () )){
					UiMng.setBlueText ( mContext,reminderText );
				}else{
					UiMng.setRedText ( mContext,reminderText );
				}
			}

			@Override
			public
			void onDateTimeCancel () {
				// Overriding onDateTimeCancel() is optional.
			}
		};
}

public static String getTimeHHmm ( Date date ) {
	SimpleDateFormat dateFormat = new SimpleDateFormat(
			"HH:mm", Locale.getDefault ());
	return dateFormat.format(date);
}

public static String getFormatedDate(Date currentDate,Date date) {
	SimpleDateFormat dateFormat = new SimpleDateFormat(
			"EEE MMM dd ", Locale.getDefault ());
	return dateFormat.format(date);
}



public static String getFormatedDate(Date date) {
	SimpleDateFormat dateFormat = new SimpleDateFormat(
			"EEE MMM dd ", Locale.getDefault ());
	return dateFormat.format(date);
}

public static
void showReminderDialog ( final Activity thisContext, final ListView listView, Context context ) {
	MaterialDialog reminderDialog = new MaterialDialog.Builder ( thisContext )
			.customView ( R.layout.dialog_reminder, false )
			.show ();
	View view = reminderDialog.getCustomView ();
	reminderDialog.getContext ();
  setView ( reminderDialog );
	mContext=context;



}

public static
void setView ( final MaterialDialog materialDialog ) {
	save = ( RelativeLayout ) materialDialog.findViewById ( R.id.save_reminder );
	remove = ( RelativeLayout ) materialDialog.findViewById ( R.id.remove_reminder );
	addReminder = ( RelativeLayout ) materialDialog.findViewById ( R.id.add_reminder );
	reminderText=(TextView)materialDialog.findViewById ( R.id.reminder_text );

	remove.setOnClickListener ( new View.OnClickListener () {
	@Override public
	void onClick ( View v ) {
		materialDialog.dismiss ();
	}
} );
	save.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			materialDialog.dismiss ();
		}
	} );
	addReminder.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			showDateTimeDialog ();
		}
	} );


}

public static
void showDateTimeDialog () {
	new SlideDateTimePicker.Builder ( TaskDetailActivity.sFragmentManager )
			.setListener ( listener )
			.setInitialDate ( new Date () )
			.build ()
			.show ();
}
}
