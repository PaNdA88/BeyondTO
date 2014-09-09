/*package com.example.beyondto;

import com.example.beyondto.giocoMappa.GameMap;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PlaceDialog {
	
	private String title, view_text;
	private Context context;
	
	public PlaceDialog(){}
	
	public void createPlaceDialog(){
		
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.dialog_map);
		dialog.setTitle(title);
		TextView text = (TextView) dialog.findViewById(R.id.textDialog);
		text.setText(view_text);
		ImageView image = (ImageView) dialog
				.findViewById(R.id.imageDialog);
		image.setImageResource(R.drawable.frejus);

		Button yesButton = (Button) dialog.findViewById(R.id.yesButton);

		yesButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.hide();
				Intent intentGame = new Intent(context,
						GameMap.class);
				startActivity(intentGame);
				finish();
			}
		});

		Button noButton = (Button) dialog.findViewById(R.id.noButton);

		noButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.hide();
			}
		});
		dialog.show();
	
		
	}

}
*/