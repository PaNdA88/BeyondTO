package com.example.beyondto;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beyondto.giocoMappa.GameMap;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends Activity implements OnMarkerClickListener {

	private GoogleMap googleMap;
	public LatLng myPosition;
	public ArrayList<Place> listPlaces;
	public ArrayList<LatLngBounds> boundPlaces;
	public String myClan = "rinnegati";
	final Context context = this;
	public Intent intent = new Intent();
	private MarkerOptions options = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		try {
			drawMap();
			InPlace(boundPlaces, listPlaces, myPosition);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Get user position
	 */
	public LatLng findMyPosition() {

		LatLng myPosition = null;
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, true);
		Location location = locationManager.getLastKnownLocation(provider);

		if (location != null) {
			myPosition = new LatLng(location.getLatitude(),
					location.getLongitude());
		}
		return myPosition;
	}
	
	/*
	 * Draw a map in MapActivity
	 */
	private void drawMap() {
		if (googleMap == null) {
			
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();
			googleMap.setMyLocationEnabled(true);
			googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			googleMap.getUiSettings().setRotateGesturesEnabled(false);
			googleMap.setOnMarkerClickListener(this);

			myPosition = findMyPosition();
			googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition,
					15));
			Connector con = new Connector();
			listPlaces = con.getLocations();
			if (listPlaces != null) {
				if (!listPlaces.isEmpty()) {
					drawMarkers(listPlaces, googleMap);
				} else {
					Toast toast = Toast.makeText(getApplicationContext(),
							"ArrayList vuoto", Toast.LENGTH_SHORT);
					toast.show();
				}
			} else {
				Toast toast = Toast.makeText(getApplicationContext(),
						"ArrayList nullo", Toast.LENGTH_SHORT);
				toast.show();
			}
		}

		if (googleMap == null) {
			Toast.makeText(getApplicationContext(),
					"Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
		}
	}

	/*
	 * Draw markers in googleMap
	 */
	public void drawMarkers(ArrayList<Place> listPlaces, GoogleMap googleMap) {

		for (int i = 0; i < listPlaces.size(); i++) {

			LatLng SO = new LatLng((listPlaces.get(i)).getLatSO(),
					(listPlaces.get(i)).getLngSO());
			LatLng NE = new LatLng((listPlaces.get(i)).getLatNE(),
					(listPlaces.get(i)).getLngNE());

			LatLngBounds boundPlace = new LatLngBounds(SO, NE);
			boundPlaces = new ArrayList<LatLngBounds>();
			boundPlaces.add(boundPlace);

			if (((String) ((listPlaces.get(i)).getProprietaFazione()))
					.equals("alchimisti")) {
				options = new MarkerOptions()
						.position(boundPlace.getCenter())
						.title((listPlaces.get(i)).getNomeLuogo())
						.icon(BitmapDescriptorFactory
								.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
			}
			if (((String) ((listPlaces.get(i)).getProprietaFazione()))
					.equals("rinnegati")) {

				options = new MarkerOptions()
						.position(boundPlace.getCenter())
						.title((listPlaces.get(i)).getNomeLuogo())
						.icon(BitmapDescriptorFactory
								.defaultMarker(BitmapDescriptorFactory.HUE_RED));

			}
			if (((String) ((listPlaces.get(i)).getProprietaFazione()))
					.equals("neutro")) {

				options = new MarkerOptions()
						.position(boundPlace.getCenter())
						.title((listPlaces.get(i)).getNomeLuogo())
						.icon(BitmapDescriptorFactory
								.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
			}
			googleMap.addMarker(options);
		}
	}
	

	public void InPlace(ArrayList<LatLngBounds> boundPlaces,
			ArrayList<Place> listPlaces, LatLng myPosition) {

		// DEVO AGGIUNGERE ALLA TABELLA TABELLA UTENTE IL CAMPO FAZIONE
		for (int i = 0; i < boundPlaces.size(); i++) {

			if (boundPlaces.get(i).contains(myPosition)) {
				if (!((listPlaces.get(i)).getProprietaFazione()).equals(myClan)
						|| ((listPlaces.get(i)).getProprietaFazione())
								.equals("neutro")) {

					/*Toast toast = Toast.makeText(getApplicationContext(),
							"Vuoi attaccare?", Toast.LENGTH_SHORT);
					toast.show();*/
				}
				/*
				 * else
				 * if(!((listPlaces.get(i)).getProprietaFazione()).equals(myClan
				 * )){ Toast toast = Toast.makeText(getApplicationContext(),
				 * "Vuoi difendere?", Toast.LENGTH_SHORT); toast.show(); }
				 */
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu, menu);
		return true;
	}

	

	@Override
	public boolean onMarkerClick(Marker marker) {

		Toast toast = Toast.makeText(getApplicationContext(), "Click marker",
				Toast.LENGTH_SHORT);
		toast.show();
		
		/*final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.dialog_map);
		dialog.setTitle("ZONA NEUTRA");
		TextView text = (TextView) dialog.findViewById(R.id.textDialog);
		text.setText("Questa zona appartiene a NESSUNO. Vuoi conquistare questo obiettivo?");
		ImageView image = (ImageView) dialog
				.findViewById(R.id.imageDialog);
		image.setImageResource(R.drawable.frejus);

		Button yesButton = (Button) dialog.findViewById(R.id.yesButton);

		yesButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.hide();
				Intent intentGame = new Intent(getApplicationContext(),
						GameMap.class);
				startActivity(intentGame);
				finish();
			}
		});

		Button noButton = (Button) dialog.findViewById(R.id.noButton);

		noButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.hide();
			}
		});
		dialog.show();*/
	
		return false;
	}	
	
	/*
	 * Menu application
	 */	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.menu_home:
			
			intent.setClass(getApplicationContext(), HomeActivity.class);
			startActivity(intent);
			return true;

		case R.id.menu_map:
			
			intent.setClass(getApplicationContext(), MapActivity.class);
			startActivity(intent);
			return true;

		case R.id.menu_target:
			
			intent.setClass(getApplicationContext(), TargetActivity.class);
			startActivity(intent);
			return true;

		case R.id.menu_medal:
			
			intent.setClass(getApplicationContext(), MedalActivity.class);
			startActivity(intent);
			return true;

		case R.id.menu_news:

			intent.setClass(getApplicationContext(), NewsActivity.class);
			startActivity(intent);
			return true;

		case R.id.menu_settings:

			intent.setClass(getApplicationContext(), SettingsActivity.class);
			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);

		}
	}
}
