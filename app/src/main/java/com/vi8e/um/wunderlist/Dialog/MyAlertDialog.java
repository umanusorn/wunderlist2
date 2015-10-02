package com.vi8e.um.wunderlist.Dialog;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.vi8e.um.wunderlist.R;


public class MyAlertDialog extends DialogFragment {
	public interface MyAlertDialogListener {
        public void onDialogPositiveClick( DialogFragment dialog, String tag );
        public void onDialogNegativeClick( DialogFragment dialog, String tag );
    }
	MyAlertDialogListener mListener;
	public static MyAlertDialog newInstance(int title,int message,int positiveButtonTitle,int... negativeParams){
		MyAlertDialog fragment=new MyAlertDialog();
		Bundle args=new Bundle ();
		args.putInt("title", title);
		args.putInt("message", message);
		args.putInt("positive", positiveButtonTitle);
		if(negativeParams.length>0)args.putInt("negative", negativeParams[0]);
		fragment.setArguments(args);
		return fragment;
	}
	public static MyAlertDialog newInstance(String title,String message,int positiveButtonTitle,int... negativeParams){
		MyAlertDialog fragment=new MyAlertDialog();
		Bundle args=new Bundle ();
		args.putString("title", title);
		args.putString("message", message);
		args.putInt("positive", positiveButtonTitle);
		if(negativeParams.length>0)args.putInt("negative", negativeParams[0]);
		fragment.setArguments(args);
		return fragment;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try{
			mListener=(MyAlertDialogListener) getTargetFragment();
			if(mListener==null)mListener=(MyAlertDialogListener) activity;
		}catch(ClassCastException e){
			throw new ClassCastException (activity.toString()+" must implement MyAlertDialogListener");
		}
	}

	@SuppressLint("NewApi")
	@Override
	public
	Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder=new Builder (getActivity());
		LayoutInflater mInflater = (LayoutInflater )
                getActivity().getApplicationContext().getSystemService( Activity.LAYOUT_INFLATER_SERVICE);
		View view=mInflater.inflate(R.layout.dialog_alert, null);
		TextView textViewTitle=(TextView )view.findViewById( R.id.textViewTitle);
		TextView textViewMessage=(TextView )view.findViewById(R.id.textViewMessage);
		if(getArguments().getInt("title",-1)==-1){
			textViewTitle.setText(getArguments().getString("title"));
			textViewMessage.setText(getArguments().getString("message"));
		}else{
			textViewTitle.setText(getArguments().getInt("title"));
			textViewMessage.setText(getArguments().getInt("message"));
		}
		
		
		final Button buttonPositive=(Button )view.findViewById(R.id.buttonPositive);
		final Button buttonNegative=(Button )view.findViewById(R.id.buttonNegative);
		if(getArguments().get("negative")!=null){
			buttonNegative.setVisibility( View.VISIBLE);
			buttonNegative.setText(getResources().getString(getArguments().getInt("negative")));
			buttonPositive.setText(getResources().getString(getArguments().getInt("positive")));
		}else{
			buttonNegative.setVisibility( View.GONE);
			buttonPositive.setText(getResources().getString(getArguments().getInt("positive")));
		}
			
		buttonPositive.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mListener.onDialogPositiveClick(MyAlertDialog.this,MyAlertDialog.this.getTag());
				dismiss();
			}
		});
		buttonNegative.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mListener.onDialogNegativeClick(MyAlertDialog.this,MyAlertDialog.this.getTag());
				dismiss();
			}
		});
		
		builder.setView(view);
//		if(getArguments().get("negative")!=null){
//			builder.setNegativeButton(getArguments().getInt("negative"), new OnClickListener() {
//	
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					mListener.onDialogNegativeClick(MyAlertDialog.this,MyAlertDialog.this.getTag());
//				}
//			});
//		}
//		builder.setPositiveButton(getArguments().getInt("positive"), new OnClickListener() {
//			
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				mListener.onDialogPositiveClick(MyAlertDialog.this,MyAlertDialog.this.getTag());
//				
//			}
//		});
		//Dialog dialog=builder.create();
		Dialog dialog=new Dialog (getActivity());
		dialog.requestWindowFeature( Window.FEATURE_NO_TITLE);
		dialog.setContentView(view);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable (0));
		DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
		int width = displayMetrics.widthPixels-2*((int)getResources().getDimension(R.dimen.activity_horizontal_margin));
		dialog.getWindow().setLayout(width, LayoutParams.WRAP_CONTENT);
//		dialog.setOnShowListener(new OnShowListener() {
//			
//			@SuppressWarnings("deprecation")
//			@Override
//			public void onShow(DialogInterface dialog) {
//				Button negativeButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
//				Button positiveButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
//				final Drawable negativeButtonDrawable = getResources().getDrawable(R.drawable.background_button);
//				final Drawable positiveButtonDrawable = getResources().getDrawable(R.drawable.background_button);
//				if (Build.VERSION.SDK_INT >= 16) {
//					negativeButton.setBackground(negativeButtonDrawable);
//			        positiveButton.setBackground(positiveButtonDrawable);
//				} else {
//			        negativeButton.setBackgroundDrawable(negativeButtonDrawable);
//			        positiveButton.setBackgroundDrawable(positiveButtonDrawable);
//				}
//				  negativeButton.setTextColor(getResources().getColor(R.color.white));
//				  positiveButton.setTextColor(getResources().getColor(R.color.white));
//			      negativeButton.invalidate();
//			      positiveButton.invalidate();				
//			}
//		});
		return dialog;
	}
	
}
