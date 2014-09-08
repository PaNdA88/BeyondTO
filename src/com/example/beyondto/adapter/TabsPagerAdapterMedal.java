package com.example.beyondto.adapter;

	import com.example.beyondto.MedalFragment_Medaglie;
	import com.example.beyondto.MedalFragment_Classifica;
	import android.app.Fragment;
	import android.app.FragmentManager;
	import android.support.v13.app.FragmentPagerAdapter;
	 
	public class TabsPagerAdapterMedal extends FragmentPagerAdapter {
	 
	    public TabsPagerAdapterMedal(FragmentManager fm2) {
	        super(fm2);
	    }
	 
	    @Override
	    public Fragment getItem(int index2) {
	 
	    	
	        switch (index2) {
	        case 0:
	            // Medal fragment activity
	            return new MedalFragment_Medaglie();
	        case 1:
	            // Chart fragment activity
	            return new MedalFragment_Classifica();
	        }
	 
	        return null;
	    }
	 
	    @Override
	    public int getCount() {
	        // get item count - equal to number of tabs
	        return 2;
	    }
	 
	
}
