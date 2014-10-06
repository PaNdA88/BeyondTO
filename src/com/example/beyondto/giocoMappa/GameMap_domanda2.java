package com.example.beyondto.giocoMappa;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.beyondto.Connector;
import com.example.beyondto.R;

public class GameMap_domanda2 extends Activity {

	ProgressBar progressBar;
	private double score;
	private String idUser, namePlace, action, userClan, risp1data, risp1corretta;
	private String risp2data, risp2corretta;
	private int idPlace, idMatch, time, myProgress, risp1, risp2, risp3;
	private String[] question2;

	public class BackgroundAsyncTask extends AsyncTask<Void, Integer, Void> {

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			myProgress = 0;
		}

		@Override
		protected Void doInBackground(Void... params) {

			while (time >= 0) {
				while (myProgress <= 100) {
					myProgress++;
					publishProgress(myProgress);
					SystemClock.sleep(100);
					if (Thread.currentThread().isInterrupted()) {
						return null;
					}
				}
				time--;
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			progressBar.setProgress(values[0]);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gioco_map_domanda2);

		progressBar = (ProgressBar) findViewById(R.id.progressbar_Horizontal);
		progressBar.setProgress(0);
		final BackgroundAsyncTask task = new BackgroundAsyncTask();

		risp1 = ((int) (Math.random() * 3));
		risp2 = (risp1 + 1) % 3;
		risp3 = (risp2 + 1) % 3;

		Intent i = getIntent();
		score = i.getDoubleExtra("score", score);
		Log.e("PUNTEGGI DOMANDA 1: ", Double.toString(score));
		idUser = i.getStringExtra("idUtente");
		namePlace = i.getStringExtra("nomeLuogo");
		action = i.getStringExtra("azione");
		userClan = i.getStringExtra("clanUtente");
		idPlace = i.getIntExtra("idLuogo", idPlace);
		idMatch = i.getIntExtra("idScontro", idMatch);
		risp1data = i.getStringExtra("risp1data");
		risp1corretta = i.getStringExtra("risp1corretta");

		Connector con = new Connector();
		question2 = con.getQuestion(idPlace);
		task.execute();

		time = 100;
		myProgress = 0;

		if (!question2[0].equals("-1")) {

			TextView text = (TextView) findViewById(R.id.domanda2);
			text.setText(question2[0]);

			final Button risp2A = (Button) findViewById(R.id.risp2A);
			risp2A.setText(question2[risp1 + 1]);

			final Button risp2B = (Button) findViewById(R.id.risp2B);
			risp2B.setText(question2[risp2 + 1]);

			final Button risp2C = (Button) findViewById(R.id.risp2C);
			risp2C.setText(question2[risp3 + 1]);

			risp2A.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v1) {

					if (risp2A.getText().equals(question2[1])) {
						score = score + (100 - myProgress);
						risp2data = (String) risp2A.getText();
						risp2corretta = (String) risp2A.getText();
						Log.e("risp1data", risp1data);
						Log.e("risp1corretta", risp1corretta);
						Log.e("risp2data", risp2data);
						Log.e("risp2corretta", risp2corretta);
					} else {
						score = score;
						risp2data = (String) risp2A.getText();
						risp2corretta = question2[1];
					}
					Intent intentGame3a = new Intent(getApplicationContext(),
							GameMap_domanda3.class);
					intentGame3a.putExtra("score", score);
					intentGame3a.putExtra("idUtente", idUser);
					intentGame3a.putExtra("nomeLuogo", namePlace);
					intentGame3a.putExtra("azione", action);
					intentGame3a.putExtra("clanUtente", userClan);
					intentGame3a.putExtra("idLuogo", idPlace);
					intentGame3a.putExtra("idScontro", idMatch);
					intentGame3a.putExtra("risp1data", risp1data);
					intentGame3a.putExtra("risp1corretta", risp1corretta);
					intentGame3a.putExtra("risp2data", risp2data);
					intentGame3a.putExtra("risp2corretta", risp2corretta);
					task.cancel(true);
					startActivity(intentGame3a);
					finish();
				}
			});

			risp2B.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v2) {

					if (risp2B.getText().equals(question2[1])) {
						score = score + (100 - myProgress);
						risp2data = (String) risp2B.getText();
						risp2corretta = (String) risp2B.getText();
						Log.e("risp1data", risp1data);
						Log.e("risp1corretta", risp1corretta);
						Log.e("risp2data", risp2data);
						Log.e("risp2corretta", risp2corretta);
					} else {
						score = 0;
						risp2data = (String) risp2B.getText();
						risp2corretta = question2[1];
						Log.e("risp1data", risp1data);
						Log.e("risp1corretta", risp1corretta);
						Log.e("risp2data", risp2data);
						Log.e("risp2corretta", risp2corretta);
					}
					Intent intentGame3b = new Intent(getApplicationContext(),
							GameMap_domanda3.class);
					intentGame3b.putExtra("score", score);
					intentGame3b.putExtra("score", score);
					intentGame3b.putExtra("idUtente", idUser);
					intentGame3b.putExtra("nomeLuogo", namePlace);
					intentGame3b.putExtra("azione", action);
					intentGame3b.putExtra("clanUtente", userClan);
					intentGame3b.putExtra("idLuogo", idPlace);
					intentGame3b.putExtra("idScontro", idMatch);
					intentGame3b.putExtra("risp1data", risp1data);
					intentGame3b.putExtra("risp1corretta", risp1corretta);
					intentGame3b.putExtra("risp2data", risp2data);
					intentGame3b.putExtra("risp2corretta", risp2corretta);
					task.cancel(true);
					startActivity(intentGame3b);
					finish();
				}
			});

			risp2C.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v3) {

					if (risp2C.getText().equals(question2[1])) {
						score = score + (100 - myProgress);
						risp2data = (String) risp2C.getText();
						risp2corretta = (String) risp2C.getText();
						Log.e("risp1data", risp1data);
						Log.e("risp1corretta", risp1corretta);
						Log.e("risp2data", risp2data);
						Log.e("risp2corretta", risp2corretta);
					} else {
						score = score;
						risp2data = (String) risp2C.getText();
						risp2corretta = question2[1];
						Log.e("risp1data", risp1data);
						Log.e("risp1corretta", risp1corretta);
						Log.e("risp2data", risp2data);
						Log.e("risp2corretta", risp2corretta);
					}
					Intent intentGame3c = new Intent(getApplicationContext(),
							GameMap_domanda3.class);
					intentGame3c.putExtra("score", score);
					intentGame3c.putExtra("score", score);
					intentGame3c.putExtra("idUtente", idUser);
					intentGame3c.putExtra("nomeLuogo", namePlace);
					intentGame3c.putExtra("azione", action);
					intentGame3c.putExtra("clanUtente", userClan);
					intentGame3c.putExtra("idLuogo", idPlace);
					intentGame3c.putExtra("idScontro", idMatch);
					intentGame3c.putExtra("risp1data", risp1data);
					intentGame3c.putExtra("risp1corretta", risp1corretta);
					intentGame3c.putExtra("risp2data", risp2data);
					intentGame3c.putExtra("risp2corretta", risp2corretta);
					task.cancel(true);
					startActivity(intentGame3c);
					finish();
				}
			});
		} else {
			finish();
		}
	}

}
