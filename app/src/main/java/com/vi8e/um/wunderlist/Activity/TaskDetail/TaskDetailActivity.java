package com.vi8e.um.wunderlist.Activity.TaskDetail;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.android.AuthActivity;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.vi8e.um.wunderlist.Activity.TaskActivity;
import com.vi8e.um.wunderlist.Model.ModelType;
import com.vi8e.um.wunderlist.Model.SubTaskModel;
import com.vi8e.um.wunderlist.Model.TaskModel;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.adapters.SubTaskAdapter;
import com.vi8e.um.wunderlist.dialogs.CustomDialog;
import com.vi8e.um.wunderlist.dialogs.ReminderDialog;
import com.vi8e.um.wunderlist.provider.subtask.SubtaskColumns;
import com.vi8e.um.wunderlist.provider.subtask.SubtaskSelection;
import com.vi8e.um.wunderlist.provider.task.TaskColumns;
import com.vi8e.um.wunderlist.sharedprefs.SessionManagement;
import com.vi8e.um.wunderlist.utils.IntentCaller;
import com.vi8e.um.wunderlist.utils.QueryHelper;
import com.vi8e.um.wunderlist.utils.RecycleUtil;
import com.vi8e.um.wunderlist.utils.UiMng;
import com.vi8e.um.wunderlist.utils.dropbox.UploadMultiPictures;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import nl.changer.polypicker.Config;
import nl.changer.polypicker.ImagePickerActivity;


public
class TaskDetailActivity extends AppCompatActivity {
private static final String TAG = TaskDetailActivity.class.getSimpleName ();
public static ListView       listViewSubTask;
public static AppCompatActivity       thisActivity;
public static Context        sContext;
public static SubTaskAdapter subTaskAdapter;
public static
android.support.v4.app.FragmentManager sFragmentManager;

Boolean isStar       = false;
Boolean showComplete = true;

EditText editTextTitle, editTextBottom;
public static TaskModel currentTask;
RelativeLayout noteLayout;
public static ImageView      star, checkBoxTask, uploadBtn;
TextView       noteEditText;
RelativeLayout addSubTask;
RelativeLayout calendarLayout;
RelativeLayout bottomRoot;
public static TextView reminderText;
private static final int INTENT_REQUEST_GET_IMAGES = 1130;

HashSet<Uri> mMedia = new HashSet<Uri> ();


///////////////////////////////////////////////////////////////////////////
//                      Your app-specific settings.                      //
///////////////////////////////////////////////////////////////////////////

// Replace this with your app key and secret assigned by Dropbox.
// Note that this is a really insecure way to do this, and you shouldn't
// ship code which contains your key & secret in such an obvious way.
// Obfuscation is good.
private static final String APP_KEY    = "u3o287hb9hajkmb";
private static final String APP_SECRET = "b8obs2bppkgrlsu";

///////////////////////////////////////////////////////////////////////////
//                      End app-specific settings.                       //
///////////////////////////////////////////////////////////////////////////

// You don't need to change these, leave them alone.
private static final String ACCOUNT_PREFS_NAME = "prefs";
private static final String ACCESS_KEY_NAME    = "ACCESS_KEY";
private static final String ACCESS_SECRET_NAME = "ACCESS_SECRET";
private final        String PHOTO_DIR          = "/Photos/";

private static final boolean USE_OAUTH1 = false;

DropboxAPI<AndroidAuthSession> mApi;

private boolean mLoggedIn;
public static final int    MAX_IMGS_SELECTION = 15;
public static final String CANCEL_UPLOAD      = "myMethod";
public static final String TRUE               = "true";
private static UploadMultiPictures mUploadMultiPictures;
public static final String FALSE = "false";
public static Bundle thisSavedInstanceState;

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView(R.layout.activity_task_detail);
	sContext = getApplicationContext ();
	thisActivity = this;
	currentTask = TaskActivity.currentTask;

	thisSavedInstanceState =savedInstanceState;
	RecycleUtil.setUpRecycleFragment(savedInstanceState, thisActivity, ModelType.SUB_TASK);

	sFragmentManager = getSupportFragmentManager ();
	// We create a new AuthSession so that we can use the Dropbox API.
	AndroidAuthSession session = buildSession ();
	mApi = new DropboxAPI<AndroidAuthSession> ( session );

