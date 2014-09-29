package com.example.beyondto;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class NewsSelectedFragment extends Fragment {

	// frammento della news selezionata, che compare sulla destra dello schermo
	// se il dispositivo � abbastanza grosso oppure in modalit� landscape

	public static NewsSelectedFragment newInstance(int index) {

		NewsSelectedFragment f = new NewsSelectedFragment();
		// Supply index input as an argument.
		Bundle args = new Bundle();
		args.putInt("index", index);
		f.setArguments(args);
		return f;
	}

	public int getShownIndex() {
		return getArguments().getInt("index", 0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			// We have different layouts, and in one of them this
			// fragment's containing frame doesn't exist. The fragment
			// may still be created from its saved state, but there is
			// no reason to try to create its view hierarchy because it
			// won't be displayed. Note this is not needed -- we could
			// just run the code below, where we would create and return
			// the view hierarchy; it would just never be used.
			return null;
		}
		
		View rootView = inflater.inflate(R.layout.fragment_newsselected,
				container, false);
		
		//------------------- dynamic strings ----------------------//
  		String testo = "Qui compare la notifica relativa all'ATTACCO o alla DIFESA di questo edificio";
  		TextView textNot = (TextView) rootView.findViewById(R.id.testoNotifica);
  		textNot.setText(Html.fromHtml((String)  testo)); 

		/*
		 * ScrollView scroller = new ScrollView(getActivity()); TextView text =
		 * new TextView(getActivity()); int padding =
		 * (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4,
		 * getActivity().getResources().getDisplayMetrics());
		 * text.setPadding(padding, padding, padding, padding);
		 * scroller.addView(text); text.setText("notifica selezionata"); return
		 * scroller;
		 */
		return rootView;

	}

}