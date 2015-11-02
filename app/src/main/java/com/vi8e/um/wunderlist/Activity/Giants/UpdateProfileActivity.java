package com.vi8e.um.wunderlist.Activity.Giants;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.adapters.MyArrayAdapter;
import com.vi8e.um.wunderlist.asyncs.AsyncGetProfile;
import com.vi8e.um.wunderlist.asyncs.AsyncUpdateProfile;
import com.vi8e.um.wunderlist.dialogs.MyAlertDialog;
import com.vi8e.um.wunderlist.dialogs.MyAlertDialog.MyAlertDialogListener;
import com.vi8e.um.wunderlist.dialogs.MyDatePickerDialog;
import com.vi8e.um.wunderlist.dialogs.MyDatePickerDialog.DatePickerDialogListener;
import com.vi8e.um.wunderlist.sharedprefs.SessionManagement;
import com.vi8e.um.wunderlist.utils.AsyncResponse;
import com.vi8e.um.wunderlist.utils.DateHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//import com.vi8e.um.wunderlist.application.MyApp;

//import com.google.android.gms.analytics.GoogleAnalytics;


public class UpdateProfileActivity extends Activity implements AsyncResponse,MyAlertDialogListener,OnClickListener,DatePickerDialogListener{
private LinearLayout linearCorporate, linearResidential, linearDFSG, linearSubscribe;
private EditText editTextEmail, editTextAccountType, editTextFirst, editTextLast, editTextNRIC, editTextDOB, editTextDFSG;
private EditText editTextCompany, editTextRegNo, editTextDFSGVCODE, editTextNationality;
private TextView textViewErrorFirst, textViewErrorLast, textViewErrorNRIC, textViewErrorDOB, textViewErrorDFSG, textViewErrorCompany,
		textViewErrorRegNo, textViewErrorDFSGVCODE;
private Spinner spinnerTitle, spinnerRace;
private CheckBox checkBoxHalal, checkBoxSubscribe;
private SessionManagement session;
private String[] title = {"- Title -" , "Mr" , "Ms" , "Mrs" , "Mdm"};
private String[] race  = {"- Race -" , "Chinese" , "Malay" , "Indian" , "Others"};
private MyArrayAdapter     titleAdapter;
private MyArrayAdapter     raceAdapter;
private AsyncGetProfile    asyncGetProfile;
private AsyncUpdateProfile asyncUpdateProfile;
private Button             buttonUpdate;

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_update_profile );
	//getActionBar ().setHomeButtonEnabled ( true );
	//getActionBar ().setDisplayHomeAsUpEnabled ( true );
	session = new SessionManagement ( getApplicationContext () );
	linearCorporate = ( LinearLayout ) findViewById ( R.id.linearCorporate );
	linearResidential = ( LinearLayout ) findViewById ( R.id.linearResidential );
	linearDFSG = ( LinearLayout ) findViewById ( R.id.linearDFSG );
	linearSubscribe = ( LinearLayout ) findViewById ( R.id.linearSubscription );
	editTextEmail = ( EditText ) findViewById ( R.id.editTextEmail );
	editTextAccountType = ( EditText ) findViewById ( R.id.editTextAccountType );
	editTextFirst = ( EditText ) findViewById ( R.id.editTextFirst );
	editTextLast = ( EditText ) findViewById ( R.id.editTextLast );
	editTextNRIC = ( EditText ) findViewById ( R.id.editTextNRIC );
	editTextDOB = ( EditText ) findViewById ( R.id.editTextDOB );
	editTextDFSG = ( EditText ) findViewById ( R.id.editTextDFSG );
	editTextCompany = ( EditText ) findViewById ( R.id.editTextCompany );
	editTextRegNo = ( EditText ) findViewById ( R.id.editTextRegNo );
	editTextDFSGVCODE = ( EditText ) findViewById ( R.id.editTextDFSGVCODE );
	editTextNationality = ( EditText ) findViewById ( R.id.editTextNationality );
	spinnerTitle = ( Spinner ) findViewById ( R.id.spinnerTitle );
	spinnerRace = ( Spinner ) findViewById ( R.id.spinnerRace );
	checkBoxHalal = ( CheckBox ) findViewById ( R.id.checkBoxHalal );
	checkBoxSubscribe = ( CheckBox ) findViewById ( R.id.checkBoxSubscribe );
	textViewErrorFirst = ( TextView ) findViewById ( R.id.textViewErrorFirst );
	textViewErrorLast = ( TextView ) findViewById ( R.id.textViewErrorLast );
	textViewErrorNRIC = ( TextView ) findViewById ( R.id.textViewErrorNRIC );
	textViewErrorDOB = ( TextView ) findViewById ( R.id.textViewErrorDOB );
	textViewErrorDFSG = ( TextView ) findViewById ( R.id.textViewErrorDFSG );
	textViewErrorCompany = ( TextView ) findViewById ( R.id.textViewErrorCompany );
	textViewErrorRegNo = ( TextView ) findViewById ( R.id.textViewErrorRegNo );
	textViewErrorDFSGVCODE = ( TextView ) findViewById ( R.id.textViewErrorDFSGVCODE );
	buttonUpdate = ( Button ) findViewById ( R.id.buttonUpdate );
	resetError ();
	buttonUpdate.setOnClickListener ( this );

	titleAdapter = new MyArrayAdapter ( getApplicationContext(), title);
		raceAdapter=new MyArrayAdapter(getApplicationContext(), race);
		spinnerRace.setAdapter(raceAdapter);
		spinnerTitle.setAdapter ( titleAdapter );
		editTextDOB.setOnClickListener(this);
		asyncGetProfile=new AsyncGetProfile(this);
		asyncGetProfile.delegate=this;
		asyncGetProfile.execute ();
	//	((MyApp)getApplication()).getTracker(MyApp.TrackerName.APP_TRACKER);
	}
	@Override
	protected void onStart() {
	//	GoogleAnalytics.getInstance(getApplicationContext()).reportActivityStart(this);
		super.onStart();
	}
	@Override
	protected void onStop() {
		//GoogleAnalytics.getInstance(getApplicationContext()).reportActivityStop(this);
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.update_profile, menu);
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
		}		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		if(v==editTextDOB){
			DialogFragment datePicker = MyDatePickerDialog.newInstance(R.string.ok_button,R.string.cancel_button);
			Bundle args=new Bundle ();
			if(!editTextDOB.equals("")){
				args.putString("year",""+ DateHelper.getYear(editTextDOB.getText().toString(), "dd MMMM yyyy"));
				args.putString("month",""+ DateHelper.getMonth(editTextDOB.getText().toString(), "dd MMMM yyyy"));
				args.putString("day",""+ DateHelper.getDay(editTextDOB.getText().toString(), "dd MMMM yyyy"));
			}
			args.putLong("maxValue", Calendar.getInstance ().getTimeInMillis());
			datePicker.setArguments(args);
			datePicker.show(getFragmentManager(), "datePicker");
		}else if(v==buttonUpdate){
			JSONObject customer=new JSONObject ();
			try {
				customer.put("email", editTextEmail.getText().toString());
				customer.put("firstname",editTextFirst.getText().toString());
				customer.put("lastname",editTextLast.getText().toString());
				customer.put("nationality", "sg");
				customer.put("salutation",title[spinnerTitle.getSelectedItemPosition()]);
				customer.put("require_halal_product",(checkBoxHalal.isChecked()?"1":"0"));
				if(editTextAccountType.getText().toString().equals("Residential")){
					customer.put("account_type", "0");
					customer.put("nric", editTextNRIC.getText().toString());
					customer.put("birthday_day", DateHelper.getDay(editTextDOB.getText().toString(), "dd MMMM yyyy"));
					customer.put("birthday_month", DateHelper.getMonth(editTextDOB.getText().toString(), "dd MMMM yyyy"));
					customer.put("birthday_year", DateHelper.getYear(editTextDOB.getText().toString(), "dd MMMM yyyy"));
					customer.put("company","");
					customer.put("company_reg_no","");
					customer.put("dfsg_vcode","");
					customer.put("race_id", (spinnerRace.getSelectedItemPosition()==0?"":""+(spinnerRace.getSelectedItemPosition())));
					customer.put("is_dfsg",editTextDFSG.getText().toString().equals("")?"0":"1");
					customer.put("dfsg_id",editTextDFSG.getText().toString());
					customer.put("customer_is_subscribed", checkBoxSubscribe.isChecked()?"1":"0");
				}else{
					customer.put("account_type", "1");
					customer.put("nric", "");
					customer.put("birthday_day", "0");
					customer.put("birthday_month", "0");
					customer.put("birthday_year", "0");
					customer.put("company",editTextCompany.getText().toString());
					customer.put("company_reg_no", editTextRegNo.getText().toString());
					customer.put("dfsg_vcode",editTextDFSGVCODE.getText().toString());
					customer.put("race_id", (""));
					customer.put("is_dfsg","0");
					customer.put("dfsg_id","");
					customer.put("subscription", 0);
				}
				customer.put("username","");
				customer.put("password","");
				customer.put("repeat_password","");
				customer.put("middlename","");
				customer.put("religion_id","");
				customer.put("delivery_same_home","");
				customer.put("delivery_same_company","");
				customer.put("gender","");
				
				asyncUpdateProfile=new AsyncUpdateProfile(this);
				asyncUpdateProfile.delegate=this;
				asyncUpdateProfile.execute(customer.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onDialogPositiveClick(DialogFragment dialog, String tag) {
		if(tag.equals("success")){
			session.setFirstName(editTextFirst.getText().toString());
			session.setLastName(editTextLast.getText().toString());
			finish();
		} else if (tag.equals("serverdown")) {
			//// TODO: 10/5/2015
			/*Intent intent = new Intent (UpdateProfileActivity.this, DashboardActivity.class);
			intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("EXIT", true);
			startActivity(intent);*/
		}
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog, String tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processFinish(String output, String tag) {
		if(tag.equals("getprofile")){
			Log.i ( "profile", output );
			try {
				JSONObject jObject=new JSONObject (output);
				if(jObject.getInt("status")==1){
					JSONObject jData=jObject.getJSONObject("data");
					
					if(jData.getString("account_type").equals("0")){
						linearResidential.setVisibility( View.VISIBLE);
						linearSubscribe.setVisibility( View.VISIBLE);
						linearCorporate.setVisibility( View.GONE);
						editTextAccountType.setText("Residential");
						if(jData.getString("is_dfsg").equals("1")){
							linearDFSG.setVisibility( View.VISIBLE);
							editTextDFSG.setText(jData.getString("dfsg_id"));
						}else{
							linearDFSG.setVisibility( View.GONE);
							editTextDFSG.setText("");
						}
					}else{
						linearResidential.setVisibility( View.GONE);
						linearSubscribe.setVisibility( View.GONE);
						linearCorporate.setVisibility( View.VISIBLE);
						editTextAccountType.setText("Corporate");
					}
					editTextEmail.setText(jData.getString("email"));
					editTextFirst.setText(jData.getString("firstname"));
					editTextLast.setText(jData.getString("lastname"));
					editTextNRIC.setText(jData.getString("nric"));
					SimpleDateFormat sdf = new SimpleDateFormat ("dd MMMM yyyy");
					SimpleDateFormat sdfOriginal = new SimpleDateFormat ("yyyy-MM-dd");
					String jsonDate = jData.getString("birthday_year")+"-"+jData.getString("birthday_month")+"-"+jData.getString("birthday_day");
					Date dd = sdfOriginal.parse(jsonDate);
					Log.e ( "date", "jsonDate = " + jsonDate + " birthday month = " + jData.getString ( "birthday_month" ) + " date = " + dd );
					String birthdate = sdf.format(dd);
					editTextDOB.setText(birthdate);
					editTextNationality.setText("Singapore");
					editTextCompany.setText(jData.getString("company"));
					editTextRegNo.setText(jData.getString("company_reg_no"));
					editTextDFSGVCODE.setText(jData.getString("dfsg_vcode"));
					checkBoxHalal.setChecked(jData.getString("require_halal_product").equals("1"));
					checkBoxSubscribe.setChecked(jData.getString("customer_is_subscribed").equals("1"));
					spinnerRace.setSelection( Integer.parseInt ( jData.getString ( "race_id" ) ));
					if(!jData.getString("salutation").equals("")){
						int idx=0;
						for(int i=0;i<title.length;i++){
							if(title[i].equals(jData.getString("salutation"))){
								idx=i;
							}
						}
						spinnerTitle.setSelection(idx);
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
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if(tag.equals("updateprofile")){
			Log.i ( "update", output );
			try {
				JSONObject jObject=new JSONObject (output);
				if(jObject.getInt("status")==1){
					Toast toast= Toast.makeText( getApplicationContext (), "Your profile has been updated successfully", Toast.LENGTH_LONG );
					((TextView )((LinearLayout )toast.getView()).getChildAt(0)).setGravity( Gravity.CENTER_HORIZONTAL);
					toast.setGravity( Gravity.CENTER, 0, 0);
					toast.show();
					session.setFirstName(editTextFirst.getText().toString());
					session.setLastName(editTextLast.getText().toString());
					finish();
//					DialogFragment newFragment = MyAlertDialog.newInstance("Information",
//							"Your profile has been updated successfully",R.string.ok_button);
//				    newFragment.show(getFragmentManager(), "success");
				}else{
					resetError();
					JSONObject jData=jObject.getJSONObject("data");
					if(!jData.isNull("firstname")){
						textViewErrorFirst.setVisibility( View.VISIBLE);
						textViewErrorFirst.setText(jData.getString("firstname"));
					}
					if(!jData.isNull("lastname")){
						textViewErrorLast.setVisibility( View.VISIBLE);
						textViewErrorLast.setText(jData.getString("lastname"));
					}
					if(!jData.isNull("nric")){
						textViewErrorNRIC.setVisibility( View.VISIBLE);
						textViewErrorNRIC.setText(jData.getString("nric"));
					}
					if(!jData.isNull("birthday_year")){
						textViewErrorDOB.setVisibility( View.VISIBLE);
						textViewErrorDOB.setText(jData.getString("birthday_year"));
					}
					if(!jData.isNull("dfsg_id")){
						textViewErrorDFSG.setVisibility( View.VISIBLE);
						textViewErrorDFSG.setText(jData.getString("dfsg_id"));
					}
					if(!jData.isNull("company_reg_no")){
						textViewErrorRegNo.setText(jData.getString("company_reg_no"));
						textViewErrorRegNo.setVisibility( View.VISIBLE);
					}
					if(!jData.isNull("company")){
						textViewErrorCompany.setText(jData.getString("company"));
						textViewErrorCompany.setVisibility( View.VISIBLE);
					}
					if(!jData.isNull("dfsg_vcode")){
						textViewErrorDFSG.setText(jData.getString("dfsg_vcode"));
						textViewErrorDFSG.setVisibility( View.VISIBLE);
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
	}
	private void resetError(){
		textViewErrorFirst.setVisibility( View.GONE);
		textViewErrorLast.setVisibility( View.GONE);
		textViewErrorNRIC.setVisibility( View.GONE);
		textViewErrorDOB.setVisibility( View.GONE);
		textViewErrorDFSG.setVisibility( View.GONE);
		textViewErrorCompany.setVisibility( View.GONE);
		textViewErrorRegNo.setVisibility( View.GONE);
		textViewErrorDFSGVCODE.setVisibility( View.GONE);
	}
	@Override
	public void onDatePickerPositiveClick(DialogFragment dialog, int year,
			int month, int day, String tag) {
		editTextDOB.setText(DateHelper.dateFormater(year+"-"+(month)+"-"+day,
				"yyyy-M-dd", "dd MMMM yyyy"));
	}
	@Override
	public void onDatePickerNegativeClick(DialogFragment dialog, String tag) {
		// TODO Auto-generated method stub
		
	}
}