	//mApi.getSession ().startOAuth2Authentication ( TaskDetailActivity.this );

	getWindow ().setSoftInputMode (
			WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
	                              );
	View view = this.getCurrentFocus ();
	if ( view != null ) {
		InputMethodManager imm = ( InputMethodManager ) getSystemService ( Context.INPUT_METHOD_SERVICE );
		imm.hideSoftInputFromWindow ( view.getWindowToken (), 0 );
	}

	setView ();
	setViewValues ();
	subTaskAdapter.setNotifyOnChange ( true );
	UiMng.setTaskListViewHeight(listViewSubTask);
}

private
void setView () {
	Log.d ( "", "setView" );
	listViewSubTask = ( ListView ) findViewById ( R.id.listViewTaskInComplete );
	checkBoxTask = ( ImageView ) findViewById ( R.id.chkBox );
	editTextTitle = ( EditText ) findViewById ( R.id.editTextTitle );
	star = ( ImageView ) findViewById ( R.id.star );
	noteEditText = ( TextView ) findViewById ( R.id.noteEdittext );
	noteLayout = ( RelativeLayout ) findViewById ( R.id.noteLayout );
	addSubTask = ( RelativeLayout ) findViewById ( R.id.addSubTask );
	calendarLayout = ( RelativeLayout ) findViewById ( R.id.calendatLayout );
	reminderText = ( TextView ) findViewById ( R.id.reminder_text_taskDetail );
	bottomRoot = ( RelativeLayout ) findViewById ( R.id.bottomBarRoot );
	editTextBottom = ( EditText ) findViewById ( R.id.editTextBottom );
	uploadBtn = ( ImageView ) findViewById ( R.id.upload );

}

private
void setViewValues () {

	editTextTitle.setText ( TaskActivity.currentTask.getTitle () );
	currentTask.setIsStar ( String.valueOf ( ! currentTask.isStar () ) );
	noteEditText.setText ( String.valueOf ( currentTask.getNote () ) );
	noteLayout.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			IntentCaller.taskNoteActivity ( getApplicationContext (), currentTask );
		}
	} );
	currentTask.setIsComplete ( String.valueOf ( ! currentTask.isComplete () ) );

	setTextViewReminderFromTaskDB ( currentTask, reminderText, sContext );
	uploadBtn.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {

			if(mUploadMultiPictures!=null){
				if(mUploadMultiPictures.isUploading){
					CustomDialog.showCancelUploadDialog ( thisActivity,getIntent (),mUploadMultiPictures);
				}
				else{
					getImages ();
				}
			}else {
				getImages ();
			}

		}
	} );

	calendarLayout.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			ReminderDialog.showReminderDialog ( thisActivity, listViewSubTask, sContext );
		}
	} );

	UiMng.toggleImgCompleteData(checkBoxTask, currentTask, getApplicationContext());
	checkBoxTask.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			UiMng.toggleImgCompleteData(v, currentTask, getApplicationContext());
		}
	} );
	UiMng.toggleImgStarData(star, currentTask, getApplicationContext());
	star.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			UiMng.toggleImgStarData(v, currentTask, getApplicationContext());
		}
	} );

	addSubTask.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			Log.d ( "", "addSubTask" );
			CustomDialog.showAddSubTaskDialog ( thisActivity, subTaskAdapter, listViewSubTask );
		}
	} );
	subTaskAdapter = setUpAdapterListView ( this, listViewSubTask, subTaskAdapter, getApplicationContext () );

	editTextBottom.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View v ) {
			IntentCaller.commentActivity ( TaskDetailActivity.this.getApplicationContext () );
		}
	} );


	bottomRoot.setOnClickListener ( new View.OnClickListener () {
		@Override public
		void onClick ( View view ) {
			IntentCaller.commentActivity ( TaskDetailActivity.this.getApplicationContext () );
		}
	} );


}

public static
void setTextViewReminderFromTaskDB ( TaskModel taskModel, TextView reminderText, Context context ) {
	String reminderTime = taskModel.getReminderDate ();
	Log.d ( TAG, "reminderTime= " + reminderTime );
	if ( reminderTime != null && ! reminderTime.isEmpty () ) {
		Date date = new Date ();
		date.setTime ( Long.parseLong ( reminderTime ) );
		ReminderDialog.setTextViewReminder ( date, reminderText, context );
	}
	else {
		reminderText.setText ( context.getResources ().getString ( R.string.reminder_text_task_detail ) );
		UiMng.setBlackText ( context, reminderText );
	}
}

