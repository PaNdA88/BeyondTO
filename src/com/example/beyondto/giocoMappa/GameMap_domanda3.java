package com.example.beyondto.giocoMappa;

import com.example.beyondto.R;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.ProgressBar;
import android.view.View;
import android.view.View.OnClickListener;

public class GameMap_domanda3 extends Activity {
	 
	ProgressBar progressBar;
	private double score;
	private String idUser, namePlace, action, userClan;
	private int idPlace, time, myProgress;

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
    	    new BackgroundAsyncTask().execute();
    	     
    	    time = 100;
    	    myProgress = 0;
    	    
    	    Button risp3a = (Button) findViewById(R.id.risp3A);
   	     	Button risp3b = (Button) findViewById(R.id.risp3B);
   	     	Button risp3c = (Button) findViewById(R.id.risp3C);
   	     	
   	     	Intent i = getIntent();
	     	score = i.getDoubleExtra("score", score);
   	     	idUser = i.getStringExtra("idUtente");
			namePlace = i.getStringExtra("nomeLuogo");
			action = i.getStringExtra("azione");
			userClan = i.getStringExtra("clanUtente");
			idPlace = i.getIntExtra("idLuogo", idPlace);

    	    risp3a.setOnClickListener(new OnClickListener() {
    	    	@Override
	                public void onClick(View v1) {
    	    		
    	    		score = score + (100 - myProgress);
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
    				startActivity(intentGameEndA);
    				finish();
    	    	}
    	     });
    	    
    	     risp3b.setOnClickListener(new OnClickListener() {
        	    	@Override
 	                public void onClick(View v2) {
        	    		
        	    		score = score + (100 - myProgress);
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
        				startActivity(intentGameEndB);
        				finish();
        	    	}
        	     });
    	     
    	     risp3c.setOnClickListener(new OnClickListener() {
        	    	@Override
 	                public void onClick(View v3) {
        	    		
        	    		score = score + (100 - myProgress);
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
        				startActivity(intentGameEndC);
        				finish();
        	    	}
        	     });
		}
		

	}
		
