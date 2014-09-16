package com.example.beyondto;

import java.util.ArrayList;
import java.util.Random;

import com.example.beyondto.R;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
 
public class MedalFragment_Classifica extends Fragment {
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        ArrayList<Player> personList=new ArrayList<Player>();
        Player [] people={
                        new Player("Andrea Marietta",3600,R.drawable.rinnegati),
                        new Player("Antonella Vannucci",3400,R.drawable.alchimisti),
                        new Player("Dario Carbone",3100,R.drawable.alchimisti),
                        new Player("Giovanni Malnati",2800,R.drawable.rinnegati),
                        new Player("Tobia Giani",2600,R.drawable.alchimisti),
                        new Player("Virginia Daniele",2500,R.drawable.rinnegati)};
        
        Random r=new Random();
        for(int i=0;i<people.length;i++){
              personList.add(people[r.nextInt(people.length)]);
        }
        
        View rootView = inflater.inflate(R.layout.fragment_medal_classifica, container, false);
        return rootView;
    }
 
}