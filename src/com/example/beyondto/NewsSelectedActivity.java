package com.example.beyondto;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class NewsSelectedActivity extends Activity {

	// attivit� che viene lanciata per visualizzare la news selezionata
	// nei casi in cui lo schermo sia piccolino e non si possa usare il
	// frammento

	private String username, namePlace, action, orario, user, nameDisplay;

	Intent intent = new Intent();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newsselected);

		Intent i = getIntent();
		username = i.getStringExtra("username");
		namePlace = i.getStringExtra("namePlace");
		action = i.getStringExtra("azione");
		orario = i.getStringExtra("orario");
		user = i.getStringExtra("user");

		System.out.println(username + " " + namePlace);
		String azione = "";

		String obiettivo = "OBIETTIVO: " + namePlace;

		if (action.equals("attaccare")) {
			if (user.equals(username)) {
				nameDisplay = "Tu";
			} else {
				nameDisplay = username;
			}
			azione = "ATTACCO!";
		} else {
			azione = "DIFESA!";
		}

		String utente = "UTENTE: " + nameDisplay;
		String act = azione;
		String giorno = " GIORNO: " + orario;

		TextView tit = (TextView) findViewById(R.id.notificaTit);
		tit.setText(Html.fromHtml((String) act));

		TextView ob = (TextView) findViewById(R.id.obiettivoAct);
		ob.setText(Html.fromHtml((String) obiettivo));

		/*
		 * 
		 * 
		 * /* TextView us = (TextView) findViewById(R.id.utenteAct);
		 * us.setText(Html.fromHtml((String) utente));
		 * 
		 * TextView az = (TextView) findViewById(R.id.azioneAct);
		 * az.setText(Html.fromHtml((String) act));
		 * 
		 * TextView t = (TextView) findViewById(R.id.giornoAct);
		 * t.setText(Html.fromHtml((String) giorno));
		 */

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			// If the screen is now in landscape mode, we can show the
			// dialog in-line with the list so we don't need this activity.

			finish();
			return;
		}

		if (savedInstanceState == null) {
			// During initial setup, plug in the details fragment.

			NewsSelectedFragment details = new NewsSelectedFragment();
			details.setArguments(getIntent().getExtras());
			getFragmentManager().beginTransaction()
					.add(android.R.id.content, details).commit();

		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			onBackPressed();
		}
		return false;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();

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
