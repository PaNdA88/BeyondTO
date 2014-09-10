package com.example.beyondto.adapter;

import com.example.beyondto.HomeFragment_Chat;
import com.example.beyondto.HomeFragment_Clan;
import com.example.beyondto.HomeFragment_User;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
	 
	public class TabsPagerAdapterHome extends FragmentPagerAdapter {
	 
	    public TabsPagerAdapterHome(FragmentManager fm) {
	        super(fm);
	    }
	 
	    @Override
	    public Fragment getItem(int index) {
	 
	        switch (index) {
	        case 0:
	            // User fragment activity
	            return new HomeFragment_User();
	        case 1:
	            // Clan fragment activity
	            return new HomeFragment_Clan();
	        case 2:
	            // Chat fragment activity
	            return new HomeFragment_Chat();
	        }
	 
	        return null;
	    }
	 
	    @Override
	    public int getCount() {
	        // get item count - equal to number of tabs
	        return 3;
	    }
	 
	
}
