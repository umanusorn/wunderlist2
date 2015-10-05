package com.vi8e.um.wunderlist.dialogs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnShowListener;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.vi8e.um.wunderlist.R;


public class WebViewDialog extends DialogFragment {
	public interface WebViewDialogtListener {
        public void onWebViewDialogPositiveClick( DialogFragment dialog, String tag );
        public void onWebViewDialogNegativeClick( DialogFragment dialog, String tag );
    }
	WebViewDialogtListener mListener;
	public static WebViewDialog newInstance(String title,int positiveButtonTitle,
			int negativeButtonTitle,String mHtml){
		WebViewDialog fragment=new WebViewDialog();
		Bundle args=new Bundle ();
		args.putString("html", mHtml);
		args.putString("title", title);
		args.putInt("positive", positiveButtonTitle);
		args.putInt("negative", negativeButtonTitle);
		fragment.setArguments(args);
		return fragment;
	}
	public static WebViewDialog newInstance(String title,int positiveButtonTitle,
			String mHtml){
		WebViewDialog fragment=new WebViewDialog();
		Bundle args=new Bundle ();
		args.putString("html", mHtml);
		args.putString("title", title);
		args.putInt("positive", positiveButtonTitle);
		fragment.setArguments(args);
		return fragment;
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mListener=(WebViewDialogtListener) getTargetFragment();
		try{
			if(mListener==null)mListener=(WebViewDialogtListener) activity;
		}catch(ClassCastException e){
			throw new ClassCastException (activity.toString()+" must implement WebViewDialogtListener");
		}
	}

	@SuppressLint("NewApi") @Override
	public
	Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
		LayoutInflater mInflater = (LayoutInflater )
                getActivity().getApplicationContext().getSystemService( Activity.LAYOUT_INFLATER_SERVICE);
		View view=mInflater.inflate(R.layout.dialog_webview, null);
		TextView textViewTitle=(TextView )view.findViewById(R.id.textViewTitle);
		WebView webViewContent=(WebView )view.findViewById(R.id.webViewContent);
		webViewContent.getSettings().setJavaScriptEnabled(true);
		webViewContent.setWebViewClient(new WebViewClient ());
		webViewContent.loadData(
				"<html>" +
						"<head>" +
						"<style>" +
							"div,table{width:100% !important}" +
						"</style>" +
						"</head>" +
				"<body>" +
				getArguments().getString("html")+
				"</body>" +
				"</html>", 
				"text/html", "UTF-8");
		textViewTitle.setText(getArguments().getString("title"));
		builder.setView(view);
		if(getArguments().get("negative")!=null){
			builder.setNegativeButton(getArguments().getInt("negative"), new OnClickListener () {
	
				@Override
				public void onClick(DialogInterface dialog, int which) {
					mListener.onWebViewDialogNegativeClick(WebViewDialog.this,WebViewDialog.this.getTag());
				}
			});
		}
		builder.setPositiveButton(getArguments().getInt("positive"), new OnClickListener () {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
					mListener.onWebViewDialogPositiveClick(WebViewDialog.this,WebViewDialog.this.getTag());
			}
		});
		Dialog dialog=builder.create();
		dialog.setOnShowListener(new OnShowListener () {
			
			@Override
			public void onShow(DialogInterface dialog) {
				Button negativeButton = ((AlertDialog ) dialog).getButton( DialogInterface.BUTTON_NEGATIVE);
				Button positiveButton = ((AlertDialog ) dialog).getButton( DialogInterface.BUTTON_POSITIVE);
				final Drawable negativeButtonDrawable = getResources().
						getDrawable( R.drawable.background_button);
				final Drawable positiveButtonDrawable = getResources().
						getDrawable(R.drawable.background_button);
				if ( Build.VERSION.SDK_INT >= 16) {
					negativeButton.setBackground(negativeButtonDrawable);
			        positiveButton.setBackground(positiveButtonDrawable);
				} else {
			        negativeButton.setBackgroundDrawable(negativeButtonDrawable);
			        positiveButton.setBackgroundDrawable(positiveButtonDrawable);
				}
					negativeButton.setTextColor(getResources().getColor(R.color.white));
				  positiveButton.setTextColor(getResources().getColor(R.color.white));
			      negativeButton.invalidate();
			      positiveButton.invalidate();				
			}
		});
		return dialog;
	}
	
}