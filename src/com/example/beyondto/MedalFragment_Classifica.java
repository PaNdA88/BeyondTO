package com.example.beyondto;

import java.util.ArrayList;
import java.util.List;
import com.example.beyondto.R;
import android.os.Bundle;
import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
 
public class MedalFragment_Classifica extends Fragment {
 
	private ListView listViewPlayers;
	private Context ctx;
   
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
	    View rootView = inflater.inflate(R.layout.fragment_medal_classifica, container, false);
          
	    ctx=(MedalActivity)getActivity(); 
        List<Player> playerList= new ArrayList<Player>();
        playerList.add(new Player("Andre M","3450","rinnegati"));
        playerList.add(new Player("Virgi D","2345","rinnegati"));
        playerList.add(new Player("Anto V","1234","alchimisti"));
        playerList.add(new Player("Dario C","456","alchimisti"));
        playerList.add(new Player("Giovanni M","345","rinnegati"));
        playerList.add(new Player("Massimo M","234","rinnegati"));
        playerList.add(new Player("Tobia G","10","alchimisti"));
        
        listViewPlayers = ( ListView )rootView.findViewById( R.id.playerlist);
        listViewPlayers.setAdapter(new PlayerListAdapter(ctx, R.layout.playerlist, playerList) );
   
	    return rootView;
    }
}