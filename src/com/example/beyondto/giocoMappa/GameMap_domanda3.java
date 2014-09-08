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
    public class BackgroundAsyncTask extends
       AsyncTask<Void, Integer, Void> {
     
     int myProgress;

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
      while(myProgress<100){
       myProgress++;
       publishProgress(myProgress);
          SystemClock.sleep(100);
          if (Thread.currentThread().isInterrupted()) {
       	   return null;
          }
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
    	    
    	    Button risp3a = (Button) findViewById(R.id.risp3A);
   	     	Button risp3b = (Button) findViewById(R.id.risp3B);
   	     	Button risp3c = (Button) findViewById(R.id.risp3C);

    	    risp3a.setOnClickListener(new OnClickListener() {
    	    	@Override
	                public void onClick(View v1) {
    	    		Intent intentGameEndA = new Intent(
    						getApplicationContext(),
    						GameMap_fineGioco.class
    					);
    				startActivity(intentGameEndA);
    				finish();
    	    	}
    	     });
    	    
    	     risp3b.setOnClickListener(new OnClickListener() {
        	    	@Override
 	                public void onClick(View v2) {
        	    		Intent intentGameEndB = new Intent(
        						getApplicationContext(),
        						GameMap_fineGioco.class
        					);
        				startActivity(intentGameEndB);
        				finish();
        	    	}
        	     });
    	     
    	     risp3c.setOnClickListener(new OnClickListener() {
        	    	@Override
 	                public void onClick(View v3) {
        	    		Intent intentGameEndC = new Intent(
        						getApplicationContext(),
        						GameMap_fineGioco.class
        					);
        				startActivity(intentGameEndC);
        				finish();
        	    	}
        	     });
		}
		

	}
		