public static
SubTaskAdapter setUpAdapterListView ( Activity activity, ListView listView, SubTaskAdapter subTaskAdapter, Context context ) {
	SubtaskSelection where = new SubtaskSelection ();
	where.taskid ( TaskActivity.currentTask.getId () );
	Cursor c = where.query ( context.getContentResolver () );
	c.moveToFirst ();
	Log.d ( TAG, "setUpAdapter" + String.valueOf ( c.getCount () ) );

	c.moveToFirst ();

	Log.d ( "setUpAdapter", String.valueOf ( c.getCount () ) );
	List<ContentValues> allListValues = QueryHelper.getValuesFromCursor ( c, SubtaskColumns.ALL_COLUMNS );
	ArrayList<SubTaskModel> arrayOfList = new ArrayList<> ();

	//landingListAdapter = new LandingListAdapter ( activity, arrayOfList );
	subTaskAdapter = new SubTaskAdapter ( context, arrayOfList );
	listView.setAdapter ( subTaskAdapter );
	for ( int i = 0 ; i < allListValues.size () ; i++ ) {
		ContentValues values = allListValues.get ( i );
		subTaskAdapter.add ( new SubTaskModel ( values.getAsString ( SubtaskColumns.SUBTASK_TITLE ),
		                                        values.getAsString ( SubtaskColumns.TASKID ),
		                                        values.getAsString ( SubtaskColumns._ID ),
		                                        values.getAsString ( SubtaskColumns.ISCOMPLETE ) ) );
	}

	UiMng.setTaskListViewHeight(listViewSubTask);

	return subTaskAdapter;
}



private
void getImages () {
	Intent intent = new Intent ( this, ImagePickerActivity.class );
	Config config = new Config.Builder ()
			.setTabBackgroundColor ( R.color.white )    // set tab background color. Default white.
			.setTabSelectionIndicatorColor ( R.color.blue )
			.setCameraButtonColor ( R.color.green )
			.setSelectionLimit ( MAX_IMGS_SELECTION )    // set photo selection limit. Default unlimited selection.
			.build ();
	ImagePickerActivity.setConfig ( config );
	startActivityForResult ( intent, INTENT_REQUEST_GET_IMAGES );
}

private
AndroidAuthSession buildSession () {
	AppKeyPair appKeyPair = new AppKeyPair ( APP_KEY, APP_SECRET );

	AndroidAuthSession session = new AndroidAuthSession ( appKeyPair );
	loadAuth ( session );
	return session;
}

/**
 * Shows keeping the access keys returned from Trusted Authenticator in a local
 * store, rather than storing user name & password, and re-authenticating each
 * time (which is not to be done, ever).
 */
private
void loadAuth ( AndroidAuthSession session ) {
	SharedPreferences prefs = getSharedPreferences ( ACCOUNT_PREFS_NAME, 0 );
	String key = prefs.getString ( ACCESS_KEY_NAME, null );
	String secret = prefs.getString ( ACCESS_SECRET_NAME, null );
	if ( key == null || secret == null || key.length () == 0 || secret.length () == 0 ) {
		return;
	}

	if ( key.equals ( "oauth2:" ) ) {
		// If the key is set to "oauth2:", then we can assume the token is for OAuth 2.
		session.setOAuth2AccessToken ( secret );
	}
	else {
		// Still support using old OAuth 1 tokens.
		session.setAccessTokenPair ( new AccessTokenPair ( key, secret ) );
	}
}

