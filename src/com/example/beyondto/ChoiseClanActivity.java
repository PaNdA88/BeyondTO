package com.example.beyondto;

import com.facebook.widget.LoginButton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class ChoiseClanActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.choise_clan_activity, container,
				false);
		Button alchimisti = (Button) view.findViewById(R.id.alchimisti);
		Button rinnegati = (Button) view.findViewById(R.id.rinnegati);

		final Bundle bundle = getIntent().getExtras();
		if (bundle.getString("tokenUser") != null) {

			alchimisti.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					Connector con = new Connector();
					String message = con.setClan("Alchimisti",
							(bundle.getString("tokenUser").toString()));
					if (message.equals("inserito clan")) {
						goToTorinoHome();
					} else {
						Toast toast = Toast.makeText(getApplicationContext(),
								"Utente non presente nel database!",
								Toast.LENGTH_SHORT);
						toast.show();
					}
					return false;
				}
			});

			rinnegati.setOnTouchListener(new OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					Connector con = new Connector();
					String message = con.setClan("Rinnegati",
							(bundle.getString("tokenUser").toString()));
					if (message.equals("inserito clan")) {
						goToTorinoHome();
					} else {
						Toast toast = Toast.makeText(getApplicationContext(),
								"Utente non presente nel database!",
								Toast.LENGTH_SHORT);
						toast.show();
					}
					return false;
				}
			});
		}

		return view;
	}

	public void goToTorinoHome() {
		Intent i = new Intent(ChoiseClanActivity.this, HomeActivity.class);
		startActivity(i);
		finish();

	}

}
