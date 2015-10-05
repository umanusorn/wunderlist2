package com.vi8e.um.wunderlist.asyncs;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.utils.AsyncResponse;
import com.vi8e.um.wunderlist.utils.DateHelper;

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


public class AsyncValidate1 extends AsyncTask<String, String, String> {
private Activity       activity;
private String         uRL;
private ProgressDialog progressDialog;
public AsyncResponse delegate = null;

public
AsyncValidate1 ( Activity activity ) {
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
	JSONObject customer = new JSONObject ();
	JSONObject jParams = new JSONObject ();
	try {
			//jObject.put("auth", Utils.auth);

			jParams.put("step",1);
			customer.put("account_type", Integer.parseInt ( params[ 0 ] ));
			customer.put("email", params[1]);
			customer.put("username", "");
			customer.put("password", params[2]);
			customer.put("repeat_password", params[3]);
			customer.put("firstname", params[4]);
			customer.put("middlename","");
			customer.put("lastname", params[5]);
			customer.put("nationality","sg");
			customer.put("require_halal_product", Integer.parseInt ( params[ 6 ] ));
			customer.put("salutation",params[7]);
			customer.put("race_id", Integer.parseInt ( params[ 8 ] ));
			customer.put("loyalty_card_usage", params[9]);
			customer.put("loyalty_card_id", params[10]);
			if(params.length>11){
				customer.put("nric", params[11]);
				customer.put("birthday_day", DateHelper.getDay(params[12], "dd MMMM yyyy"));
				customer.put("birthday_month", DateHelper.getMonth(params[12],"dd MMMM yyyy"));
				customer.put("birthday_year",  DateHelper.getYear(params[12],"dd MMMM yyyy"));
				if(params.length>13){
					customer.put("is_dfsg",1);
					customer.put("dfsg_id",params[13]);
				}else{
					customer.put("is_dfsg",0);
					customer.put("dfsg_id","");
				}
			}else{
				customer.put("nric", "");
				customer.put("birthday_day", "");
				customer.put("birthday_month", "");
				customer.put("birthday_year",  "");
				customer.put("is_dfsg",0);
				customer.put("dfsg_id","");
			}
			customer.put("gender","");
			customer.put("religion_id",0);
		    customer.put("company","");
		    customer.put("company_reg_no","");
		    customer.put("delivery_same_home",0);
		    customer.put("delivery_same_company",0);
			jParams.put("Customer", customer);
			
			JSONObject customerAddress=new JSONObject ();
			customerAddress.put("firstname", "");
			customerAddress.put("middlename", "");
			customerAddress.put("lastname", "");
			customerAddress.put( "postal_code","");
			customerAddress.put( "street1","");
			customerAddress.put("street2", "");
			customerAddress.put("city", "");
			customerAddress.put("state", "");
			customerAddress.put("country", "");
			customerAddress.put( "mobile_phone","");
			customerAddress.put( "phone","");
			customerAddress.put( "fax","");
			jParams.put("CustomerAddress",customerAddress);
			 

			JSONObject customerAddressShipping=new JSONObject ();
			customerAddressShipping.put("firstname", "");
			customerAddressShipping.put("middlename", "");
			customerAddressShipping.put("lastname", "");
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
			
			JSONObject customerAddressCompany=new JSONObject ();
			customerAddressCompany.put("firstname", "");
			customerAddressCompany.put("middlename", "");
			customerAddressCompany.put("firstname", "");
			customerAddressCompany.put( "postal_code","");
			customerAddressCompany.put( "street1","");
			customerAddressCompany.put("street2", "");
			customerAddressCompany.put("city", "");
			customerAddressCompany.put("state", "");
			customerAddressCompany.put("country", "");
			customerAddressCompany.put( "mobile_phone","");
			customerAddressCompany.put( "phone","");
			customerAddressCompany.put( "fax","");
			jParams.put("CustomerAddressCompany", customerAddressCompany);
			
			JSONObject others=new JSONObject ();
			 others.put("toc",0);
			 others.put("subscribe",0);
			 jParams.put("Others", others);

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
            //String decryptResult=Encryptor.Decrypt(outPut);
            return outPut+"|"+customer.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
			return "408";
		}
	}

}
