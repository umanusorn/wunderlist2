package com.vi8e.um.wunderlist.asyncs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.sharedprefs.SessionManagement;
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


/**
 * Created by evania91 on 09-09-2015.
 */
public class AsyncCheckPAssionCard extends AsyncTask<String, String, String> {
private Activity       activity;
private String         url;
private ProgressDialog progressDialog;
public AsyncResponse delegate = null;
private SessionManagement session;

public
AsyncCheckPAssionCard ( Activity activity ) {
	this.activity = activity;
}

@Override
protected
void onPreExecute () {
	progressDialog = new ProgressDialog ( activity );
	progressDialog.setProgressStyle ( ProgressDialog.STYLE_SPINNER );
	progressDialog.setCancelable ( false );
	progressDialog = ProgressDialog.show ( activity, null, "Loading" );
	session = new SessionManagement ( activity.getApplicationContext () );
	super.onPreExecute ();
}

@Override
protected
String doInBackground ( String... params ) {
	url = activity.getResources ().getString ( R.string.server_url ) +
	      activity.getResources ().getString ( R.string.passion_card_standalone );
	String output = "";
	try {
		HttpPost httppost = new HttpPost ( url );
		httppost.addHeader(new BasicHeader ("Content-Type", "application/json"));
            HttpParams httpParameters = new BasicHttpParams ();
            int timeout = 7000;
            HttpConnectionParams.setConnectionTimeout ( httpParameters, timeout );
            HttpClient client = new DefaultHttpClient (httpParameters);
            HttpResponse response = client.execute(httppost);
            output = EntityUtils.toString ( response.getEntity () );
            Log.i ( "output", output );
            return output;
        }
        catch (Exception e) {
            e.printStackTrace();
            return "408";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        progressDialog.dismiss();
        delegate.processFinish(s, "checkpassioncard");
        super.onPostExecute(s);
    }
}
