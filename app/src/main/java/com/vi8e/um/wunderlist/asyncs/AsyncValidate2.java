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


public class AsyncValidate2 extends AsyncTask<String, String, String> {
private Activity       activity;
private String         uRL;
private ProgressDialog progressDialog;
public AsyncResponse delegate = null;

public
AsyncValidate2 ( Activity activity ) {
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
		/*JSONObject jObject=new JSONObject();
		JSONObject jRequest=new JSONObject();*/
		JSONObject customerAddress=new JSONObject ();
		JSONObject customerAddressCompany=new JSONObject ();
		JSONObject jParams=new JSONObject ();
		try {
			JSONObject customer=new JSONObject (params[1]);
			jParams.put("step",2);
			jParams.put("Customer", customer);
			
			
			if(params[0].equals("residential")){
				customerAddress.put("firstname", params[2]);
				customerAddress.put("middlename", "");
				customerAddress.put("lastname", params[3]);
				customerAddress.put( "postal_code",params[4]);
				customerAddress.put( "street1",params[5]);
				customerAddress.put("street2", "");
				customerAddress.put("city", "");
				customerAddress.put("state", "");
				customerAddress.put("country", "");
				customerAddress.put( "mobile_phone",params[6]);
				customerAddress.put( "phone",params[7]);
				customerAddress.put( "fax",params[8]);
			}else{
				customerAddress.put("firstname", "");
				customerAddress.put("middlename", "");
				customerAddress.put("firstname", "");
				customerAddress.put( "postal_code","");
				customerAddress.put( "street1","");
				customerAddress.put("street2", "");
				customerAddress.put("city", "");
				customerAddress.put("state", "");
				customerAddress.put("country", "");
				customerAddress.put( "mobile_phone","");
				customerAddress.put( "phone","");
				customerAddress.put( "fax","");
			}
			jParams.put("CustomerAddress",customerAddress);
			 

			JSONObject customerAddressShipping=new JSONObject ();
			customerAddressShipping.put("firstname", "");
			customerAddressShipping.put("middlename", "");
			customerAddressShipping.put("firstname", "");
			customerAddressShipping.put( "postal_code","");
			customerAddressShipping.put( "street1","");
			customerAddressShipping.put("street2", "");
			customerAddressShipping.put("city", "");
			customerAddressShipping.put("state", "");
			customerAddressShipping.put("country", "");
			customerAddressShipping.put( "mobile_phone","");
			customerAddressShipping.put( "phone","");
			customerAddressShipping.put( "fax","");
			jParams.put("CustomerAddressShipping", customerAddressShipping);
			
			
			
			if(params[0].equals("company")){
				customerAddressCompany.put("firstname", params[2]);
				customerAddressCompany.put("middlename", "");
				customerAddressCompany.put("lastname", params[3]);
				customerAddressCompany.put( "postal_code",params[4]);
				customerAddressCompany.put( "street1",params[5]);
				customerAddressCompany.put("street2", "");
				customerAddressCompany.put("city", "");
				customerAddressCompany.put("state", "");
				customerAddressCompany.put("country", "sg");
				customerAddressCompany.put( "mobile_phone",params[6]);
				customerAddressCompany.put( "phone",params[7]);
				customerAddressCompany.put( "fax",params[8]);
			}else{
				customerAddressCompany.put("firstname", "");
				customerAddressCompany.put("middlename", "");
				customerAddressCompany.put("lastname", "");
				customerAddressCompany.put( "postal_code","");
				customerAddressCompany.put( "street1","");
				customerAddressCompany.put("street2", "");
				customerAddressCompany.put("city", "");
				customerAddressCompany.put("state", "");
				customerAddressCompany.put("country", "sg");
				customerAddressCompany.put( "mobile_phone","");
				customerAddressCompany.put( "phone","");
				customerAddressCompany.put( "fax","");
			}
			jParams.put("CustomerAddressCompany", customerAddressCompany);
			
			JSONObject others=new JSONObject ();
			 others.put("toc",0);
			 others.put("subscribe",0);
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
            return outPut+"|"+customerAddress.toString()+"|"+customerAddressCompany.toString()+"|"+params[1];
		}
		catch (Exception e) {
			e.printStackTrace();
			return "408";
		}
	}

}
