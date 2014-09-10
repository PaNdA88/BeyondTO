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
			setContentView(R.layout.gioco_map_domanda2);
			
        	progressBar = (ProgressBar)findViewById(R.id.progressbar_Horizontal);
    	    progressBar.setProgress(0);
    	    final BackgroundAsyncTask task=new BackgroundAsyncTask();
    	    task.execute();
    	    
    	    Button risp2a = (Button) findViewById(R.id.risp2A);
   	     	Button risp2b = (Button) findViewById(R.id.risp2B);
   	     	Button risp2c = (Button) findViewById(R.id.risp2C);

    	    risp2a.setOnClickListener(new OnClickListener() {
    	    	@Override
	                public void onClick(View v1) {
    	    		Intent intentGame3a = new Intent(
    						getApplicationContext(),
    						GameMap_domanda3.class
    					);
    	    		task.cancel(true);
    				startActivity(intentGame3a);
    				finish();
    	    	}
    	     });
    	    
    	     risp2b.setOnClickListener(new OnClickListener() {
        	    	@Override
 	                public void onClick(View v2) {
        	    		Intent intentGame3b = new Intent(
        						getApplicationContext(),
        						GameMap_domanda3.class
        					);
        	    		task.cancel(true);
        				startActivity(intentGame3b);
        				finish();
        	    	}
        	     });
    	     
    	     risp2c.setOnClickListener(new OnClickListener() {
        	    	@Override
 	                public void onClick(View v3) {
        	    		Intent intentGame3c = new Intent(
        						getApplicationContext(),
        						GameMap_domanda3.class
        					);
        	    		task.cancel(true);
        				startActivity(intentGame3c);
        				finish();
        	    	}
        	     });
		}
		

	}
		