@Override
protected
void onActivityResult ( int requestCode, int resultCode, Intent intent ) {
	super.onActivityResult ( requestCode, resultCode, intent );

	if ( resultCode == Activity.RESULT_OK ) {
		Log.d ( TAG, "onActivityResult" );
		if ( requestCode == INTENT_REQUEST_GET_IMAGES ) {
			Parcelable[] parcelableUris = intent.getParcelableArrayExtra ( ImagePickerActivity.EXTRA_IMAGE_URIS );

			if ( parcelableUris == null ) {
				return;
			}

			// Java doesn't allow array casting, this is a little hack
			Uri[] uris = new Uri[ parcelableUris.length ];
			System.arraycopy ( parcelableUris, 0, uris, 0, parcelableUris.length );
			File[] files = new File[ parcelableUris.length ];
			if ( uris != null ) {
				SessionManagement session;
				session = new SessionManagement ( getApplicationContext () );
				int i = 0;
				for ( Uri uri : uris ) {
					Log.i ( TAG, " uri: " + uri );
					mMedia.add ( uri );
					files[ i ] = new File ( uri.toString () );
					//Log.d ( TAG,"files="+files[i].toString () );
					i++;
				}

				mUploadMultiPictures = new UploadMultiPictures ( sContext, mApi, PHOTO_DIR, files );
				mUploadMultiPictures.execute ();
			}
		}
	}
}
public static
void setActiveUploadBtn () {
	Log.d ( TAG,"Set red upload btn" );
	TaskDetailActivity.uploadBtn.setColorFilter ( sContext.getResources ().getColor ( R.color.red_400 ) );
}

public static
void setInActiveUploadBtn () {
	Log.d ( TAG,"Set grey upload btn" );
	TaskDetailActivity.uploadBtn.setColorFilter (sContext.getResources ().getColor ( R.color.grey_500 ) );
}

@Override
protected
void onPause () {
	super.onPause ();
	TaskModel currentTask = TaskActivity.currentTask;
	currentTask.setTitle ( editTextTitle.getText ().toString () );
	String id = currentTask.getId ();
	Uri uri = Uri.parse ( String.valueOf ( TaskColumns.CONTENT_URI ) + "/" + id );
	getContentResolver ().update ( uri, currentTask.getValues (), null, null );
	saveSubTaskAdapterToDb ();

}

private
void saveSubTaskAdapterToDb () {
	for ( int i = 0 ; i < subTaskAdapter.getCount () ; i++ ) {
		//ListModel recordData = mLandingListAdapter.getArrayList ().get ( i );
		SubTaskModel recordData = subTaskAdapter.getItem ( i );
		String id = recordData.getId ();
		Uri uri = Uri.parse ( String.valueOf ( SubtaskColumns.CONTENT_URI ) + "/" + id );
		//try {
		getContentResolver ().update ( uri, recordData.getValues (), null, null );

		/*catch ( IllegalArgumentException e ) {
			Log.e ( "errorOnUpdateData", e.getMessage () );
			uri = getContentResolver ().insert ( ListColumns.CONTENT_URI, recordData.getValues () );
			Log.d ( "ChkColumn ", "title" + recordData.getTitle () + "newId=" + uri.getPathSegments ().get ( 1 ) );
		}*/
	}
}

@Override
protected
void onNewIntent ( Intent intent ) {
	super.onNewIntent ( intent );
	cancelUpload ( intent, mUploadMultiPictures );
}

private static
void cancelUpload ( Intent intent, UploadMultiPictures uploadMultiPictures ) {


	String intentKey = intent.getStringExtra ( CANCEL_UPLOAD );
	if ( intentKey != null ) {
		if ( intentKey.equals ( TRUE ) ) {
			Log.d ( TAG, "enter cancel uploadBtn" );
			if ( uploadMultiPictures != null ) {
				uploadMultiPictures.cancelUpload ();
				intent.putExtra ( CANCEL_UPLOAD, FALSE );
				Log.d ( TAG, "canceled uploadBtn" );
			}
			else{
				Log.d ( TAG,"uploadPic is null???" );
			}
		}
	}
}

@Override
protected
void
onResume () {
	super.onResume ();
	Log.d ( "OnResume", "" );
	setView ();
	setViewValues ();
	setUpAdapterListView ();
	dropboxResume ();
	cancelUpload ( getIntent (), mUploadMultiPictures );

}

public
void dropboxResume () {
	AndroidAuthSession session = mApi.getSession ();
	// The next part must be inserted in the onResume() method of the
	// activity from which session.startAuthentication() was called, so
	// that Dropbox authentication completes properly.
	if ( session.authenticationSuccessful () ) {
		try {
			// Mandatory call to complete the auth
			session.finishAuthentication ();

			// Store it locally in our app for later use
			storeAuth ( session );
			setLoggedIn ( true );
		}
		catch ( IllegalStateException e ) {
			showToast ( "Couldn't authenticate with Dropbox:" + e.getLocalizedMessage () );
			Log.i ( TAG, "Error authenticating", e );
		}
	}
}

