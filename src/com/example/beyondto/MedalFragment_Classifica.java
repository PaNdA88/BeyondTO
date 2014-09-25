package com.example.beyondto;

import java.util.ArrayList;
import com.example.beyondto.R;
import android.os.Bundle;
import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
 
public class MedalFragment_Classifica extends Fragment{
 
	private ListView listViewPlayers;
	private ArrayList mListItem;
	private Context ctx;
   
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
	    View rootView = inflater.inflate(R.layout.fragment_medal_classifica, container, false);
	    ctx=(MedalActivity)getActivity(); 
        listViewPlayers = ( ListView )rootView.findViewById(R.id.playerlist);
        mListItem = Player.getItems();
        listViewPlayers.setAdapter(new PlayerListAdapter(ctx, R.layout.playerlist, mListItem));
        return rootView;
    }
}