package com.vi8e.um.wunderlist.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DateHelper {
	public static
	String dateFormater(String oldDate, String oldDateFormat, String newDateFormat){
		try{
			Date myDate=new SimpleDateFormat (oldDateFormat, Locale.getDefault ()).parse(oldDate);
			return new SimpleDateFormat (newDateFormat, Locale.getDefault ()).format(myDate);
		}catch(ParseException e){
			return oldDate;
		}
	}
	public static int getDay(String date,String dateFormat){
		Date myDate=null;
		try {
			myDate = new SimpleDateFormat (dateFormat, Locale.getDefault ()).parse(date);
			Calendar c= Calendar.getInstance ();
			c.setTime(myDate);
			int day=c.get( Calendar.DATE);
			return day;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
			
		}
		
	}
	public static int getMonth(String date,String dateFormat){
		Date myDate=null;
		try {
			myDate = new SimpleDateFormat (dateFormat, Locale.getDefault ()).parse(date);
			Calendar c= Calendar.getInstance ();
			c.setTime(myDate);
			int day=c.get( Calendar.MONTH);
			return day+1;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	public static int getYear(String date,String dateFormat){
		Date myDate=null;
		try {
			myDate = new SimpleDateFormat (dateFormat, Locale.getDefault ()).parse(date);
			Calendar c= Calendar.getInstance ();
			c.setTime(myDate);
			int day=c.get( Calendar.YEAR);
			return day;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	public static
	Long StringtoUNIX(String date,String dateFormat){
		Date myDate=null;
		try {
			myDate = new SimpleDateFormat (dateFormat, Locale.getDefault ()).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c= Calendar.getInstance ();
		c.setTime(myDate);
		return c.getTimeInMillis();
	}
	public static
	String UNIXtoString(long timestamp,String dateFormat){
		Calendar c= Calendar.getInstance ();
		c.setTimeInMillis(timestamp);
		String time=c.get( Calendar.DATE)+"-"+(c.get( Calendar.MONTH)+1)+"-"+c.get( Calendar.YEAR)+" "+
				c.get( Calendar.HOUR_OF_DAY)+":"+c.get( Calendar.MINUTE);
		try{
			Date myDate=new SimpleDateFormat ("dd-MM-yyyy HH:mm", Locale.getDefault ()).parse(time);
			return new SimpleDateFormat (dateFormat, Locale.getDefault ()).format(myDate);
		}catch(ParseException e){
			return time;
		}
	}
}
