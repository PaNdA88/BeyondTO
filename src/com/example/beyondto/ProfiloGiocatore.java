package com.example.beyondto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ProfiloGiocatore extends Activity {
	
	Intent intent = new Intent();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profilogiocatore);
		
		Intent i = getIntent();
		String nome = i.getStringExtra("nome");
		String punti = i.getStringExtra("punti");
		TextView txtName = (TextView) findViewById(R.id.nome);
		txtName.setText(nome);
		TextView txtPoints = (TextView) findViewById(R.id.punti);
		txtPoints.setText("Punteggio: " + punti);
		String edi = "Edifici Conquistati: 23";
		TextView txtEdi = (TextView) findViewById(R.id.edifici);
		txtEdi.setText(edi);
		//ImageView img = i.getStringExtra("clan");
	}
	
}
