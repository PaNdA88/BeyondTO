package com.example.beyondto;

import java.util.HashMap;
import java.util.Map;

import com.example.beyondto.R;
import android.os.Bundle;
import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeFragment_Clan extends Fragment {
	
	private String[] info, infoClan;
	//private Context context;
	private Map<String, String> nomiFileClan = new HashMap<String, String>();
	
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
		
		ImageView imageClan = (ImageView) rootView.findViewById(R.id.logoClan);
		nomiFileClan.put("Alchimisti", "alchimisti");
		nomiFileClan.put("Rinnegati", "rinnegati");
		String uri = "drawable/" + nomiFileClan.get(clan);
		Context context = ((HomeActivity)getActivity()).getApplicationContext();
		int imageResource = context.getResources().getIdentifier(uri, null,
				context.getPackageName());
		Drawable img = context.getResources().getDrawable(imageResource);
		imageClan.setImageDrawable(img);

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
