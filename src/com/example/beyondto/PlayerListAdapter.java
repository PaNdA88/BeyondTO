package com.example.beyondto;

import java.util.List;
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
    
    public PlayerListAdapter (Context ctx, int resourceId, List<Player> objects) {
            super(ctx, resourceId, objects);
            resource = resourceId;
            inflater = LayoutInflater.from(ctx);
            context=ctx;
    }
    
    @Override
    public View getView ( int position, View convertView, ViewGroup parent ) {
    	
            convertView = ( RelativeLayout ) inflater.inflate( resource, null );
            Player Giocatore = getItem( position );
                            TextView giocatoreName = (TextView) convertView.findViewById(R.id.nome);
            giocatoreName.setText(Giocatore.getName());
             
            TextView legendBorn = (TextView) convertView.findViewById(R.id.punti);
            legendBorn.setText(Giocatore.getPunti());
             
            ImageView clanImage = (ImageView) convertView.findViewById(R.id.clan);
            String uri = "drawable/" + Giocatore.getImage();
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            Drawable image = context.getResources().getDrawable(imageResource);
            clanImage.setImageDrawable(image);
       
            return convertView;
    }
}