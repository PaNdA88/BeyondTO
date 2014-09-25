package com.example.beyondto;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class NewsSelectedActivity extends Activity {

	// attivitˆ che viene lanciata per visualizzare la news selezionata
	// nei casi in cui lo schermo sia piccolino e non si possa usare il
	// frammento

	Intent intent = new Intent();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			// If the screen is now in landscape mode, we can show the
			// dialog in-line with the list so we don't need this activity.
			finish();
			return;
		}

		if (savedInstanceState == null) {
			// During initial setup, plug in the details fragment.
			
			
			
			String action = getIntent().getExtras().getString("azione");
			
			if (action == "attacco"){
			NewsSelectedFragment_Attacco details = new NewsSelectedFragment_Attacco();
			details.setArguments(getIntent().getExtras());
			getFragmentManager().beginTransaction()
					.add(android.R.id.content, details).commit();
			}
			else {
				NewsSelectedFragment_Difesa details = new NewsSelectedFragment_Difesa();
				details.setArguments(getIntent().getExtras());
				getFragmentManager().beginTransaction()
						.add(android.R.id.content, details).commit();
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.menu_home:

			intent.setClass(getApplicationContext(), HomeActivity.class);

			startActivity(intent);

			return true;

		case R.id.menu_map:

			intent.setClass(getApplicationContext(), MapActivity.class);

			startActivity(intent);
			return true;

		case R.id.menu_medal:
			intent.setClass(getApplicationContext(), MedalActivity.class);

			startActivity(intent);

			return true;

		case R.id.menu_news:

			intent.setClass(getApplicationContext(), NewsActivity.class);

			startActivity(intent);
			return true;

		case R.id.menu_settings:

			intent.setClass(getApplicationContext(), SettingsActivity.class);

			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);

		}

	}

}
