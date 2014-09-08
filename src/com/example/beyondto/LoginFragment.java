package com.example.beyondto;

import java.util.Arrays;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

public class LoginFragment extends Fragment {
	
	private static final String TAG = "MainFragment";
	private UiLifecycleHelper uiHelper;
	private String USER_TOKEN, EXPIRATION;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		uiHelper = new UiLifecycleHelper(getActivity(), statusCallback);
		uiHelper.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.login_fragment, container, false);
		LoginButton authButton = (LoginButton) view
				.findViewById(R.id.authButton);
		// richiedo i permessi 
		authButton.setReadPermissions(Arrays.asList("basic_info","email"));
		authButton.setFragment(this);
		return view;
	}

	private Session.StatusCallback statusCallback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			onSessionStateChange(session, state, exception);
		}
	};

	private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {
		if (state.isOpened()) {
			Log.i(TAG, "Logged in...");
			getUserToken(session);
			Log.d("USER TOKEN:", USER_TOKEN);
			Log.d("TOKEN EXPIRE:", EXPIRATION);
			
		} else if (state.isClosed()) {
			Log.i(TAG, "Logged out...");
		}
	}

	// mi ricavo il token creato da facebook
	public void getUserToken(final Session session){  
		USER_TOKEN = session.getAccessToken().toString(); 
		EXPIRATION = (session.getExpirationDate()).toString(); 
		
		Request request = Request.newMeRequest(session, 
	            new Request.GraphUserCallback() {
	        @Override
	        public void onCompleted(GraphUser user, Response response) {
	            if (session == Session.getActiveSession()) {
	                if (user != null) {
	                	
	                    String USER_ID = user.getId();
	                    Log.d("USER ID", USER_ID);
	                    String USER_NAME = user.getName();
	                    Log.d("USER NAME",USER_NAME);
	                    String USER_EMAIL = user.asMap().get("email").toString();
	                    Log.d("USER EMAIL",USER_EMAIL);
	            		Connector con = new Connector();
	            		String result = con.doLoginFromFacebook(USER_ID, USER_TOKEN, EXPIRATION,USER_EMAIL, USER_NAME );
	            		Log.d("RISULTATO:",result);
	            			goToTorinoHome();            		
	                }
	            }
	            if (response.getError() != null) {
	            	
	            	Log.d("ERROR:", response.getError().toString());
	            }
	        }
	    });
	    request.executeAsync();		
	} 
	
	public void goToTorinoHome(){
		Intent i = new Intent((LoginActivity)getActivity(), HomeActivity.class);
		getActivity().startActivity(i); 
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onResume() {
		super.onResume();
		// For scenarios where the main activity is launched and user
		// session is not null, the session state change notification
		// may not be triggered. Trigger it if it's open/closed.
		Session session = Session.getActiveSession();
		if (session != null && (session.isOpened() || session.isClosed())) {
			onSessionStateChange(session, session.getState(), null);
		}
		uiHelper.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		uiHelper.onSaveInstanceState(outState);
	}

}
