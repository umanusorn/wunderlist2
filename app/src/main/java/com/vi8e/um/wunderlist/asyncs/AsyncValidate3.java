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


public class AsyncValidate3 extends AsyncTask<String, String, String> {
private Activity       activity;
private String         uRL;
private ProgressDialog progressDialog;
public AsyncResponse delegate = null;

public
AsyncValidate3 ( Activity activity ) {
	this.activity = activity;
}

@Override
protected
void onPostExecute ( String result ) {
	progressDialog.dismiss ();
	delegate.processFinish ( result, "register" );
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
	      activity.getApplication ().getResources ().getString ( R.string.validate );
	String outPut = "";
	//JSONObject jObject=new JSONObject();
	//JSONObject jRequest=new JSONObject();
		JSONObject customerAddressShipping=new JSONObject ();
		JSONObject others=new JSONObject ();
		JSONObject jParams=new JSONObject ();
		try {
			//jObject.put("auth", Utils.auth);

			JSONObject customer=new JSONObject (params[0]);
			jParams.put("Customer", customer);
			JSONObject customerAddress=new JSONObject (params[1]);
			jParams.put("CustomerAddress",customerAddress);
			JSONObject customerAddressCompany=new JSONObject (params[2]);
			jParams.put("CustomerAddressCompany", customerAddressCompany);
			jParams.put("step",3);
			customerAddressShipping.put("firstname", params[3]);
			customerAddressShipping.put("middlename", "");
			customerAddressShipping.put("lastname", params[4]);
			customerAddressShipping.put( "postal_code",params[5]);
			customerAddressShipping.put( "street1",params[6]);
			customerAddressShipping.put("street2", "");
			customerAddressShipping.put("city", "");
			customerAddressShipping.put("state", "");
			customerAddressShipping.put("country", "sg");
			customerAddressShipping.put( "mobile_phone",params[7]);
			customerAddressShipping.put( "phone",params[8]);
			customerAddressShipping.put( "fax",params[9]);
			jParams.put("CustomerAddressShipping", customerAddressShipping);
			
			
			
			 others.put("toc", Integer.parseInt ( params[ 10 ] ));
			 others.put("subscribe", Integer.parseInt ( params[ 11 ] ));
			 jParams.put("Others", others);
			
			/*jObject.put("params", jParams);
			Log.i("json", jObject.toString());
			String encryptAuth=Encryptor.Encrypt(jObject.toString());
			jRequest.put("request", encryptAuth);*/
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
            return outPut+"|"+params[0]+"|"+params[1]+"|"+params[2]+"|"+customerAddressShipping.toString()+"|"+others.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
			return "408";
		}
	}
}