/**
 * Convenience function to change UI state based on being logged in
 */
private
void setLoggedIn ( boolean loggedIn ) {
	mLoggedIn = loggedIn;
	if ( loggedIn ) {
		//mSubmit.setText("Unlink from Dropbox");
		//mDisplay.setVisibility(View.VISIBLE);
	}
	else {
		//	mSubmit.setText("Link with Dropbox");
//		mDisplay.setVisibility(View.GONE);
//		mImage.setImageDrawable ( null );
	}
}

private
void showToast ( String msg ) {
	Toast error = Toast.makeText ( this, msg, Toast.LENGTH_LONG );
	error.show ();
}

/**
 * Shows keeping the access keys returned from Trusted Authenticator in a local
 * store, rather than storing user name & password, and re-authenticating each
 * time (which is not to be done, ever).
 */
private
void storeAuth ( AndroidAuthSession session ) {
	// Store the OAuth 2 access token, if there is one.
	String oauth2AccessToken = session.getOAuth2AccessToken ();
	if ( oauth2AccessToken != null ) {
		SharedPreferences prefs = getSharedPreferences ( ACCOUNT_PREFS_NAME, 0 );
		SharedPreferences.Editor edit = prefs.edit ();
		edit.putString ( ACCESS_KEY_NAME, "oauth2:" );
		edit.putString ( ACCESS_SECRET_NAME, oauth2AccessToken );
		edit.commit ();
		return;
	}
	// Store the OAuth 1 access token, if there is one.  This is only necessary if
	// you're still using OAuth 1.
	AccessTokenPair oauth1AccessToken = session.getAccessTokenPair ();
	if ( oauth1AccessToken != null ) {
		SharedPreferences prefs = getSharedPreferences ( ACCOUNT_PREFS_NAME, 0 );
		SharedPreferences.Editor edit = prefs.edit ();
		edit.putString ( ACCESS_KEY_NAME, oauth1AccessToken.key );
		edit.putString ( ACCESS_SECRET_NAME, oauth1AccessToken.secret );
		edit.commit ();
		return;
	}
}

public static
SubTaskAdapter setUpAdapterListView () {
	return setUpAdapterListView ( thisActivity, listViewSubTask, subTaskAdapter, sContext );
}

@Override
public
boolean onCreateOptionsMenu ( Menu menu ) {
	getMenuInflater ().inflate ( R.menu.menu_task_detail, menu );
	return true;
}

@Override
public
boolean onOptionsItemSelected ( MenuItem item ) {
	int id = item.getItemId ();

	if ( id == R.id.action_settings ) {
		return true;
	}
	return super.onOptionsItemSelected ( item );
}

private
void logOut () {
	// Remove credentials from the session
	mApi.getSession ().unlink ();

	// Clear our stored keys
	clearKeys ();
	// Change UI state to display logged out version
	setLoggedIn ( false );
}

private
void clearKeys () {
	SharedPreferences prefs = getSharedPreferences ( ACCOUNT_PREFS_NAME, 0 );
	SharedPreferences.Editor edit = prefs.edit ();
	edit.clear ();
	edit.commit ();
}

private
void checkAppKeySetup () {
	// Check to make sure that we have a valid app key
	if ( APP_KEY.startsWith ( "CHANGE" ) ||
	     APP_SECRET.startsWith ( "CHANGE" ) ) {
		showToast ( "You must apply for an app key and secret from developers.dropbox.com, and add them to the DBRoulette ap before trying it." );
		finish ();
		return;
	}

	// Check if the app has set up its manifest properly.
	Intent testIntent = new Intent ( Intent.ACTION_VIEW );
	String scheme = "db-" + APP_KEY;
	String uri = scheme + "://" + AuthActivity.AUTH_VERSION + "/test";
	testIntent.setData ( Uri.parse ( uri ) );
	PackageManager pm = getPackageManager ();
	if ( 0 == pm.queryIntentActivities ( testIntent, 0 ).size () ) {
		showToast ( "URL scheme in your app's " +
		            "manifest is not set up correctly. You should have a " +
		            "com.dropbox.client2.android.AuthActivity with the " +
		            "scheme: " + scheme );
		finish ();
	}
}
}
