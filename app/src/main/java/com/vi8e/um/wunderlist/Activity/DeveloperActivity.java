package com.vi8e.um.wunderlist.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.vi8e.um.wunderlist.R;
import com.vi8e.um.wunderlist.util.QueryHelper;



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
    Button viewDB2Btn = (Button ) findViewById(R.id.viewDB2Btn);
    Button removeDB2 = (Button ) findViewById(R.id.removeDB2Btn);



	viewDbBtn.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View view) {
			Intent callIntent = new Intent (getApplication(), ViewListDBActivity.class);
			startActivity ( callIntent );
		}
	});

    viewDB2Btn.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
            Intent callIntent = new Intent (getApplication(), ViewTaskDBActivity.class);
            startActivity ( callIntent );
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
            QueryHelper.deleteListValue ( getApplicationContext () );
        }
    });

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
