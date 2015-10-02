package com.vi8e.um.wunderlist.async.asyncs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.util.AsyncResponse;

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


public class AsyncLogout extends AsyncTask<String, String, String> {
private Activity       activity;
private String         uRL;
private ProgressDialog progressDialog;
public AsyncResponse delegate = null;

public
AsyncLogout ( Activity activity ) {
	this.activity = activity;
}

@Override
protected
void onPostExecute ( String result ) {
	progressDialog.dismiss ();
	delegate.processFinish ( result, "logout" );
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
	      activity.getApplication ().getResources ().getString ( R.string.logout );
	String outPut = "";
	//JSONObject jObject=new JSONObject();
	//JSONObject jRequest=new JSONObject();
		JSONObject jParams=new JSONObject ();
		try {
			//jObject.put("auth", Utils.auth);
			jParams.put("login_id", Integer.parseInt ( params[ 0 ] ));
			//jObject.put("params", jParams);
			Log.i ( "input login", jParams.toString () );
			//String encryptAuth=Encryptor.Encrypt(jObject.toString());
			//jRequest.put("request", jParams.toString());
		} catch (JSONException e1) {
			e1.printStackTrace();
		} /*catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
		try{
			HttpPost httppost = new HttpPost (uRL);
			httppost.addHeader(new BasicHeader ("Content-Type", "application/json"));
			httppost.addHeader(new BasicHeader ("Accept", "application/json"));
			((HttpPost ) httppost).setEntity(new StringEntity (jParams.toString().replace("\\n", "")));
			HttpParams httpParameters=new BasicHttpParams ();
			int timeout=7000;
			HttpConnectionParams.setConnectionTimeout ( httpParameters, timeout );
			HttpClient client = new DefaultHttpClient (httpParameters);
			HttpResponse response = client.execute(httppost);
            outPut = EntityUtils.toString ( response.getEntity () );
            Log.i ( "Output login", outPut );
            //String decryptResult=Encryptor.Decrypt(outPut);
            return outPut;
		}
		catch (Exception e) {
			e.printStackTrace();
			return "408";
		}
	}

}

