package com.vi8e.um.wunderlist.Dialog;
import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.vi8e.um.wunderlist.Activity.LandingActivity;
import com.vi8e.um.wunderlist.Activity.TaskActivity;
import com.vi8e.um.wunderlist.Activity.TaskDetailActivity;
import com.vi8e.um.wunderlist.Model.TaskModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.util.UiMng;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/**
 * Created by um.anusorn on 9/23/2015.
 */
public
class ReminderDialog {

static RelativeLayout save, remove, addReminder;
static
TextView reminderText;
static Activity mActivity;
static Context  mContext;
static private       SlideDateTimeListener listener = getSlideDateTimeListener ();
private static final String                TAG      = LandingActivity.class.getSimpleName ();


@NonNull private static
SlideDateTimeListener getSlideDateTimeListener () {
	return new SlideDateTimeListener () {

		@Override
		public
		void onDateTimeSet ( Date date ) {
			TaskActivity.currentTask.setReminderDate ( String.valueOf ( date.getTime () ) );
			setTextViewReminder ( date, reminderText, mContext );


		}

		@Override
		public
		void onDateTimeCancel () {
			// Overriding onDateTimeCancel() is optional.
		}
	};
}

public static
void setTextViewReminder ( Date date, TextView reminderText, Context context ) {
	try {
		reminderText.setText ( "Reminder " + UiMng.getYesterdayOrTodayOrTmr ( date ) + " at " + UiMng.getTimeHHmm ( date ) );
	}
	catch ( ParseException e ) {
		Log.e ( TAG, e.getMessage () + e.toString () );
	}

	if ( ! date.before ( Calendar.getInstance ().getTime () ) ) {
		UiMng.setBlueText ( context, reminderText );
	}
	else {
		UiMng.setRedText ( context, reminderText );
	}
}


public static
void showReminderDialog ( final Activity thisContext, final ListView listView, Context context ) {
	MaterialDialog reminderDialog = new MaterialDialog.Builder ( thisContext )
			.customView ( R.layout.dialog_reminder, false )
			.show ();
	View view = reminderDialog.getCustomView ();
	reminderDialog.getContext ();
	setView ( reminderDialog );
	mContext = context;
	TaskDetailActivity.setTextViewReminderFromTaskDB ( TaskDetailActivity.currentTask, reminderText, context );

}

public static
void setView ( final MaterialDialog materialDialog ) {
	save = ( RelativeLayout ) materialDialog.findViewById ( R.id.save_reminder );
	remove = ( RelativeLayout ) materialDialog.findViewById ( R.id.remove_reminder );
	addReminder = ( RelativeLayout ) materialDialog.findViewById ( R.id.add_reminder );
	reminderText = ( TextView ) materialDialog.findViewById ( R.id.reminder_text );

	remove.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			TaskDetailActivity.currentTask.setReminderDate ( null );
			TaskDetailActivity.setTextViewReminderFromTaskDB ( TaskDetailActivity.currentTask, TaskDetailActivity.reminderText, mContext );
			materialDialog.dismiss ();
		}
	} );
	save.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {

			TaskDetailActivity.setTextViewReminderFromTaskDB ( TaskDetailActivity.currentTask,TaskDetailActivity.reminderText,mContext );
			AddEventToCalendar(TaskActivity.currentTask);
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


public static int AddEventToCalendar(TaskModel taskModel) {
	// TODO Auto-generated method stub
	ContentValues event = new ContentValues ();

	event.put( CalendarContract.Events.CALENDAR_ID, taskModel.getId ());
	event.put(CalendarContract.Events.TITLE, taskModel.getTitle ());
	event.put(CalendarContract.Events.DTSTART, System.currentTimeMillis ());
	event.put(CalendarContract.Events.DTEND, System.currentTimeMillis() + 5*1000);

	long reminderDate = Long.parseLong ( taskModel.getReminderDate () );
	Log.d ( TAG,"remidnerTime="+reminderDate );
	event.put(CalendarContract.Events.DTSTART, reminderDate );
	event.put(CalendarContract.Events.DTEND, reminderDate + 60*60*1000);

	event.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault ().toString ());
	event.put("allDay", 0);
	//status: 0~ tentative; 1~ confirmed; 2~ canceled
	event.put("eventStatus", 1);
	//0~ default; 1~ confidential; 2~ private; 3~ public
	//event.put("visibility", 0);
	//0~ opaque, no timing conflict is allowed; 1~ transparency, allow overlap of scheduling
	//event.put("transparency", 0);
	//0~ false; 1~ true
	event.put("hasAlarm", 1);
	Uri add_eventUri;
	if ( Build.VERSION.SDK_INT >= 8) {
		add_eventUri = Uri.parse("content://com.android.calendar/events");
	} else {
		add_eventUri = Uri.parse("content://calendar/events");
	}
	Uri l_uri = mContext.getContentResolver().insert(add_eventUri, event);
	if(l_uri != null)
	{
		long eventID = Long.parseLong(l_uri.getLastPathSegment());
		return (int) eventID;
	}
	else
		return 0;
}



/*
public static int AddEventToCalendar(TaskModel taskModel) {
	// TODO Auto-generated method stub
	ContentValues event = new ContentValues ();

	event.put( CalendarContract.Events.CALENDAR_ID, taskModel.getId ());
	event.put(CalendarContract.Events.TITLE, taskModel.getTitle ());
	event.put(CalendarContract.Events.DTSTART, System.currentTimeMillis ());
	event.put(CalendarContract.Events.DTEND, System.currentTimeMillis() + 65*1000);
	event.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault ().toString ());
	event.put(CalendarContract.Events.ALL_DAY, 0);
	//status: 0~ tentative; 1~ confirmed; 2~ canceled
	event.put(CalendarContract.Events.STATUS, 1);
	//0~ default; 1~ confidential; 2~ private; 3~ public
	//event.put(CalendarContract.Events.VISIBLE, 0);
	//0~ opaque, no timing conflict is allowed; 1~ transparency, allow overlap of scheduling
	//event.put("transparency", 0);
	//0~ false; 1~ true
	event.put(CalendarContract.Events.HAS_ALARM, 1);
	Uri add_eventUri;
	if ( Build.VERSION.SDK_INT >= 8) {
		add_eventUri = Uri.parse("content://com.android.calendar/events");
	} else {
		add_eventUri = Uri.parse("content://calendar/events");
	}
	Uri l_uri = mContext.getContentResolver().insert ( add_eventUri, event );
	if(l_uri != null)
	{
		long eventID = Long.parseLong(l_uri.getLastPathSegment());
		return (int) eventID;
	}
	else
		return 0;



}
*/

public static void removeCurrentTaskreminder(){
	Uri reminderUri = ContentUris.withAppendedId (
			CalendarContract.Reminders.CONTENT_URI, Long.parseLong ( TaskActivity.currentTask.getId () ) );
	int rows = mContext.getContentResolver ().delete ( reminderUri, null, null );
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
