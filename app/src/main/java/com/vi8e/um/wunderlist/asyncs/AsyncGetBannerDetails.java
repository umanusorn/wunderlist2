package com.vi8e.um.wunderlist.asyncs;

import android.app.Activity;
import android.app.ProgressDialog;
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


/**
 * Created by evania91 on 25-09-2015.
 */
public class AsyncGetBannerDetails extends AsyncTask<String, String, String> {
private Activity       activity;
private String         url;
private ProgressDialog progressDialog;
public AsyncResponse delegate = null;

public
AsyncGetBannerDetails ( Activity activity ) {
	this.activity = activity;
}

@Override
protected
void onPreExecute () {
	progressDialog = new ProgressDialog ( activity );
	progressDialog.setProgressStyle ( ProgressDialog.STYLE_SPINNER );
	progressDialog.setCancelable ( false );
	progressDialog = ProgressDialog.show ( activity, null, "Loading" );
	super.onPreExecute ();
}

@Override
protected
String doInBackground ( String... params ) {
	url = activity.getResources ().getString ( R.string.server_url ) +
	      activity.getResources ().getString ( R.string.get_banner_detail );
	try {
		String output = "";
		HttpPost httppost = new HttpPost ( url );
		httppost.addHeader (new BasicHeader ("Content-Type", "application/json"));
            HttpParams httpParameters = new BasicHttpParams ();
            int timeout=5000;
            HttpConnectionParams.setConnectionTimeout ( httpParameters, timeout );
            HttpClient client = new DefaultHttpClient (httpParameters);
            HttpResponse response = client.execute(httppost);
            output = EntityUtils.toString ( response.getEntity () );
            Log.e ( "banner output", "banner output = " + output );
            return output;
        } catch (Exception e) {
            e.printStackTrace();
            return "408";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        progressDialog.dismiss();
        delegate.processFinish(s, "banner");
        super.onPostExecute(s);
    }
}
