package com.example.beyondto.giocoMappa;

import com.example.beyondto.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RisposteDialog {
	
	private Context context;
	private String title, risp1data, risp1corretta, risp2data, risp2corretta, risp3data, risp3corretta;
	private Activity activity;
	private Button closeButton;
	Intent intent = new Intent();

	public RisposteDialog() {
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public Context getContext() {
		return context;
	}
	
	public void setContext(Context context) {
		this.context = context;
	}	

	public Activity getActivity() {
		return activity;
	}
	
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String getRisp1data() {
		return risp1data;
	}

	public void setRisp1data(String risp1data) {
		this.risp1data = risp1data;
	}

	public String getRisp2data() {
		return risp2data;
	}

	public void setRisp2data(String risp2data) {
		this.risp2data = risp2data;
	}

	public String getRisp3data() {
		return risp3data;
	}

	public void setRisp3data(String risp3data) {
		this.risp3data = risp3data;
	}

	public String getRisp1corretta() {
		return risp1corretta;
	}

	public void setRisp1corretta(String risp1corretta) {
		this.risp1corretta = risp1corretta;
	}

	public String getRisp2corretta() {
		return risp2corretta;
	}

	public void setRisp2corretta(String risp2corretta) {
		this.risp2corretta = risp2corretta;
	}

	public String getRisp3corretta() {
		return risp3corretta;
	}

	public void setRisp3corretta(String risp3corretta) {
		this.risp3corretta = risp3corretta;
	}
		
	public void showRisposteDialog() {

		final Dialog dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent); 
		dialog.setContentView(R.layout.dialog_risposte);

		TextView titledialog = (TextView) dialog.findViewById(R.id.titleDialog);
		titledialog.setText("Risposte corrette");
		
		RelativeLayout risp1 = (RelativeLayout) dialog.findViewById(R.id.risp1);
		TextView r1 = (TextView) new TextView(context);
				
		if (risp1corretta.equals(risp1data)){
			r1.setTextColor(Color.GREEN);
			r1.setText("Risposta 1 CORRETTA: " + risp1data);
			risp1.addView(r1);
			}
		else {
			r1.setTextColor(Color.RED);
			r1.setText("Risposta 1 ERRATA: " + risp1data + ".\nRisposta CORRETTA: " + risp1corretta);
			risp1.addView(r1);
			}
		
		RelativeLayout risp2 = (RelativeLayout) dialog.findViewById(R.id.risp2);
		TextView r2 = (TextView) new TextView(context);
		
		if (risp2corretta.equals(risp2data)){
			r2.setTextColor(Color.GREEN);
			r2.setText("Risposta 2 CORRETTA: " + risp2data);
			risp2.addView(r2);
			}
		else {
			r2.setTextColor(Color.RED);
			r2.setText("Risposta 2 ERRATA: " + risp2data + ".\nRisposta CORRETTA: " + risp2corretta);
			risp2.addView(r2);
			}
		
		RelativeLayout risp3 = (RelativeLayout) dialog.findViewById(R.id.risp3);
		TextView r3 = (TextView) new TextView(context);
		r3.setText("Risposta 3: " + risp3data);
		
		if (risp3corretta.equals(risp3data)){
			r3.setTextColor(Color.GREEN);
			r3.setText("Risposta 3 CORRETTA: " + risp3data);
			risp3.addView(r3);
			}
		else {
			r3.setTextColor(Color.RED);
			r3.setText("Risposta 3 ERRATA: " + risp3data + ".\nRisposta CORRETTA: " + risp3corretta);
			risp3.addView(r3);
			}
		
		closeButton = (Button) dialog.findViewById(R.id.closeButton);
		closeButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.hide();
				}
			});

		dialog.show();
		
		}

}

