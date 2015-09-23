package com.vi8e.um.wunderlist.Dialog;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.vi8e.um.wunderlist.Activity.TaskDetailActivity;
import com.vi8e.um.wunderlist.R;

import java.util.Date;


/**
 * Created by um.anusorn on 9/23/2015.
 */
public
class ReminderDialog {

static RelativeLayout save, remove, addReminder;
Activity context;
static private SlideDateTimeListener listener = getSlideDateTimeListener ();

public static
void setView ( final MaterialDialog materialDialog ) {
	save = ( RelativeLayout ) materialDialog.findViewById ( R.id.save_reminder );
	remove = ( RelativeLayout ) materialDialog.findViewById ( R.id.remove_reminder );
	addReminder = ( RelativeLayout ) materialDialog.findViewById ( R.id.add_reminder );

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

@NonNull private static
SlideDateTimeListener getSlideDateTimeListener () {
	return new SlideDateTimeListener () {

			@Override
			public
			void onDateTimeSet ( Date date ) {
				// Do something with the date. This Date object contains
				// the date and time that the user has selected.
			}

			@Override
			public
			void onDateTimeCancel () {
				// Overriding onDateTimeCancel() is optional.
			}
		};
}

public static
void showReminderDialog ( final Activity thisContext, final ListView listView, Context context ) {
	MaterialDialog reminderDialog = new MaterialDialog.Builder ( thisContext )
			.customView ( R.layout.dialog_reminder, false )
			.show ();
	View view = reminderDialog.getCustomView ();
	reminderDialog.getContext ();
setView ( reminderDialog );


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
