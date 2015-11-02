package com.vi8e.um.wunderlist.Activity.Giants;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.asyncs.AsyncPopulateAddress;
import com.vi8e.um.wunderlist.asyncs.AsyncValidate2;
import com.vi8e.um.wunderlist.dialogs.MyAlertDialog;
import com.vi8e.um.wunderlist.utils.AsyncResponse;

import org.json.JSONException;
import org.json.JSONObject;


public class RegisterSecondActivity extends Activity implements OnClickListener,AsyncResponse, MyAlertDialog.MyAlertDialogListener{
private EditText editTextCompany, editTextRegNo, editTextDFSG, editTextFirst, editTextLast, editTextPostal, editTextAddress, editTextMobile, editTextOffice,
		editTextHome;
private boolean  isResidential;
private Button   buttonNext;
private TextView textViewErrorCompany, textViewErrorRegNo, textViewErrorDFSG, textViewErrorFirst, textViewErrorLast, textViewErrorPostal,
		textViewErrorAddress, textViewErrorMobile, textViewErrorHome, textViewErrorOffice, textViewAddress;
private String customer, passionCardID;
private LinearLayout   linearCompany;
private AsyncValidate2 asyncRegister;
private String         firstname, lastname;
private AsyncPopulateAddress asyncPopulateAddress;
private boolean isFocus = false;
private TextView textViewHomeNumber, textViewMobileNumber, textViewOfficeNumber, textViewTitle;

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_register_second );
	//getActionBar ().setDisplayHomeAsUpEnabled ( true );
	//getActionBar ().setHomeButtonEnabled ( true );
	editTextCompany = ( EditText ) findViewById ( R.id.editTextCompanyName );
	editTextRegNo = ( EditText ) findViewById ( R.id.editTextRegNumber );
	editTextDFSG = ( EditText ) findViewById ( R.id.editTextDFSG );
	editTextFirst = ( EditText ) findViewById ( R.id.editTextFirstName );
	editTextLast = ( EditText ) findViewById ( R.id.editTextLastName );
	editTextPostal = ( EditText ) findViewById ( R.id.editTextPostalCode );
	editTextAddress = ( EditText ) findViewById ( R.id.editTextBillingAddress );
	editTextMobile = ( EditText ) findViewById ( R.id.editTextMobile );
	editTextOffice = ( EditText ) findViewById ( R.id.editTextOffice );
	editTextHome = ( EditText ) findViewById ( R.id.editTextHome );
	textViewTitle = ( TextView ) findViewById ( R.id.textViewTitle );
	buttonNext = ( Button ) findViewById ( R.id.buttonNext );
	linearCompany = ( LinearLayout ) findViewById ( R.id.linearCompany );
