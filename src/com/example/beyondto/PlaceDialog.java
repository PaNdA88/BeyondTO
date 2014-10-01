package com.example.beyondto;

import java.util.HashMap;
import java.util.Map;

import com.example.beyondto.giocoMappa.GameMap;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlaceDialog {
	
	private String title, view_text, orario, action, clan, statePlace, numberAtt, numberDif, userClan, idFacebook;
	private Context context;
	private Activity activity;
	private Button yesButton, noButton;
	
	private Map<String, String> nomiFile = new HashMap<String, String>();
	
	public String getIdFacebook() {
		return idFacebook;
	}

	public void setIdFacebook(String idFacebook) {
		this.idFacebook = idFacebook;
	}

	public String getUserClan() {
		return userClan;
	}

	public void setUserClan(String userClan) {
		this.userClan = userClan;
	}

	public PlaceDialog() {
	}

	public String getTitle() {
		return title;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getView_text() {
		return view_text;
	}

	public void setView_text(String view_text) {
		this.view_text = view_text;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getClan() {
		return clan;
	}

	public void setClan(String clan) {
		this.clan = clan;
	}

	public String getStatePlace() {
		return statePlace;
	}

	public void setStatePlace(String statePlace) {
		this.statePlace = statePlace;
	}

	public String getNumberAtt() {
		return numberAtt;
	}

	public void setNumberAtt(String numberAtt) {
		this.numberAtt = numberAtt;
	}

	public String getNumberDif() {
		return numberDif;
	}

	public void setNumberDif(String numberDif) {
		this.numberDif = numberDif;
	}
	
	public String getOrario() {
		return orario;
	}

	public void setOrario(String orario) {
		this.orario = orario;
	}
	
	public void showPlaceDialog() {

		final Dialog dialog = new Dialog(context);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent); 
		dialog.setContentView(R.layout.dialog_map);
		
		nomiFile.put("Politecnico", "poli");
		nomiFile.put("Carcere Le Nuove", "nuove");
		nomiFile.put("Il Fante", "fante");
		//dialog.setTitle(title);
				
		// ------------------- dynamic strings ----------------------//
		
		TextView titledialog = (TextView) dialog.findViewById(R.id.titleDialog);
		titledialog.setText(title);
		String luogo = title;

		ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
		String uri = "drawable/" + nomiFile.get(luogo);
		int imageResource = context.getResources().getIdentifier(uri, null,
				context.getPackageName());
		Drawable img = context.getResources().getDrawable(imageResource);
		image.setImageDrawable(img);
		
		TextView textPropriety = (TextView) dialog.findViewById(R.id.textProp);
		final Resources res = context.getResources();
		String prop = String.format(res.getString(R.string.proprierty), clan);
		textPropriety.setText(Html.fromHtml((String) prop));

		TextView textStatePlace = (TextView) dialog
				.findViewById(R.id.textStatePlace);
		String state = String.format(res.getString(R.string.statePlace),
				statePlace);
		textStatePlace.setText(Html.fromHtml((String) state));

		if (!statePlace.equals("nessuno")) {
			// #ATTACCANTI, #DIFENSORI
			TextView textNumberAtt = (TextView) dialog
					.findViewById(R.id.textNumberAtt);
			String att = String.format(res.getString(R.string.numberAtt),
					numberAtt);
			textNumberAtt.setText(Html.fromHtml((String) att));

			TextView textNumberDif = (TextView) dialog
					.findViewById(R.id.textNumberDif);
			String dif = String.format(res.getString(R.string.numberDif),
					numberDif);
			textNumberDif.setText(Html.fromHtml((String) dif));
			
			TextView textOrario = (TextView) dialog
					.findViewById(R.id.textFirstAtt);
			String or = String.format(res.getString(R.string.textFirstAtt),
					orario);
			textOrario.setText(Html.fromHtml((String) or));
		}
		else {
			TextView textNumberAtt = (TextView) dialog
					.findViewById(R.id.textNumberAtt);
			textNumberAtt.setText(null);
			TextView textNumberDif = (TextView) dialog
					.findViewById(R.id.textNumberDif);
			textNumberDif.setText(null);
			TextView textOrario = (TextView) dialog
					.findViewById(R.id.textFirstAtt);
			textOrario.setText(null);
		}

		// -----------------------------------------------------------------//

		if ((statePlace.equals("nessuno") && action.equals("attaccare"))
				|| (statePlace.equals("sotto attacco") && action
						.equals("difendere"))
				|| (statePlace.equals("sotto attacco") && action
						.equals("attaccare"))) {

			Log.e("AZIONE", action);
			/* chiedo se vogliono attaccare o meno l'edificio */
			TextView textAction = (TextView) dialog
					.findViewById(R.id.textAction);
			String act = String.format(res.getString(R.string.action), action);
			textAction.setText(Html.fromHtml((String) act));

		yesButton = (Button) dialog.findViewById(R.id.yesButton);
			noButton = (Button) dialog.findViewById(R.id.noButton);

			yesButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

					Intent intentGame = new Intent(context,GameMap.class);
					intentGame.putExtra("idUtente", idFacebook);

					intentGame.putExtra("nomeLuogo", title);
					intentGame.putExtra("azione", action);
					intentGame.putExtra("clanUtente", userClan);
					intentGame.putExtra("statoLuogo", statePlace);

					getActivity().startActivity(intentGame);
					dialog.hide();
					// finish();
				}
			});

			noButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.hide();
				}
			});

		}

		dialog.show();
	}
}
