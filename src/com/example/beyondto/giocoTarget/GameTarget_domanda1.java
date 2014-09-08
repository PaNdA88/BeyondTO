package com.example.beyondto.giocoTarget;

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

public class GameTarget_domanda1 extends Activity {
	 

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
			setContentView(R.layout.gioco_target_domanda1);
			
        	progressBar = (ProgressBar)findViewById(R.id.progressbar_Horizontal);
    	    progressBar.setProgress(0);
    	    new BackgroundAsyncTask().execute();
    	    
    	    Button risp1a = (Button) findViewById(R.id.risp1A);
   	     	Button risp1b = (Button) findViewById(R.id.risp1B);
   	     	Button risp1c = (Button) findViewById(R.id.risp1C);

    	    risp1a.setOnClickListener(new OnClickListener() {
    	    	@Override
	                public void onClick(View v1) {
    	    		Intent intentGame2a = new Intent(
    						getApplicationContext(),
    						GameTarget_domanda2.class
    					);
    				startActivity(intentGame2a);
    				finish();
    	    	}
    	     });
    	    
    	     risp1b.setOnClickListener(new OnClickListener() {
        	    	@Override
 	                public void onClick(View v2) {
        	    		Intent intentGame2b = new Intent(
        						getApplicationContext(),
        						GameTarget_domanda2.class
        					);
        				startActivity(intentGame2b);
        				finish();
        	    	}
        	     });
    	     
    	     risp1c.setOnClickListener(new OnClickListener() {
        	    	@Override
 	                public void onClick(View v3) {
        	    		Intent intentGame2c = new Intent(
        						getApplicationContext(),
        						GameTarget_domanda2.class
        					);
        				startActivity(intentGame2c);
        				finish();
        	    	}
        	     });
		}
		

	}
		
