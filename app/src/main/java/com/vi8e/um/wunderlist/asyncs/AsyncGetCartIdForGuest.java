package com.vi8e.um.wunderlist.asyncs;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.sharedprefs.SessionManagement;
import com.vi8e.um.wunderlist.utils.AsyncResponse;
import com.vi8e.um.wunderlist.utils.Utils;

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


public class AsyncGetCartIdForGuest extends AsyncTask<String, String, String> {
private Activity          activity;
private String            uRL;
private SessionManagement session;
public AsyncResponse delegate = null;

public
AsyncGetCartIdForGuest ( Activity activity ) {
	this.activity = activity;
}

@Override
protected
void onPostExecute ( String result ) {
	delegate.processFinish ( result, "cartid" );
	super.onPostExecute ( result );
}

@Override
protected
void onPreExecute () {
	session = new SessionManagement ( activity.getApplicationContext () );
	super.onPreExecute ();
}

@Override
protected
String doInBackground ( String... params ) {
	uRL = activity.getApplicationContext ().getResources ().getString ( R.string.server_url ) +
	      activity.getApplication ().getResources ().getString ( R.string.get_cart_id );
	String outPut = "";
	//JSONObject jObject=new JSONObject();
	//JSONObject jRequest=new JSONObject();
	JSONObject jParams = new JSONObject ();
	try {
		//jObject.put("auth", Utils.auth);
		jParams.put ( "customer_id", ( session.getCustomerId () == null || session.getCustomerId ().equals ( "" ) ? "0" : session.getCustomerId()));
			jParams.put("ip_address", Utils.getIPAddress(true));
			//jObject.put("params", jParams);
			Log.i ( "input", jParams.toString () );
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
			((HttpPost ) httppost).setEntity(new StringEntity (jParams.toString().replace("\\n", "")));
			HttpParams httpParameters=new BasicHttpParams ();
			int timeout=7000;
			HttpConnectionParams.setConnectionTimeout ( httpParameters, timeout );
			HttpClient client = new DefaultHttpClient (httpParameters);
			HttpResponse response = client.execute(httppost);
            outPut = EntityUtils.toString ( response.getEntity () );
			Log.i ( "output", outPut );
            //String decryptResult=Encryptor.Decrypt(outPut);
            return outPut;
		}
		catch (Exception e) {
			e.printStackTrace();
			return "408";
		}
	}

}
