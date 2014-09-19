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

public class GameMap_domanda2 extends Activity { 

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
			setContentView(R.layout.gioco_map_domanda2);
			
        	progressBar = (ProgressBar)findViewById(R.id.progressbar_Horizontal);
    	    progressBar.setProgress(0);
    	    final BackgroundAsyncTask task=new BackgroundAsyncTask();
    	    task.execute();
    	    
    	    time = 100;
    	    myProgress = 0;
    	    Button risp2a = (Button) findViewById(R.id.risp2A);
   	     	Button risp2b = (Button) findViewById(R.id.risp2B);
   	     	Button risp2c = (Button) findViewById(R.id.risp2C);
   	     	
   	     	Intent i = getIntent();
   	     	score = i.getDoubleExtra("score", score);
   	     	idUser = i.getStringExtra("idUtente");
			namePlace = i.getStringExtra("nomeLuogo");
			action = i.getStringExtra("azione");
			userClan = i.getStringExtra("clanUtente");
			idPlace = i.getIntExtra("idLuogo", idPlace);
  
    	    risp2a.setOnClickListener(new OnClickListener() {
    	    	@Override
	                public void onClick(View v1) {
    	    		
    	    		score = score + (100 - myProgress);
    	    		Intent intentGame3a = new Intent(
    						getApplicationContext(),
    						GameMap_domanda3.class
    					);
    	    		intentGame3a.putExtra("score", score);
    	    		intentGame3a.putExtra("idUtente", idUser);
    	    		intentGame3a.putExtra("nomeLuogo", namePlace);
    	    		intentGame3a.putExtra("azione", action);
    	    		intentGame3a.putExtra("clanUtente", userClan);
    	    		intentGame3a.putExtra("idLuogo", idPlace);
    	    		task.cancel(true);
    				startActivity(intentGame3a);
    				finish();
    	    	}
    	     });
    	    
    	     risp2b.setOnClickListener(new OnClickListener() {
        	    	@Override
 	                public void onClick(View v2) {
        	    		
        	    		score = score + (100 - myProgress);
        	    		Intent intentGame3b = new Intent(
        						getApplicationContext(),
        						GameMap_domanda3.class
        					);
        	    		intentGame3b.putExtra("score", score);
        	    		intentGame3b.putExtra("score", score);
        	    		intentGame3b.putExtra("idUtente", idUser);
        	    		intentGame3b.putExtra("nomeLuogo", namePlace);
        	    		intentGame3b.putExtra("azione", action);
        	    		intentGame3b.putExtra("clanUtente", userClan);
        	    		intentGame3b.putExtra("idLuogo", idPlace);
        	    		task.cancel(true);
        				startActivity(intentGame3b);
        				finish();
        	    	}
        	     });
    	     
    	     risp2c.setOnClickListener(new OnClickListener() {
        	    	@Override
 	                public void onClick(View v3) {
        	    		
        	    		score = score + (100 - myProgress);
        	    		Intent intentGame3c = new Intent(
        						getApplicationContext(),
        						GameMap_domanda3.class
        					);
        	    		intentGame3c.putExtra("score", score);
        	    		intentGame3c.putExtra("score", score);
        	    		intentGame3c.putExtra("idUtente", idUser);
        	    		intentGame3c.putExtra("nomeLuogo", namePlace);
        	    		intentGame3c.putExtra("azione", action);
        	    		intentGame3c.putExtra("clanUtente", userClan);
        	    		intentGame3c.putExtra("idLuogo", idPlace);
        	    		task.cancel(true);
        				startActivity(intentGame3c);
        				finish();
        	    	}
        	     });
		}
		

	}
		
