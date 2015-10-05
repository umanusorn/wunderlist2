package com.vi8e.um.wunderlist.models;

public class PaymentMethodItem {
private String key, value;
private boolean hasAdditional;
private String  additionalId = "", additionalValue, adittionalHint;
private String additional2Id = "", additionalValue2 = "", additionalHint2;
private String additionalDateId = "", additionalDateValue = "";

public
PaymentMethodItem () {
}

public
boolean isHasAdditional () {
	return hasAdditional;
}

public
void setHasAdditional ( boolean hasAdditional ) {
	this.hasAdditional = hasAdditional;
}

public
String getAdditionalId () {
		return additionalId;
	}

	public void setAdditionalId(String additionalId) {
		this.additionalId = additionalId;
	}

	public
	String getAdditionalValue() {
		return additionalValue;
	}

	public void setAdditionalValue(String additionalValue) {
		this.additionalValue = additionalValue;
	}

	public
	String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public
	String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public
	String getAdittionalHint() {
		return adittionalHint;
	}

	public void setAdittionalHint(String adittionalHint) {
		this.adittionalHint = adittionalHint;
	}

	public
	String getAdditional2Id() {
		return additional2Id;
	}

	public void setAdditional2Id(String additional2Id) {
		this.additional2Id = additional2Id;
	}

	public
	String getAdditionalValue2() {
		return additionalValue2;
	}

	public void setAdditionalValue2(String additionalValue2) {
		this.additionalValue2 = additionalValue2;
	}

	public
	String getAdditionalHint2() {
		return additionalHint2;
	}

	public void setAdditionalHint2(String additionalHint2) {
		this.additionalHint2 = additionalHint2;
	}

	public
	String getAdditionalDateId() {
		return additionalDateId;
	}

	public void setAdditionalDateId(String additionalDateId) {
		this.additionalDateId = additionalDateId;
	}

	public
	String getAdditionalDateValue() {
		return additionalDateValue;
	}

	public void setAdditionalDateValue(String additionalDateValue) {
		this.additionalDateValue = additionalDateValue;
	}
}
