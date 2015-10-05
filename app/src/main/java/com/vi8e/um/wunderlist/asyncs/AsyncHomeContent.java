package com.vi8e.um.wunderlist.asyncs;

import android.app.Activity;
import android.os.AsyncTask;

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


public class AsyncHomeContent extends AsyncTask<String, String, String> {
private Activity activity;
private String   uRL;
public AsyncResponse delegate = null;

public
AsyncHomeContent ( Activity activity ) {
	this.activity = activity;
}

@Override
protected
void onPostExecute ( String result ) {
	String[] results = result.split ( "\\|" );
	if ( results.length > 1 ) {
		delegate.processFinish ( results[ 0 ], results[ 1 ] );
//		}else{
//			delegate.processFinish(results[0], "error");
	}
	super.onPostExecute ( result );
}

@Override
protected
void onPreExecute () {
	super.onPreExecute ();
}

@Override
protected
String doInBackground ( String... params ) {
	uRL = activity.getApplicationContext ().getResources ().getString ( R.string.server_url ) +
	      activity.getApplication ().getResources ().getString ( R.string.get_tab_url );
	String outPut = "";
	//JSONObject jObject=new JSONObject();
	//JSONObject jRequest=new JSONObject();
	JSONObject jParams=new JSONObject ();
		try {
			//jObject.put("auth", Utils.auth);
			jParams.put("tab_name", params[0]);
			jParams.put("limit", "4");
			//jObject.put("params", jParams);
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
            //String decryptResult=Encryptor.Decrypt(outPut);
            return outPut+"|"+params[0];
		}
		catch (Exception e) {
			e.printStackTrace();
			return "408|"+params[0];
		}
	}

}

