package com.vi8e.um.wunderlist.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.vi8e.um.wunderlist.Model.QueryHelper;
import com.vi8e.um.wunderlist.R;

import java.util.Locale;



public class DeveloperActivity extends Activity {
Activity mActivity;

@Override
protected
void onCreate ( Bundle savedInstanceState ) {
	super.onCreate ( savedInstanceState );
	setContentView ( R.layout.activity_dev_database );



	Button genRecord = (Button ) findViewById(R.id.GenRecordBtn);
	Button removeAllHealthRecordBtn = (Button ) findViewById(R.id.removeHealthDbBtn);
	Button viewDbBtn = (Button ) findViewById(R.id.viewDbBtn);
	Button setValueBtn = (Button ) findViewById(R.id.setValueBtn);
	Button alarmBtn = (Button ) findViewById(R.id.alarmBtn);
	Button chartBtn = (Button ) findViewById(R.id.chartBtn);
    Button viewDB2Btn = (Button ) findViewById(R.id.viewDB2Btn);
    Button dummyCalBtn = (Button ) findViewById(R.id.dummyCalBtn);
    Button removeDB2 = (Button ) findViewById(R.id.removeDB2Btn);

	Button mornitorSleepBtn = (Button ) findViewById(R.id.monitorSleepBtn);



	viewDbBtn.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View view) {
			Intent callIntent = new Intent (getApplication(), ViewDBActivity.class);
			startActivity(callIntent);
		}
	});

    viewDB2Btn.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
            Intent callIntent = new Intent (getApplication(), ViewDBActivity.class);
            startActivity(callIntent);
        }
    });

	/*genRecord.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			QueryHelper.getGenRecord ( mActivity, getApplicationContext () );
		}
	});

	dummyCalBtn.setOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			QueryHelper.loadDummyCalories ( getApplicationContext () );
		}
	});

	setValueBtn.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View view) {
			IntentCaller.callSetValuesActivity(0, getApplicationContext());
		}
	});
  mornitorSleepBtn.setOnClickListener(new View.OnClickListener() {
	@Override public void onClick(View v) {
		IntentCaller.callSleepAsAndroid(getApplicationContext());
	}
});
	removeAllHealthRecordBtn.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			QueryHelper.deleteRecord(getApplicationContext());
		}
	});*/

    removeDB2.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
            QueryHelper.deleteRecordValue ( getApplicationContext () );
        }
    });


    Spinner mySpinner = (Spinner ) findViewById(R.id.spinner1);
    final Locale currentLocal = getResources().getConfiguration().locale;
// Set default state.
    if (currentLocal.getLanguage().equals(
            new Locale ("th").getLanguage())) {
        mySpinner.setSelection(0);
        Log.d ( "localize", "select TH" );
    }else if (currentLocal.getLanguage().equals( Locale.ENGLISH.getLanguage())) {
        mySpinner.setSelection(1);
        Log.d ( "localize", "select EN" );
    }else if(currentLocal.getLanguage().equals(
            new Locale ("zh").getLanguage())) {
        mySpinner.setSelection(2);
        Log.d ( "localize", "select CH" );
    }else if(currentLocal.getLanguage().equals(
            new Locale ("ja").getLanguage())) {
        mySpinner.setSelection(3);
        Log.d ( "localize", "select JP" );
    }

}


@Override
public boolean onCreateOptionsMenu(Menu menu) {
	return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {
	// Handle action bar item clicks here. The action bar will
	// automatically handle clicks on the Home/Up button, so long
	// as you specify a parent activity in AndroidManifest.xml.
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
