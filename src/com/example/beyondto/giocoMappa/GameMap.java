package com.example.beyondto.giocoMappa;

import com.example.beyondto.Connector;
import com.example.beyondto.HomeActivity;
import com.example.beyondto.MapActivity;
import com.example.beyondto.MedalActivity;
import com.example.beyondto.NewsFragment;
import com.example.beyondto.R;
import com.example.beyondto.SettingsActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GameMap extends Activity {
	
	
	private String idUser, action, namePlace, userClan, statePlace, firstAttack;
	private int idPlace;
	
	 
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.gioco_map);
	
			/*prendo extra data da intent*/
			idPlace = -1;
			Intent i = getIntent();
			idUser = i.getStringExtra("idUtente");
			namePlace = i.getStringExtra("nomeLuogo");
			action = i.getStringExtra("azione");
			userClan = i.getStringExtra("clanUtente");
			statePlace = i.getStringExtra("statoLuogo");
	
        	Button sButton=(Button)findViewById(R.id.startButton);
            Button qButton=(Button)findViewById(R.id.quitButton);
        	
            sButton.setOnClickListener(new OnClickListener(){  
            	@Override  
            	public void onClick(View arg1) {  
            		
            		Connector con = new Connector();
            		int infoId[] = con.startNewAttack(idUser,namePlace, action);
            		
            		Intent intentGame1 = new Intent(
    						getApplicationContext(),
    						GameMap_domanda1.class
    					);
            		intentGame1.putExtra("idUtente", idUser);
            		intentGame1.putExtra("nomeLuogo", namePlace);
            		intentGame1.putExtra("azione", action);
            		intentGame1.putExtra("clanUtente", userClan);
            		intentGame1.putExtra("idLuogo", infoId[0]);
            		intentGame1.putExtra("idScontro", infoId[1]);
    				startActivity(intentGame1);
    				finish();
            	}  
            });  
            
            qButton.setOnClickListener(new OnClickListener(){  
            	@Override  
            	public void onClick(View arg2) {  
            		Intent intentMap = new Intent(
    						getApplicationContext(),
    						MapActivity.class
    					);
    				startActivity(intentMap);
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
	        	Intent intentHome = new Intent(
						getApplicationContext(),
						HomeActivity.class
					);
				startActivity(intentHome);
				finish();
	        	return true;
	 
	        case R.id.menu_map:
	        	Intent intentMap = new Intent(
						getApplicationContext(),
						MapActivity.class
					);
				startActivity(intentMap);
				finish();
	            return true;
	 
	        case R.id.menu_medal:
	        	Intent intentMedal = new Intent(
						getApplicationContext(),
						MedalActivity.class
					);
				startActivity(intentMedal);
				finish();
	            return true;
	 
	        case R.id.menu_news:
	        	Intent intentNews = new Intent(
						getApplicationContext(),
						NewsFragment.class
					);
				startActivity(intentNews);
				finish();
	            return true;
	 
	        case R.id.menu_settings:
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