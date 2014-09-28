package com.example.beyondto;

import com.example.beyondto.R;
import com.facebook.Session;

import android.os.Bundle;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
 
public class MedalFragment_Medaglie extends Fragment {

	String info[];
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	//Facebook session
		Session session = new Session((MedalActivity)getActivity());
		Session.setActiveSession(session);
		
		Connector con = new Connector(((MedalActivity)getActivity()).getApplicationContext());
		info = con.getUserInfo(session.getAccessToken());
 
        View rootView = inflater.inflate(R.layout.fragment_medal_medaglie, container, false);
      
        LinearLayout linLayout1 = (LinearLayout) rootView.findViewById(R.id.linLayout1);
              
        addMedal1(linLayout1);
        addMedal2(linLayout1);
        addMedal3(linLayout1);
        addMedal4(linLayout1);
        addMedal5(linLayout1);
        addMedal6(linLayout1);
        
        return rootView;
    }
    
//    METODI PER L'ASSEGNAZIONE DELLE MEDAGLIE

	private void addMedal1(LinearLayout linLayout1) {

    	Context ctx = getActivity();
		ImageView imageView = new ImageView(ctx);
		TextView nomeMed = new TextView(ctx);
		TextView descMed = new TextView(ctx);
		ImageView gr = new ImageView(ctx);
        
		nomeMed.setText("DUELLANTE");
		nomeMed.setGravity(Gravity.CENTER);
		descMed.setText("Hai sferrato il tuo primo attacco");
		descMed.setGravity(Gravity.CENTER);
		
		if (Integer.parseInt(info[6]) > 0) {
	    imageView.setImageResource(R.drawable.medaglia1);
		nomeMed.setTextColor(Color.parseColor("#FFFFFF"));
		descMed.setTextColor(Color.parseColor("#FFFFFF"));
		}
		else {
		imageView.setImageResource(R.drawable.medaglia1bn);
		nomeMed.setTextColor(Color.parseColor("#999999"));
		descMed.setTextColor(Color.parseColor("#999999"));
		}
		
	    linLayout1.addView(imageView);
	    linLayout1.addView(nomeMed);
	    linLayout1.addView(descMed);

	    gr.setImageResource(R.drawable.gradiente);
	    linLayout1.addView(gr);
	}
 
	private void addMedal2(LinearLayout linLayout1) {

    	Context ctx = getActivity();
		ImageView imageView = new ImageView(ctx);
		TextView nomeMed = new TextView(ctx);
		TextView descMed = new TextView(ctx);
		ImageView gr = new ImageView(ctx);
		
		nomeMed.setText("APPRENDISTA");
		nomeMed.setGravity(Gravity.CENTER);
		descMed.setText("Hai difeso un edificio per la prima volta");
		descMed.setGravity(Gravity.CENTER);
		
		if (Integer.parseInt(info[7]) > 0) {
	    imageView.setImageResource(R.drawable.medaglia3);
		nomeMed.setTextColor(Color.parseColor("#FFFFFF"));
		descMed.setTextColor(Color.parseColor("#FFFFFF"));
		}
		else {
		imageView.setImageResource(R.drawable.medaglia3bn);
		nomeMed.setTextColor(Color.parseColor("#999999"));
		descMed.setTextColor(Color.parseColor("#999999"));
		}
		
	    linLayout1.addView(imageView);
	    linLayout1.addView(nomeMed);
	    linLayout1.addView(descMed);

	    gr.setImageResource(R.drawable.gradiente);
	    linLayout1.addView(gr);
	}
	
	private void addMedal3(LinearLayout linLayout1) {

    	Context ctx = getActivity();
		ImageView imageView = new ImageView(ctx);
		TextView nomeMed = new TextView(ctx);
		TextView descMed = new TextView(ctx);
		ImageView gr = new ImageView(ctx);
		
		nomeMed.setText("CONQUISTATORE");
		nomeMed.setGravity(Gravity.CENTER);
		descMed.setText("Hai attaccato 30 luoghi");
		descMed.setGravity(Gravity.CENTER);
		
		if (Integer.parseInt(info[6]) > 29) {
	    imageView.setImageResource(R.drawable.medaglia4);
		nomeMed.setTextColor(Color.parseColor("#FFFFFF"));
		descMed.setTextColor(Color.parseColor("#FFFFFF"));
		}
		else {
		imageView.setImageResource(R.drawable.medaglia4bn);
		nomeMed.setTextColor(Color.parseColor("#999999"));
		descMed.setTextColor(Color.parseColor("#999999"));
		}
		
	    linLayout1.addView(imageView);
	    linLayout1.addView(nomeMed);
	    linLayout1.addView(descMed);
	    
	    gr.setImageResource(R.drawable.gradiente);
	    linLayout1.addView(gr);
	}
 
	private void addMedal4(LinearLayout linLayout1) {

    	Context ctx = getActivity();
		ImageView imageView = new ImageView(ctx);
		TextView nomeMed = new TextView(ctx);
		TextView descMed = new TextView(ctx);
		ImageView gr = new ImageView(ctx);
		
		nomeMed.setText("DIFENSORE");
		nomeMed.setGravity(Gravity.CENTER);
		descMed.setText("Hai difeso 30 edifici");
		descMed.setGravity(Gravity.CENTER);
		
		if (Integer.parseInt(info[7]) > 29) {
	    imageView.setImageResource(R.drawable.medaglia6);
		nomeMed.setTextColor(Color.parseColor("#FFFFFF"));
		descMed.setTextColor(Color.parseColor("#FFFFFF"));
		}
		else {
		imageView.setImageResource(R.drawable.medaglia6bn);
		nomeMed.setTextColor(Color.parseColor("#999999"));
		descMed.setTextColor(Color.parseColor("#999999"));
		}
		
	    linLayout1.addView(imageView);
	    linLayout1.addView(nomeMed);
	    linLayout1.addView(descMed);

	    gr.setImageResource(R.drawable.gradiente);
	    linLayout1.addView(gr);
	}

	private void addMedal5(LinearLayout linLayout1) {

    	Context ctx = getActivity();
		ImageView imageView = new ImageView(ctx);
		TextView nomeMed = new TextView(ctx);
		TextView descMed = new TextView(ctx);
		ImageView gr = new ImageView(ctx);
		
		nomeMed.setText("LEADER");
		nomeMed.setGravity(Gravity.CENTER);
		descMed.setText("Hai totalizzato 1000 punti");
		descMed.setGravity(Gravity.CENTER);
		
		if (Integer.parseInt(info[1]) > 999) {
	    imageView.setImageResource(R.drawable.medaglia5);
		nomeMed.setTextColor(Color.parseColor("#FFFFFF"));
		descMed.setTextColor(Color.parseColor("#FFFFFF"));
		}
		else {
		imageView.setImageResource(R.drawable.medaglia5bn);
		nomeMed.setTextColor(Color.parseColor("#999999"));
		descMed.setTextColor(Color.parseColor("#999999"));
		}
		
	    linLayout1.addView(imageView);
	    linLayout1.addView(nomeMed);
	    linLayout1.addView(descMed);

	    gr.setImageResource(R.drawable.gradiente);
	    linLayout1.addView(gr);
	}
 
	private void addMedal6(LinearLayout linLayout1) {

    	Context ctx = getActivity();
		ImageView imageView = new ImageView(ctx);
		TextView nomeMed = new TextView(ctx);
		TextView descMed = new TextView(ctx);
		ImageView gr = new ImageView(ctx);
		
		nomeMed.setText("MASTER");
		nomeMed.setGravity(Gravity.CENTER);
		descMed.setText("HAI TOTALIZZATO 10000 PUNTI");
		descMed.setGravity(Gravity.CENTER);
		
		if (Integer.parseInt(info[1]) > 9999) {
	    imageView.setImageResource(R.drawable.medaglia2);
		nomeMed.setTextColor(Color.parseColor("#FFFFFF"));
		descMed.setTextColor(Color.parseColor("#FFFFFF"));
		}
		else {
		imageView.setImageResource(R.drawable.medaglia2bn);
		nomeMed.setTextColor(Color.parseColor("#999999"));
		descMed.setTextColor(Color.parseColor("#999999"));
		}
		
	    linLayout1.addView(imageView);
	    linLayout1.addView(nomeMed);
	    linLayout1.addView(descMed);

	}
}