//		textViewError=(TextView)findViewById(R.id.textViewError);
	textViewAddress = ( TextView ) findViewById ( R.id.textViewAddress );
	textViewErrorCompany = ( TextView ) findViewById ( R.id.textViewErrorCompanyName );
	textViewErrorRegNo = ( TextView ) findViewById ( R.id.textViewErrorRegNumber );
	textViewErrorDFSG = ( TextView ) findViewById ( R.id.textViewErrorDFSG );
	textViewErrorFirst = ( TextView ) findViewById ( R.id.textViewErrorFirstName );
	textViewErrorLast = ( TextView ) findViewById ( R.id.textViewErrorLastName);
		textViewErrorPostal=(TextView )findViewById(R.id.textViewErrorPostal);
		textViewErrorAddress=(TextView )findViewById(R.id.textViewErrorAddress);
		textViewErrorMobile=(TextView )findViewById(R.id.textViewErrorMobile);
		textViewErrorHome=(TextView )findViewById(R.id.textViewErrorHome);
		textViewErrorOffice=(TextView )findViewById(R.id.textViewErrorOffice);
		
		textViewHomeNumber=(TextView )findViewById(R.id.textViewHomeNumber);
		textViewOfficeNumber=(TextView )findViewById(R.id.textViewOfficeNumber);
		textViewMobileNumber=(TextView )findViewById(R.id.textViewMobileNumber);
		resetErrorMessage();
		
		Intent i=getIntent();
		firstname=i.getStringExtra("firstname");
		lastname=i.getStringExtra ( "lastname" );
		isResidential=i.getBooleanExtra ( "isResidential", true );
		customer=i.getStringExtra("jsonCustomer");
		passionCardID = i.getStringExtra("passionCard");
		editTextFirst.setText(firstname);
		editTextLast.setText ( lastname );
		if(isResidential){
			textViewTitle.setText("Billing Address");
			linearCompany.setVisibility( View.GONE);
//			editTextCompany.setVisibility(View.GONE);
//			editTextRegNo.setVisibility(View.GONE);
//			editTextDFSG.setVisibility(View.GONE);
			textViewAddress.setText("Billing address");
			textViewHomeNumber.setText("Home Number *");
			textViewMobileNumber.setText("Mobile Number *");
			textViewOfficeNumber.setText("Office Number");
		}else{
			textViewTitle.setText("Company Detail");
			linearCompany.setVisibility( View.VISIBLE);
//			editTextCompany.setVisibility(View.VISIBLE);
//			editTextRegNo.setVisibility(View.VISIBLE);
//			editTextDFSG.setVisibility(View.VISIBLE);
			textViewAddress.setText("Company address *");
			textViewHomeNumber.setText("Home Number");
			textViewMobileNumber.setText("Mobile Number *");
			textViewOfficeNumber.setText("Office Number *");

		}
		buttonNext.setOnClickListener ( this );
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
						asyncPopulateAddress = new AsyncPopulateAddress ( RegisterSecondActivity.this );
						asyncPopulateAddress.delegate = RegisterSecondActivity.this;
						asyncPopulateAddress.execute ( editTextPostal.getText ().toString () );
					}
				}
			}
		} );
		//((MyApp)getApplication()).getTracker(MyApp.TrackerName.APP_TRACKER);
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register_second, menu);
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

	@Override
	public void onClick(View v) {
		if(v==buttonNext){
				if(isResidential){
					asyncRegister=new AsyncValidate2(this);
					asyncRegister.delegate=this;
					asyncRegister.execute(
							"residential",
							customer,
							editTextFirst.getText().toString(),
							editTextLast.getText().toString(),
							editTextPostal.getText().toString(),
							editTextAddress.getText().toString(),
							editTextMobile.getText().toString(),
							editTextHome.getText().toString(),
							editTextOffice.getText().toString()
							);
				}else{
					try {
						JSONObject jCustomer=new JSONObject (customer);
						jCustomer.put("company_reg_no", editTextRegNo.getText().toString());
						jCustomer.put("company",editTextCompany.getText().toString());
						jCustomer.put("dfsg_vcode", editTextDFSG.getText().toString());
						asyncRegister=new AsyncValidate2(this);
						asyncRegister.delegate=this;
						asyncRegister.execute(
								"company",
								jCustomer.toString(),
								editTextFirst.getText().toString(),
								editTextLast.getText().toString(),
								editTextPostal.getText().toString(),
								editTextAddress.getText().toString(),
								editTextMobile.getText().toString(),
								editTextHome.getText().toString(),
								editTextOffice.getText().toString()
								);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					
				}
		}
	}
	private void resetErrorMessage(){
		textViewErrorCompany.setVisibility( View.GONE);
		textViewErrorRegNo.setVisibility( View.GONE);
		textViewErrorDFSG.setVisibility( View.GONE);
		textViewErrorFirst.setVisibility( View.GONE);
		textViewErrorLast.setVisibility( View.GONE);
		textViewErrorPostal.setVisibility( View.GONE);
		textViewErrorAddress.setVisibility( View.GONE);
		textViewErrorMobile.setVisibility( View.GONE);
		textViewErrorHome.setVisibility( View.GONE);
		textViewErrorOffice.setVisibility( View.GONE);
	}
	@Override
	public void processFinish(String output, String tag) {
		if(tag.equals("register")){
			Log.i ( "reg2", output );
			String[]outputs=output.split("\\|");
			if(outputs.length>3){
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
						if(!jData.isNull("company_reg_no")){
							textViewErrorRegNo.setText(jData.getString("company_reg_no").split(";")[0]);
							textViewErrorRegNo.setVisibility( View.VISIBLE);
						}
						if(!jData.isNull("company")){
							textViewErrorCompany.setText(jData.getString("company").split(";")[0]);
							textViewErrorCompany.setVisibility( View.VISIBLE);
						}
						if(!jData.isNull("dfsg_vcode")){
							textViewErrorDFSG.setText(jData.getString("dfsg_vcode").split(";")[0]);
							textViewErrorDFSG.setVisibility( View.VISIBLE);
						}
					}else{
						Intent i=new Intent (getApplicationContext(), RegisterThirdActivity.class);
						i.putExtra("firstnamecustomer", firstname);
						i.putExtra("lastnamecustomer", lastname);
						i.putExtra("first_name", editTextFirst.getText().toString());
						i.putExtra("last_name", editTextLast.getText().toString());
						i.putExtra("address", editTextAddress.getText().toString());
						i.putExtra("mobile_phone", editTextMobile.getText().toString());
						i.putExtra("home", editTextHome.getText().toString());
						i.putExtra("office", editTextOffice.getText().toString());
						i.putExtra("postal", editTextPostal.getText().toString());
						i.putExtra("isResidential", isResidential);
						i.putExtra("passionCard", passionCardID);
						i.putExtra("jsonCustomer", outputs[3]);
						i.putExtra("customerAddress", outputs[1]);
						i.putExtra("customerCompany", outputs[2]);
						startActivity(i);
					}
				} catch (JSONException e) {
					try {
						JSONObject jError = new JSONObject (output);
						Log.i ( "error masuk", "error masuk" );
						DialogFragment newFragment = MyAlertDialog.newInstance("Information", jError.getString("error_message"),R.string.ok_button);
						newFragment.show(getFragmentManager(), "serverdown");
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
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
	}

	@Override
	public void onDialogPositiveClick(DialogFragment dialog, String tag) {
		if (tag.equals("serverdown")) {
			
			//// TODO: 10/5/2015  
			/*Intent intent = new Intent (RegisterSecondActivity.this, DashboardActivity.class);
			intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("EXIT", true);
			startActivity(intent);*/
		}
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog, String tag) {

	}
}
