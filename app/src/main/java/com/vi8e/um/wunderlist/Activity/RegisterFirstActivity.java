package com.vi8e.um.wunderlist.Activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.adapters.MyArrayAdapter;
import com.vi8e.um.wunderlist.asyncs.AsyncValidate1;
import com.vi8e.um.wunderlist.dialogs.MyAlertDialog;
import com.vi8e.um.wunderlist.dialogs.MyAlertDialog.MyAlertDialogListener;
import com.vi8e.um.wunderlist.dialogs.MyDatePickerDialog;
import com.vi8e.um.wunderlist.dialogs.MyDatePickerDialog.DatePickerDialogListener;
import com.vi8e.um.wunderlist.utils.AsyncResponse;
import com.vi8e.um.wunderlist.utils.DateHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

//import com.google.android.gms.analytics.GoogleAnalytics;
//import com.vi8e.um.wunderlist.application.MyApp;


public class RegisterFirstActivity extends Activity implements OnClickListener,AsyncResponse,DatePickerDialogListener, MyAlertDialogListener{
private RadioGroup radioGroupAcountType/*,radioGroupDFSG*/;
private EditText   editTextEmail, editTextPassword, editTextConfirm, editTextFirstName, editTextLastName,
		editTextDFSG, editTextNRIC, editTextDOB, editTextPAssionCard;
private TextView textViewErrorEmail, textViewErrorPassword, textViewErrorConfirm, textViewErrorFirstName, textViewErrorLastName,
		textViewErrorDFSG, textViewErrorNRIC, textViewErrorDOB, textViewErrorPassionCard;
private Spinner spinnerTitle, spinnerRace;
private RadioButton radioButtonResidential, radioButtonCorporate/*,radioButtonDFSGYes,radioButtonDFSGNo*/;
private CheckBox checkBoxHalal, checkBoxDFSG, checkBoxPAssionCard;
private TextView textViewDFSG/*,textViewError*/;
private Button   buttonNext;
private String[] title = {"- Title -" , "Mr" , "Ms" , "Mrs" , "Mdm"};
private String[] race  = {"- Race -" , "Chinese" , "Malay" , "Indian" , "Others"};
private MyArrayAdapter titleAdapter;
private MyArrayAdapter raceAdapter;
private AsyncValidate1 asyncRegister;
private LinearLayout   linearResidential, linearDFSG, llPAssionCard;
private String passionCardID;

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_register_first );
//	getActionBar ().setDisplayHomeAsUpEnabled ( true );
//	getActionBar ().setHomeButtonEnabled ( true );
	passionCardID = "";
	radioGroupAcountType = ( RadioGroup ) findViewById ( R.id.radioGroupAccountType );
//		radioGroupDFSG=(RadioGroup)findViewById(R.id.radioGroupDFSG);
	editTextEmail = ( EditText ) findViewById ( R.id.editTextEmail );
	editTextPassword = ( EditText ) findViewById ( R.id.editTextPassword );
	editTextConfirm = ( EditText ) findViewById ( R.id.editTextRepeatPassword );
	editTextFirstName = ( EditText ) findViewById ( R.id.editTextFirstName );
	editTextLastName = ( EditText ) findViewById ( R.id.editTextLastName );
	editTextDFSG = ( EditText ) findViewById ( R.id.editTextDFSG );
	editTextNRIC = ( EditText ) findViewById ( R.id.editTextNRIC );
	editTextDOB = ( EditText ) findViewById ( R.id.editTextDOB );
	editTextPAssionCard = ( EditText ) findViewById ( R.id.editTextPAssionCard );
	linearResidential = ( LinearLayout ) findViewById ( R.id.linearResidential );
	linearDFSG = ( LinearLayout ) findViewById ( R.id.linearDFSG );
	llPAssionCard = ( LinearLayout ) findViewById ( R.id.llPAssionCard );
	textViewErrorEmail = ( TextView ) findViewById ( R.id.textViewErrorEmail );
	textViewErrorPassword = ( TextView ) findViewById ( R.id.textViewErrorPassword );
	textViewErrorConfirm = ( TextView ) findViewById ( R.id.textViewErrorRepeatPassword );
	textViewErrorFirstName = ( TextView ) findViewById ( R.id.textViewErrorFirstName );
	textViewErrorLastName = ( TextView ) findViewById ( R.id.textViewErrorLastName );
	textViewErrorDFSG = ( TextView ) findViewById ( R.id.textViewErrorDFSG );
	textViewErrorNRIC = ( TextView ) findViewById ( R.id.textViewErrorNRIC );
	textViewErrorDOB = ( TextView ) findViewById ( R.id.textViewErrorDOB );
	textViewErrorPassionCard = ( TextView ) findViewById ( R.id.textViewErrorPassionCard );

	spinnerTitle = ( Spinner ) findViewById ( R.id.spinnerTitle );
	spinnerRace = ( Spinner ) findViewById ( R.id.spinnerRace );
	textViewDFSG = ( TextView ) findViewById ( R.id.textViewDFSG );
	radioButtonResidential = ( RadioButton ) findViewById ( R.id.radioResidental );
	radioButtonCorporate = ( RadioButton ) findViewById ( R.id.radioCorporate );
