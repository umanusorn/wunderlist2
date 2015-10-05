package com.vi8e.um.wunderlist.asyncs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.sharedprefs.CartManagement;
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


public class AsyncCartInfo extends AsyncTask<String, String, String> {
private Activity       activity;
private String         uRL;
private ProgressDialog progressDialog;
private CartManagement cartHeader;
public AsyncResponse delegate = null;
private boolean isLoading;

public
AsyncCartInfo ( Activity activity, boolean isLoading ) {
	this.activity = activity;
	this.isLoading = isLoading;
}

@Override
protected
void onPostExecute ( String result ) {
	if ( isLoading )
		if ( progressDialog != null ) progressDialog.dismiss ();
	delegate.processFinish ( result, "cartinfo" );
	super.onPostExecute ( result );
}

@Override
protected
void onPreExecute () {
	if ( isLoading ) {
		progressDialog = new ProgressDialog ( activity );
		progressDialog.setProgressStyle ( ProgressDialog.STYLE_SPINNER );
		progressDialog.setCancelable ( false );
		progressDialog = ProgressDialog.show ( activity, null, "Loading" );
	}
	cartHeader = new CartManagement ( activity.getApplicationContext () );
	super.onPreExecute ();
}

@Override
protected
String doInBackground ( String... params ) {
	uRL = activity.getApplicationContext ().getResources ().getString ( R.string.server_url ) +
	      activity.getApplication().getResources().getString(R.string.get_cart_info);
		String outPut="";
		JSONObject jParams=new JSONObject ();
		try {
			jParams.put("cart_id", cartHeader.getCartId());
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		try{
			HttpPost httppost = new HttpPost (uRL);
			httppost.addHeader(new BasicHeader ("Content-Type", "application/json"));
			((HttpPost ) httppost).setEntity(new StringEntity (jParams.toString().replace("\\n", "")));
			HttpParams httpParameters=new BasicHttpParams ();
			int timeout=7000;
			HttpConnectionParams.setConnectionTimeout ( httpParameters, timeout );
			HttpClient client = new DefaultHttpClient (httpParameters);
			HttpResponse response = client.execute(httppost);
            outPut = EntityUtils.toString ( response.getEntity () );
			Log.i ( "output", outPut );
            return outPut;
		}
		catch (Exception e) {
			e.printStackTrace();
			return "408";
		}
	}

}