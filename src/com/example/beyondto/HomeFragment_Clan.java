package com.example.beyondto;

import com.example.beyondto.R;
import android.os.Bundle;
import android.app.Fragment;
import android.content.res.Resources;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment_Clan extends Fragment {
	
	private String[] info, infoClan;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_home_clan,
				container, false);
		
		Connector conClan = new Connector();
		infoClan = conClan.getClanInfo(Infoton.getInstance().getUserId());

		// ------------------- dynamic strings ----------------------//
		String clan = info[2];
		TextView textClan = (TextView) rootView.findViewById(R.id.nomeClan);
		Resources res1 = ((HomeActivity) getActivity()).getResources();
		String cl = String.format(res1.getString(R.string.nomeClan), clan);
		textClan.setText(Html.fromHtml((String) cl));

		String membri = infoClan[0];
		TextView textMembers = (TextView) rootView.findViewById(R.id.numMembri);
		Resources res2 = ((HomeActivity) getActivity()).getResources();
		String memb = String.format(res2.getString(R.string.numMembri), membri);
		textMembers.setText(Html.fromHtml((String) memb));

		String vittorie = infoClan[1];
		TextView textVitt = (TextView) rootView.findViewById(R.id.numVittorie);
		Resources res3 = ((HomeActivity) getActivity()).getResources();
		String vitt = String.format(res3.getString(R.string.numVittorie),
				vittorie);
		textVitt.setText(Html.fromHtml((String) vitt));

		return rootView;
	}

	private Connector Connector() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setInfoUser(String[] info) {
		this.info = info;

	}
}
