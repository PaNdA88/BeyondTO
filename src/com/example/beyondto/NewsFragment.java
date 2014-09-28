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

	// Context ctx;
	ListView lista;
	String azione= null;

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
			TextView label = (TextView) row.findViewById(R.id.value);
			label.setText(notifica.getCategory());

			TextView edificio = (TextView) row.findViewById(R.id.edificio);
			edificio.setText(notifica.getEdificio());

			ImageView icon = (ImageView) row.findViewById(R.id.icon);

			String uri = "drawable/" + notifica.getImage();
			
			if (uri == "drawable/sword"){
				azione = "attacco";
			} else {
				azione = "difesa";
			}
			
			int imageResource = myContext.getResources().getIdentifier(uri,
					null, myContext.getPackageName());
			Drawable image = myContext.getResources()
					.getDrawable(imageResource);
			icon.setImageDrawable(image);
			return row;
		}
	}

	boolean mDualPane;
	int mCurCheckPosition = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_news, container, false);

		// ctx=(MedalActivity)getActivity();
		List<Notifica> listaNotifiche = new ArrayList<Notifica>();
		
		//int i = 0;
		
		//for(i=0; i<10; i++){			
					
			//if (azione == "attacco"){
		
		listaNotifiche.add(new Notifica("Il tuo attacco è andato a buon fine",
				"Palazzo Trucchi di Levaldigi", "sword"));
		listaNotifiche.add(new Notifica("Hai iniziato un attacco",
				"Palazzo Trucchi di Levaldigi", "sword"));
		listaNotifiche.add(new Notifica(
				"Il tuo clan ha conquistato l'edificio", "Palazzo Madama",
				"sword"));
		listaNotifiche.add(new Notifica("Il tuo clan ha attaccato un edificio",
				"Palazzo Madama", "sword"));
			//}
		
			//else {
				
		listaNotifiche.add(new Notifica("Hai protetto l'edificio dall'attacco",
				"Fontana del Frejus", "scudo"));
		listaNotifiche.add(new Notifica("Un tuo edificio è stato attaccato!",
				"Fontana del Frejus", "scudo"));
		listaNotifiche.add(new Notifica(
				"I nemici si aggirano nel tuo territorio!",
				"Fontana del Frejus", "scudo"));
			//}
		
		//}
		
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

		if (mDualPane) {
			// We can display everything in-place with fragments, so update
			// the list to highlight the selected item and show the data.
			getListView().setItemChecked(index, true);

			// Check what fragment is currently shown, replace if needed.
			NewsSelectedFragment details = (NewsSelectedFragment) getFragmentManager()
					.findFragmentById(R.id.details);
			if (details == null || details.getShownIndex() != index) {
				// Make new fragment to show this selection.
				details = NewsSelectedFragment.newInstance(index);

				// Execute a transaction, replacing any existing fragment
				// with this one inside the frame.
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				// if (index == 0) {
				ft.replace(R.id.details, details);
				// } else {
				// ft.replace(R.id.a_item, details);
				// }
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
				}
			
		} else {
			// Otherwise we need to launch a new activity to display
			// the dialog fragment with selected text.
			Intent intent = new Intent();
			intent.setClass(getActivity(), NewsSelectedActivity.class);
			intent.putExtra("index", index);
			intent.putExtra("azione", azione);
			startActivity(intent);
		}

	}
}
