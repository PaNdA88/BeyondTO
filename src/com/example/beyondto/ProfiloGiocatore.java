package com.example.beyondto;

import android.app.Activity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
 
public class ProfiloGiocatore extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.profilogiocatore);
         
        TextView txtNome = (TextView) findViewById(R.id.nome);
        Intent i = getIntent();
        // getting attached intent data
        String nome = i.getStringExtra("nome");
        // displaying selected product name
        txtNome.setText(nome);
         
    }
}