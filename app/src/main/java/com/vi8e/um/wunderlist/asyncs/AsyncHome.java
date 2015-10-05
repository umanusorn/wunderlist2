package com.vi8e.um.wunderlist.asyncs;

import android.app.Activity;
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


public class AsyncHome extends AsyncTask<String, String, String> {
private Activity activity;
private String   uRL;
public AsyncResponse delegate = null;

public
AsyncHome ( Activity activity ) {
	this.activity = activity;
}

@Override
protected
void onPostExecute ( String result ) {
	Log.i ( "home", result );
	delegate.processFinish ( result, "home" );
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
	      activity.getApplication ().getResources ().getString ( R.string.get_homepage );
	String outPut = "";
		/*JSONObject jObject=new JSONObject();
		JSONObject jRequest=new JSONObject();
		try {
			jObject.put("auth", Utils.auth);
			//String encryptAuth=Encryptor.Encrypt(jObject.toString());
			jRequest.put("request", jObject.toString());
		} catch (JSONException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
		try{
			HttpPost httppost = new HttpPost (uRL);
			httppost.addHeader(new BasicHeader ("Content-Type", "application/json"));
			//String request=jRequest.toString().replace("\\n", "");
			//Log.i("request", request);
			//((HttpPost) httppost).setEntity(new StringEntity(request));
			HttpParams httpParameters=new BasicHttpParams ();
			int timeout=7000;
			HttpConnectionParams.setConnectionTimeout ( httpParameters, timeout );
			HttpClient client = new DefaultHttpClient (httpParameters);
			HttpResponse response = client.execute(httppost);
            outPut = EntityUtils.toString ( response.getEntity () );
            //String decryptResult=Encryptor.Decrypt(outPut);
            return outPut;
		}
		catch (Exception e) {
			e.printStackTrace();
			return "408";
		}
	}

}