//		radioButtonDFSGYes=(RadioButton)findViewById(R.id.radioYes);
//		radioButtonDFSGNo=(RadioButton)findViewById(R.id.radioNo);
	checkBoxHalal = ( CheckBox ) findViewById ( R.id.checkBoxHalal );
	checkBoxDFSG = ( CheckBox ) findViewById ( R.id.checkBoxDFSG );
	checkBoxPAssionCard = ( CheckBox ) findViewById ( R.id.checkBoxPAssionCard);
		buttonNext=(Button )findViewById(R.id.buttonNext);
		titleAdapter=new MyArrayAdapter(getApplicationContext(), title);
		raceAdapter=new MyArrayAdapter(getApplicationContext(), race);
		spinnerRace.setAdapter(raceAdapter);
		spinnerTitle.setAdapter ( titleAdapter );
		editTextDOB.setOnClickListener ( this );
		radioButtonCorporate.setOnCheckedChangeListener(new OnCheckedChangeListener () {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					linearResidential.setVisibility( View.GONE);
//					textViewDFSG.setVisibility(View.GONE);
//					radioGroupDFSG.setVisibility(View.GONE);
//					editTextDFSG.setVisibility(View.GONE);
//					editTextNRIC.setVisibility(View.GONE);
//					editTextDOB.setVisibility(View.GONE);
//					spinnerRace.setVisibility(View.GONE);
					getActionBar().setTitle ( "Contact Person" );
				}
			}
		});
		radioButtonResidential.setOnCheckedChangeListener(new OnCheckedChangeListener () {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					linearResidential.setVisibility( View.VISIBLE);
//					textViewDFSG.setVisibility(View.VISIBLE);
//					radioGroupDFSG.setVisibility(View.VISIBLE);
//					editTextNRIC.setVisibility(View.VISIBLE);
//					editTextDOB.setVisibility(View.VISIBLE);
//					spinnerRace.setVisibility(View.VISIBLE);
					getActionBar().setTitle("Personal Details");
//					radioButtonDFSGNo.setChecked(true);
					checkBoxDFSG.setChecked(false);
				}
			}
		});
		checkBoxDFSG.setOnCheckedChangeListener(new OnCheckedChangeListener () {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked)
					linearDFSG.setVisibility( View.VISIBLE);
				else
					linearDFSG.setVisibility( View.GONE);
			}
		});
		checkBoxPAssionCard.setOnCheckedChangeListener ( new OnCheckedChangeListener () {
			@Override
			public
			void onCheckedChanged ( CompoundButton buttonView, boolean isChecked ) {
				if ( isChecked ) {
					llPAssionCard.setVisibility ( View.VISIBLE );
				}
				else llPAssionCard.setVisibility ( View.GONE );
			}
		} );
		buttonNext.setOnClickListener ( this );
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
		getMenuInflater().inflate(R.menu.register_first, menu);
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
//			if(isValid()){
//				textViewError.setVisibility(View.GONE);
				asyncRegister=new AsyncValidate1(this);
				asyncRegister.delegate=this;
				if(radioButtonResidential.isChecked()){
					if(!checkBoxDFSG.isChecked()){
						asyncRegister.execute(
								"0",
								editTextEmail.getText().toString(),
								editTextPassword.getText().toString(),
								editTextConfirm.getText().toString(),
								editTextFirstName.getText().toString(),
								editTextLastName.getText().toString(),
								(checkBoxHalal.isChecked()?"1":"0"),
								title[spinnerTitle.getSelectedItemPosition()],
								spinnerRace.getSelectedItemPosition()+"",
								(checkBoxPAssionCard.isChecked() ? "yes" : "no"),
								editTextPAssionCard.getText().toString(),
								editTextNRIC.getText().toString(),
								editTextDOB.getText().toString());
					}else{
						asyncRegister.execute(
								"0",
								editTextEmail.getText().toString(),
								editTextPassword.getText().toString(),
								editTextConfirm.getText().toString(),
								editTextFirstName.getText().toString(),
								editTextLastName.getText().toString(),
								(checkBoxHalal.isChecked()?"1":"0"),
								title[spinnerTitle.getSelectedItemPosition()],
								(spinnerRace.getSelectedItemPosition()-1)+"",
								(checkBoxPAssionCard.isChecked() ? "yes" : "no"),
								editTextPAssionCard.getText().toString(),
								editTextNRIC.getText().toString(),
								editTextDOB.getText().toString(),
								editTextDFSG.getText().toString());
					}
				}else{
					asyncRegister.execute(
							"1",
							editTextEmail.getText().toString(),
							editTextPassword.getText().toString(),
							editTextConfirm.getText().toString(),
							editTextFirstName.getText().toString(),
							editTextLastName.getText().toString(),
							(checkBoxHalal.isChecked()?"1":"0"),
							title[spinnerTitle.getSelectedItemPosition()],
							spinnerRace.getSelectedItemPosition()+"",
							(checkBoxPAssionCard.isChecked() ? "yes" : "no"),
							editTextPAssionCard.getText().toString());
				}
