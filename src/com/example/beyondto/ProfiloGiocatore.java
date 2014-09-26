package com.example.beyondto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ProfiloGiocatore extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profilogiocatore);
		Intent i = getIntent();
		String s = i.getStringExtra("nome");
	}
}
