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
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class AsyncProduct extends AsyncTask<String, String, String> {
private Activity       activity;
private String         uRL;
private ProgressDialog progressDialog;
public AsyncResponse delegate = null;
private boolean isFirst;
private String  type;

public
AsyncProduct ( Activity activity, boolean isFirst, String type ) {
	this.activity = activity;
	this.isFirst = isFirst;
	this.type = type;
}

@Override
protected
void onPostExecute ( String result ) {
	try {
		if ( isFirst ) {
			progressDialog.dismiss ();
		}
	}
	catch ( Exception e ) {
		e.printStackTrace ();
		progressDialog = null;
	}

	delegate.processFinish ( result, "product" );
	super.onPostExecute ( result );
}

@Override
protected
void onPreExecute () {
	if ( isFirst ) {
		progressDialog = new ProgressDialog ( activity );
		progressDialog.setProgressStyle ( ProgressDialog.STYLE_SPINNER );
		progressDialog.setCancelable ( false );
		progressDialog = ProgressDialog.show ( activity, null, "Loading" );
	}
	super.onPreExecute ();
}

@Override
protected
String doInBackground ( String... params ) {
	uRL = activity.getApplicationContext ().getResources ().getString (R.string.server_url)+
				activity.getApplication().getResources().getString(R.string.get_product);
		String outPut="";
		JSONObject jParams=new JSONObject ();
		try {
			//jObject.put("auth", Utils.auth);
			if(type.equals("category")){
				jParams.put("category_id", params[0]);
			}else if(type.equals("brand")){
				jParams.put("brand_id", params[0]);
			}
			jParams.put("page", params[1]);
            Log.i ( "input ", jParams.toString () );
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
		}catch (UnsupportedEncodingException e) {
			try {
				JSONObject jError=new JSONObject (outPut);
				return(outPut);
			} catch (JSONException e1) {
				e1.printStackTrace();
				return "408";
			}
		}catch (NullPointerException e) {
			try {
				JSONObject jError=new JSONObject (outPut);
				return(outPut);
			} catch (JSONException e1) {
				e1.printStackTrace();
				return "408";
			}
		}catch (Exception e) {
			e.printStackTrace();
			return "408";
		}
	}

}
