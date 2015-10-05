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


public class AsyncFilter extends AsyncTask<String, String, String> {
private Activity       activity;
private String         uRL;
private ProgressDialog progressDialog;
public AsyncResponse delegate = null;

public
AsyncFilter ( Activity activity ) {
	this.activity = activity;
}

@Override
protected
void onPostExecute ( String result ) {
	progressDialog.dismiss ();
	delegate.processFinish ( result, "filter" );
	super.onPostExecute ( result );
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
	uRL = activity.getApplicationContext ().getResources ().getString ( R.string.server_url ) +
	      activity.getApplication ().getResources ().getString ( R.string.get_filter );
	String outPut = "";
	JSONObject jParams = new JSONObject ();
	try {
		if ( ! params[ 1 ].equals ( "" ) ) {
			jParams.put("brand_ids", params[1]);
			}
			if(!params[2].equals("")){
				jParams.put("category_ids", params[2]);
			} else {
				jParams.put("category_ids", params[0]);
			}
			jParams.put("filter_min_price", params[3].replace(",", ""));
			jParams.put("filter_max_price", params[4].replace(",", ""));
			jParams.put("page", params[5]);

			Log.i ( "input", jParams.toString () );
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
            Log.i ( "result", outPut );
            
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
