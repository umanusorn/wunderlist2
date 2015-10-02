package com.vi8e.um.wunderlist.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class CartManagement {
private SharedPreferences pref;
private Editor            editor;
private Context           context;
private              int    PRIVATE_MODE     = 0;
private static final String PREF_NAME        = "cartheader";
public static final  String CART_ID          = "cart_id";
public static final  String CART_QTY         = "cart_qty";
public static final  String CART_SUBTOTAL    = "cart_subtotal";
public static final  String CART_GRANDTOTAL  = "cart_grandtotal";
public static final  String CART_SAVINGS     = "cart_savings";
public static final  String CART_CHECKOUT_ID = "checkout_id";
public static final  String CART_QTY_TOTAL   = "qty_total";

public
CartManagement ( Context context ) {
	this.context = context;
	pref = context.getSharedPreferences ( PREF_NAME, PRIVATE_MODE );
	editor = pref.edit ();
}

public
void createCart ( String cartId ) {
	editor.putString(CART_ID,cartId);
		editor.commit();
	}
//	public void setCartId(String id){
//		editor.putString(CART_ID, id);
//		editor.commit();
//	}
	public
	String getCartId(){
		return pref.getString(CART_ID, "");
	}
	public void setTotalQty(int qty){
		editor.putInt(CART_QTY_TOTAL, qty);
		editor.commit();
	}
	public int getTotalQty(){
		return pref.getInt(CART_QTY_TOTAL, 0);
	}
	public void setCheckoutId(String id){
		editor.putString(CART_CHECKOUT_ID, id);
		editor.commit();
	}
	public
	String getCheckoutId(){
		return pref.getString(CART_CHECKOUT_ID, "");
	}
	public void setCartQty(int qty){
		editor.putInt(CART_QTY, qty);
		editor.commit();
	}
	public int getCartQty(){
		return pref.getInt(CART_QTY, 0);
	}
	public void setCartSubtotal(String subtotal){
		editor.putString(CART_SUBTOTAL, subtotal);
		editor.commit();
	}
	public
	String getCartSubtotal(){
		return pref.getString(CART_SUBTOTAL, "");
	}
	public void setCartGrandTotal(String grandTotal){
		editor.putString(CART_GRANDTOTAL, grandTotal);
		editor.commit();
	}
	public
	String getCartGrandTotal(){
		return pref.getString(CART_GRANDTOTAL, "");
	}
	public void setCartSavings(String savings){
		editor.putString(CART_SAVINGS, savings);
		editor.commit();
	}
	public
	String getCartSavings(){
		return pref.getString(CART_SAVINGS, "");
	}
	public void logOut(){
		editor.clear();
		editor.commit();
	}
}