//			}else{
////				textViewError.setVisibility(View.VISIBLE);
//			}
		}else if(v==editTextDOB){
			DialogFragment datePicker = MyDatePickerDialog.newInstance(R.string.ok_button,R.string.cancel_button);
			Bundle args=new Bundle ();
			if(!editTextDOB.getText().toString().equals("")){
				args.putString("year",""+ DateHelper.getYear(editTextDOB.getText().toString(), "dd MMMM yyyy"));
				args.putString("month",""+ DateHelper.getMonth(editTextDOB.getText().toString(), "dd MMMM yyyy"));
				args.putString("day",""+ DateHelper.getDay(editTextDOB.getText().toString(), "dd MMMM yyyy"));
			}
			args.putLong("maxValue", Calendar.getInstance ().getTimeInMillis());
			datePicker.setArguments(args);
			datePicker.show(getFragmentManager(), "datePicker");
		}
	}
/*	private boolean isValid(){
		if(editTextEmail.getText().toString().equals("")){
			textViewError.setText("Please enter your email address");
			return false;
		}
		if(editTextPassword.getText().toString().equals("")){
			textViewError.setText("Please enter your password");
			return false;
		}
		if(editTextConfirm.getText().toString().equals("")){
			textViewError.setText("Please confirm your password");
			return false;
		}
		if(editTextFirstName.getText().toString().equals("")){
			textViewError.setText("Please enter your first name");
			return false;
		}
		if(editTextLastName.getText().toString().equals("")){
			textViewError.setText("Please enter your last name");
			return false;
		}
		if(radioButtonResidential.isChecked()){
			if(editTextNRIC.getText().toString().equals("")){
				textViewError.setText("Please enter your NRIC");
				return false;
			}
			if(editTextDOB.getText().toString().equals("")){
				textViewError.setText("Please enter your date of birth");
				return false;
			}
			if(radioButtonDFSGYes.isChecked()){
				if(editTextDFSG.getText().toString().equals("")){
					textViewError.setText("Please enter your DFSG ID number");
					return false;
				}
			}
		}
		if(!editTextEmail.getText().toString().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-\\+]+)*@"
							+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
			textViewError.setText("Please enter a valid email address");
			return false;
		}
		if(!editTextPassword.getText().toString().equals(editTextConfirm.getText().toString())){
			textViewError.setText("Your password do not match");
			return false;
		}
		return true;
	}*/

	@Override
	public void processFinish(String output, String tag) {
		if(tag.equals("register")){
			Log.i ( "register", output );
			String[]outputs=output.split("\\|");
			if(outputs.length>1){
				try {
					JSONObject jObject=new JSONObject (outputs[0]);
					resetError();
					if(jObject.getInt("status")==0){
						
						JSONObject jData=jObject.getJSONObject("data");
						if(!jData.isNull("email")){
							String email=jData.getString("email");
							textViewErrorEmail.setText(email.split(";")[0]);
							textViewErrorEmail.setVisibility( View.VISIBLE);
						}
						if(!jData.isNull("password")){
							textViewErrorPassword.setText(jData.getString("password").split(";")[0]);
							textViewErrorPassword.setVisibility( View.VISIBLE);
						}
						if(!jData.isNull("repeat_password")){
							String confirm=jData.getString("repeat_password");
							textViewErrorConfirm.setText(confirm.split(";")[0]);
							textViewErrorConfirm.setVisibility( View.VISIBLE);
						}
						if(!jData.isNull("firstname")){
							textViewErrorFirstName.setText(jData.getString("firstname").split(";")[0]);
							textViewErrorFirstName.setVisibility( View.VISIBLE);
						}
						if(!jData.isNull("lastname")){
							textViewErrorLastName.setText(jData.getString("lastname").split(";")[0]);
							textViewErrorLastName.setVisibility( View.VISIBLE);
						}
						if(!jData.isNull("nric")){
							textViewErrorNRIC.setText(jData.getString("nric"));
							textViewErrorNRIC.setVisibility( View.VISIBLE);
						}
						if(!jData.isNull("birthday_year")){
							if(jData.getString("birthday_year").split(";")[0].equals("Date of Birth [Year] is required.")){
								textViewErrorDOB.setText("Date of Birth is required.");
							}else{
								textViewErrorDOB.setText(jData.getString("birthday_year").split(";")[0]);
							}
							textViewErrorDOB.setVisibility( View.VISIBLE);
						}
						if(!jData.isNull("dfsg_id")){
							textViewErrorDFSG.setText(jData.getString("dfsg_id").split(";")[0]);
							textViewErrorDFSG.setVisibility( View.VISIBLE);
						}
						if(!jData.isNull("loyalty_card_id")){
							String confirm=jData.getString("loyalty_card_id");
							textViewErrorPassionCard.setText(confirm.split(";")[0]);
							textViewErrorPassionCard.setVisibility( View.VISIBLE);
						}
					}else{
//						textViewError.setVisibility(View.GONE);
						passionCardID = editTextPAssionCard.getText().toString();
						Intent i=new Intent (getApplicationContext(),RegisterSecondActivity.class);
						i.putExtra("firstname", editTextFirstName.getText().toString());
						i.putExtra("lastname", editTextLastName.getText().toString());
						i.putExtra("isResidential", radioButtonResidential.isChecked());
						i.putExtra("jsonCustomer", outputs[1]);
						i.putExtra("passionCard", passionCardID);
						startActivity(i);
					}
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
	}
	private void resetError(){
		textViewErrorEmail.setVisibility( View.GONE);
		textViewErrorPassword.setVisibility( View.GONE);
		textViewErrorConfirm.setVisibility( View.GONE);
		textViewErrorFirstName.setVisibility( View.GONE);
		textViewErrorLastName.setVisibility( View.GONE);
		textViewErrorDFSG.setVisibility( View.GONE);
		textViewErrorNRIC.setVisibility( View.GONE);
		textViewErrorDOB.setVisibility( View.GONE);
		textViewErrorPassionCard.setVisibility( View.GONE);
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

	@Override
	public void onDialogPositiveClick(DialogFragment dialog, String tag) {
		if (tag.equals("serverdown")) {
			//// TODO: 10/5/2015  
			
			/*Intent intent = new Intent (RegisterFirstActivity.this, DashboardActivity.class);
			intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("EXIT", true);
			startActivity(intent);*/
		}
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog, String tag) {

	}
}
