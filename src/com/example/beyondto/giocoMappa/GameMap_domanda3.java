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

public class GameMap_domanda3 extends Activity {

	ProgressBar progressBar;
	private double score;
	private String idUser, namePlace, action, userClan, risp1data, risp2data,risp1corretta, risp2corretta;
	private String risp3data, risp3corretta;
	private int idPlace, idMatch, time, myProgress, risp1, risp2, risp3;
	private String[] question3;

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
		setContentView(R.layout.gioco_map_domanda3);

		progressBar = (ProgressBar) findViewById(R.id.progressbar_Horizontal);
		progressBar.setProgress(0);
		final BackgroundAsyncTask task = new BackgroundAsyncTask();

		risp1 = ((int) (Math.random() * 3));
		risp2 = (risp1 + 1) % 3;
		risp3 = (risp2 + 1) % 3;

		Intent i = getIntent();
		score = i.getDoubleExtra("score", score);
		Log.e("PUNTEGGI DOMANDA 2: ", Double.toString(score));
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

		Connector con = new Connector();
		question3 = con.getQuestion(idPlace);
		task.execute();

		time = 100;
		myProgress = 0;

		if (!question3[0].equals("-1")) {

			TextView text = (TextView) findViewById(R.id.domanda3);
			text.setText(question3[0]);

			final Button risp3a = (Button) findViewById(R.id.risp3A);
			risp3a.setText(question3[risp1 + 1]);

			final Button risp3b = (Button) findViewById(R.id.risp3B);
			risp3b.setText(question3[risp2 + 1]);

			final Button risp3c = (Button) findViewById(R.id.risp3C);
			risp3c.setText(question3[risp3 + 1]);

			risp3a.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v1) {

					if (risp3a.getText().equals(question3[1])) {
						score = score + (100 - myProgress);
						Log.e("SCORE: ", Double.toString(score));
						risp3data = (String) risp3a.getText();
						risp3corretta=(String) risp3a.getText();
						Log.e("risp1data", risp1data);
						Log.e("risp1corretta", risp1corretta);
						Log.e("risp2data", risp2data);
						Log.e("risp2corretta", risp2corretta);
						Log.e("risp3data", risp3data);
						Log.e("risp3corretta", risp3corretta);
					
					}else{
						risp3data = (String) risp3a.getText();
						risp3corretta = question3[1];
					}
					Intent intentGameEndA = new Intent(getApplicationContext(),
							GameMap_fineGioco.class);
					intentGameEndA.putExtra("score", score);
					intentGameEndA.putExtra("idUtente", idUser);
					intentGameEndA.putExtra("nomeLuogo", namePlace);
					intentGameEndA.putExtra("azione", action);
					intentGameEndA.putExtra("clanUtente", userClan);
					intentGameEndA.putExtra("idLuogo", idPlace);
					intentGameEndA.putExtra("idScontro", idMatch);
					intentGameEndA.putExtra("risp1data", risp1data);
					intentGameEndA.putExtra("risp1corretta", risp1corretta);
					intentGameEndA.putExtra("risp2data", risp2data);
					intentGameEndA.putExtra("risp2corretta", risp2corretta);
					intentGameEndA.putExtra("risp3data", risp3data);
					intentGameEndA.putExtra("risp3corretta", risp3corretta);
					startActivity(intentGameEndA);
					finish();
				}
			});

			risp3b.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v2) {

					if (risp3b.getText().equals(question3[1])) {
						score = score + (100 - myProgress);
						risp3data = (String) risp3b.getText();
						risp3corretta=(String) risp3b.getText();
						Log.e("risp1data", risp1data);
						Log.e("risp1corretta", risp1corretta);
						Log.e("risp2data", risp2data);
						Log.e("risp2corretta", risp2corretta);
						Log.e("risp3data", risp3data);
						Log.e("risp3corretta", risp3corretta);
					}else{
						risp3data = (String) risp3b.getText();
						risp3corretta = question3[1];
					}
					Intent intentGameEndB = new Intent(getApplicationContext(),
							GameMap_fineGioco.class);
					intentGameEndB.putExtra("score", score);
					intentGameEndB.putExtra("idUtente", idUser);
					intentGameEndB.putExtra("nomeLuogo", namePlace);
					intentGameEndB.putExtra("azione", action);
					intentGameEndB.putExtra("clanUtente", userClan);
					intentGameEndB.putExtra("idLuogo", idPlace);
					intentGameEndB.putExtra("idScontro", idMatch);
					intentGameEndB.putExtra("risp1data", risp1data);
					intentGameEndB.putExtra("risp1corretta", risp1corretta);
					intentGameEndB.putExtra("risp2data", risp2data);
					intentGameEndB.putExtra("risp2corretta", risp2corretta);
					intentGameEndB.putExtra("risp3data", risp3data);
					intentGameEndB.putExtra("risp3corretta", risp3corretta);
					startActivity(intentGameEndB);
					finish();
				}
			});

			risp3c.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v3) {

					if (risp3c.getText().equals(question3[1])) {
						score = score + (100 - myProgress);
						risp3data = (String) risp3c.getText();
						risp3corretta = (String) risp3c.getText();
						Log.e("risp1data", risp1data);
						Log.e("risp1corretta", risp1corretta);
						Log.e("risp2data", risp2data);
						Log.e("risp2corretta", risp2corretta);
						Log.e("risp3data", risp3data);
						Log.e("risp3corretta", risp3corretta);
					}else{
						risp3data = (String) risp3c.getText();
						risp3corretta = question3[1];
					}
					Intent intentGameEndC = new Intent(getApplicationContext(),
							GameMap_fineGioco.class);
					intentGameEndC.putExtra("score", score);
					intentGameEndC.putExtra("idUtente", idUser);
					intentGameEndC.putExtra("nomeLuogo", namePlace);
					intentGameEndC.putExtra("azione", action);
					intentGameEndC.putExtra("clanUtente", userClan);
					intentGameEndC.putExtra("idLuogo", idPlace);
					intentGameEndC.putExtra("idScontro", idMatch);
					intentGameEndC.putExtra("risp1data", risp1data);
					intentGameEndC.putExtra("risp1corretta", risp1corretta);
					intentGameEndC.putExtra("risp2data", risp2data);
					intentGameEndC.putExtra("risp2corretta", risp2corretta);
					intentGameEndC.putExtra("risp3data", risp3data);
					intentGameEndC.putExtra("risp3corretta", risp3corretta);
					startActivity(intentGameEndC);
					finish();
				}
			});
		} else {
			finish();
		}
	}

}
