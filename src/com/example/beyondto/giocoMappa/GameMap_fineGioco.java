package com.example.beyondto.giocoMappa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.beyondto.Connector;
import com.example.beyondto.HomeActivity;
import com.example.beyondto.Infoton;
import com.example.beyondto.MapActivity;
import com.example.beyondto.MedalActivity;
import com.example.beyondto.NewsFragment;
import com.example.beyondto.R;
import com.example.beyondto.SettingsActivity;
import com.example.beyondto.ShareDialogScore;

public class GameMap_fineGioco extends Activity {

	private double score;
	private String idUser, namePlace, action, userClan, risp1data, risp2data,
			risp3data, risp1corretta, risp2corretta, risp3corretta;
	private int idPlace, idMatch;
	final Context ctx = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.gioco_map_fine);

		Intent i = getIntent();
		score = i.getDoubleExtra("score", score);
		Log.e("PUNTEGGI DOMANDA 3: ", Double.toString(score));
		idUser = i.getStringExtra("idUtente");
		namePlace = i.getStringExtra("nomeLuogo");
		action = i.getStringExtra("azione");
		userClan = i.getStringExtra("clanUtente");
		idPlace = i.getIntExtra("idLuogo", idPlace);
		idMatch = i.getIntExtra("idScontro", idMatch);
		risp1data = i.getStringExtra("risp1data");
		risp1corretta = i.getStringExtra("risp1corretta");
		risp2data = i.getStringExtra("risp2data");
		risp2corretta = i.getStringExtra("risp2corretta");
		risp3data = i.getStringExtra("risp3data");
		risp3corretta = i.getStringExtra("risp3corretta");

		String punti = Double.toString(score);
		TextView textPunti = (TextView) findViewById(R.id.punteggioFinale);
		Resources res1 = getApplicationContext().getResources();
		String pt = String.format(res1.getString(R.string.punteggioFinale),
				punti);
		textPunti.setText(Html.fromHtml((String) pt));

		Button bButton = (Button) findViewById(R.id.backButton);

		bButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg1) {

				Connector con = new Connector();
				con.setScoreAttDif(score, Infoton.getInstance().getUserId(),
						namePlace, action, userClan, idPlace, idMatch);

				Intent intentMap = new Intent(getApplicationContext(),
						MapActivity.class);
				startActivity(intentMap);
				finish();
			}
		});

		// Facebook dialog

		Button shareButton = (Button) findViewById(R.id.shareButton);

		shareButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg1) {
				Intent i = new Intent(getApplicationContext(),
						ShareDialogScore.class);
				i.putExtra("score", score);
				i.putExtra("idUtente", Infoton.getInstance().getUserId());
				i.putExtra("nomeLuogo", namePlace);
				i.putExtra("azione", action);
				i.putExtra("clanUtente", userClan);
				i.putExtra("idLuogo", idPlace);
				i.putExtra("idScontro", idMatch);

				startActivity(i);
			}
		});

		Button rispButton = (Button) findViewById(R.id.rispButton);

		rispButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg1) {

				RisposteDialog risposteDialog = new RisposteDialog();
				risposteDialog.setContext(ctx);
				risposteDialog.setRisp1data(risp1data);
				risposteDialog.setRisp2data(risp2data);
				risposteDialog.setRisp3data(risp3data);
				risposteDialog.setRisp1corretta(risp1corretta);
				risposteDialog.setRisp2corretta(risp2corretta);
				risposteDialog.setRisp3corretta(risp3corretta);
				risposteDialog.showRisposteDialog();
			}
		});

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