package com.example.beyondto.giocoTarget;

import com.example.beyondto.HomeActivity;
import com.example.beyondto.MapActivity;
import com.example.beyondto.MedalActivity;
import com.example.beyondto.NewsFragment;
import com.example.beyondto.R;
import com.example.beyondto.SettingsActivity;
import com.example.beyondto.TargetActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GameTarget_fineGioco extends Activity {
	 
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.gioco_target_fine);
			
        	Button bButton=(Button)findViewById(R.id.backButton);
        	
            bButton.setOnClickListener(new OnClickListener(){  
            	@Override  
            	public void onClick(View arg1) {  
            		Intent intentTarget = new Intent(
    						getApplicationContext(),
    						TargetActivity.class
    					);
    				startActivity(intentTarget);
    				finish();
            	}  
            }); 
         }

		// Menu
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu)
	    {
	        MenuInflater menuInflater = getMenuInflater();
	        menuInflater.inflate(R.menu.menu, menu);
	        return true;
	    }
	     
	    /**
	     * Event Handling for Individual menu item selected
	     * Identify single menu item by it's id
	     * */
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item)
	    {
		
	        switch (item.getItemId()){
	         
	        case R.id.menu_home:
	        	//Toast.makeText(MainActivity.this, "Home is Selected", Toast.LENGTH_SHORT).show();
	        	Intent intentHome = new Intent(
						getApplicationContext(),
						HomeActivity.class
					);
				startActivity(intentHome);
				finish();
	        	return true;
	 
	        case R.id.menu_map:
	            //Toast.makeText(MainActivity.this, "Map is Selected", Toast.LENGTH_SHORT).show();
	        	Intent intentMap = new Intent(
						getApplicationContext(),
						MapActivity.class
					);
				startActivity(intentMap);
				finish();
	            return true;
	 
	        case R.id.menu_target:
	            //Toast.makeText(MainActivity.this, "Target is Selected", Toast.LENGTH_SHORT).show();
	        	Intent intentTarget = new Intent(
						getApplicationContext(),
						TargetActivity.class
					);
				startActivity(intentTarget);
				finish();
	            return true;
	 
	        case R.id.menu_medal:
	            //Toast.makeText(MainActivity.this, "Medal is Selected", Toast.LENGTH_SHORT).show();
	        	Intent intentMedal = new Intent(
						getApplicationContext(),
						MedalActivity.class
					);
				startActivity(intentMedal);
				finish();
	            return true;
	 
	        case R.id.menu_news:
	            //Toast.makeText(MainActivity.this, "News is Selected", Toast.LENGTH_SHORT).show();
	        	Intent intentNews = new Intent(
						getApplicationContext(),
						NewsFragment.class
					);
				startActivity(intentNews);
				finish();
	            return true;
	 
	        case R.id.menu_settings:
	            //Toast.makeText(MainActivity.this, "Settings is Selected", Toast.LENGTH_SHORT).show();
	        	Intent intentSettings = new Intent(
						getApplicationContext(),
						SettingsActivity.class
					);
				startActivity(intentSettings);
				finish();
	            return true;
	 
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    } 

	}