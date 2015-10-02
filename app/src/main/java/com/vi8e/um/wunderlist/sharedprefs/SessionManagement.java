package com.vi8e.um.wunderlist.sharedprefs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.paypal.android.sdk.payments.LoginActivity;

import java.util.Calendar;


public class SessionManagement {
private SharedPreferences pref;
private Editor            editor;
private Context           context;
private              int    PRIVATE_MODE                    = 0;
private static final String PREF_NAME                       = "giantpref";
private static final String IS_LOGIN                        = "IsLoggedIn";
public static final  String SESSION_ID                      = "sess_id";
public static final  String EXPIRATION                      = "expired";
public static final  String CUSTOMER_ID                     = "customer_id";
public static final  String CUSTOMER_EMAIL                  = "customer_email";
public static final  String CUSTOMER_FIRSTNAME              = "customer_firstname";
public static final  String CUSTOMER_LASTNAME               = "customer_lastname";
public static final  String CUSTOMER_PHONE                  = "customer_phone";
public static final  String PASSION_CARD_ENABLED            = "passion_card_enabled";
public static final  String PASSION_CARD_REDEMPTION_ENABLED = "passion_card_redemption_enabled";
public static final  String PASSION_CARD_AWARD_ENABLED      = "passion_card_award_enabled";
public static final  String PASSION_CARD_NO                 = "passion_card_no";
public static final  String IS_PLUSSELL                     = "isplussell";

public static final String CUSTOMER_FORCE_PASS = "force_pass";
public static final String CART_ID             = "cart_id";

public
SessionManagement ( Context context ) {
	this.context = context;
	pref = context.getSharedPreferences ( PREF_NAME, PRIVATE_MODE );
	editor = pref.edit ();
}

public
void createLoginSession ( String session_id, String customer_id, String customer_email ) {
	//public void createLoginSession(String session_id,String customer_id,String customer_email){
		editor.putBoolean(IS_LOGIN, true);
		editor.putString(SESSION_ID,session_id);
		editor.putString(CUSTOMER_ID, customer_id);
		editor.putString(CUSTOMER_EMAIL, customer_email);
		editor.putLong(EXPIRATION, Calendar.getInstance ().getTimeInMillis());
		editor.putBoolean(IS_PLUSSELL, true);
		editor.commit();
	}

	public void createPAssionCardSession(int passionCardEnabled, int passionCardRedemptionEnabled, int passionCardAwardEnabled) {
		editor.putInt(PASSION_CARD_ENABLED, passionCardEnabled);
		editor.putInt(PASSION_CARD_REDEMPTION_ENABLED, passionCardRedemptionEnabled);
		editor.putInt(PASSION_CARD_AWARD_ENABLED, passionCardAwardEnabled);
		editor.commit();
	}

	public boolean isForcePass(){
		return pref.getBoolean(CUSTOMER_FORCE_PASS, false);
	}
	public void setForcePass(boolean isForce){
		editor.putBoolean(CUSTOMER_FORCE_PASS, isForce);
		editor.commit();
		
	}
	public long getExpiration(){
		return pref.getLong(EXPIRATION, 0);
	}
	
	
	public void setPlussell(boolean isPlusSell){
		editor.putBoolean(IS_PLUSSELL, isPlusSell);
		editor.commit();
	}
	public boolean isPlusSell(){
		return pref.getBoolean(IS_PLUSSELL, true);
	}
	
	public void setFirstName(String firstname){
		editor.putString(CUSTOMER_FIRSTNAME, firstname);
		editor.commit();
	}
	public
	String getFirstName(){
		return pref.getString(CUSTOMER_FIRSTNAME, "");
	}
	public void setPhone(String phone){
		editor.putString(CUSTOMER_PHONE, phone);
		editor.commit();
	}
	public
	String getPhone(){
		return pref.getString(CUSTOMER_PHONE, "");
	}
	public void setLastName(String lastname){
		editor.putString(CUSTOMER_LASTNAME, lastname);
		editor.commit();
	}
	public
	String getLastName(){
		return pref.getString(CUSTOMER_LASTNAME, "");
	}
	
	public void setCartId(String id){
		editor.putString(CART_ID, id);
		editor.commit();
	}

	public void setPAssionCardID(String id) {
		editor.putString(PASSION_CARD_NO, id);
		editor.commit();
	}

	public
	String getCartId(){
		return pref.getString(CART_ID, "");
	}
	public
	String getCustomerId(){
		return pref.getString(CUSTOMER_ID, "");
	}
	public
	String getSessionID(){
		return pref.getString(SESSION_ID,"");
	}
	public
	String getEmail(){
		return pref.getString(CUSTOMER_EMAIL,"");
	}
	public
	String getPAssionCardID() { return pref.getString(PASSION_CARD_NO, ""); }
	public void checkLogin(){
		if(!isLoggedIn()){
		   	Intent i=new Intent (context, LoginActivity.class);
			i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_SINGLE_TOP);
			i.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(i);			   
		}else{
//		   	Intent i=new Intent(context, DashboardActivity.class);
//		   	i.putExtra("flag", 0);
//			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
//			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			context.startActivity(i);			   
		}
	}
	/**
	 * Ketika logout, hapus semua data user dari sharedpreferences
	 */
	public void logOut(){
		editor.clear();
		editor.commit();
//		Intent i = new Intent(context, LoginActivity.class);
//		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//	    context.startActivity(i);
	}
	public boolean isLoggedIn(){
		return pref.getBoolean(IS_LOGIN, false);
	}
}
