package com.example.beyondto;

import java.util.ArrayList;
import java.util.List;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class NewsFragment extends ListFragment {

	Context ctx;
	ListView lista;
	boolean mDualPane;

	// Connector con = new Connector();
	ArrayList<Notifica> listaNotifiche;
	// = con.getAllNotifications();

	// Connector con2 = new Connector();
	String[] user;
	// = con2.getUserInfo(Infoton.getInstance().getUserId());

	int mCurCheckPosition;

	public class MyListAdapter extends ArrayAdapter<Notifica> {

		Context myContext;

		public MyListAdapter(Context context, int textViewResourceId,
				List<Notifica> objects) {
			super(context, textViewResourceId, objects);
			myContext = context;

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			LayoutInflater inflater = (LayoutInflater) myContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			View row = inflater.inflate(R.layout.row, parent, false);
			Notifica notifica = getItem(position);

			String text;

			// caso attacco
			if (notifica.getCategory().equals("attaccare")) {
				if (notifica.getUserClan().equals(user[2])) {
					text = "Attacco della tua squadra!";
				} else {
					text = "Attacco della squadra avversaria!";
				}
			} else {// caso difesa
				if (notifica.getUserClan().equals(user[2])) {
					text = "Difesa della tua squadra!";
				} else {
					text = "Difesa della squadra avversaria!";
				}
			}

			TextView label = (TextView) row.findViewById(R.id.value);
			label.setText(text);

			TextView edificio = (TextView) row.findViewById(R.id.edificio);
			edificio.setText("del giorno: " + notifica.getOrario());

			ImageView icon = (ImageView) row.findViewById(R.id.icon);

			String uri = "drawable/" + notifica.getImage();
			int imageResource = myContext.getResources().getIdentifier(uri,
					null, myContext.getPackageName());
			Drawable image = myContext.getResources()
					.getDrawable(imageResource);
			icon.setImageDrawable(image);

			return row;
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mCurCheckPosition = 0;
		Connector con = new Connector();
		listaNotifiche = con.getAllNotifications();
		Connector con2 = new Connector();
		user = con2.getUserInfo(Infoton.getInstance().getUserId());

		View v = inflater.inflate(R.layout.fragment_news, container, false);

		MyListAdapter myListAdapter = new MyListAdapter(getActivity(),
				R.layout.row, listaNotifiche);
		setListAdapter(myListAdapter);

		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// Check to see if we have a frame in which to embed the details
		// fragment directly in the containing UI.
		View detailsFrame = getActivity().findViewById(R.id.details);
		mDualPane = detailsFrame != null
				&& detailsFrame.getVisibility() == View.VISIBLE;

		if (savedInstanceState != null) {
			// Restore last state for checked position.
			mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
		}

		if (mDualPane) {
			// In dual-pane mode, the list view highlights the selected item.
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			// Make sure our UI is in the correct state.
			showDetails(mCurCheckPosition);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("curChoice", mCurCheckPosition);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		showDetails(position);
	}

	/**
	 * Helper function to show the details of a selected item, either by
	 * displaying a fragment in-place in the current UI, or starting a whole new
	 * activity in which it is displayed.
	 */
	void showDetails(int index) {
		mCurCheckPosition = index;

		String username = listaNotifiche.get(index).getUserName();
		String userClan = listaNotifiche.get(index).getUserClan();
		String namePlace = listaNotifiche.get(index).getEdificio();
		String azione = listaNotifiche.get(index).getCategory();
		String edificioClan = listaNotifiche.get(index).getEdificioClan();
		String orario = listaNotifiche.get(index).getOrario();
		String USER = user[1];

		if (mDualPane) {
			// We can display everything in-place with fragments, so update
			// the list to highlight the selected item and show the data.
			getListView().setItemChecked(index, true);

			// Check what fragment is currently shown, replace if needed.
			NewsSelectedFragment details = (NewsSelectedFragment) getFragmentManager()
					.findFragmentById(R.id.details);

			if (details == null || details.getShownIndex() != index) {
				// Make new fragment to show this selection.

				details = NewsSelectedFragment
						.newInstance(index, username, userClan, namePlace,
								azione, edificioClan, orario, USER);

				// Execute a transaction, replacing any existing fragment
				// with this one inside the frame.
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();

				ft.replace(R.id.details, details);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}

		} else {
			// Otherwise we need to launch a new activity to display
			// the dialog fragment with selected text.
			Intent intent = new Intent();
			intent.setClass((NewsActivity) getActivity(),
					NewsSelectedActivity.class);
			intent.putExtra("index", index);
			intent.putExtra("username", username);
			intent.putExtra("namePlace", namePlace);
			intent.putExtra("azione", azione);
			intent.putExtra("orario", orario);
			intent.putExtra("user", user[1]);
			System.out.println("sno qui");
			System.out.println(username);

			startActivity(intent);
		}

	}
}