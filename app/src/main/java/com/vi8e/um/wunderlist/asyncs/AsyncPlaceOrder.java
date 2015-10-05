package com.vi8e.um.wunderlist.asyncs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.sharedprefs.CartManagement;
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


public class AsyncPlaceOrder extends AsyncTask<String, String, String> {
private Activity          activity;
private String            uRL;
private ProgressDialog    progressDialog;
private SessionManagement session;
private CartManagement    cartHeader;
private int           isPaypal = 0;
public  AsyncResponse delegate = null;

public
AsyncPlaceOrder ( Activity activity ) {
	this.activity = activity;
}

public
AsyncPlaceOrder ( Activity activity, int isPaypal ) {
	this.activity = activity;
	this.isPaypal = isPaypal;
}

@Override
protected
void onPostExecute ( String result ) {
	progressDialog.dismiss ();
	if ( isPaypal == 0 )
		delegate.processFinish ( result, "placeorder" );
	else delegate.processFinish ( result, "placeorder_paypal" );
	super.onPostExecute ( result );
}

@Override
protected
void onPreExecute () {
	progressDialog = new ProgressDialog ( activity );
	progressDialog.setProgressStyle ( ProgressDialog.STYLE_SPINNER );
	progressDialog.setCancelable ( false );
	progressDialog = ProgressDialog.show ( activity, null, "Loading" );
	session = new SessionManagement ( activity.getApplicationContext () );
	cartHeader = new CartManagement ( activity.getApplicationContext () );
	super.onPreExecute ();
}

@Override
protected
String doInBackground ( String... params) {
		uRL=activity.getApplicationContext().getResources().getString(R.string.server_url)+
				activity.getApplication().getResources().getString(R.string.post_placeorder);
		String outPut="";
		//JSONObject jObject=new JSONObject();
		//JSONObject jRequest=new JSONObject();
		JSONObject jParams=new JSONObject ();
		try {
			//jObject.put("auth", Utils.auth);
			jParams.put("session_id", cartHeader.getCheckoutId());
			jParams.put("action", "post");
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
			Log.i ( "output asyncplaceorder", "output place order = " + outPut );
            //String decryptResult=Encryptor.Decrypt(outPut);
            return outPut;
		}
		catch (Exception e) {
			e.printStackTrace();
			return "408";
		}
	}

}