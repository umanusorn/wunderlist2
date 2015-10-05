package com.vi8e.um.wunderlist.asyncs;


import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.utils.AsyncResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;


public class AsyncBanner extends AsyncTask<String, String, String> {
private Activity activity;
private String   uRL;
public AsyncResponse delegate = null;

public
AsyncBanner ( Activity activity ) {
	this.activity = activity;
}

@Override
protected
void onPostExecute ( String result ) {
	delegate.processFinish ( result, "banner" );
	super.onPostExecute ( result );
}

@Override
protected
void onPreExecute () {
	super.onPreExecute ();
}

@Override
protected
String doInBackground ( String... params ) {
	uRL = activity.getApplicationContext ().getResources ().getString ( R.string.server_url ) +
	      activity.getApplication ().getResources ().getString ( R.string.get_banner_url );
	String outPut = "";
	try {
		HttpPost httppost = new HttpPost ( uRL );
		httppost.addHeader ( new BasicHeader ( "Content-Type", "application/json" ) );
		HttpParams httpParameters=new BasicHttpParams ();
			int timeout=5000;
			HttpConnectionParams.setConnectionTimeout ( httpParameters, timeout );
			HttpClient client = new DefaultHttpClient (httpParameters);
			HttpResponse response = client.execute(httppost);
            outPut = EntityUtils.toString ( response.getEntity () );
			Log.e ( "async banner", "banner output = " + outPut );
            return outPut;
		}
		catch (Exception e) {
			e.printStackTrace();
			return "408";
		}
	}

}

