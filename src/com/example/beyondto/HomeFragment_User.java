package com.example.beyondto;

import com.example.beyondto.R;
import com.facebook.Session;

import android.os.Bundle;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment_User extends Fragment {

	//variabili - cambiare con valori da db 
	int attacchi = 40;
	int difese = 25;
	int puntiUt = 3500;
		
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    		//Facebook session
    		Session session = new Session((HomeActivity)getActivity());
    		Session.setActiveSession(session);

        	View rootView = inflater.inflate(R.layout.fragment_home_user, container, false); 
        	
        	//------------------- dynamic strings ----------------------//
      		String nome = "Andrea";
      		TextView textUser = (TextView) rootView.findViewById(R.id.nomeUtente);
      		Resources res1 =((HomeActivity) getActivity()).getResources();
      		String user = String.format(res1.getString(R.string.nomeUtente),nome);
      		textUser.setText(Html.fromHtml((String)  user )); 
      		
      		String clan = "Rinnegati";
      		TextView textClan = (TextView) rootView.findViewById(R.id.clanAppartenenza);
      		Resources res2 =((HomeActivity) getActivity()).getResources();
      		String cl = String.format(res2.getString(R.string.clanAppartenenza),clan);
      		textClan.setText(Html.fromHtml((String)  cl ));
      		
      		String punti = "3500";
      		TextView textPoints = (TextView) rootView.findViewById(R.id.puntiUtente);
      		Resources res3 =((HomeActivity) getActivity()).getResources();
      		String pt = String.format(res3.getString(R.string.puntiUtente),punti);
      		textPoints.setText(Html.fromHtml((String)  pt )); 
      		
      		String edifici = "14";
      		TextView textEdifici = (TextView) rootView.findViewById(R.id.edificiConquistati);
      		Resources res4 =((HomeActivity) getActivity()).getResources();
      		String edi = String.format(res4.getString(R.string.edificiConquistati),edifici);
      		textEdifici.setText(Html.fromHtml((String)  edi )); 
      		
      		
      		
            
      		if (attacchi > 0){
      			//Context ctx = getActivity();
      			//ImageView imageView = new ImageView(ctx);
      			ImageView image = (ImageView) rootView.findViewById(R.id.med1);
       			image.setImageResource(R.drawable.medaglia1);
      			//linLayout1.addView(imageView);
      			image.setOnClickListener(new OnClickListener(){
      				public void onClick(View v) {
      					Toast.makeText(getActivity(), "Medaglia DUELLANTE", Toast.LENGTH_SHORT).show();
      				}
      			});
      		}
      		
      		if (difese > 0){
      			ImageView image = (ImageView) rootView.findViewById(R.id.med3);
       			image.setImageResource(R.drawable.medaglia3);
      			image.setOnClickListener(new OnClickListener(){
      				public void onClick(View v) {
      					Toast.makeText(getActivity(), "Medaglia APPRENDISTA", Toast.LENGTH_SHORT).show();
      				}
      			});
      		}
      		
      		if (attacchi > 29){
      			ImageView image = (ImageView) rootView.findViewById(R.id.med4);
       			
      			
      			image.setImageResource(R.drawable.medaglia4);
      			
      			image.setOnClickListener(new OnClickListener(){
      				public void onClick(View v) {
      					Toast.makeText(getActivity(), "Medaglia CONQUISTATORE", Toast.LENGTH_SHORT).show();
      				}
      			});
      		}
      		
      		if (difese > 29){
      			ImageView image = (ImageView) rootView.findViewById(R.id.med6);
      			
      			image.setImageResource(R.drawable.medaglia6);
      			
      			image.setOnClickListener(new OnClickListener(){
          			public void onClick(View v) {
          				Toast.makeText(getActivity(), "Medaglia DIFENSORE", Toast.LENGTH_SHORT).show();
          		    }
          		});
          	}
          		
          	if (puntiUt > 999){

          		ImageView image = (ImageView) rootView.findViewById(R.id.med5);
      			
      			image.setImageResource(R.drawable.medaglia5);
      			
      			image.setOnClickListener(new OnClickListener(){
          			public void onClick(View v) {
          				Toast.makeText(getActivity(), "Medaglia LEADER", Toast.LENGTH_SHORT).show();
          		    }
          		});
          	}
          		
          	if (puntiUt > 9999){
          		ImageView image = (ImageView) rootView.findViewById(R.id.med2);
      			image.setImageResource(R.drawable.medaglia2);
      			
      			image.setOnClickListener(new OnClickListener(){
          			public void onClick(View v) {
          				Toast.makeText(getActivity(), "Medaglia MASTER", Toast.LENGTH_SHORT).show();
          		    }
          		});
          	}
      		
        return rootView;
    }
}
