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

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import com.vi8e.um.wunderlist.Activity.LandingActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;


@RunWith( AndroidJUnit4.class )
@LargeTest
public
class ChangeTextBehaviorTest {

public static final String                            STRING_TO_BE_TYPED = "Espresso";
public static final String                            TARGET             = "5-2";
@Rule
public              ActivityTestRule<LandingActivity> mActivityRule      = new ActivityTestRule<> (
		LandingActivity.class );
public static final String                            ADD_A_COMMENT      = "Add a comment...";

@Test
public
void newList () {
	String testText = "testText";
	onView ( withId ( R.id.action_a ) )
			.perform ( click (), closeSoftKeyboard () );
	onView ( withId ( R.id.action_a ) )
			.perform ( click ());
	//onView ( withHint ( R.string.add_list ) ).perform ( typeText ( testText ) );
	//onView ( withText ("ADD" ) ).perform ( click () );
	performMaterialDialogOkClick ();
}

/**
 * Clicks positive button in visible/active {@link com.afollestad.materialdialogs.MaterialDialog}
 */
public static
void performMaterialDialogOkClick () {
	onView ( withId ( R.id.buttonDefaultPositive ) ).inRoot ( isDialog () ).perform ( click () );
}

@Test
public
void enterTaskDetail () {
	//onData ( allOf ( is ( instanceOf ( Map.class ) ), hasEntry ( equalTo ( "STR" ), is ( "item: 50" ) ) ).perform ( click () ));
//	onRow ( "Category 4" ).onChildView ( withId ( R.id.row_list_root_view ) ).perform ( click () );
	onView ( withText ( "Category 5" ) )
			.perform ( click (), closeSoftKeyboard () );
	onView ( withText ( containsString ( "5-2" ) ) ).perform ( click (), closeSoftKeyboard () );

	ViewInteraction editTextTitle = onView ( withId ( R.id.editTextTitle ) );
	editTextTitle.perform ( clearText () );
	editTextTitle.perform ( typeText ( "changed Task 5-2" ) );

	onView ( withText ( containsString ( ADD_A_COMMENT ) ) ).perform ( click () );
	for ( int i = 0 ; i < 10 ; i++ ) {
		onView ( withId ( R.id.editTextComment ) ).perform ( typeText ( String.valueOf ( i ) ) );
		onView ( withText ( "SEND" ) ).perform ( click () );
	}


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
 * Perform action of waiting for a specific time. Useful when you need
 * to wait for animations to end and Espresso fails at waiting.
 * <p/>
 * E.g.:
 * onView(isRoot()).perform(waitAtLeast(Sampling.SECONDS_15));
 *
 * @param millis
 * @return
 */
public static ViewAction waitAtLeast(final long millis) {
	return new ViewAction() {
		@Override
		public Matcher<View> getConstraints() {
			return null;
		}

		@Override
		public String getDescription() {
			return "wait for at least " + millis + " millis.";
		}

		@Override
		public void perform(final UiController uiController, final View view) {
			uiController.loopMainThreadUntilIdle();
			uiController.loopMainThreadForAtLeast(millis);
		}
	};
}
/**
 * Perform action of waiting until UI thread is free.
 * <p/>
 * E.g.:
 * onView(isRoot()).perform(waitUntilIdle());
 *
 * @return
 */
public static
ViewAction waitUntilIdle() {
	return new ViewAction() {
		@Override
		public
		Matcher<View> getConstraints() {
			return null;
		}

		@Override
		public String getDescription() {
			return "wait until UI thread is free";
		}

		@Override
		public void perform(final UiController uiController, final View view) {
			uiController.loopMainThreadUntilIdle();
		}
	};
}


}