package com.vi8e.um.wunderlist.utils.dropbox;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.DropboxAPI.UploadRequest;
import com.dropbox.client2.ProgressListener;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.exception.DropboxFileSizeException;
import com.dropbox.client2.exception.DropboxIOException;
import com.dropbox.client2.exception.DropboxParseException;
import com.dropbox.client2.exception.DropboxPartialFileException;
import com.dropbox.client2.exception.DropboxServerException;
import com.dropbox.client2.exception.DropboxUnlinkedException;
import com.vi8e.um.wunderlist.Activity.TaskDetailActivity;
import com.vi8e.um.wunderlist.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


/**
 * Here we show uploading a file in a background thread, trying to show
 * typical exception handling and flow of control for an app that uploads a
 * file from Dropbox.
 */
public
class UploadMultiPictures extends AsyncTask<Void, Long, Boolean> {

private DropboxAPI<?> mApi;
private String        mPath;
private UploadRequest mRequest;
private Context       mContext;
private String        mErrorMsg;
private int           mCurrentFileIndex;

private File[] mFilesToUpload;
private File[] mToBeUploaded;
public static final String  TAG             = "UploadMulti";
public static       boolean isUploading     = false;
final               int     NOTIFICATION_ID = 100;

NotificationManager mNotifyManager;
final NotificationCompat.Builder mNotificationBuilder;
ArrayList<String> uploadFilesPath = new ArrayList<> ();

public
UploadMultiPictures ( Context context, DropboxAPI<?> api, String dropboxPath, File[] filesToUpload ) {
	// We set the context this way so we don't accidentally leak activities
	mContext = context.getApplicationContext ();
	mApi = api;
	mPath = dropboxPath;

	mFilesToUpload = filesToUpload;
	mToBeUploaded = mFilesToUpload;
	mCurrentFileIndex = 0;

	mNotificationBuilder = new NotificationCompat.Builder ( TaskDetailActivity.thisActivity );
	setUpNotification ();
}

public
void setUpNotification () {
	mNotifyManager = ( NotificationManager ) mContext.getSystemService ( Context.NOTIFICATION_SERVICE );

	Intent intent = new Intent ( TaskDetailActivity.thisActivity, TaskDetailActivity.class );
	Intent intentCancelUpload = new Intent ( TaskDetailActivity.thisActivity, TaskDetailActivity.class );
	intentCancelUpload.putExtra ( TaskDetailActivity.CANCEL_UPLOAD, TaskDetailActivity.TRUE );

	PendingIntent pendingIntent = PendingIntent.getActivity ( TaskDetailActivity.thisActivity, ( int ) System.currentTimeMillis (), intent, 0 );
	PendingIntent pendingIntentCancel = PendingIntent.getActivity ( TaskDetailActivity.thisActivity, ( int ) System.currentTimeMillis (), intentCancelUpload,
	                                                                0 );


	mNotificationBuilder.setContentTitle ( "Uploading pictures to the server" )
	                    .setContentText ( "Upload in progress" )
	                    .setAutoCancel ( true )
	                    .setContentIntent ( pendingIntent )
	                    .setPriority ( NotificationCompat.PRIORITY_HIGH )
	                    .setVisibility ( NotificationCompat.VISIBILITY_PUBLIC )
	                    .setCategory ( NotificationCompat.CATEGORY_ALARM )
	                    .addAction ( R.drawable.ic_action_delete, "Cancel upload", pendingIntentCancel )
	                    .setSmallIcon ( R.drawable.ic_action_accept );

}

@Override
protected
Boolean doInBackground ( Void... params ) {
	try {
		Log.d ( TAG, "files number =" + mToBeUploaded.length );
		for ( int i = 0 ; i < mToBeUploaded.length ; i++ ) {
			mCurrentFileIndex = i;
			File file = mToBeUploaded[ i ];
			Log.d ( TAG, "upload loop=" + i );
			// By creating a request, we get a handle to the putFile operation,
			// so we can cancel it later if we want to
			FileInputStream fis = new FileInputStream ( file );
			String path = mPath + file.getName ();
			mRequest = mApi.putFileRequest ( path, fis, file.length (), null, true, getUploadProgressListener () );
			mRequest.upload ();
			uploadFilesPath.add ( path );
		}
		return true;
	}
	catch ( DropboxUnlinkedException e ) {
		// This session wasn't authenticated properly or user unlinked
		mErrorMsg = "This app wasn't authenticated properly.";
	}
	catch ( DropboxFileSizeException e ) {
		// File size too big to uploadBtn via the API
		mErrorMsg = "This file is too big to upload";
	}
	catch ( DropboxPartialFileException e ) {
		// We canceled the operation
		mErrorMsg = "Upload canceled";
	}
	catch ( DropboxServerException e ) {
		// Server-side exception.  These are examples of what could happen,
		// but we don't do anything special with them here.
		if ( e.error == DropboxServerException._401_UNAUTHORIZED ) {
			//todo auto logout?
			Log.e ( TAG, "Unauthorized, so we should unlink them.  You may want to\n"
			             + "\t\t\t automatically log the user out in this case." );
		}
		else if ( e.error == DropboxServerException._403_FORBIDDEN ) {
			Log.e ( TAG, "Not allowed to access this" );
		}
		else if ( e.error == DropboxServerException._404_NOT_FOUND ) {
			Log.e ( TAG, " path not found (or if it was the thumbnail, can't be thumbnailed)" );
		}
		else if ( e.error == DropboxServerException._507_INSUFFICIENT_STORAGE ) {
			Log.e ( TAG, "user is over quota" );
		}
		else {
			Log.e ( TAG, "something else : [" + e.error + "]" );
		}

		// This gets the Dropbox error, translated into the user's language
		mErrorMsg = e.body.userError;
		if ( mErrorMsg == null ) {
			mErrorMsg = e.body.error;
		}
	}
	catch ( DropboxIOException e ) {
		// Happens all the time, probably want to retry automatically.
		mErrorMsg = "Network error.  Try again.";
	}
	catch ( DropboxParseException e ) {
		// Probably due to Dropbox server restarting, should retry
		mErrorMsg = "Dropbox error.  Try again.";
	}
	catch ( DropboxException e ) {
		// Unknown error
		mErrorMsg = "Unknown error.  Try again.";
	}
	catch ( FileNotFoundException e ) {
		mErrorMsg = "File not found";
	}
	return false;
}

@NonNull public
ProgressListener getUploadProgressListener () {
	return new ProgressListener () {
		@Override
		public
		void onProgress ( long bytes, long total ) {
			if ( isCancelled () ) {
				// This will cancel the putFile operation
				mRequest.abort ();
			}
			else {
				publishProgress ( bytes );
			}
		}

		@Override
		public
		long progressInterval () {
			// Update the progress bar every half-second or so
			return 500;
		}
	};
}

public
void cancelUpload () {
	mNotificationBuilder.setContentTitle ( "Upload cancelled" );
	mNotificationBuilder.setContentText ( "" );
	mNotifyManager.notify ( NOTIFICATION_ID, mNotificationBuilder.build () );
	isUploading = false;
	cancel ( true );
	isCancelled ();
	Log.d ( TAG, "done cancel upload" );
	TaskDetailActivity.setInActiveUploadBtn ();
}

@Override
protected
void onPostExecute ( Boolean result ) {
	mNotifyManager.cancel ( NOTIFICATION_ID );
	TaskDetailActivity.setInActiveUploadBtn ();
	isUploading = false;
	if ( result ) {
		showToast ( "Images successfully uploaded" );
	}
	else {
		showToast ( mErrorMsg );
	}

	GetDropboxLinks getDropboxLinks = new GetDropboxLinks ();
	getDropboxLinks.execute ();
}

class GetDropboxLinks extends AsyncTask<String, Integer, Long> {

