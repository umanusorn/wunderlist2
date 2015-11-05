package com.vi8e.um.wunderlist.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.vi8e.um.wunderlist.Activity.Giants.LoginActivity;
import com.vi8e.um.wunderlist.Activity.Giants.RegisterFirstActivity;
import com.vi8e.um.wunderlist.Activity.Giants.UpdateProfileActivity;
import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.utils.QueryHelper;

import nl.changer.polypicker.Config;
import nl.changer.polypicker.ImagePickerActivity;


public class DeveloperActivity extends Activity {
Activity mActivity;
private static final int INTENT_REQUEST_GET_IMAGES = 1130;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_dev_database);

	Button removeLists = (Button) findViewById(R.id.removeListBtn);
	Button viewDbBtn = (Button) findViewById(R.id.viewDbBtn);
	Button genListTask = (Button) findViewById(R.id.genListTask);
	Button viewDB2Btn = (Button) findViewById(R.id.viewDB2Btn);
	Button removeTask = (Button) findViewById(R.id.removeTaskBtn);
	Button removeSubTask = (Button) findViewById(R.id.removeSubTaskBtn);
	Button removeComment = (Button) findViewById(R.id.removeCommentBtn);
	Button login = (Button) findViewById(R.id.login);
	Button updateProfile = (Button) findViewById(R.id.updateProfile);
	Button myAccount = (Button) findViewById(R.id.myAccount);
	Button signUp = (Button) findViewById(R.id.signUp);
	Button testDropbox = (Button) findViewById(R.id.linkDropbox);
	Button multiImgChooser = (Button) findViewById(R.id.multiImgChooser);
	Button recycleView = (Button) findViewById(R.id.landing);

	recycleView.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			Intent i = new Intent(getApplicationContext(), LandingActivity.class);
			startActivity(i);
		}
	});

	removeSubTask.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			QueryHelper.deleteAllSubTaskValues(getApplicationContext());
		}
	});

	removeComment.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			QueryHelper.deleteAllCommentValues(getApplicationContext());
		}
	});

	multiImgChooser.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
		/*	Intent intent = new Intent();
			intent.setType("image*//*");
			intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
			intent.setAction(Intent.ACTION_GET_CONTENT);
			startActivityForResult(Intent.createChooser(intent,"Select Picture"), 1);*/

			getImages();
		}
	});


	testDropbox.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			Intent i = new Intent(getApplicationContext(), LoginDropboxActivity.class);
			startActivity(i);
		}
	});

	signUp.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View view) {
			Intent i = new Intent(getApplicationContext(), RegisterFirstActivity.class);
			startActivity(i);
		}
	});

	myAccount.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View view) {
			Intent i = new Intent(getApplicationContext(), MyAccountActivity.class);
			startActivity(i);
		}
	});

	updateProfile.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View view) {
			Intent i = new Intent(getApplicationContext(), UpdateProfileActivity.class);
			startActivity(i);
		}
	});

	login.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View view) {
			Intent i = new Intent(getApplicationContext(), LoginActivity.class);
			startActivity(i);
			//startActivityForResult(i, REQUEST_CODE_LOGIN);
		}
	});

	genListTask.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			QueryHelper.genListAndTask(getApplicationContext());
		}
	});

	viewDbBtn.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View view) {
			Intent callIntent = new Intent(getApplication(), ViewListDBActivity.class);
			startActivity(callIntent);
		}
	});

	viewDB2Btn.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View view) {
			Intent callIntent = new Intent(getApplication(), ViewTaskDBActivity.class);
			startActivity(callIntent);
		}
	});

	removeTask.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			QueryHelper.deleteAllTaskValues(getApplicationContext());
		}
	});
	removeLists.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			QueryHelper.deleteAllListValues(getApplicationContext());
		}
	});

}

private void getImages() {
	Intent intent = new Intent(this, ImagePickerActivity.class);
	Config config = new Config.Builder()
			.setTabBackgroundColor(R.color.white)    // set tab background color. Default white.
			.setTabSelectionIndicatorColor(R.color.blue)
			.setCameraButtonColor(R.color.green)
			.setSelectionLimit(2)    // set photo selection limit. Default unlimited selection.
			.build();
	ImagePickerActivity.setConfig(config);
	startActivityForResult(intent, INTENT_REQUEST_GET_IMAGES);
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
	return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
	int id = item.getItemId();

	//noinspection SimplifiableIfStatement
	if (id == android.R.id.home) {
		super.onBackPressed();
	}
	if (id == R.id.action_settings) {
		return true;
	}

	return super.onOptionsItemSelected(item);
}
}
