package com.example.beyondto;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beyondto.giocoTarget.GameTarget;

public class TargetActivity extends Activity {

	Intent intent = new Intent();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_target);

		final Context context = this;
		Button dbutton = (Button) findViewById(R.id.dialogButton);

		dbutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				// Create custom dialog object
				final Dialog dialog = new Dialog(context);
				// Include dialog.xml file
				dialog.setContentView(R.layout.dialog_target);
				// Set dialog title
				dialog.setTitle("AVVERSARIO");

				// set values for custom dialog components - text, image and
				// button
				TextView text = (TextView) dialog.findViewById(R.id.textDialog);
				text.setText("Vuoi sfidare questo avversario?");
				ImageView image = (ImageView) dialog
						.findViewById(R.id.imageDialog);
				image.setImageResource(R.drawable.frejus);

				Button yesButton = (Button) dialog.findViewById(R.id.yesButton);

				yesButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// Close dialog
						dialog.hide();
						Intent intentGame = new Intent(getApplicationContext(),
								GameTarget.class);
						startActivity(intentGame);
						finish();
					}
				});

				Button noButton = (Button) dialog.findViewById(R.id.noButton);

				noButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// Close dialog
						dialog.hide();
					}
				});
				dialog.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.menu_home:

			intent.setClass(getApplicationContext(), HomeActivity.class);

			startActivity(intent);

			return true;

		case R.id.menu_map:

			intent.setClass(getApplicationContext(), MapActivity.class);

			startActivity(intent);
			return true;

		case R.id.menu_target:

			intent.setClass(getApplicationContext(), TargetActivity.class);

			startActivity(intent);
			return true;

		case R.id.menu_medal:
			intent.setClass(getApplicationContext(), MedalActivity.class);

			startActivity(intent);

			return true;

		case R.id.menu_news:

			intent.setClass(getApplicationContext(), NewsActivity.class);

			startActivity(intent);
			return true;

		case R.id.menu_settings:

			intent.setClass(getApplicationContext(), SettingsActivity.class);

			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);

		}

	}
}