	@Override protected
	Long doInBackground ( String... params ) {

		for ( String filePath : uploadFilesPath ) {
			DropboxAPI.DropboxLink dropboxLink = null;
			try {
				dropboxLink = mApi.share ( filePath );
			}
			catch ( DropboxException e ) {
				e.printStackTrace ();
			}
			Log.d ( TAG, "Dropbox link = " + dropboxLink.url + " exp=" + dropboxLink.expires );
		}
		return null;
	}
}

@Override
protected
void onProgressUpdate ( Long... progress ) {
// Start a lengthy operation in a background thread
	final NotificationManager finalMNotifyManager = mNotifyManager;
	updateProgress ( progress, mFilesToUpload, mCurrentFileIndex );
	isUploading = true;
	TaskDetailActivity.setActiveUploadBtn ();

}

private
void updateProgress ( Long[] progress, File[] filesToUpload, int currentFileIndex ) {
	Long totalBytes = ( long ) 0;
	Long bytesUploaded = ( long ) 0;
	for ( int i = 0 ; i < filesToUpload.length ; i++ ) {
		Long bytes = filesToUpload[ i ].length ();
		totalBytes += bytes;

		if ( i < currentFileIndex ) {
			bytesUploaded += bytes;
		}
	}
	bytesUploaded += progress[ 0 ];

	int progressUpdate = ( int ) ( ( bytesUploaded / totalBytes ) * 100 );
	String filesStatus = "Uploading " + ( currentFileIndex + 1 ) + " / " + filesToUpload.length + " pictures";
	mNotificationBuilder.setProgress ( 100, progressUpdate, false );
	mNotificationBuilder.setContentText ( filesStatus );
	mNotifyManager.notify ( NOTIFICATION_ID, mNotificationBuilder.build () );
}

private
void showToast ( String msg ) {
	Toast error = Toast.makeText ( mContext, msg, Toast.LENGTH_LONG );
	error.show ();
}
}