package com.vi8e.um.wunderlist.Activity.Giants;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.asyncs.AsyncGetCartId;
import com.vi8e.um.wunderlist.asyncs.AsyncPopulateAddress;
import com.vi8e.um.wunderlist.asyncs.AsyncRegister;
import com.vi8e.um.wunderlist.asyncs.AsyncStatic;
import com.vi8e.um.wunderlist.asyncs.AsyncValidate3;
import com.vi8e.um.wunderlist.dialogs.MyAlertDialog;
import com.vi8e.um.wunderlist.dialogs.WebViewDialog;
import com.vi8e.um.wunderlist.dialogs.WebViewDialog.WebViewDialogtListener;
import com.vi8e.um.wunderlist.sharedprefs.CartManagement;
import com.vi8e.um.wunderlist.sharedprefs.SessionManagement;
import com.vi8e.um.wunderlist.utils.AsyncResponse;

import org.json.JSONException;
import org.json.JSONObject;

//import com.google.android.gms.analytics.GoogleAnalytics;
//import com.vi8e.um.wunderlist.application.MyApp;


public class RegisterThirdActivity extends Activity implements OnClickListener,AsyncResponse,WebViewDialogtListener, MyAlertDialog.MyAlertDialogListener{
private CheckBox checkBoxDelivery, checkBoxTerms, checkBoxSubscribe;
private EditText editTextFirst, editTextLast, editTextAddress, editTextMobile, editTextHome, editTextOffice, editTextPostal;
private TextView textViewErrorFirst, textViewErrorLast, textViewErrorAddress, textViewErrorMobile, textViewErrorHome,
		textViewErrorOffice, textViewErrorPostal, textViewErrorTerms;
private boolean isResidential;
private Button  buttonRegister;
private String  firstname, lastName, address, mobile, home, office, postal;
private String customer, customerAddress, customerCompany;
private AsyncValidate3    asyncRegister;
private AsyncRegister     asyncRegistration;
private SessionManagement session;
private CartManagement    cartHeader;
private String            email;
private AsyncGetCartId    asyncGetCartId;
private String            firstnamecustomer, lastnamecustomer, passionCardID;
private AsyncPopulateAddress asyncPopulateAddress;
private boolean isFocus = false;
private TextView textViewTerms, textViewPrivacy;
private AsyncStatic asyncStatic;
private TextView    textViewHomeNumber, textViewMobileNumber, textViewOfficeNumber;

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_register_third );
	//getActionBar ().setDisplayHomeAsUpEnabled ( true );
	//getActionBar ().setHomeButtonEnabled ( true );
	session = new SessionManagement ( getApplicationContext () );
	cartHeader = new CartManagement ( getApplicationContext () );
	editTextFirst = ( EditText ) findViewById ( R.id.editTextFirstName );
	editTextLast = ( EditText ) findViewById ( R.id.editTextLastName );
	editTextAddress = ( EditText ) findViewById ( R.id.editTextDeliveryAddress );
	editTextMobile = ( EditText ) findViewById ( R.id.editTextMobile );
	editTextHome = ( EditText ) findViewById ( R.id.editTextHome );
	editTextOffice = ( EditText ) findViewById ( R.id.editTextOffice );
	editTextPostal = ( EditText ) findViewById ( R.id.editTextPostal );
	checkBoxDelivery = ( CheckBox ) findViewById ( R.id.checkBoxDelivery );
	checkBoxSubscribe = ( CheckBox ) findViewById ( R.id.checkBoxSubscribe );

	textViewErrorFirst = ( TextView ) findViewById ( R.id.textViewErrorFirstName );
	textViewErrorLast = ( TextView ) findViewById ( R.id.textViewErrorLastName );
	textViewErrorAddress = ( TextView ) findViewById ( R.id.textViewErrorAddress );
	textViewErrorMobile = ( TextView ) findViewById ( R.id.textViewErrorMobile );
	textViewErrorHome = ( TextView ) findViewById ( R.id.textViewErrorHome );
	textViewErrorOffice = ( TextView ) findViewById ( R.id.textViewErrorOffice );
	textViewErrorPostal = ( TextView ) findViewById ( R.id.textViewErrorPostal );
	textViewErrorTerms = ( TextView ) findViewById ( R.id.textViewErrorTerms );
	textViewTerms = ( TextView ) findViewById ( R.id.textViewTerms );
	textViewPrivacy = ( TextView ) findViewById ( R.id.textViewPrivacy );

	SpannableString spannableTerms = new SpannableString ( getResources ().getString ( R.string.terms ) );
	spannableTerms.setSpan ( new UnderlineSpan (), 0, spannableTerms.length (), 0 );

	SpannableString spannablePrivacy = new SpannableString ( getResources ().getString ( R.string.privacy ) );
	spannablePrivacy.setSpan ( new UnderlineSpan (), 0, spannablePrivacy.length (), 0 );

	textViewTerms.setText ( "Yes, I Accept the " + spannableTerms );
	textViewPrivacy.setText ( "and " + spannablePrivacy );

	textViewHomeNumber = ( TextView ) findViewById ( R.id.textViewHomeNumber );
	textViewOfficeNumber = ( TextView ) findViewById ( R.id.textViewOfficeNumber );
	textViewMobileNumber = ( TextView ) findViewById ( R.id.textViewMobileNumber );

	textViewTerms.setOnClickListener ( this );
	textViewPrivacy.setOnClickListener ( this );
	checkBoxTerms = ( CheckBox ) findViewById ( R.id.checkBoxTerms);
		buttonRegister=(Button )findViewById(R.id.buttonRegister);
		checkBoxDelivery.setChecked ( true );
		buttonRegister.setOnClickListener(this);
		passionCardID = "";
		Intent i=getIntent();
		firstnamecustomer=i.getStringExtra("firstnamecustomer");
		lastnamecustomer=i.getStringExtra("lastnamecustomer");
		customer=i.getStringExtra("jsonCustomer");
		customerAddress=i.getStringExtra("customerAddress");
		customerCompany=i.getStringExtra ( "customerCompany" );
		isResidential=i.getBooleanExtra ( "isResidential", true );
		firstname=i.getStringExtra("first_name");
		lastName=i.getStringExtra("last_name");
		address=i.getStringExtra("address");
		mobile=i.getStringExtra("mobile_phone");
		home=i.getStringExtra("home");
		office=i.getStringExtra("office");
		postal=i.getStringExtra("postal");
		passionCardID = i.getStringExtra("passionCard");
		setDefaultContent ();
		editTextFirst.setEnabled(!checkBoxDelivery.isChecked());
		editTextLast.setEnabled(!checkBoxDelivery.isChecked());
		editTextAddress.setEnabled(!checkBoxDelivery.isChecked());
		editTextMobile.setEnabled(!checkBoxDelivery.isChecked());
		editTextHome.setEnabled(!checkBoxDelivery.isChecked());
		editTextOffice.setEnabled(!checkBoxDelivery.isChecked());
		editTextPostal.setEnabled ( ! checkBoxDelivery.isChecked () );
		checkBoxDelivery.setOnCheckedChangeListener ( new OnCheckedChangeListener () {

			@Override
			public
			void onCheckedChanged ( CompoundButton buttonView, boolean isChecked ) {
				if ( isChecked ) {
					setDefaultContent ();
				}
				editTextFirst.setEnabled ( ! isChecked );
				editTextLast.setEnabled ( ! isChecked );
				editTextAddress.setEnabled ( ! isChecked );
				editTextMobile.setEnabled ( ! isChecked );
				editTextHome.setEnabled ( ! isChecked );
				editTextOffice.setEnabled ( ! isChecked );
				editTextPostal.setEnabled ( ! isChecked );
			}
		} );
		try {
			JSONObject jCustomer=new JSONObject (customer);
			email=jCustomer.getString("email");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		editTextPostal.setOnFocusChangeListener ( new OnFocusChangeListener () {

			@Override
			public
			void onFocusChange ( View v, boolean hasFocus ) {
				if ( hasFocus ) {
					isFocus = true;
				}
				else if ( ! hasFocus && isFocus ) {
					isFocus = false;
					if ( ! editTextPostal.getText ().toString ().equals ( "" ) ) {
						asyncPopulateAddress = new AsyncPopulateAddress ( RegisterThirdActivity.this );
						asyncPopulateAddress.delegate = RegisterThirdActivity.this;
						asyncPopulateAddress.execute ( editTextPostal.getText ().toString () );
					}
				}
			}
		} );
		//((MyApp)getApplication()).getTracker(MyApp.TrackerName.APP_TRACKER);
		if(isResidential){
			textViewHomeNumber.setText("Home Number *");
			textViewMobileNumber.setText("Mobile Number *");
			textViewOfficeNumber.setText("Office Number");
		}else{
			textViewHomeNumber.setText("Home Number");
			textViewMobileNumber.setText("Mobile Number *");
			textViewOfficeNumber.setText("Office Number *");
		}
	}
	@Override
	protected void onStart() {
		//GoogleAnalytics.getInstance(getApplicationContext()).reportActivityStart(this);
		super.onStart();
	}
	@Override
	protected void onStop() {
		//GoogleAnalytics.getInstance(getApplicationContext()).reportActivityStop(this);
		super.onStop();
	}
	private void setDefaultContent(){
		editTextFirst.setText(firstname);
		editTextLast.setText(lastName);
		editTextAddress.setText(address);
		editTextMobile.setText(mobile);
		editTextHome.setText(home);
		editTextOffice.setText(office);
		editTextPostal.setText(postal);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register_third, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	/*private boolean isValid(){
		if(editTextFirst.getText().toString().equals("")){
			textViewError.setText("Please enter your first name");
			return false;
		}
		if(editTextLast.getText().toString().equals("")){
			textViewError.setText("Please enter your last name");
			return false;
		}
		if(editTextPostal	.getText().toString().equals("")){
			textViewError.setText("Please enter your postal code");
			return false;
		}
		if(editTextAddress.getText().toString().equals("")){
			textViewError.setText("Please enter your address");
			return false;
		}
		if(!checkBoxTerms.isChecked()){
			textViewError.setText("Please accept the terms and condition before register");
			return false;
		}
		if(isResidential){
			if(editTextMobile.getText().toString().equals("")){
				textViewError.setText("Please enter your mobile number");
				return false;
			}
			if(editTextHome.getText().toString().equals("")){
				textViewError.setText("Please enter your home phone number");
				return false;
			}
		}else{
			if(editTextMobile.getText().toString().equals("")){
				textViewError.setText("Please enter your mobile number");
				return false;
			}
			if(editTextOffice.getText().toString().equals("")){
				textViewError.setText("Please enter your office phone number");
				return false;
			}
		}
		return true;
	}*/

	@Override
	public void onClick(View v) {
		if(v==buttonRegister){
				Log.i ( "registering", "registering" );
				asyncRegister=new AsyncValidate3(this);
				asyncRegister.delegate=this;
				if(checkBoxDelivery.isChecked()){
					try {
						JSONObject jCustomer=new JSONObject (customer);
						if(isResidential){
							jCustomer.put("delivery_same_home", 1);
						}else{
							jCustomer.put("delivery_same_company", 1);
						}
						customer=jCustomer.toString();
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
				}
					asyncRegister.execute(customer,customerAddress,customerCompany,
							editTextFirst.getText().toString(),
							editTextLast.getText().toString(),
							editTextPostal.getText().toString(),
							editTextAddress.getText().toString(),
							editTextMobile.getText().toString(),
							editTextHome.getText().toString(),
							editTextOffice.getText().toString(),
							(checkBoxTerms.isChecked()?"1":"0"),
							(checkBoxSubscribe.isChecked()?"1":"0")
							);
		}else if(v==textViewPrivacy){
			asyncStatic=new AsyncStatic(this);
			asyncStatic.delegate=this;
			asyncStatic.execute("privacy-policy");
		}else if(v==textViewTerms){
			asyncStatic=new AsyncStatic(this);
			asyncStatic.delegate=this;
			asyncStatic.execute("terms-and-conditions");
		}
	}
	private void resetErrorMessage(){
		textViewErrorFirst.setVisibility( View.GONE);
		textViewErrorLast.setVisibility( View.GONE);
		textViewErrorAddress.setVisibility( View.GONE);
		textViewErrorMobile.setVisibility( View.GONE);
		textViewErrorHome.setVisibility( View.GONE);
		textViewErrorOffice.setVisibility( View.GONE);
		textViewErrorPostal.setVisibility( View.GONE);
		textViewErrorTerms.setVisibility( View.GONE);
	}
	@Override
	public void processFinish(String output, String tag) {
		if(tag.equals("register")){
			Log.i ( "register3", output );
			String[]outputs=output.split("\\|");
			if(outputs.length>5){
				try {
					JSONObject jObject=new JSONObject (outputs[0]);
					if(jObject.getInt("status")==0){
						resetErrorMessage();
						JSONObject jData=jObject.getJSONObject("data");
						if(!jData.isNull("firstname")){
							textViewErrorFirst.setText(jData.getString("firstname").split(";")[0]);
							textViewErrorFirst.setVisibility( View.VISIBLE);
						}
						if(!jData.isNull("lastname")){
							textViewErrorLast.setText(jData.getString("lastname").split(";")[0]);
							textViewErrorLast.setVisibility( View.VISIBLE);
						}
						if(!jData.isNull("postal_code")){
							textViewErrorPostal.setText(jData.getString("postal_code").split(";")[0]);
							textViewErrorPostal.setVisibility( View.VISIBLE);
						}
						if(!jData.isNull("street1")){
							textViewErrorAddress.setText(jData.getString("street1").split(";")[0]);
							textViewErrorAddress.setVisibility( View.VISIBLE);
						}
						if(!jData.isNull("phone")){
							textViewErrorHome.setText(jData.getString("phone").split(";")[0]);
							textViewErrorHome.setVisibility( View.VISIBLE);
						}
						if(!jData.isNull("mobile_phone")){
							textViewErrorMobile.setText(jData.getString("mobile_phone").split(";")[0]);
							textViewErrorMobile.setVisibility( View.VISIBLE);
						}
						if(!jData.isNull("fax")){
							textViewErrorOffice.setText(jData.getString("fax").split(";")[0]);
							textViewErrorOffice.setVisibility( View.VISIBLE);
						}
					}else{
						if(checkBoxTerms.isChecked()){
							asyncRegistration=new AsyncRegister(this);
							asyncRegistration.delegate=this;
							asyncRegistration.execute(outputs[1],outputs[2],outputs[3],outputs[4],outputs[5],passionCardID);
							//asyncRegistration.execute(outputs[1],outputs[2],outputs[3],outputs[4],outputs[5]);
							textViewErrorTerms.setVisibility( View.GONE);
						}else{
							resetErrorMessage();
							textViewErrorTerms.setVisibility( View.VISIBLE);
							textViewErrorTerms.setText("Please agree to the Terms and Conditions and Privacy Policy before proceeding.");
						}
					}
				} catch (JSONException e) {
					try {
						JSONObject jError = new JSONObject (output);
						Log.i ( "error masuk", "error masuk" );
						if (jError.getInt("error_no") == 102) {
							DialogFragment newFragment = MyAlertDialog.newInstance("Information", jError.getString("error_message"), R.string.ok_button);
							newFragment.show(getFragmentManager(), "serverdown");
						} else {
							DialogFragment newFragment = MyAlertDialog.newInstance("Information", jError.getString("error_message"), R.string.ok_button);
							newFragment.show(getFragmentManager(), "");
						}
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
			}
		}else if(tag.equals("registerfinal")){
			Log.e ( "output register", "register = " + output );
			try {
				JSONObject jObject=new JSONObject (output);
				if(jObject.getInt("status")==1){
					session.createLoginSession(jObject.getString("login_id"),
							jObject.getString("customer_id"), email);
					if (jObject.has("force_password"))
						session.setForcePass(jObject.getString("force_password").equals("1"));
					else session.setForcePass(false);
					session.setFirstName(jObject.getString("customer_firstname"));
					session.setLastName(jObject.getString("customer_lastname"));
					session.setPhone(jObject.getString("customer_phone"));
					session.setPAssionCardID(passionCardID);

					JSONObject pcStatus = jObject.getJSONObject("passion_card_status");
					session.createPAssionCardSession(pcStatus.getInt("is_passion_card_enabled"),
							pcStatus.getInt("is_passion_card_redemption_enabled"),
							pcStatus.getInt("is_passion_card_award_enabled"));

					asyncGetCartId=new AsyncGetCartId(this);
					asyncGetCartId.delegate=this;
					asyncGetCartId.execute();
				}
			} catch (JSONException e) {
				try {
					JSONObject jError = new JSONObject (output);
					Log.i ( "error masuk", "error masuk = " + output );
					if (jError.has("error_no") && jError.getInt("error_no") == 102) {
						DialogFragment newFragment = MyAlertDialog.newInstance("Information", jError.getString("error_message"), R.string.ok_button);
						newFragment.show(getFragmentManager(), "serverdown");
					} else {
						DialogFragment newFragment = MyAlertDialog.newInstance("Information", jError.getString("error_message"), R.string.ok_button);
						newFragment.show(getFragmentManager(), "");
					}
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			Log.i ( "registering", output );
		}else if(tag.equals("cartid")){
			Log.i ( "cartid", output );
			JSONObject jObject;
			try {
				jObject = new JSONObject (output);
				session.setCartId(jObject.getString("cart_id"));
				cartHeader.createCart(jObject.getString("cart_id"));
				DialogFragment newFragment = MyAlertDialog.newInstance("REGISTRATION SUCCESSFUL", "Congratulations! Your registration is successful. You can start Shopping with us.", R.string.ok_button);
				newFragment.setCancelable(false);
				newFragment.show(getFragmentManager(), "success");
			} catch (JSONException e) {
				try {
					JSONObject jError = new JSONObject (output);
					Log.i ( "error masuk", "error masuk" );
					if (jError.has("error_no") && jError.getInt("error_no") == 102) {
						DialogFragment newFragment = MyAlertDialog.newInstance("Information", jError.getString("error_message"), R.string.ok_button);
						newFragment.show(getFragmentManager(), "serverdown");
					} else {
						DialogFragment newFragment = MyAlertDialog.newInstance("Information", jError.getString("error_message"), R.string.ok_button);
						newFragment.show(getFragmentManager(), "");
					}
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}else if(tag.equals("populateaddress")){
			Log.i ( "populate address", output );
			try {
				JSONObject jObject=new JSONObject (output);
				editTextAddress.setText(jObject.getString("street_range")+", "+jObject.getString("street"));
			} catch (JSONException e) {
				try {
					JSONObject jError = new JSONObject (output);
					Log.i ( "error masuk", "error masuk" );
					if (jError.has("error_no") && jError.getInt("error_no") == 102) {
						DialogFragment newFragment = MyAlertDialog.newInstance("Information", jError.getString("error_message"), R.string.ok_button);
						newFragment.show(getFragmentManager(), "serverdown");
					} else {
						DialogFragment newFragment = MyAlertDialog.newInstance("Information", jError.getString("error_message"), R.string.ok_button);
						newFragment.show(getFragmentManager(), "");
					}
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			
		}else if(tag.equals("static")){
			try {
				JSONObject jObject=new JSONObject (output);
				DialogFragment newFragment = WebViewDialog.newInstance(jObject.getString("page_title"),
						R.string.ok_button,jObject.getString("page_content"));
			    newFragment.show(getFragmentManager(), "webcontent");
			} catch (JSONException e) {
				try {
					JSONObject jError = new JSONObject (output);
					Log.i ( "error masuk", "error masuk" );
					if (jError.has("error_no") && jError.getInt("error_no") == 102) {
						DialogFragment newFragment = MyAlertDialog.newInstance("Information", jError.getString("error_message"), R.string.ok_button);
						newFragment.show(getFragmentManager(), "serverdown");
					} else {
						DialogFragment newFragment = MyAlertDialog.newInstance("Information", jError.getString("error_message"), R.string.ok_button);
						newFragment.show(getFragmentManager(), "");
					}
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
		}
	}
	@Override
	public void onWebViewDialogPositiveClick(DialogFragment dialog, String tag) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onWebViewDialogNegativeClick(DialogFragment dialog, String tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDialogPositiveClick(DialogFragment dialog, String tag) {
		if (tag.equals("serverdown")) {
			//// TODO: 10/5/2015
			/*Intent intent = new Intent (RegisterThirdActivity.this, DashboardActivity.class);
			intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("EXIT", true);
			startActivity(intent);*/
		} else if (tag.equals("success")) {
			//// TODO: 10/5/2015  
			/*Intent i = new Intent (getApplicationContext(),DashboardActivity.class);
			i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);*/
			finish();
		}
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog, String tag) {

	}
}
