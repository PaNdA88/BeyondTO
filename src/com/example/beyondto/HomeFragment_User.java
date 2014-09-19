package com.example.beyondto;

import com.example.beyondto.R;
import android.os.Bundle;
import android.app.Fragment;
import android.content.res.Resources;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment_User extends Fragment {
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

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
      		
        return rootView;
    }
}