package com.vi8e.um.wunderlist.Activity.Giants;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.asyncs.AsyncCartInfo;
import com.vi8e.um.wunderlist.asyncs.AsyncGetCartId;
import com.vi8e.um.wunderlist.asyncs.AsyncLogin;
import com.vi8e.um.wunderlist.dialogs.MyAlertDialog;
import com.vi8e.um.wunderlist.dialogs.MyAlertDialog.MyAlertDialogListener;
import com.vi8e.um.wunderlist.sharedprefs.CartManagement;
import com.vi8e.um.wunderlist.sharedprefs.SessionManagement;
import com.vi8e.um.wunderlist.utils.ActivityUi;
import com.vi8e.um.wunderlist.utils.AsyncResponse;
import com.vi8e.um.wunderlist.utils.ConnectionDetector;

import org.json.JSONException;
import org.json.JSONObject;

//import com.google.android.gms.analytics.GoogleAnalytics;


public class LoginActivity extends AppCompatActivity implements OnClickListener,AsyncResponse,MyAlertDialogListener{
private EditText editTextEmail, editTextPassword;
private Button buttonLogin, buttonRegister;
private TextView textViewForgot, textViewRegisterLater, textViewError;
private AsyncLogin         asyncLogin;
private AsyncGetCartId     asyncGetCartId;
private SessionManagement  session;
private CartManagement     cartHeader;
private ConnectionDetector internet;
private AsyncCartInfo      asyncCartInfo;
Toolbar toolBar;
String title="Log In";
@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_login );
	internet = new ConnectionDetector ( getApplicationContext () );
	ActivityUi.setToolBar ( this, toolBar, title );
	Intent i = getIntent ();
	if ( i.getBooleanExtra ( "loggedIn", false ) ) {
		//todo
		/*Intent intent = new Intent ( getApplicationContext (), MyAccountActivity.class );
		startActivity ( intent );
		finish ();*/
	}
	if ( i.getIntExtra ( "expired", 0 ) == 1 ) {
		//// TODO: 10/2/2015
		/*DialogFragment newFragment = MyAlertDialog.newInstance ( "Login Expired",
		                                                         "Your session has expired. Please login again", R.string.ok_button );
		newFragment.show ( getFragmentManager (), "loginexpired" );*/
	}
	editTextEmail = ( EditText ) findViewById ( R.id.editTextEmail );
	editTextPassword = ( EditText ) findViewById ( R.id.edittextPassword );
	buttonLogin = ( Button ) findViewById ( R.id.buttonLogin );
	buttonRegister = ( Button ) findViewById ( R.id.buttonRegister );
	textViewForgot = ( TextView ) findViewById ( R.id.textViewForgotPassword );
	textViewError = ( TextView ) findViewById ( R.id.textViewError );
	textViewError.setVisibility ( View.GONE );
	textViewRegisterLater = ( TextView ) findViewById ( R.id.textViewLater );
	buttonRegister.setOnClickListener ( this );
	buttonLogin.setOnClickListener ( this );
	textViewForgot.setOnClickListener ( this );
	textViewRegisterLater.setOnClickListener(this);
		session=new SessionManagement(getApplicationContext());
		cartHeader=new CartManagement(getApplicationContext());
		//((MyApp)getApplication()).getTracker(MyApp.TrackerName.APP_TRACKER);
	}
	@Override
	protected void onStart() {
		//// TODO: 10/2/2015
		//GoogleAnalytics.getInstance(getApplicationContext()).reportActivityStart(this);
		super.onStart();
	}
	@Override
	protected void onStop() {
		//// TODO: 10/2/2015  
		//GoogleAnalytics.getInstance(getApplicationContext()).reportActivityStop(this);
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.landing, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		if(v==buttonLogin){
			if(internet.isConnectingToInternet()){
				asyncLogin=new AsyncLogin(this);
				asyncLogin.delegate=this;
				asyncLogin.execute(editTextEmail.getText().toString(),editTextPassword.getText().toString());
			}else{
				
			}
		}
//// TODO: 10/2/2015  
		/*else if(v==buttonRegister){

			Intent i=new Intent (getApplicationContext(), RegisterFirstActivity.class);
			startActivity(i);
		}else if(v==textViewForgot){
			Intent i=new Intent (getApplicationContext(), ForgotPasswordActivity.class);
			startActivity ( i );
		}*/else if(v==textViewRegisterLater){
			finish ();
		}
	}

	@Override
	public void processFinish(String output, String tag) {
		if(tag.equals("login")){
			Log.i ( "login", output );
			textViewError.setVisibility( View.GONE);
			try {
				JSONObject jObject=new JSONObject (output);
				if(jObject.getInt("status")==1) {
					session.createLoginSession(jObject.getString("login_id"), 
							jObject.getString("customer_id"),editTextEmail.getText().toString());
					session.setForcePass(jObject.getString("force_password").equals("1"));
					session.setFirstName(jObject.getString("customer_firstname"));
					session.setLastName(jObject.getString("customer_lastname"));
					session.setPhone(jObject.getString("customer_phone"));

					JSONObject pcStatus = jObject.getJSONObject("passion_card_status");
					session.createPAssionCardSession(pcStatus.getInt("is_passion_card_enabled"),
							pcStatus.getInt("is_passion_card_redemption_enabled"),
							pcStatus.getInt("is_passion_card_award_enabled"));

					if (jObject.has("passion_card_id") && jObject.getString("passion_card_id") != null
							&& !jObject.getString("passion_card_id").equals("")) {
						session.setPAssionCardID(jObject.getString("passion_card_id"));
					}

					asyncGetCartId=new AsyncGetCartId(this);
					asyncGetCartId.delegate=this;
					asyncGetCartId.execute();
				}else{
					textViewError.setText(jObject.getString("message"));
					textViewError.setVisibility( View.VISIBLE);
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
		}else if(tag.equals("cartid")){
			Log.i ( "cartid", output );
			JSONObject jObject;
			cartHeader.logOut();
			try {
				jObject = new JSONObject (output);
				session.setCartId(jObject.getString("cart_id"));
				cartHeader.createCart(jObject.getString("cart_id"));
				if(session.isForcePass()){

					//// TODO: 10/2/2015  
					/*Intent i=new Intent (getApplicationContext(),ChangePasswordActivity.class);
					i.putExtra("force", 1);
					i.putExtra("firstTime", 1);
					startActivity(i);
					finish();
*/
				}else{
					asyncCartInfo=new AsyncCartInfo(this,true);
					asyncCartInfo.delegate=this;
					asyncCartInfo.execute();
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
		}else if(tag.equals("cartinfo")){
			JSONObject jObject;
			try {
				jObject = new JSONObject (output);
				JSONObject cart=jObject.getJSONObject("cart");
				Log.i ( "cart", cart.toString () );
				cartHeader.setCartQty(cart.getInt("cart_qty"));
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
			Toast toast= Toast.makeText( getApplicationContext (), "You have been logged in successfully", Toast.LENGTH_LONG );
			((TextView )((LinearLayout )toast.getView()).getChildAt(0)).setGravity( Gravity.CENTER_HORIZONTAL);
			toast.setGravity( Gravity.CENTER, 0, 0);
			toast.show();
			setResult(RESULT_OK);
			finish();
		}
	}

	@Override
	public void onDialogPositiveClick(DialogFragment dialog, String tag) {
		// TODO Auto-generated method stub
		if(tag.equals("loginsuccess")){
//			setResult(RESULT_OK);
//			finish();
		}else if(tag.equals("serverdown")){
//// TODO: 10/2/2015  
			/*Intent intent = new Intent (LoginActivity.this, DashboardActivity.class);
			intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("EXIT", true);
			startActivity(intent);*/
		}
		
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog, String tag) {
		// TODO Auto-generated method stub
		
	}
}
