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
	private String idUser, namePlace, action, userClan;
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
					} else {
						score = score;
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
					} else {
						score = 0;
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
					} else {
						score = score;
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
