package com.example.beyondto;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NewsSelectedFragment extends Fragment {

	/*
	 * private String username, userClan; private String namePlace; private
	 * String azione; private String edificioClan; private String orario;
	 * private String user; private String prova;
	 */

	String username = "";
	Context myContext = (NewsActivity) getActivity();
	static Bundle args = new Bundle();

	public static NewsSelectedFragment newInstance(int index, String username,
			String userClan, String namePlace, String azione,
			String edificioClan, String orario, String user) {

		NewsSelectedFragment f = new NewsSelectedFragment();
		// Supply index input as an argument.
		args.putInt("index", index);
		args.putString("username", username);
		args.putString("userClan", userClan);
		args.putString("namePlace", namePlace);
		args.putString("azione", azione);
		args.putString("edificioClan", edificioClan);
		args.putString("orario", orario);
		args.putString("user", user);

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

		/*
		 * ImageView icon = (ImageView) rootView.findViewById(R.id.icon);
		 * 
		 * String uri = "drawable/" + args.getString("azione"); int
		 * imageResource = myContext.getResources().getIdentifier(uri, null,
		 * myContext.getPackageName()); Drawable image =
		 * myContext.getResources().getDrawable(imageResource);
		 * icon.setImageDrawable(image);
		 */

		// ------------------- dynamic strings ----------------------//

		String azione = "";

		String obiettivo = "OBIETTIVO: " + args.getString("namePlace");

		if (args.getString("azione").equals("attaccare")) {
			if (args.getString("user").equals(args.getString("username"))) {
				username = "Tu";
			} else {
				username = args.getString("username");
			}
			azione = "attacco";
		} else {
			azione = "difesa";
		}

		String utente = "UTENTE: " + username;
		String act = "AZIONE: " + azione;
		String giorno = " GIORNO: " + args.getString("orario");

		TextView ob = (TextView) rootView.findViewById(R.id.obiettivo);
		ob.setText(Html.fromHtml((String) obiettivo));

		TextView us = (TextView) rootView.findViewById(R.id.utente);
		us.setText(Html.fromHtml((String) utente));

		TextView az = (TextView) rootView.findViewById(R.id.azione);
		az.setText(Html.fromHtml((String) act));

		TextView t = (TextView) rootView.findViewById(R.id.giorno);
		t.setText(Html.fromHtml((String) giorno));
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