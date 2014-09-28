package com.example.beyondto.giocoMappa;

import com.example.beyondto.Connector;
import com.example.beyondto.R;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class GameMap_domanda1 extends Activity {

	ProgressBar progressBar;
	private double score;
	private String idUser, namePlace, action, userClan;
	private int idPlace, idMatch, time, myProgress, risp1, risp2, risp3;
	private String[] question1;

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
			// TODO Auto-generated method stub

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
		setContentView(R.layout.gioco_map_domanda1);
		progressBar = (ProgressBar) findViewById(R.id.progressbar_Horizontal);
		progressBar.setProgress(0);
		final BackgroundAsyncTask task = new BackgroundAsyncTask();
		time = 100;
		myProgress = 0;

		risp1 = ((int) (Math.random() * 3));
		risp2 = (risp1 + 1) % 3;
		risp3 = (risp2 + 1) % 3;

		Intent i = getIntent();
		idUser = i.getStringExtra("idUtente");
		namePlace = i.getStringExtra("nomeLuogo");
		action = i.getStringExtra("azione");
		userClan = i.getStringExtra("clanUtente");
		idPlace = i.getIntExtra("idLuogo", idPlace);
		idMatch = i.getIntExtra("idScontro", idMatch);

		Connector con = new Connector();
		question1 = con.getQuestion(idPlace);
		task.execute();

		if (!question1[0].equals("-1")) {

			TextView text = (TextView) findViewById(R.id.domanda1);
			text.setText(question1[0]);


			final Button risp1a = (Button) findViewById(R.id.risp1A);
			risp1a.setText(question1[risp1 + 1]);

			final Button risp1b = (Button) findViewById(R.id.risp1B);
			risp1b.setText(question1[risp2 + 1]);

			final Button risp1c = (Button) findViewById(R.id.risp1C);
			risp1c.setText(question1[risp3 + 1]);


			risp1a.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v1) {

					if (risp1a.getText().equals(question1[1])) {
						score = 100 - myProgress;
					} else {
						score = 0;
					}

					Intent intentGame2a = new Intent(getApplicationContext(),
							GameMap_domanda2.class);
					intentGame2a.putExtra("score", score);
					intentGame2a.putExtra("idUtente", idUser);
					intentGame2a.putExtra("nomeLuogo", namePlace);
					intentGame2a.putExtra("azione", action);
					intentGame2a.putExtra("clanUtente", userClan);
					intentGame2a.putExtra("idLuogo", idPlace);
					intentGame2a.putExtra("idScontro", idMatch);
					task.cancel(true);
					startActivity(intentGame2a);
					finish();
				}
			});

			risp1b.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v2) {

					if (risp1b.getText().equals(question1[1])) {
						score = 100 - myProgress;
					} else {
						score = 0;
					}
					Intent intentGame2b = new Intent(getApplicationContext(),
							GameMap_domanda2.class);
					intentGame2b.putExtra("score", score);
					intentGame2b.putExtra("idUtente", idUser);
					intentGame2b.putExtra("nomeLuogo", namePlace);
					intentGame2b.putExtra("azione", action);
					intentGame2b.putExtra("clanUtente", userClan);
					intentGame2b.putExtra("idLuogo", idPlace);
					intentGame2b.putExtra("idScontro", idMatch);
					task.cancel(true);
					startActivity(intentGame2b);
					finish();
				}
			});

			risp1c.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v3) {

					if (risp1c.getText().equals(question1[1])) {
						score = 100 - myProgress;
					} else {
						score = 0;
					}
					Intent intentGame2c = new Intent(getApplicationContext(),
							GameMap_domanda2.class);
					intentGame2c.putExtra("score", score);
					intentGame2c.putExtra("idUtente", idUser);
					intentGame2c.putExtra("nomeLuogo", namePlace);
					intentGame2c.putExtra("azione", action);
					intentGame2c.putExtra("clanUtente", userClan);
					intentGame2c.putExtra("idLuogo", idPlace);
					intentGame2c.putExtra("idScontro", idMatch);
					task.cancel(true);
					startActivity(intentGame2c);
					finish();
				}
			});
		} else {
			finish();
		}
	}

}
