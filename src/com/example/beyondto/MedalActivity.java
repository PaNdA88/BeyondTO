package com.example.beyondto;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.beyondto.adapter.TabsPagerAdapterMedal;

public class MedalActivity extends Activity implements ActionBar.TabListener {

	private ViewPager viewPager;
	private TabsPagerAdapterMedal mAdapter;
	private ActionBar actionBar;
	Intent intent = new Intent();
	private String[] tabs = { "Medaglie", "Classifica" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medal);

		// Initialization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapterMedal(getFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 * */

		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
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