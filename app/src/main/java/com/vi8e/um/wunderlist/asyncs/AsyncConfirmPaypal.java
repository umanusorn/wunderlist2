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
import org.json.JSONObject;


/**
 * Created by Evania on 13-Aug-15.
 */
public class AsyncConfirmPaypal extends AsyncTask<String, String, String> {
private Activity       activity;
private String         uRL;
private ProgressDialog progressDialog;
public AsyncResponse delegate = null;
private SessionManagement session;
private CartManagement    cartHeader;

public
AsyncConfirmPaypal ( Activity activity ) {
	this.activity = activity;
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
String doInBackground ( String... params ) {
	uRL = activity.getApplicationContext ().getResources ().getString ( R.string.server_url ) +
	      activity.getApplication ().getResources ().getString ( R.string.post_paypal_confirmation );
	String output = "";
        /*JSONObject jObject = new JSONObject();
        JSONObject jRequest = new JSONObject();*/
        JSONObject jParams = new JSONObject ();
        try {
            //jObject.put("auth", Utils.auth);
            jParams.put("session_id", cartHeader.getCheckoutId());
            jParams.put("action", "post");
            jParams.put("order_id", params[0]);
            jParams.put("payment_info", params[1].replace("\\", ""));
            /*jObject.put("params", jParams);
            Log.i("input", jObject.toString());
            //String encryptAuth= Encryptor.Encrypt(jObject.toString());
            jRequest.put("request", jObject.toString());*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            HttpPost httppost = new HttpPost (uRL);
            httppost.addHeader(new BasicHeader ("Content-Type", "application/json"));
            ((HttpPost ) httppost).setEntity(new StringEntity (jParams.toString().replace("\\n", "")));
            HttpParams httpParameters=new BasicHttpParams ();
            int timeout = 7000;
            HttpConnectionParams.setConnectionTimeout ( httpParameters, timeout );
            HttpClient client = new DefaultHttpClient (httpParameters);
            HttpResponse response = client.execute(httppost);
            output = EntityUtils.toString ( response.getEntity () );
            Log.i ( "output paypal", "output paypal = " + output );
            //String decryptResult=Encryptor.Decrypt(output);
            return output;
        } catch (Exception e) {
            e.printStackTrace();
            return "408";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        progressDialog.dismiss();
        delegate.processFinish(result, "verify");
        super.onPostExecute(result);
    }
}
