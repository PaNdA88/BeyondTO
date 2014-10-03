package com.example.beyondto;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.beyondto.giocoMappa.GameMap;

public class PlaceDialog {

	private String title, view_text, orario, action, clan, statePlace,
			numberAtt, numberDif, userClan, idFacebook;
	private Context context;
	private Activity activity;
	// private Button yesButton, noButton;

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
		dialog.getWindow().setBackgroundDrawableResource(
				android.R.color.transparent);
		dialog.setContentView(R.layout.dialog_map);

		nomiFile.put("Politecnico", "poli");
		nomiFile.put("Carcere Le Nuove", "nuove");
		nomiFile.put("Il Fante", "fante");
		// dialog.setTitle(title);

		// ------------------- Stringhe fisse ----------------------//

		TextView titledialog = (TextView) dialog.findViewById(R.id.titleDialog);
		titledialog.setText(title);
		String luogo = title;

		ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
		String uri = "drawable/" + nomiFile.get(luogo);
		int imageResource = context.getResources().getIdentifier(uri, null,
				context.getPackageName());
		Drawable img = context.getResources().getDrawable(imageResource);
		image.setImageDrawable(img);

		// -------------------- Stringhe dinamiche -----------------//

		RelativeLayout textProp = (RelativeLayout) dialog
				.findViewById(R.id.textProp);
		TextView textPropriety = new TextView(getActivity());
		final Resources res = context.getResources();
		String prop = String.format(res.getString(R.string.proprierty), clan);
		textPropriety.setText(Html.fromHtml((String) prop));
		textProp.setGravity(Gravity.CENTER_HORIZONTAL);
		textProp.addView(textPropriety);

		RelativeLayout textState = (RelativeLayout) dialog
				.findViewById(R.id.textState);
		TextView textStatePlace = new TextView(getActivity());
		String state = String.format(res.getString(R.string.statePlace),
				statePlace);
		textStatePlace.setText(Html.fromHtml((String) state));
		textState.setGravity(Gravity.CENTER_HORIZONTAL);
		textState.addView(textStatePlace);

		if (!statePlace.equals("nessuno")) {
			// #ATTACCANTI, #DIFENSORI
			RelativeLayout textFirst = (RelativeLayout) dialog
					.findViewById(R.id.textFirst);
			TextView textOrario = new TextView(getActivity());
			String ora = String.format(res.getString(R.string.textFirstAtt),
					orario);
			textOrario.setText(Html.fromHtml((String) ora));
			textFirst.setGravity(Gravity.CENTER_HORIZONTAL);
			textFirst.addView(textOrario);

			RelativeLayout textAtt = (RelativeLayout) dialog
					.findViewById(R.id.textAtt);
			TextView textNumberAtt = new TextView(getActivity());
			String att = String.format(res.getString(R.string.numberAtt),
					numberAtt);
			textNumberAtt.setText(Html.fromHtml((String) att));
			textAtt.setGravity(Gravity.CENTER_HORIZONTAL);
			textAtt.addView(textNumberAtt);

			RelativeLayout textDif = (RelativeLayout) dialog
					.findViewById(R.id.textDif);
			TextView textNumberDif = new TextView(getActivity());
			String dif = String.format(res.getString(R.string.numberDif),
					numberDif);
			textNumberDif.setText(Html.fromHtml((String) dif));
			textDif.setGravity(Gravity.CENTER_HORIZONTAL);
			textDif.addView(textNumberDif);

		}

		// -----------------------------------------------------------------//

		if ((statePlace.equals("nessuno") && action.equals("attaccare"))
				|| (statePlace.equals("sotto attacco") && action
						.equals("difendere"))
				|| (statePlace.equals("sotto attacco") && action
						.equals("attaccare"))) {

			// chiedo se vogliono attaccare o meno l'edificio

			RelativeLayout textAct = (RelativeLayout) dialog
					.findViewById(R.id.textAct);
			TextView textAction = new TextView(getActivity());
			String act = String.format(res.getString(R.string.action), action);
			textAction.setText(Html.fromHtml((String) act));
			textAct.setGravity(Gravity.CENTER_HORIZONTAL);
			textAct.addView(textAction);

			RelativeLayout textButtyes = (RelativeLayout) dialog
					.findViewById(R.id.textButtyes);

			Button yesButton = new Button(getActivity());
			yesButton.setText("SI");
			yesButton.setTextColor(Color.WHITE);
			yesButton.setGravity(Gravity.CENTER_HORIZONTAL
					| Gravity.CENTER_VERTICAL);

			RelativeLayout textButtno = (RelativeLayout) dialog
					.findViewById(R.id.textButtno);

			Button noButton = new Button(getActivity());
			noButton.setText("NO");
			noButton.setTextColor(Color.WHITE);
			noButton.setGravity(Gravity.CENTER_HORIZONTAL
					| Gravity.CENTER_VERTICAL);

			int buttonyesNormalResId = R.drawable.selector_yes;
			int buttonnoNormalResId = R.drawable.selector_no;
			yesButton.setBackgroundResource(buttonyesNormalResId);
			noButton.setBackgroundResource(buttonnoNormalResId);

			textButtyes.addView(yesButton);
			textButtno.addView(noButton);

			yesButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {

					Intent intentGame = new Intent(context, GameMap.class);
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

		} else {

			RelativeLayout textEsci = (RelativeLayout) dialog
					.findViewById(R.id.textEsci);

			Button exitButton = new Button(getActivity());
			exitButton.setText("ESCI");
			exitButton.setTextColor(Color.WHITE);
			exitButton.setGravity(Gravity.CENTER_HORIZONTAL
					| Gravity.CENTER_VERTICAL);
			int buttonexitNormalResId = R.drawable.selector_no;
			exitButton.setBackgroundResource(buttonexitNormalResId);
			textEsci.addView(exitButton);

			exitButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.hide();
				}
			});

		}
		dialog.show();
	}
}
