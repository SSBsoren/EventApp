package com.YourPackage.EventApp;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class EventApp extends TabActivity {

	Context context = this;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		int widget = getIntent().getIntExtra("widget", 0);
		final TabHost tabHost = getTabHost();

		// Tab for welcome
		TabSpec Welcome = tabHost.newTabSpec("Welcome");
		Welcome.setIndicator("Welcome", getResources().getDrawable(R.drawable.your_event_logo));//CHANGE ME(just if you want): change tab 1 name here
		Intent WelcomeIntent = new Intent(this, Welcome.class);
		Welcome.setContent(WelcomeIntent);

		// Tab for Shedule
		TabSpec shedule = tabHost.newTabSpec("Schedule");
		shedule.setIndicator("Schedule", getResources().getDrawable(R.drawable.shedule));//CHANGE ME(just if you want): change tab 2 name here
		Intent sheduleIntent = new Intent(this, Shedule.class);
		shedule.setContent(sheduleIntent);

		// Tab for Map
		TabSpec map = tabHost.newTabSpec("Map");
		map.setIndicator("Map", getResources().getDrawable(R.drawable.map));//CHANGE ME(just if you want): change tab 3 name here
		Intent mapIntent = new Intent(this, Map.class);
		map.setContent(mapIntent);

		// Tab for Social
		TabSpec social = tabHost.newTabSpec("Social");
		social.setIndicator("Social", getResources().getDrawable(R.drawable.twitter));//CHANGE ME(just if you want): change tab 4 name here
		Intent socialIntent = new Intent(this, Social.class);
		social.setContent(socialIntent);

		// Adding all TabSpec to TabHost
		tabHost.addTab(Welcome);
		tabHost.addTab(shedule);
		tabHost.addTab(map);
		tabHost.addTab(social);
		

		//goto shedule tab if wiget clicked
		if (widget == 1234)
			tabHost.setCurrentTab(1);

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_share:
			Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
			sharingIntent.setType("text/plain");
			sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.Share_Subject));
			sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.Share_body));
			startActivity(Intent.createChooser(sharingIntent, "Share via"));
			break;

		}
		return true;
	}

}