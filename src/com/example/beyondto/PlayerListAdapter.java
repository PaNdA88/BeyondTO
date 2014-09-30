package com.example.beyondto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PlayerListAdapter extends ArrayAdapter<Player> {

	private int resource;
	private LayoutInflater inflater;
	private Context context;
	private Map<String, String> nomiFileClan = new HashMap<String, String>();

	public PlayerListAdapter(Context ctx, int resourceId, List<Player> objects) {
		super(ctx, resourceId, objects);
		resource = resourceId;
		inflater = LayoutInflater.from(ctx);
		context = ctx;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		convertView = (RelativeLayout) inflater.inflate(resource, null);
		Player Giocatore = getItem(position);

		TextView giocatoreName = (TextView) convertView.findViewById(R.id.nome);
		giocatoreName.setText(Giocatore.getName());

		TextView Punteggio = (TextView) convertView.findViewById(R.id.punti);
		Punteggio.setText(Giocatore.getPunti());

		ImageView clanImage = (ImageView) convertView.findViewById(R.id.clan);
		nomiFileClan.put("Alchimisti", "alchimisti");
		nomiFileClan.put("Rinnegati", "rinnegati");
		String clan = Giocatore.getFazione();
		String uri = "drawable/" + nomiFileClan.get(clan);
		int imageResource = context.getResources().getIdentifier(uri, null,
				context.getPackageName());
		Drawable img = context.getResources().getDrawable(imageResource);
		clanImage.setImageDrawable(img);
		
		return convertView;
	}
}