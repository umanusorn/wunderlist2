package com.vi8e.um.wunderlist.dialogs;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

import com.vi8e.um.wunderlist.R;

import java.util.Calendar;


public class MyDatePickerDialog extends DialogFragment {
private Long maxValue = 0l;
private Long minValue = 0l;

public
interface DatePickerDialogListener {
	public
	void onDatePickerPositiveClick ( DialogFragment dialog, int year, int month, int day, String tag );

	public
	void onDatePickerNegativeClick ( DialogFragment dialog, String tag );
}

DatePickerDialogListener mListener;

@Override
public
void onAttach ( Activity activity ) {
	super.onAttach ( activity );
	mListener = ( DatePickerDialogListener ) getTargetFragment ();
	try {
		if ( mListener == null ) mListener = ( DatePickerDialogListener ) activity;
	}
	catch ( ClassCastException e ) {
		throw new ClassCastException ( activity.toString () + " must implement DatePickerDialogListener" );
	}
}

public static
MyDatePickerDialog newInstance ( int positiveButtonTitle,
                                 int negativeButtonTitle){
		MyDatePickerDialog fragment=new MyDatePickerDialog();
		Bundle args=new Bundle ();
		args.putInt("positive", positiveButtonTitle);
		args.putInt("negative", negativeButtonTitle);
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public void setArguments(Bundle args) {
		super.setArguments(args);
	}
	@Override
	public
	Dialog onCreateDialog(Bundle savedInstanceState) {
		int year,month,day;
		if(this.getArguments()!=null){
			if(this.getArguments().getString("year")!=null){
				year= Integer.parseInt ( this.getArguments ().getString ( "year" ) );
				month= Integer.parseInt ( this.getArguments ().getString ( "month" ) )-1;
				day= Integer.parseInt ( this.getArguments ().getString ( "day" ) );
			}else{
				final Calendar c= Calendar.getInstance ();
				c.set( Calendar.YEAR, 1970);
				c.set( Calendar.MONTH, 0);
				c.set( Calendar.DAY_OF_MONTH, 1);
				year=c.get( Calendar.YEAR);
				month=c.get( Calendar.MONTH);
				day=c.get( Calendar.DAY_OF_MONTH);
			}
			maxValue=this.getArguments().getLong("maxValue",0);
			minValue=this.getArguments().getLong("minValue",0);
		}else{
			final Calendar c= Calendar.getInstance ();
			c.set( Calendar.YEAR, 1970);
			c.set( Calendar.MONTH, 1);
			c.set( Calendar.DAY_OF_MONTH, 0);
			year=c.get( Calendar.YEAR);
			month=c.get( Calendar.MONTH);
			day=c.get( Calendar.DAY_OF_MONTH);
		}
//		DatePickerDialog datePicker=new DatePickerDialog(getActivity(),this,year,month,day);
//		
//		datePicker.requestWindowFeature(Window.FEATURE_NO_TITLE);
//		if(maxValue!=0){
//			datePicker.getDatePicker().setMaxDate(maxValue);
//		}
//		if(minValue!=0){
//			datePicker.getDatePicker().setMinDate(minValue);
//		}
		
		
		
		LayoutInflater mInflater = (LayoutInflater )
                getActivity().getApplicationContext().getSystemService( Activity.LAYOUT_INFLATER_SERVICE);
		View view=mInflater.inflate(R.layout.dialog_date_picker, null);
		final DatePicker datePicker2=(DatePicker )view.findViewById( R.id.datePicker1);
		datePicker2.init(year, month, day, new OnDateChangedListener () {
			
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				
			}
		});
		if(maxValue!=0){
			datePicker2.setMaxDate(maxValue);
		}
		if(minValue!=0){
			datePicker2.setMinDate(minValue);
		}
		final Button buttonPositive=(Button )view.findViewById(R.id.buttonPositive);
		final Button buttonNegative=(Button )view.findViewById(R.id.buttonNegative);
		buttonPositive.setOnClickListener(new OnClickListener () {
			
			@Override
			public void onClick(View v) {
				mListener.onDatePickerPositiveClick(MyDatePickerDialog.this, datePicker2.getYear(), datePicker2.getMonth()+1, 
						datePicker2.getDayOfMonth(), MyDatePickerDialog.this.getTag());
				dismiss();
			}
		});
		buttonNegative.setOnClickListener(new OnClickListener () {
			
			@Override
			public void onClick(View v) {
				mListener.onDatePickerNegativeClick(MyDatePickerDialog.this, MyDatePickerDialog.this.getTag());
				dismiss();
			}
		});
		buttonPositive.setText("OK");
		buttonNegative.setText("Cancel");
		
		Dialog dialog=new Dialog (getActivity());
		dialog.requestWindowFeature( Window.FEATURE_NO_TITLE);
		dialog.setContentView(view);
		
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable (0));
		DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
		int width = displayMetrics.widthPixels-2*((int)getResources().getDimension(R.dimen.activity_horizontal_margin));
		dialog.getWindow().setLayout(width, LayoutParams.WRAP_CONTENT);
		return dialog;
		//return datePicker;
	}
}