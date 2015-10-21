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

import android.support.annotation.NonNull;
import android.support.test.espresso.Espresso;
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
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;


@RunWith( AndroidJUnit4.class )
@LargeTest
public
class ChangeTextBehaviorTest {

@Rule
public ActivityTestRule<LandingActivity> mActivityRule = new ActivityTestRule<> (
		LandingActivity.class );

public static final String STRING_TO_BE_TYPED = "Espresso";
public static final String TARGET             = "5-2";

public static final String ADD_A_COMMENT      = "Add a comment...";
public static final int    MAX_COMMENTS       = 10;
public static final String TEST_COMMENT_TEXT  = "testComment text";
public static final int    DEFAULT_SLEEP_TIME = 500;
public static final String TEST_NEW_LIST      = "testNewList";
public static final String TEST_SUB_TASK      = "test subTask";

@Test
public
void newList () {
	String testText = TEST_NEW_LIST;
	onView ( withId ( R.id.action_a )).perform ( click (), closeSoftKeyboard () );
	onView ( withId ( R.id.action_a ) ).perform ( click () );
	onView ( withHint ( R.string.add_list ) ).perform ( typeText ( testText ) );
	performMaterialDialogClickADD ();

}


@Test
public
void enterTaskDetail () {
	/**
	 * Remove comments and SubTasks since subTasks cannot be deleted just like WunderList
	 */
	onView ( withId ( R.id.menu_setting ) ).perform ( click () );
	onView ( withId ( R.id.removeSubTaskBtn ) ).perform ( click () );
	onView ( withId ( R.id.removeCommentBtn ) ).perform ( click () );
	pressBack ();

	/**
	 * Enter Category
	 */
	onView ( withText ( "Category 5" ) ).perform ( click () );
	onView ( withText ( containsString ( "5-2" ) ) ).perform ( click (), closeSoftKeyboard () );

	ViewInteraction editTextTitle = onView ( withId ( R.id.editTextTitle ) );
	editTextTitle.perform ( clearText () );
	editTextTitle.perform ( typeText ( "changed Task 5-2" ) );

	/**
	 * Add 10 Comments and swipe up&down to verify that the comment is really created then longClick on the comments to delete it
	 */

	/**
	 * Add 10 comments
	 */
	onView ( withText ( containsString ( ADD_A_COMMENT ) ) ).perform ( click () );
	for ( int i = 0 ; i < MAX_COMMENTS ; i++ ) {
		onView ( withId ( R.id.editTextComment ) ).perform ( clearText () );
		onView ( withId ( R.id.editTextComment ) ).perform ( typeText ( TEST_COMMENT_TEXT + i ) );
		onView ( withText ( "SEND" ) ).perform ( click () );
	}

	Espresso.closeSoftKeyboard ();


	for ( int i = 0 ; i < MAX_COMMENTS ; i++ ) {
		/**
		 * swipe act only a short distance in some cases it might not see the list below
		 * this loop gives more distance
		 * in the future I will put some condition to reduce the testing time
		 */
		for ( int k = 0 ; k < 2 ; k++ ) {
			getEachComment ( i ).perform ( swipeUp () );
		}
	}

	for ( int i = MAX_COMMENTS - 1 ; i >= 0 ; i-- ) {
		getEachComment ( i ).perform ( swipeDown () );
	}

	/**
	 * delete every comments
	 */
	for ( int i = 0 ; i < MAX_COMMENTS ; i++ ) {
		getEachComment ( i ).perform ( longClick () );
		/**
		 * if don't wait the test will randomly crashed
		 */
		sleep ();
		onView ( withId ( R.id.delete ) ).perform ( click () );
	}


	pressBack ();

	/**
	 * test create 10 subTasks
	 */

	for ( int i = 0 ; i < MAX_COMMENTS ; i++ ) {
		onView ( withId ( R.id.addSubTask ) ).perform ( click () );
		onView ( withHint ( R.string.add_sub_task ) ).perform ( typeText ( TEST_SUB_TASK + i ) );
		performMaterialDialogClickADD ();
	}

	for ( int i = 0 ; i < MAX_COMMENTS ; i++ ) {
		getSubTask ( i ).perform ( click () );
	}


}


public
void sleep () {
	sleep ( DEFAULT_SLEEP_TIME );
}

public
void sleep ( int miliSecond ) {
	try {
		Thread.sleep ( miliSecond );
	}
	catch ( InterruptedException e ) {
		e.printStackTrace ();
	}
}


/**
 * Just simplify the app component
 */
@NonNull public
ViewInteraction getSubTask ( int i ) {
	return onView (  allOf ( withId ( R.id.subTaskTitle), withText ( TEST_SUB_TASK+ i ) ));
}


/**
 * Just simplify the app component
 */
@NonNull public
ViewInteraction getEachComment ( int i ) {
	return onView ( allOf ( withId ( R.id.commentTitle ), withText ( TEST_COMMENT_TEXT + i ) ) );
}

/**
 * Clicks positive button in visible/active {@link com.afollestad.materialdialogs.MaterialDialog}
 */
public static
void performMaterialDialogClickADD () {
	onView ( withId ( R.id.buttonDefaultPositive ) ).inRoot ( isDialog () ).perform ( click () );
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
public static
ViewAction waitAtLeast ( final long millis ) {
	return new ViewAction () {
		@Override
		public
		Matcher<View> getConstraints () {
			return null;
		}

		@Override
		public
		String getDescription () {
			return "wait for at least " + millis + " millis.";
		}

		@Override
		public
		void perform ( final UiController uiController, final View view ) {
			uiController.loopMainThreadUntilIdle ();
			uiController.loopMainThreadForAtLeast ( millis );
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
ViewAction waitUntilIdle () {
	return new ViewAction () {
		@Override
		public
		Matcher<View> getConstraints () {

			return null;
		}

		@Override
		public
		String getDescription () {
			return "wait until UI thread is free";
		}

		@Override
		public
		void perform ( final UiController uiController, final View view ) {
			uiController.loopMainThreadUntilIdle ();
		}
	};
}


}