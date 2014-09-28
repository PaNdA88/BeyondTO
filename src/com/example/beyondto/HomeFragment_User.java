package com.example.beyondto;

import com.example.beyondto.R;
import com.facebook.Session;

import android.os.Bundle;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment_User extends Fragment {

	String info[];

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_home_user,
				container, false);

		// ------------------- dynamic strings ----------------------//
		
		String nome = info[1];
		TextView textUser = (TextView) rootView.findViewById(R.id.nomeUtente);
		Resources res1 = ((HomeActivity) getActivity()).getResources();
		String user = String.format(res1.getString(R.string.nomeUtente), nome);
		textUser.setText(Html.fromHtml((String) user));

		String clan = info[2];
		TextView textClan = (TextView) rootView
				.findViewById(R.id.clanAppartenenza);
		Resources res2 = ((HomeActivity) getActivity()).getResources();
		String cl = String.format(res2.getString(R.string.clanAppartenenza),
				clan);
		textClan.setText(Html.fromHtml((String) cl));

		String punti = info[0];
		TextView textPoints = (TextView) rootView
				.findViewById(R.id.puntiUtente);
		Resources res3 = ((HomeActivity) getActivity()).getResources();
		String pt = String.format(res3.getString(R.string.puntiUtente), punti);
		textPoints.setText(Html.fromHtml((String) pt));

		String edifici = info[4];
		TextView textEdifici = (TextView) rootView
				.findViewById(R.id.edificiConquistati);
		Resources res4 = ((HomeActivity) getActivity()).getResources();
		String edi = String.format(res4.getString(R.string.edificiConquistati),
				edifici);
		textEdifici.setText(Html.fromHtml((String) edi));

		LinearLayout linLayout1 = (LinearLayout) rootView
				.findViewById(R.id.linLayout1);

		if (Integer.parseInt(info[5]) > 0) {
			Context ctx = getActivity();
			ImageView imageView = new ImageView(ctx);
			imageView.setImageResource(R.drawable.medaglia1);
			linLayout1.addView(imageView);
			imageView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Toast.makeText(getActivity(), "Medaglia DUELLANTE",
							Toast.LENGTH_SHORT).show();
				}
			});
		}

		if (Integer.parseInt(info[6]) > 0) {
			Context ctx = getActivity();
			ImageView imageView = new ImageView(ctx);
			imageView.setImageResource(R.drawable.medaglia3);
			linLayout1.addView(imageView);
			imageView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Toast.makeText(getActivity(), "Medaglia APPRENDISTA",
							Toast.LENGTH_SHORT).show();
				}
			});
		}

		if (Integer.parseInt(info[5]) > 29) {
			Context ctx = getActivity();
			ImageView imageView = new ImageView(ctx);
			imageView.setImageResource(R.drawable.medaglia4);
			linLayout1.addView(imageView);
			imageView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Toast.makeText(getActivity(), "Medaglia CONQUISTATORE",
							Toast.LENGTH_SHORT).show();
				}
			});
		}

		if (Integer.parseInt(info[6]) > 29) {
			Context ctx = getActivity();
			ImageView imageView = new ImageView(ctx);
			imageView.setImageResource(R.drawable.medaglia6);
			linLayout1.addView(imageView);
			imageView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Toast.makeText(getActivity(), "Medaglia DIFENSORE",
							Toast.LENGTH_SHORT).show();
				}
			});
		}

		if (Integer.parseInt(info[0]) > 999) {
			Context ctx = getActivity();
			ImageView imageView = new ImageView(ctx);
			imageView.setImageResource(R.drawable.medaglia5);
			linLayout1.addView(imageView);
			imageView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Toast.makeText(getActivity(), "Medaglia LEADER",
							Toast.LENGTH_SHORT).show();
				}
			});
		}

		if (Integer.parseInt(info[0]) > 9999) {
			Context ctx = getActivity();
			ImageView imageView = new ImageView(ctx);
			imageView.setImageResource(R.drawable.medaglia2);
			linLayout1.addView(imageView);
			imageView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Toast.makeText(getActivity(), "Medaglia MASTER",
							Toast.LENGTH_SHORT).show();
				}
			});
		}

		return rootView;
	}

	public void setInfoUser(String[] info) {
		this.info = info;	
	}
}
