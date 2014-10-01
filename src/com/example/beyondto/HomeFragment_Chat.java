package com.example.beyondto;

import java.util.HashMap;
import java.util.Map;

import com.example.beyondto.R;
import android.os.Bundle;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
 
public class HomeFragment_Chat extends Fragment {
 
	private String[] info;
	private Map<String, String> nomiFileClan = new HashMap<String, String>();

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home_chat, container, false);
        
		String clan = info[2];
		TextView textClan = (TextView) rootView.findViewById(R.id.nomeChat);
		Resources res1 = ((HomeActivity) getActivity()).getResources();
		String cl = String.format(res1.getString(R.string.nomeChat), clan);
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
		
		Button enterButton = (Button) rootView.findViewById(R.id.entra);

		enterButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg1) {
				((HomeActivity) getActivity()).entraChat();
			}
		});
		
        return rootView;
    }

	public void setInfoUser(String[] info) {
		this.info = info;
		
	}

}