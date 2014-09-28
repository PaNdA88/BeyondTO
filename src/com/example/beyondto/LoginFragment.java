package com.example.beyondto;

import java.util.Arrays;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
	private Session session;
	private boolean logSession, newUser;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		newUser = false;
		logSession = false;
		Session session = new Session(
				((LoginActivity) getActivity()).getApplicationContext());
		Session.setActiveSession(session);

		if (session != null
				&& (session.getState()
						.equals(SessionState.CREATED_TOKEN_LOADED)
						|| session.getState().equals(SessionState.OPENING) || session
						.getState().equals(SessionState.OPENED_TOKEN_UPDATED))) {

			logSession = true;
		} else {
			logSession = false;
		}

		uiHelper = new UiLifecycleHelper(getActivity(), statusCallback);
		uiHelper.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.login_fragment, container, false);

		if (logSession) {

			String testo1 = "Attendere prego";
			TextView textTitle = (TextView) view.findViewById(R.id.titoloLogin);
			Resources res1 = getActivity().getResources();
			String tit = String.format(res1.getString(R.string.titoloLogin),
					testo1);
			textTitle.setText(Html.fromHtml((String) tit));

			ProgressBar p = new ProgressBar((LoginActivity) getActivity(),
					null, android.R.attr.progressBarStyle);
			p.getIndeterminateDrawable().setColorFilter(
					getResources().getColor(R.color.accent_orange),
					android.graphics.PorterDuff.Mode.SRC_IN);
			p.setVisibility(View.VISIBLE);
			RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
					100, 100);
			params.addRule(RelativeLayout.CENTER_IN_PARENT);
			((ViewGroup) view).addView(p, params);

			String testo2 = "Stiamo effettuando la connessione a Facebook";
			TextView textSubtitle = (TextView) view
					.findViewById(R.id.sottotitolo);
			Resources res2 = getActivity().getResources();
			String sott = String.format(res2.getString(R.string.sottotitolo),
					testo2);
			textSubtitle.setText(Html.fromHtml((String) sott));

		}

		else {

			String testo1 = "ACCEDI A FACEBOOK!";
			TextView textTitle = (TextView) view.findViewById(R.id.titoloLogin);
			Resources res1 = getActivity().getResources();
			String tit = String.format(res1.getString(R.string.titoloLogin),
					testo1);
			textTitle.setText(Html.fromHtml((String) tit));

			String testo2 = "Per giocare con BeyondTO è necessario effettuare l'accesso a Facebook";
			TextView textSubtitle = (TextView) view
					.findViewById(R.id.sottotitolo);
			Resources res2 = getActivity().getResources();
			String sott = String.format(res2.getString(R.string.sottotitolo),
					testo2);
			textSubtitle.setText(Html.fromHtml((String) sott));
		}

		LoginButton authButton = (LoginButton) view
				.findViewById(R.id.authButton);
		// richiedo i permessi
		authButton.setReadPermissions(Arrays.asList("public_profile", "email"));
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
	public void getUserToken(final Session session) {
		USER_TOKEN = session.getAccessToken().toString();
		EXPIRATION = (session.getExpirationDate()).toString();

		Request request = Request.newMeRequest(session,
				new Request.GraphUserCallback() {
					private boolean newUser;

					@Override
					public void onCompleted(GraphUser user, Response response) {
						if (session == Session.getActiveSession()) {
							if (user != null) {

								String USER_ID = user.getId();
								Log.d("USER ID", USER_ID);
								String USER_NAME = user.getName();
								Log.d("USER NAME", USER_NAME);
								String USER_EMAIL = user.asMap().get("email")
										.toString();
								Log.d("USER EMAIL", USER_EMAIL);
								Connector con = new Connector();
								String result = con.doLoginFromFacebook(
										USER_ID, USER_TOKEN, EXPIRATION,
										USER_EMAIL, USER_NAME);
								Log.d("RISULTATO:", result);
								
								Infoton infoton = Infoton.getInstance();
								infoton.setUserId(user.getId());
								
								if (result.equals("0")) {
									newUser = true;
									// goToChoiseClan();
								}
								/*
								 * else{ goToTorinoHome(); }
								 */
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

	public void goToTorinoHome() {
		Intent i = new Intent((LoginActivity) getActivity(), HomeActivity.class);
		getActivity().startActivity(i);
	}

	public void goToChoiseClan() {
		Intent i = new Intent((LoginActivity) getActivity(),
				ChoiseClanActivity.class);
		i.putExtra("tokenUser", USER_TOKEN);
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