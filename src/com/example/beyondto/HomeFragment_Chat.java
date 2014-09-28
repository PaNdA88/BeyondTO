package com.example.beyondto;

import com.example.beyondto.R;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
 
public class HomeFragment_Chat extends Fragment {
 
	private String[] info;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home_chat, container, false);
         
        return rootView;
    }

	public void setInfoUser(String[] info) {
		this.info = info;
		
	}
 
}