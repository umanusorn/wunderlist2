/*
 * Copyright 2015, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vi8e.um.wunderlist;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.vi8e.um.wunderlist.Activity.LandingActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;


@RunWith( AndroidJUnit4.class )
@LargeTest
public
class ChangeTextBehaviorTest {

public static final String STRING_TO_BE_TYPED = "Espresso";

@Rule
public ActivityTestRule<LandingActivity> mActivityRule = new ActivityTestRule<> (
		LandingActivity.class );

@Test
public
void newList () {
	String testText = "testText";
	onView ( withId ( R.id.action_a ) )
			.perform ( click (), closeSoftKeyboard () );
	//onView ( withHint ( R.string.add_list ) ).perform ( typeTextIntoFocusedView ( testText ) );
	//onView ( withText ("ADD" ) ).perform ( click () );

}

@Test
public
void enterTaskDetail () {
	String testText = "testText";
	//onData ( allOf ( is ( instanceOf ( Map.class ) ), hasEntry ( equalTo ( "STR" ), is ( "item: 50" ) ) ).perform ( click () ));
	onRow ( "Category 4" ).onChildView ( withId ( R.id.row_list_root_view ) ).perform ( click () );

}


@Test
public
void changeText_newActivity () {
	// Type text and then press the button.
/*	onView ( withId ( R.id.editTextUserInput ) ).perform ( typeText ( STRING_TO_BE_TYPED ),
	                                                       closeSoftKeyboard () );
	onView ( withId ( R.id.activityChangeTextB  tn ) ).perform ( click () );

	// This view is in a different Activity, no need to tell Espresso.
	onView ( withId ( R.id.show_text_view ) ).check ( matches ( withText ( STRING_TO_BE_TYPED ) ) );*/
}

/**
 * Uses {@link Espresso#onData(org.hamcrest.Matcher)} to get a reference to a specific row.
 * <p>
 * Note: A custom matcher can be used to match the content and have more readable code.
 * See the Custom Matcher Sample.
 * </p>
 *
 * @param str the content of the field
 * @return a {@link DataInteraction} referencing the row
 */
private static
DataInteraction onRow ( String str ) {

	return onData ( hasEntry ( equalTo ( "Category" ), is ( str ) ) );
}
}