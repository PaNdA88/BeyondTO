package com.example.beyondto;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment_User extends Fragment {

	String info[];
		
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	
        	View rootView = inflater.inflate(R.layout.fragment_home_user, container, false); 
        	
        	//------------------- dynamic strings ----------------------//
        	String nome = info[1];
    		TextView textUser = (TextView) rootView.findViewById(R.id.nomeUtente);
    		Resources res1 = ((HomeActivity) getActivity()).getResources();
    		String user = String.format(res1.getString(R.string.nomeUtente), nome);
    		textUser.setText(Html.fromHtml((String) user));

    		String clan = info[2];
    		TextView textClan = (TextView) rootView
    				.findViewById(R.id.clanAppartenenza);
    		Resources res2 = ((HomeActivity) getActivity()).getResources();
    		String cl = String.format(res2.getString(R.string.clanAppartenenza),
    				clan);
    		textClan.setText(Html.fromHtml((String) cl));

    		String punti = info[0];
    		TextView textPoints = (TextView) rootView
    				.findViewById(R.id.puntiUtente);
    		Resources res3 = ((HomeActivity) getActivity()).getResources();
    		String pt = String.format(res3.getString(R.string.puntiUtente), punti);
    		textPoints.setText(Html.fromHtml((String) pt));

    		String edifici = info[4];
    		TextView textEdifici = (TextView) rootView
    				.findViewById(R.id.edificiConquistati);
    		Resources res4 = ((HomeActivity) getActivity()).getResources();
    		String edi = String.format(res4.getString(R.string.edificiConquistati),
    				edifici);
    		textEdifici.setText(Html.fromHtml((String) edi));

      	
      		if (Integer.parseInt(info[5]) > 0){
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
      		
      		if (Integer.parseInt(info[6]) > 0){
      			ImageView image = (ImageView) rootView.findViewById(R.id.med3);
       			image.setImageResource(R.drawable.medaglia3);
      			image.setOnClickListener(new OnClickListener(){
      				public void onClick(View v) {
      					Toast.makeText(getActivity(), "Medaglia APPRENDISTA", Toast.LENGTH_SHORT).show();
      				}
      			});
      		}
      		
      		if (Integer.parseInt(info[5])  > 29){
      			ImageView image = (ImageView) rootView.findViewById(R.id.med4);
       			
      			
      			image.setImageResource(R.drawable.medaglia4);
      			
      			image.setOnClickListener(new OnClickListener(){
      				public void onClick(View v) {
      					Toast.makeText(getActivity(), "Medaglia CONQUISTATORE", Toast.LENGTH_SHORT).show();
      				}
      			});
      		}
      		
      		if (Integer.parseInt(info[6])  > 29){
      			ImageView image = (ImageView) rootView.findViewById(R.id.med6);
      			
      			image.setImageResource(R.drawable.medaglia6);
      			
      			image.setOnClickListener(new OnClickListener(){
          			public void onClick(View v) {
          				Toast.makeText(getActivity(), "Medaglia DIFENSORE", Toast.LENGTH_SHORT).show();
          		    }
          		});
          	}
          		
          	if (Integer.parseInt(info[0])  > 999){

          		ImageView image = (ImageView) rootView.findViewById(R.id.med5);
      			
      			image.setImageResource(R.drawable.medaglia5);
      			
      			image.setOnClickListener(new OnClickListener(){
          			public void onClick(View v) {
          				Toast.makeText(getActivity(), "Medaglia LEADER", Toast.LENGTH_SHORT).show();
          		    }
          		});
          	}
          		
          	if (Integer.parseInt(info[0])  > 9999){
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
	
	public void setInfoUser(String[] info) {
		this.info = info;	
	}

}
