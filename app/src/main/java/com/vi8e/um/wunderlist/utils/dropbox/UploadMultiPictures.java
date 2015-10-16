/*
 * Copyright (c) 2011 Dropbox, Inc.
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */


package com.vi8e.um.wunderlist.utils.dropbox;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
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


/**
 * Here we show uploading a file in a background thread, trying to show
 * typical exception handling and flow of control for an app that uploads a
 * file from Dropbox.
 */
public
class UploadMultiPictures extends AsyncTask<Void, Long, Boolean> {

private DropboxAPI<?>  mApi;
private String         mPath;
private UploadRequest  mRequest;
private Context        mContext;
private ProgressDialog mDialog;

private String mErrorMsg;

//new class variables:
private int    mFilesUploaded;
private File[] mFilesToUpload;
private File[] mToBeUploaded;
private int    mCurrentFileIndex;
private boolean isCancelled = false;
public static final String TAG = "UploadMulti";
public static boolean isUploading=false;
Integer NOTIFICATION_ID = 100;
NotificationManager mNotifyManager;
final NotificationCompat.Builder mNotificationBuilder;
public
UploadMultiPictures ( Context context, DropboxAPI<?> api, String dropboxPath, File[] filesToUpload ) {
	// We set the context this way so we don't accidentally leak activities
	mContext = context.getApplicationContext ();
	mApi = api;
	mPath = dropboxPath;

	//set number of files uploaded to zero.
	mFilesUploaded = 0;
	mFilesToUpload = filesToUpload;
	mToBeUploaded = mFilesToUpload;
	mCurrentFileIndex = 0;

	mNotificationBuilder = new NotificationCompat.Builder ( TaskDetailActivity.thisActivity );
	setUpNotification ();

	//showUploadProgressDialog ( filesToUpload );
}

public
void setUpNotification () {
	mNotifyManager =
			( NotificationManager ) mContext.getSystemService ( Context.NOTIFICATION_SERVICE );

	Intent intent = new Intent ( TaskDetailActivity.thisActivity, TaskDetailActivity.class );
	Intent intentCancelUpload = new Intent ( TaskDetailActivity.thisActivity, TaskDetailActivity.class );
	intentCancelUpload.putExtra ( TaskDetailActivity.CANCEL_UPLOAD, TaskDetailActivity.TRUE );

	PendingIntent pIntent = PendingIntent.getActivity ( TaskDetailActivity.thisActivity, ( int ) System.currentTimeMillis (), intent, 0 );
	PendingIntent pIntentCancel = PendingIntent.getActivity ( TaskDetailActivity.thisActivity, ( int ) System.currentTimeMillis (), intentCancelUpload, 0 );


	mNotificationBuilder.setContentTitle ( "Uploading pictures to the server" )
	                    .setContentText ( "Upload in progress" )
	                    .setAutoCancel ( true )
	                    .setContentIntent ( pIntent )
	                    .setPriority ( NotificationCompat.PRIORITY_HIGH )
	                    .setVisibility ( NotificationCompat.VISIBILITY_PUBLIC )
	                    .setCategory ( NotificationCompat.CATEGORY_ALARM )
	                    .addAction ( R.drawable.ic_action_delete, "Cancel upload", pIntentCancel )
	                    .setSmallIcon ( R.drawable.ic_action_accept );

}

@Override
protected
Boolean doInBackground ( Void... params ) {
	try {
		Log.d ( TAG, "files num=" + mToBeUploaded.length );
		for ( int i = 0 ; i < mToBeUploaded.length ; i++ ) {
			mCurrentFileIndex = i;
			File file = mToBeUploaded[ i ];
			Log.d ( TAG, "loop=" + i );
			// By creating a request, we get a handle to the putFile operation,
			// so we can cancel it later if we want to
			FileInputStream fis = new FileInputStream ( file );
			String path = mPath + file.getName ();
			mRequest = mApi.putFileRequest ( path, fis, file.length (), null, true, getUploadProgressListener () );
			//mApi.share (  )
			mRequest.upload ();

			if ( ! isCancelled ) {
				mFilesUploaded++;
			}
			else {
				return false;
			}
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
			// Unauthorized, so we should unlink them.  You may want to
			// automatically log the user out in this case.
		}
		else if ( e.error == DropboxServerException._403_FORBIDDEN ) {
			// Not allowed to access this
		}
		else if ( e.error == DropboxServerException._404_NOT_FOUND ) {
			// path not found (or if it was the thumbnail, can't be
			// thumbnailed)
		}
		else if ( e.error == DropboxServerException._507_INSUFFICIENT_STORAGE ) {
			// user is over quota
		}
		else {
			// Something else
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

@Override
protected
void onPostExecute ( Boolean result ) {
	mNotifyManager.cancel ( NOTIFICATION_ID );
	TaskDetailActivity.setInActiveUploadBtn ();
	isUploading=false;
	if ( result ) {
		showToast ( "Images successfully uploaded" );
	}
	else {
		showToast ( mErrorMsg );
	}
}

@Override
protected
void onProgressUpdate ( Long... progress ) {
// Start a lengthy operation in a background thread
	final NotificationManager finalMNotifyManager = mNotifyManager;
	updateProgressOfDialog ( progress, mFilesToUpload, mCurrentFileIndex, mDialog );
	isUploading=true;
	TaskDetailActivity.setActiveUploadBtn ();

}

private
void updateProgressOfDialog ( Long[] progress, File[] filesToUpload, int currentFileIndex, ProgressDialog dialog ) {
	Long totalBytes = Long.valueOf ( 0 );
	Long bytesUploaded = Long.valueOf ( 0 );
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
	//Log.d ( TAG, "updatingProgress " + progressUpdate + " " + filesStatus );
	mNotificationBuilder.setProgress ( 100, progressUpdate, false );
	mNotificationBuilder.setContentText ( filesStatus );
	mNotifyManager.notify ( NOTIFICATION_ID, mNotificationBuilder.build () );
}

private
void showToast ( String msg ) {
	Toast error = Toast.makeText ( mContext, msg, Toast.LENGTH_LONG );
	error.show ();
}

public
void cancelUpload () {
	mNotificationBuilder.setContentTitle ( "Upload cancelled" );
	mNotificationBuilder.setContentText ( "" );
	cancel ( true );
	setIsCancelled ( true );
	isCancelled ();
	Log.d ( TAG, "done cancel upload" );
}

public
void setIsCancelled ( boolean isCancelled ) {
	this.isCancelled = isCancelled;
}
}