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
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by evania91 on 02-09-2015.
 */
public class AsyncPassionCardConfig extends AsyncTask<String, String, String> {
private Activity          activity;
public  AsyncResponse     delegate;
private ProgressDialog    progressDialog;
private SessionManagement session;
private String            url;
private String            param0;

public
AsyncPassionCardConfig ( Activity activity ) {
	this.activity = activity;
}

@Override
protected
void onPreExecute () {
	param0 = "";
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
	param0 = params[ 0 ];
	if ( ! params[ 0 ].equals ( "" ) ) {
		url = activity.getResources ().getString ( R.string.server_url ) +
		      activity.getResources ().getString ( R.string.link_passion_card );
	}
	else {
		url = activity.getResources ().getString ( R.string.server_url ) +
		      activity.getResources().getString(R.string.unlink_passion_card);
        }
        String output = "";
        JSONObject jParams = new JSONObject ();
        try {
            jParams.put("customer_id", session.getCustomerId());
            if (!params[0].equals("")) jParams.put("code", params[0]);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            HttpPost httppost = new HttpPost (url);
            httppost.addHeader(new BasicHeader ("Content-Type", "application/json"));
            ((HttpPost ) httppost).setEntity(new StringEntity (jParams.toString().replace("\\n", "")));
            HttpParams httpParameters=new BasicHttpParams ();
            int timeout=7000;
            HttpConnectionParams.setConnectionTimeout ( httpParameters, timeout );
            HttpClient client = new DefaultHttpClient (httpParameters);
            HttpResponse response = client.execute(httppost);
            output = EntityUtils.toString ( response.getEntity () );
            Log.i ( "output", output );
            return output;
        } catch (Exception e) {
            e.printStackTrace();
            return "408";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        progressDialog.dismiss();
        if (!param0.equals(""))
            delegate.processFinish(s, "linkpassioncard");
        else delegate.processFinish(s, "unlinkpassioncard");
        super.onPostExecute(s);
    }
}
