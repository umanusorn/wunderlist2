package com.vi8e.um.wunderlist.asyncs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.provider.Settings;
import android.util.Log;

import com.vi8e.um.wunderlist.R;
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


public class AsyncRegister extends AsyncTask<String, String, String> {
private Activity       activity;
private String         uRL;
private ProgressDialog progressDialog;
public AsyncResponse delegate = null;

public
AsyncRegister ( Activity activity ) {
	this.activity = activity;
}

@Override
protected
void onPostExecute ( String result ) {
	progressDialog.dismiss ();
	delegate.processFinish ( result, "registerfinal" );
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
	      activity.getApplication ().getResources ().getString ( R.string.register );
	String outPut = "";
	//JSONObject jObject=new JSONObject();
	//JSONObject jRequest=new JSONObject();
	JSONObject jParams = new JSONObject ();
		try {
			//jObject.put("auth", Utils.auth);
			jParams.put("client_type", "android");
			jParams.put("client_name", android.os.Build.MODEL);
			jParams.put("client_version", android.os.Build.VERSION.RELEASE);
			jParams.put("retries", "0");
			jParams.put("ip", Utils.getIPAddress(true));
			jParams.put("app_version", R.string.app_version);

			JSONObject customer=new JSONObject (params[0]);
			if (!params[5].equals("")) {
				customer.put("loyalty_card_usage", "yes");
				customer.put("loyalty_card_id", params[5]);
			} else {
				customer.put("loyalty_card_usage", "no");
			}
			jParams.put("Customer", customer);
			JSONObject customerAddress=new JSONObject (params[1]);
			jParams.put("CustomerAddress",customerAddress);
			JSONObject customerAddressCompany=new JSONObject (params[2]);
			jParams.put("CustomerAddressCompany", customerAddressCompany);
			JSONObject customerAddressShipping=new JSONObject (params[3]);
			jParams.put("CustomerAddressShipping", customerAddressShipping);
			JSONObject others=new JSONObject (params[4]);
			jParams.put("Others", others);
			JSONObject deviceJson = new JSONObject ();
			deviceJson.put("unique_id", Settings.Secure.getString(activity.getContentResolver(),
					Settings.Secure.ANDROID_ID));
			deviceJson.put("type", "android");
			jParams.put("CustomerDevice", deviceJson);
			//jObject.put("params", jParams);
			Log.i ( "json", jParams.toString () );
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