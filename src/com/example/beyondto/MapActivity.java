package com.example.beyondto;

import java.util.ArrayList;

import android.app.Activity;
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
import android.widget.Toast;

import com.facebook.Session;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;

public class MapActivity extends Activity implements OnMarkerClickListener {

	static final String MAP_ID = "elviggio.jlac1733";
	private TileOverlay tiles;
	private GoogleMap googleMap;
	public LatLng myPosition;
	public ArrayList<Place> listPlaces;
	public ArrayList<LatLngBounds> boundPlaces;
	private String myClan, otherClan, idFacebook;
	public Intent intent = new Intent();
	private MarkerOptions options = null;
	private MarkerOptions myLocation = null;
	final Context ctx = this;
	private Session session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		try {
			drawMap();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Get user info
	 */
	public void getUser() {
		Connector conUser = new Connector();
		String info[] = conUser.getUserInfo(Infoton.getInstance().getUserId());
		idFacebook = Infoton.getInstance().getUserId();
		Log.e("ID FACEBOOK: ", idFacebook);
		myClan = info[2];
		Log.e("MY CLAN: ", myClan);
		otherClan = info[3];
		Log.e("OTHER CLAN: ", otherClan);
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

		getUser();

		if (googleMap == null) {

			googleMap = ((MapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();
			googleMap.setMyLocationEnabled(true);
			googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);

			// TILE OVERLAY

			final MapBoxOnlineTileProvider provider = new MapBoxOnlineTileProvider(
					MAP_ID);
			final TileOverlayOptions overlay = new TileOverlayOptions()
					.tileProvider(provider);
			tiles = googleMap.addTileOverlay(overlay);

			googleMap.getUiSettings().setMyLocationButtonEnabled(false);
			googleMap.getUiSettings().setRotateGesturesEnabled(false);
			googleMap.setOnMarkerClickListener(this);

			myPosition = findMyPosition();

			// custom my location icon
			if (myClan.equals("Alchimisti")) {
				myLocation = new MarkerOptions()
						.position(myPosition)
						.title("La mia posizione")
						.snippet("Io sono qui!")
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.alchimisti));
			} else {
				myLocation = new MarkerOptions()
						.position(myPosition)
						.title("La mia posizione")
						.snippet("Io sono qui!")
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.rinnegati));
			}

			googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition,
					15));

			// custom my location icon
			if (myClan.equals("Alchimisti")) {
				myLocation = new MarkerOptions()
						.position(myPosition)
						.title("La mia posizione")
						.snippet("Io sono qui!")
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.myloc_alc));
			} else {
				myLocation = new MarkerOptions()
						.position(myPosition)
						.title("La mia posizione")
						.snippet("Io sono qui!")
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.myloc_rin));
			}

			googleMap.addMarker(myLocation);

			Connector con = new Connector();
			listPlaces = con.getLocations();
			if (listPlaces != null) {
				if (!listPlaces.isEmpty()) {
					drawMarkers(listPlaces, googleMap);
				} else {
					Toast toast2 = Toast.makeText(getApplicationContext(),
							"ArrayList vuoto", Toast.LENGTH_SHORT);
					toast2.show();
				}
			} else {
				Toast toast1 = Toast.makeText(getApplicationContext(),
						"ArrayList nullo", Toast.LENGTH_SHORT);
				toast1.show();
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

			if (((String) ((listPlaces.get(i)).getProprietaFazione()))
					.equals("Alchimisti")) {
				options = new MarkerOptions()
						.position(boundPlace.getCenter())
						.title((listPlaces.get(i)).getNomeLuogo())
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.blue_gem));
			}
			if (((String) ((listPlaces.get(i)).getProprietaFazione()))
					.equals("Rinnegati")) {

				options = new MarkerOptions()
						.position(boundPlace.getCenter())
						.title((listPlaces.get(i)).getNomeLuogo())
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.red_gem));

			}
			if (((String) ((listPlaces.get(i)).getProprietaFazione()))
					.equals("neutro")) {

				options = new MarkerOptions()
						.position(boundPlace.getCenter())
						.title((listPlaces.get(i)).getNomeLuogo())
						.icon(BitmapDescriptorFactory
								.fromResource(R.drawable.grey_gem));
			}
			googleMap.addMarker(options);
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

		String nomeLuogo = marker.getTitle();

		Connector con = new Connector();
		con.setWinnersMatch(nomeLuogo);

		Connector con2 = new Connector();
		String[] place = con2.checkPlaceState(nomeLuogo);

		Log.e("ORARIO", place[6]);

		PlaceDialog placeDialog = new PlaceDialog();
		placeDialog.setContext(ctx);
		placeDialog.setTitle(nomeLuogo);
		placeDialog.setStatePlace(place[5]);
		placeDialog.setActivity(this);
		placeDialog.setIdFacebook(idFacebook);
		placeDialog.setUserClan(myClan);
		placeDialog.setClan(place[0]);
		placeDialog.setNumberAtt(place[7]);
		placeDialog.setNumberDif(place[8]);
		placeDialog.setOrario(place[6]);

		if (place[0].equals(myClan)) {
			placeDialog.setAction("difendere");
		} else {
			placeDialog.setAction("attaccare");
		}
		placeDialog.showPlaceDialog();
		/*
		 * boolean inPlace = InPlace(place);
		 * 
		 * if(inPlace){ //devo controllare il clan del luogo
		 * 
		 * Toast toast = Toast.makeText(getApplicationContext(),
		 * "Sono nel luogo", Toast.LENGTH_SHORT); toast.show(); }else{ Toast
		 * toast = Toast.makeText(getApplicationContext(), "Non sono nel luogo",
		 * Toast.LENGTH_SHORT); toast.show(); }
		 */
		return false;
	}

	/* check if the user is in Place */
	/*
	 * public boolean InPlace(String dati[]) {
	 * 
	 * LatLngBounds boundPlace = new LatLngBounds(new LatLng(
	 * Double.parseDouble(dati[1]), Double.parseDouble(dati[2])), new
	 * LatLng(Double.parseDouble(dati[3]), Double .parseDouble(dati[4])));
	 * 
	 * if (boundPlace.contains(myPosition)) { return true; }else{ return false;
	 * } }
	 */

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
