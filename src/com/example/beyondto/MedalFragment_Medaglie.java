package com.example.beyondto;

import com.example.beyondto.R;

import android.os.Bundle;
import android.app.Fragment;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
 
public class MedalFragment_Medaglie extends Fragment {

	String info[];
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	Connector con = new Connector();
		info = con.getUserInfo(Infoton.getInstance().getUserId());
 
        View rootView = inflater.inflate(R.layout.fragment_medal_medaglie, container, false);
           
        //MEDAGLIA 1
        ImageView imageView1 = (ImageView) rootView.findViewById(R.id.med1);
        TextView nomeMed1 = (TextView) rootView.findViewById(R.id.nome_med1);
        TextView descMed1 = (TextView) rootView.findViewById(R.id.desc_med1);
        nomeMed1.setText("DUELLANTE");
		descMed1.setText("Hai sferrato il tuo primo attacco");
		if (Integer.parseInt(info[5]) > 0) {
		    imageView1.setImageResource(R.drawable.medaglia1);
			nomeMed1.setTextColor(Color.parseColor("#FFFFFF"));
			descMed1.setTextColor(Color.parseColor("#FFFFFF"));
		}
		else {
			imageView1.setImageResource(R.drawable.medaglia1bn);
			nomeMed1.setTextColor(Color.parseColor("#999999"));
			descMed1.setTextColor(Color.parseColor("#999999"));
		}
		
		//MEDAGLIA 2
        ImageView imageView2 = (ImageView) rootView.findViewById(R.id.med2);
        TextView nomeMed2 = (TextView) rootView.findViewById(R.id.nome_med2);
        TextView descMed2 = (TextView) rootView.findViewById(R.id.desc_med2);
        nomeMed2.setText("APPRENDISTA");
		descMed2.setText("Hai difeso un edificio per la prima volta");
		if (Integer.parseInt(info[6]) > 0) {
		    imageView2.setImageResource(R.drawable.medaglia3);
			nomeMed2.setTextColor(Color.parseColor("#FFFFFF"));
			descMed2.setTextColor(Color.parseColor("#FFFFFF"));
		}
		else {
			imageView2.setImageResource(R.drawable.medaglia3bn);
			nomeMed2.setTextColor(Color.parseColor("#999999"));
			descMed2.setTextColor(Color.parseColor("#999999"));
		}
		
		//MEDAGLIA 3
        ImageView imageView3 = (ImageView) rootView.findViewById(R.id.med3);
        TextView nomeMed3 = (TextView) rootView.findViewById(R.id.nome_med3);
        TextView descMed3 = (TextView) rootView.findViewById(R.id.desc_med3);
        nomeMed3.setText("CONQUISTATORE");
		descMed3.setText("Hai attaccato 30 luoghi");
		if (Integer.parseInt(info[5]) > 29) {
		    imageView3.setImageResource(R.drawable.medaglia4);
			nomeMed3.setTextColor(Color.parseColor("#FFFFFF"));
			descMed3.setTextColor(Color.parseColor("#FFFFFF"));
		}
		else {
			imageView3.setImageResource(R.drawable.medaglia4bn);
			nomeMed3.setTextColor(Color.parseColor("#999999"));
			descMed3.setTextColor(Color.parseColor("#999999"));
		}
		
		//MEDAGLIA 4
        ImageView imageView4 = (ImageView) rootView.findViewById(R.id.med4);
        TextView nomeMed4 = (TextView) rootView.findViewById(R.id.nome_med4);
        TextView descMed4 = (TextView) rootView.findViewById(R.id.desc_med4);
        nomeMed4.setText("DIFENSORE");
		descMed4.setText("Hai difeso 30 edifici");
		if (Integer.parseInt(info[6]) > 29) {
		    imageView4.setImageResource(R.drawable.medaglia6);
			nomeMed4.setTextColor(Color.parseColor("#FFFFFF"));
			descMed4.setTextColor(Color.parseColor("#FFFFFF"));
		}
		else {
			imageView4.setImageResource(R.drawable.medaglia6bn);
			nomeMed4.setTextColor(Color.parseColor("#999999"));
			descMed4.setTextColor(Color.parseColor("#999999"));
		}
		
		//MEDAGLIA 5
        ImageView imageView5 = (ImageView) rootView.findViewById(R.id.med5);
        TextView nomeMed5 = (TextView) rootView.findViewById(R.id.nome_med5);
        TextView descMed5 = (TextView) rootView.findViewById(R.id.desc_med5);
        nomeMed5.setText("LEADER");
		descMed5.setText("Hai totalizzato 1000 punti");
		if (Integer.parseInt(info[0]) > 999) {
		    imageView5.setImageResource(R.drawable.medaglia5);
			nomeMed5.setTextColor(Color.parseColor("#FFFFFF"));
			descMed5.setTextColor(Color.parseColor("#FFFFFF"));
		}
		else {
			imageView5.setImageResource(R.drawable.medaglia5bn);
			nomeMed5.setTextColor(Color.parseColor("#999999"));
			descMed5.setTextColor(Color.parseColor("#999999"));
		}
		
		//MEDAGLIA 6
        ImageView imageView6 = (ImageView) rootView.findViewById(R.id.med5);
        TextView nomeMed6 = (TextView) rootView.findViewById(R.id.nome_med5);
        TextView descMed6 = (TextView) rootView.findViewById(R.id.desc_med5);
        nomeMed6.setText("MASTER");
		descMed6.setText("HAI TOTALIZZATO 10000 PUNTI!");
		if (Integer.parseInt(info[0]) > 9999) {
		    imageView6.setImageResource(R.drawable.medaglia2);
			nomeMed6.setTextColor(Color.parseColor("#FFFFFF"));
			descMed6.setTextColor(Color.parseColor("#FFFFFF"));
		}
		else {
			imageView5.setImageResource(R.drawable.medaglia2bn);
			nomeMed5.setTextColor(Color.parseColor("#999999"));
			descMed5.setTextColor(Color.parseColor("#999999"));
		}
		
        
        return rootView;
    }
}