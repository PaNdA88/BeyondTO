package com.example.beyondto.giocoMappa;

import com.example.beyondto.Connector;
import com.example.beyondto.R;
import com.example.beyondto.giocoMappa.GameMap_domanda2.BackgroundAsyncTask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;

public class GameMap_domanda3 extends Activity {
	 
	ProgressBar progressBar;
	private double score;
	private String idUser, namePlace, action, userClan;
	private int idPlace, idMatch, time, myProgress, risp1, risp2, risp3;
	private String[] question3;
	
    public class BackgroundAsyncTask extends
       AsyncTask<Void, Integer, Void> {


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

	     while(time >= 0){	 
	    	 while(myProgress<=100){
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
			
        	progressBar = (ProgressBar)findViewById(R.id.progressbar_Horizontal);
    	    progressBar.setProgress(0);
    	    final BackgroundAsyncTask task = new BackgroundAsyncTask();
    	    
    	    risp1 = ((int) (Math.random() * 3));
    		risp2 = (risp1 + 1) % 3;
    		risp3 = (risp2 + 1) % 3;

    		Intent i = getIntent();
	     	score = i.getDoubleExtra("score", score);
   	     	idUser = i.getStringExtra("idUtente");
			namePlace = i.getStringExtra("nomeLuogo");
			action = i.getStringExtra("azione");
			userClan = i.getStringExtra("clanUtente");
			idPlace = i.getIntExtra("idLuogo", idPlace);
			idMatch = i.getIntExtra("idScontro", idMatch);
			
			Connector con = new Connector(getApplicationContext());
			question3 = con.getQuestion(idPlace);
			task.execute();
    	     
    	    time = 100;
    	    myProgress = 0;
    	    
    	    if (!question3[0].equals("-1")) {

    	    	TextView text = (TextView) findViewById(R.id.domanda3);
    			text.setText(question3[0]);
    	    	
	    	    final RadioButton risp3a = (RadioButton) findViewById(R.id.risp3A);
	    	    risp3a.setText(question3[risp1+1]);
	    	    
	    	    final RadioButton risp3b = (RadioButton) findViewById(R.id.risp3B);
	    	    risp3b.setText(question3[risp2+1]);
	    	    
	    	    final RadioButton risp3c = (RadioButton) findViewById(R.id.risp3C);
	    	    risp3c.setText(question3[risp3+1]);
	    	   
	   	     	
	    	    risp3a.setOnClickListener(new OnClickListener() {
	    	    	@Override
		                public void onClick(View v1) {
	    	    		
	    	    		if(risp3a.getText().equals(question3[1])){
							score = score + (100 - myProgress);
						}else{
							score = 0;
						}
	    	    		Intent intentGameEndA = new Intent(
	    						getApplicationContext(),
	    						GameMap_fineGioco.class
	    					);
	    	    		intentGameEndA.putExtra("score", score);
	    	    		intentGameEndA.putExtra("idUtente", idUser);
	    	    		intentGameEndA.putExtra("nomeLuogo", namePlace);
	    	    		intentGameEndA.putExtra("azione", action);
	    	    		intentGameEndA.putExtra("clanUtente", userClan);
	    	    		intentGameEndA.putExtra("idLuogo", idPlace);
	    	    		intentGameEndA.putExtra("idScontro", idMatch);
	    	    		startActivity(intentGameEndA);
	    				finish();
	    	    	}
	    	     });
	    	    
	    	     risp3b.setOnClickListener(new OnClickListener() {
	        	    	@Override
	 	                public void onClick(View v2) {
	        	    		
	        	    		if(risp3a.getText().equals(question3[1])){
								score = score + (100 - myProgress);
							}else{
								score = 0;
							}
	        	    		Intent intentGameEndB = new Intent(
	        						getApplicationContext(),
	        						GameMap_fineGioco.class
	        					);
	        	    		intentGameEndB.putExtra("score", score);
	        	    		intentGameEndB.putExtra("idUtente", idUser);
	        	    		intentGameEndB.putExtra("nomeLuogo", namePlace);
	        	    		intentGameEndB.putExtra("azione", action);
	        	    		intentGameEndB.putExtra("clanUtente", userClan);
	        	    		intentGameEndB.putExtra("idLuogo", idPlace);
	        	    		intentGameEndB.putExtra("idScontro", idMatch);
	        				startActivity(intentGameEndB);
	        				finish();
	        	    	}
	        	     });
	    	     
	    	     risp3c.setOnClickListener(new OnClickListener() {
	        	    	@Override
	 	                public void onClick(View v3) {
	        	    		
	        	    		if(risp3a.getText().equals(question3[1])){
								score = score + (100 - myProgress);
							}else{
								score = 0;
							}
	        	    		Intent intentGameEndC = new Intent(
	        						getApplicationContext(),
	        						GameMap_fineGioco.class
	        					);
	        	    		intentGameEndC.putExtra("score", score);
	        	    		intentGameEndC.putExtra("idUtente", idUser);
	        	    		intentGameEndC.putExtra("nomeLuogo", namePlace);
	        	    		intentGameEndC.putExtra("azione", action);
	        	    		intentGameEndC.putExtra("clanUtente", userClan);
	        	    		intentGameEndC.putExtra("idLuogo", idPlace);
	        	    		intentGameEndC.putExtra("idScontro", idMatch);
	        				startActivity(intentGameEndC);
	        				finish();
	        	    	}
	        	     });
    	    }else{
    	    	finish();
    	    }
		}
		

	}
		
