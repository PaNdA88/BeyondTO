package com.example.beyondto.giocoMappa;

import com.example.beyondto.Connector;
import com.example.beyondto.HomeActivity;
import com.example.beyondto.MapActivity;
import com.example.beyondto.MedalActivity;
import com.example.beyondto.NewsFragment;
import com.example.beyondto.R;
import com.example.beyondto.SettingsActivity;
import com.facebook.Session;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GameMap_fineGioco extends Activity {

	private double score;
	private String idUser, namePlace, action, userClan;
	private int idPlace, idMatch;
	private Session session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		session = new Session(getApplicationContext());
	    Session.setActiveSession(session);
		
	   
		setContentView(R.layout.gioco_map_fine);
		
		Intent i = getIntent();
		score = i.getDoubleExtra("score", score);
	    idUser = i.getStringExtra("idUtente");
		namePlace = i.getStringExtra("nomeLuogo");
		action = i.getStringExtra("azione");
		userClan = i.getStringExtra("clanUtente");
		idPlace = i.getIntExtra("idLuogo", idPlace);
		idMatch = i.getIntExtra("idScontro", idMatch);

		Button bButton = (Button) findViewById(R.id.backButton);

		bButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg1) {
				
				Connector con = new Connector();
				con.setScoreAttDif(score, idUser, namePlace, action, userClan, idPlace, idMatch);
				
				Intent intentMap = new Intent(getApplicationContext(),
						MapActivity.class);
				startActivity(intentMap);
				finish();
			}
		});
	}
	
	


	// Menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu, menu);
		return true;
	}

	/**
	 * Event Handling for Individual menu item selected Identify single menu
	 * item by it's id
	 * */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case R.id.menu_home:
			Intent intentHome = new Intent(getApplicationContext(),
					HomeActivity.class);
			startActivity(intentHome);
			finish();
			return true;

		case R.id.menu_map:
			Intent intentMap = new Intent(getApplicationContext(),
					MapActivity.class);
			startActivity(intentMap);
			finish();
			return true;

		case R.id.menu_medal:
			Intent intentMedal = new Intent(getApplicationContext(),
					MedalActivity.class);
			startActivity(intentMedal);
			finish();
			return true;

		case R.id.menu_news:
			Intent intentNews = new Intent(getApplicationContext(),
					NewsFragment.class);
			startActivity(intentNews);
			finish();
			return true;

		case R.id.menu_settings:
			Intent intentSettings = new Intent(getApplicationContext(),
					SettingsActivity.class);
			startActivity(intentSettings);
			finish();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

}