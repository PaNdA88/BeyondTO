package com.example.beyondto;

import java.util.HashMap;
import java.util.Map;

import com.example.beyondto.giocoMappa.GameMap;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerDialog {
	
	private String title, view_text, nome, clan, punti, edifici;
	private Context context;
	private Activity activity;
	private Button closeButton;
	Intent intent = new Intent();
	
	private Map<String, String> nomiFileClan = new HashMap<String, String>();

	public PlayerDialog() {
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	public String getView_text() {
		return view_text;
	}

	public void setView_text(String view_text) {
		this.view_text = view_text;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClan() {
		return clan;
	}

	public void setClan(String clan) {
		this.clan = clan;
	}

	public String getPunti() {
		return punti;
	}

	public void setPunti(String punti) {
		this.punti = punti;
	}

	public String getEdifici() {
		return edifici;
	}

	public void setEdifici(String edifici) {
		this.edifici = edifici;
	}
	
	public void showPlayerDialog() {

		final Dialog dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent); 
		dialog.setContentView(R.layout.dialog_player);
		
		nomiFileClan.put("Alchimisti", "alchimisti");
		nomiFileClan.put("Rinnegati", "rinnegati");
		//dialog.setTitle(title);
				
		// ------------------- dynamic strings ----------------------//
		
		/*Intent i = getIntent();
		String nome = i.getStringExtra("nome");
		String punti = i.getStringExtra("punti");
		TextView txtName = (TextView) findViewById(R.id.nome);
		txtName.setText(nome);
		TextView txtPoints = (TextView) findViewById(R.id.punti);
		txtPoints.setText("Punteggio: " + punti);
		String edi = "Edifici Conquistati: 23";
		TextView txtEdi = (TextView) findViewById(R.id.edifici);
		txtEdi.setText(edi);*/
		
		TextView titledialog = (TextView) dialog.findViewById(R.id.titleDialog);
		title = "Nome Utente";
		titledialog.setText(title);
		
		String clan = "Alchimisti";

		ImageView image = (ImageView) dialog.findViewById(R.id.imageClan);
		String uri = "drawable/" + nomiFileClan.get(clan);
		int imageResource = context.getResources().getIdentifier(uri, null,
				context.getPackageName());
		Drawable img = context.getResources().getDrawable(imageResource);
		image.setImageDrawable(img);
		
		TextView textClan = (TextView) dialog.findViewById(R.id.textClan);
		textClan.setText("Clan " + clan);

		TextView textPunti = (TextView) dialog.findViewById(R.id.textPunti);
		punti = "Punti: 1234";
		textPunti.setText(punti);

		TextView textEdifici = (TextView) dialog.findViewById(R.id.textEdifici);
		edifici = "Edifici conquistati: 10";
		textEdifici.setText(edifici);
		
		closeButton = (Button) dialog.findViewById(R.id.closeButton);
		closeButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.hide();
				}
			});

		dialog.show();
		
		}

	public void setActivity(MedalFragment_Classifica medalFragment_Classifica) {
		// TODO Auto-generated method stub
		this.activity = activity;
	}

}

