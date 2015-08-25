/*
 * Copyright (C) 2014 Francesco Azzola
 *  Surviving with Android (http://www.survivingwithandroid.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.vi8e.um.wunderlist.Model;


public class ContactInfo {
    protected String name;
    protected String surname;
    protected String email;


    protected static final String NAME_PREFIX = "Name_";
    protected static final String SURNAME_PREFIX = "Surname_";
    protected static final String EMAIL_PREFIX = "email_";

public static
String getNamePrefix () {
	return NAME_PREFIX;
}

public static
String getSurnamePrefix () {
	return SURNAME_PREFIX;
}

public static
String getEmailPrefix () {
	return EMAIL_PREFIX;
}

public
String getName () {

	return name;
}

public
void setName ( String name ) {
	this.name = name;
}

public
String getSurname () {
	return surname;
}

public
void setSurname ( String surname ) {
	this.surname = surname;
}

public
String getEmail () {
	return email;
}

public
void setEmail ( String email ) {
	this.email = email;
}
}

