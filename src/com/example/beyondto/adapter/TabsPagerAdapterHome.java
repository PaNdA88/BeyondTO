package com.example.beyondto.adapter;

import com.example.beyondto.HomeFragment_Chat;
import com.example.beyondto.HomeFragment_Clan;
import com.example.beyondto.HomeFragment_User;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
	 
	public class TabsPagerAdapterHome extends FragmentPagerAdapter {
	 
	    private String[] info;

		public TabsPagerAdapterHome(FragmentManager fm) {
	        super(fm);
	    }
	 
	    @Override
	    public Fragment getItem(int index) {
	 
	        switch (index) {
	        case 0:
	            // User fragment activity
	            HomeFragment_User user = new HomeFragment_User();
	            user.setInfoUser(info);
	            return user;
	        case 1:
	            // Clan fragment activity
	        	HomeFragment_Clan clan = new HomeFragment_Clan();
	        	clan.setInfoUser(info);
	        	return clan;
	        case 2:
	            // Chat fragment activity
	        	HomeFragment_Chat chat = new HomeFragment_Chat();
	        	//chat.setInfoUser(info);
	        	return chat;
	        }
	 
	        return null;
	    }
	 
	    @Override
	    public int getCount() {
	        // get item count - equal to number of tabs
	        return 3;
	    }
	    
	    public void setInfoUser(String[] info){
	    	this.info = info;
	    	
	    }
	 
	
}
