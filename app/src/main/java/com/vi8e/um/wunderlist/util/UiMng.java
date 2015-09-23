package com.vi8e.um.wunderlist.util;
import android.content.Context;
import android.widget.TextView;

import com.vi8e.um.wunderlist.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


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

public static
String getTimeHHmm ( Date date ) {
	SimpleDateFormat dateFormat = new SimpleDateFormat (
			"HH:mm", Locale.getDefault () );
	return dateFormat.format ( date );
}

public static
String getFormatedDate ( Date date ) {

	String format;
	if ( date.getYear () != Calendar.getInstance ().getTime ().getYear () ) {
		format = "EEE dd MMM yyyy";
	}
	else {
		format = "EEE dd MMM ";
	}
	SimpleDateFormat dateFormat = new SimpleDateFormat (
			format, Locale.getDefault () );
	return dateFormat.format ( date );
}

public static
String getYesterdayOrTodayOrTmr ( Date dateTime ) throws ParseException {
	//Date dateTime = new SimpleDateFormat("EEE hh:mma MMM d, yyyy").parse(date);
	Calendar calendar = Calendar.getInstance ();
	calendar.setTime ( dateTime );
	Calendar today = Calendar.getInstance ();
	Calendar yesterday = Calendar.getInstance ();
	yesterday.add ( Calendar.DATE, - 1 );

	Calendar tomorrow = Calendar.getInstance ();
	tomorrow.add ( Calendar.DATE, 1 );

	DateFormat timeFormatter = new SimpleDateFormat ( "hh:mma" );

	if ( calendar.get ( Calendar.YEAR ) == today.get ( Calendar.YEAR ) && calendar.get ( Calendar.DAY_OF_YEAR ) == today.get ( Calendar.DAY_OF_YEAR ) ) {
		return "Today ";// + timeFormatter.format(dateTime);
	}
	else if ( calendar.get ( Calendar.YEAR ) == yesterday.get ( Calendar.YEAR )
	          && calendar.get ( Calendar.DAY_OF_YEAR ) == yesterday.get ( Calendar.DAY_OF_YEAR ) ) {
		return "Yesterday ";// + timeFormatter.format(dateTime);
	}
	else if ( calendar.get ( Calendar.YEAR ) == tomorrow.get ( Calendar.YEAR )
	          && calendar.get ( Calendar.DAY_OF_YEAR ) == tomorrow.get ( Calendar.DAY_OF_YEAR ) ) {
		return "Tomorrow ";// + timeFormatter.format(dateTime);
	}
	else {
		return getFormatedDate ( dateTime );
	}
}
}
