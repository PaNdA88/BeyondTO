package com.example.beyondto;

import org.jivesoftware.smack.ConnectionListener;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.beyondto.adapter.TabsPagerAdapterHome;
import com.facebook.Session;
import com.quickblox.module.chat.QBChatService;
import com.quickblox.module.users.model.QBUser;
import com.quickblox.sample.chat.core.RoomChat;
import com.quickblox.sample.chat.ui.activities.ChatActivity;
import com.quickblox.sample.chat.ui.activities.ChatLoginActivity;
import com.quickblox.sample.chat.ui.activities.RegistrationActivity;

public class HomeActivity extends Activity implements ActionBar.TabListener {

	private ConnectionListener connectionListener;
	private static final int AUTHENTICATION_REQUEST = 1;
	private ViewPager viewPager;
	private TabsPagerAdapterHome mAdapterHome;
	private ActionBar actionBar;
	// Tab titles
	private String[] tabsHome = { "Utente", "Scheda Clan", "Chat" };
	Intent intent = new Intent();
	String[] info;
	private String[] infoUser;
	String clan;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		//Facebook session
		Session session = new Session(getApplicationContext());
		Session.setActiveSession(session);

	    Connector con = new Connector();
	    
		info = con.getUserInfo(Infoton.getInstance().getUserId());
		clan = info[2];
		
		// Initialization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		actionBar.removeAllTabs();
		mAdapterHome = new TabsPagerAdapterHome(getFragmentManager());
		mAdapterHome.setInfoUser(info);
		viewPager.setAdapter(mAdapterHome);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Adding Tabs
		for (String tab_name : tabsHome) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}
	

	public void entraChat() {
		// TODO Auto-generated method stub
		QBUser qbUser = ((App) getApplication()).getQbUser();
        if (qbUser != null) {
           
        	connectionListener = new ChatConnectionListener();
            QBChatService.getInstance().addConnectionListener(connectionListener);

            if (clan.equals("Alchimisti")) {	            	
            	Bundle bundle = createChatBundle("provaAlchimisti", false); 
            	ChatActivity.start(this, bundle); 
            }else {
	            Bundle bundle = createChatBundle("provaRinnegati", false);
	            ChatActivity.start(this, bundle); 
            }
            
                       
        	
        } else {	            
            Intent intent = new Intent(getApplicationContext(), ChatLoginActivity.class);
            startActivityForResult(intent, AUTHENTICATION_REQUEST); 	            	            	            
        }

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.menu_home:

			intent.setClass(getApplicationContext(), HomeActivity.class);
			startActivity(intent);

			return true;

		case R.id.menu_map:

			intent.setClass(getApplicationContext(), MapActivity.class);
			startActivity(intent);
			return true;

		case R.id.menu_medal:
			intent.setClass(getApplicationContext(), MedalActivity.class);
			startActivity(intent);

			return true;

		case R.id.menu_news:

			intent.setClass(getApplicationContext(), NewsActivity.class);
			startActivity(intent);
			return true;

		case R.id.menu_settings:

			intent.setClass(getApplicationContext(), SettingsActivity.class);

			startActivity(intent);
			return true;
			
			
		default:
			return super.onOptionsItemSelected(item);

		}
		
		
		
	}
	
	
	
	
	 @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (resultCode == RESULT_OK) {

	            connectionListener = new ChatConnectionListener();
	            QBChatService.getInstance().addConnectionListener(connectionListener);

	            if (clan.equals("Alchimisti")) {	            	
	            	Bundle bundle = createChatBundle("provaAlchimisti", false); 
	            	ChatActivity.start(this, bundle); 
	            }else {
		            Bundle bundle = createChatBundle("provaRinnegati", false);
		            ChatActivity.start(this, bundle); 
	            }
	            
	        } else {
	        	
	            Intent intent2 = new Intent(getApplicationContext(), RegistrationActivity.class);
	            startActivityForResult(intent2, AUTHENTICATION_REQUEST);
	        	
	            //Intent passaARegistrazione= new Intent(MainActivity.this, RegistrationActivity.class);			
	            //startActivity(passaARegistrazione);
	            

	        }
	    }
	
	
	private Bundle createChatBundle(String roomName, boolean createChat) {
     Bundle bundle = new Bundle();
     bundle.putSerializable(ChatActivity.EXTRA_MODE, ChatActivity.Mode.GROUP);
     bundle.putString(RoomChat.EXTRA_ROOM_NAME, roomName);
     if (createChat) {
         bundle.putSerializable(RoomChat.EXTRA_ROOM_ACTION, RoomChat.RoomAction.CREATE);
     } else {
         bundle.putSerializable(RoomChat.EXTRA_ROOM_ACTION, RoomChat.RoomAction.JOIN);
     }
     return bundle;
 }
	
	
	public class ChatConnectionListener implements ConnectionListener {

     @Override
     public void connectionClosed() {
         showToast("connectionClosed");
     }

     @Override
     public void connectionClosedOnError(Exception e) {
         showToast("connectionClosed on error" + e.getLocalizedMessage());
     }

     @Override
     public void reconnectingIn(int i) {

     }

     @Override
     public void reconnectionSuccessful() {

     }

     @Override
     public void reconnectionFailed(Exception e) {

     }
 }
	
	
	private void showToast(final String msg) {
     runOnUiThread(new Runnable() {
         @Override
         public void run() {
             Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
         }
     });
	}
}