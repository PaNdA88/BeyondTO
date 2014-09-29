package com.example.beyondto.adapter;

	import java.util.ArrayList;

import com.example.beyondto.HomeFragment_User;
import com.example.beyondto.MedalFragment_Medaglie;
import com.example.beyondto.MedalFragment_Classifica;
import com.example.beyondto.Player;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
	 
	public class TabsPagerAdapterMedal extends FragmentPagerAdapter {
		
		private ArrayList<Player> infoUsers;
	 
	    public TabsPagerAdapterMedal(FragmentManager fm2) {
	        super(fm2);
	    }
	 
	    @Override
	    public Fragment getItem(int index2) {
	 
	    	
	        switch (index2) {
	        case 0:
	            return new MedalFragment_Medaglie();
	            
	        case 1:
	        	MedalFragment_Classifica classifica = new MedalFragment_Classifica();
	        	classifica.setListUsers(infoUsers);
	            return classifica;
	        }
	 
	        return null;
	    }
	 
	    @Override
	    public int getCount() {
	        // get item count - equal to number of tabs
	        return 2;
	    }
	    

		public void setListUsers(ArrayList<Player> infoUsers) {
			this.infoUsers = infoUsers;
			
		}
	 
	
}
