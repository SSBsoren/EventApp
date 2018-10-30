package com.YourPackage.EventApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Shedule extends Activity {
	Context context = this;
	
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shedule);
		
		String[] ActivityTime = getResources().getStringArray(R.array.schedule_time);
		String[] ActivityName = getResources().getStringArray(R.array.schedule_title);

		ListView SheduleList = (ListView) findViewById(R.id.listView1);
		SheduleList.setAdapter(new Shedule_ArrayAdapter(this, ActivityTime, ActivityName));

		//handle click on list
		SheduleList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				try {
					Class<?> classtostart = Class.forName(getApplicationContext().getPackageName()+".ActivityDetails");
					Intent intent = new Intent(context, classtostart).putExtra("index", position);
					startActivity(intent);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

			}

		});
	}

}