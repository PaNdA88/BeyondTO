package com.example.beyondto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class NewsActivity extends Activity {

	Intent intent = new Intent();

	// ArrayList<Notifica> not = new ArrayList<Notifica>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
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
