package com.vi8e.um.wunderlist.Activity;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.adapters.MyAccountAdapter;
import com.vi8e.um.wunderlist.asyncs.AsyncCheckPAssionCard;
import com.vi8e.um.wunderlist.asyncs.AsyncGetCartId;
import com.vi8e.um.wunderlist.asyncs.AsyncGetProfile;
import com.vi8e.um.wunderlist.asyncs.AsyncLogout;
import com.vi8e.um.wunderlist.asyncs.AsyncPassionCardConfig;
import com.vi8e.um.wunderlist.dialogs.MyAlertDialog;
import com.vi8e.um.wunderlist.dialogs.MyAlertDialog.MyAlertDialogListener;
import com.vi8e.um.wunderlist.models.NavDrawerItem;
import com.vi8e.um.wunderlist.sharedprefs.CartManagement;
import com.vi8e.um.wunderlist.sharedprefs.SessionManagement;
import com.vi8e.um.wunderlist.utils.AsyncResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//import com.google.android.gms.analytics.GoogleAnalytics;
//import com.vi8e.giant.OrderListActivity;
//import com.vi8e.um.wunderlist.R;
//import com.vi8e.giant.ShoppingListActivity;
//import com.vi8e.um.wunderlist.application.MyApp;


public class MyAccountActivity extends Activity implements AsyncResponse,MyAlertDialogListener{
private ListView                 listViewAccount;
private ArrayList<NavDrawerItem> items;
private MyAccountAdapter         adapter;
private AsyncLogout              logout;
private AsyncGetProfile          asyncGetProfile;
private AsyncGetCartId           asyncGetCartId;
private SessionManagement        session;
private CartManagement           cartHeader;
private String                   passionCardID, passionCardMenuText;

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_my_account );
//	getActionBar ().setDisplayHomeAsUpEnabled ( true );
//	getActionBar ().setHomeButtonEnabled ( true );
	session = new SessionManagement ( getApplicationContext () );
	cartHeader = new CartManagement ( getApplicationContext () );
	passionCardMenuText = "Link Up PAssion Card";

	//DialogFragment newFragment = MyAlertDialog.newInstance("Information", "Testing 1234",R.string.ok_button);
	//newFragment.show(getFragmentManager(), "serverdown");

		/*AsyncCheckPAssionCard asyncCheckPAssionCard = new AsyncCheckPAssionCard(this);
		asyncCheckPAssionCard.delegate = this;
		asyncCheckPAssionCard.execute("");*/
	passionCardID = "";

	listViewAccount = ( ListView ) findViewById ( R.id.listViewMenuAccount );
	items = new ArrayList<NavDrawerItem> ();
	setDefaultItem ();
	adapter = new MyAccountAdapter ( getApplicationContext (), items );
	listViewAccount.setAdapter ( adapter );
	listViewAccount.setOnItemClickListener ( new OnItemClickListener () {

		@Override
		public
		void onItemClick ( AdapterView<?> parent, View view,
		                   int position, long id ) {
			Intent i = null;
			if ( position == 0 ) {
				i = new Intent ( getApplicationContext (), UpdateProfileActivity.class );
				startActivity ( i );
			}//// TODO: 10/5/2015  
			/*else if ( position == 1 ) {
				i = new Intent ( getApplicationContext (), AddressListActivity.class );
				startActivity ( i );
			}
			else if ( position == 2 ) {
				i = new Intent ( getApplicationContext (), ChangePasswordActivity.class );
				i.putExtra ( "force", 0 );
				startActivity ( i );
			}
			else if ( position == 3 ) {
				i = new Intent ( getApplicationContext (), ShoppingListActivity.class );
				startActivity ( i );
			}
			else if ( position == 4 ) {
				i = new Intent ( getApplicationContext (), OrderListActivity.class );
				startActivity ( i );
			}*/
			if ( items.size () > 5 ) {
				if ( position == 5 ) {
					if ( items.get ( 5 ).getTitle ().equals ( "Link Up PAssion Card" ) ) {
						//// TODO: 10/5/2015
				/*		i = new Intent ( getApplicationContext (), PAssionCardActivity.class );
						startActivity ( i );*/
					}
					else {
						DialogFragment newFragment = MyAlertDialog.newInstance ( "Confirmation",
						                                                         "Are you sure you want to unlink PAssion Card " + session.getPAssionCardID () + "?",
						                                                         R.string.ok_button,
						                                                         R.string.cancel_button );
						newFragment.show ( getFragmentManager (), "unlink" );
					}
				}
				else if ( position == 6 ) {
					logout = new AsyncLogout ( MyAccountActivity.this );
					logout.delegate = MyAccountActivity.this;
					logout.execute ( session.getSessionID () );
				}
			}
			else {
				if ( position == 5 ) {
					logout = new AsyncLogout ( MyAccountActivity.this );
					logout.delegate = MyAccountActivity.this;
					logout.execute ( session.getSessionID () );
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
	private void setDefaultItem(){
		NavDrawerItem item=new NavDrawerItem();
		item.setTitle("Update Profile");
		items.add(item);
		item=new NavDrawerItem();
		item.setTitle("Manage Address");
		items.add(item);
		item=new NavDrawerItem();
		item.setTitle("Change Password");
		items.add(item);
		item=new NavDrawerItem();
		item.setTitle("Shopping List");
		items.add(item);
		item=new NavDrawerItem();
		item.setTitle("Order History");
		items.add(item);
		item=new NavDrawerItem();
		item.setTitle("Log Out");
		items.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_account, menu);
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
	protected void onResume() {
		AsyncCheckPAssionCard asyncCheckPAssionCard = new AsyncCheckPAssionCard(this);
		asyncCheckPAssionCard.delegate = this;
		asyncCheckPAssionCard.execute("");
		super.onResume();
	}

	@Override
	public void processFinish(String output, String tag) {
		if(tag.equals("logout")){
			Log.i ( "logout", output );
			try {
				JSONObject jObject=new JSONObject (output);
				if(jObject.getInt("status")==1){
					session.logOut();
					cartHeader.logOut();
					Toast toast= Toast.makeText( getApplicationContext (), jObject.getString ( "message" ), Toast.LENGTH_LONG );
					((TextView )((LinearLayout )toast.getView()).getChildAt(0)).setGravity( Gravity.CENTER_HORIZONTAL);
					toast.setGravity( Gravity.CENTER, 0, 0);
					toast.show();
					asyncGetCartId=new AsyncGetCartId(this);
					asyncGetCartId.delegate=this;
					asyncGetCartId.execute();
//					DialogFragment newFragment = MyAlertDialog.newInstance("Information",
//							jObject.getString("message"),R.string.ok_button);
//				    newFragment.show(getFragmentManager(), "logoutconfirmation");
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
		}else if(tag.equals("cartid")){
			Log.i ( "cartid", output );
			JSONObject jObject;
			cartHeader.logOut();
			try {
				jObject = new JSONObject (output);
				session.setCartId(jObject.getString("cart_id"));
				cartHeader.createCart(jObject.getString("cart_id"));
				setResult(RESULT_OK);
				finish();
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
		} else if (tag.equals("getprofile")) {
			Log.e ( "getprofile", output );
			try {
				JSONObject jObject = new JSONObject (output);
				if (jObject.getInt("status") == 1) {
					JSONObject jData = jObject.getJSONObject("data");
					passionCardID = jData.getString("loyalty_card_id");
					if (passionCardID.equals("")) {
						passionCardMenuText = "Link Up PAssion Card";
					} else passionCardMenuText = "Unlink PAssion Card";
					if (items.size() <= 6) {
						NavDrawerItem item = new NavDrawerItem();
						item.setTitle(passionCardMenuText);
						items.add(5, item);
					} else {
						items.get(5).setTitle(passionCardMenuText);
					}
					adapter.notifyDataSetChanged();
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
		} else if (tag.equals("unlinkpassioncard")) {
			try {
				JSONObject jObject = new JSONObject (output);
				int status = jObject.getInt("status");
				if (status == 1) {
					passionCardMenuText = "Link Up PAssion Card";
				} else passionCardMenuText = "Unlink PAssion Card";
				items.get(5).setTitle(passionCardMenuText);
				adapter.notifyDataSetChanged();
				Toast toast = Toast.makeText( getApplicationContext (), "PAssion Card has been unlinked from your account.", Toast.LENGTH_LONG );
				((TextView )((LinearLayout )toast.getView()).getChildAt(0)).setGravity( Gravity.CENTER_HORIZONTAL);
				toast.setGravity( Gravity.CENTER, 0, 0);
				toast.show();
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
		} else if (tag.equals("checkpassioncard")) {
			Log.e ( "check", "check = " + output );
			try {
				JSONObject jObject = new JSONObject (output);
				JSONObject pcStatus = jObject.getJSONObject("passion_card_status");
				if (pcStatus.getInt("is_passion_card_enabled") == 1) {
					Log.e ( "masuk sini", "masuk sini" );
					asyncGetProfile = new AsyncGetProfile(this);
					asyncGetProfile.delegate = this;
					asyncGetProfile.execute("");
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
			}
		}
	}
	@Override
	public void onDialogPositiveClick(DialogFragment dialog, String tag) {
		if(tag.equals("logoutconfirmation")){
//			asyncGetCartId=new AsyncGetCartId(this);
//			asyncGetCartId.delegate=this;
//			asyncGetCartId.execute();
		} else if (tag.equals("unlink")) {
			AsyncPassionCardConfig pConfig = new AsyncPassionCardConfig(MyAccountActivity.this);
			pConfig.delegate = MyAccountActivity.this;
			pConfig.execute("");
		} else if (tag.equals("serverdown")) {
			
			//// TODO: 10/5/2015  
			/*Intent intent = new Intent (MyAccountActivity.this, DashboardActivity.class);
			intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra("EXIT", true);
			startActivity(intent);*/
		}
	}
	@Override
	public void onDialogNegativeClick(DialogFragment dialog, String tag) {

	}
}
