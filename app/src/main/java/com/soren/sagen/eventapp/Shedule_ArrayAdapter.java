package com.YourPackage.EventApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Shedule_ArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] time, activity;

	public Shedule_ArrayAdapter(Context context, String[] time, String[] activity) {
		super(context, R.layout.shedule_row, time);
		this.context = context;
		this.time = time;
		this.activity = activity;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		//get view and textview from xml
		View rowView = inflater.inflate(R.layout.shedule_row, parent, false);
		TextView ActivityTime = (TextView) rowView.findViewById(R.id.time);
		TextView ActivityName = (TextView) rowView.findViewById(R.id.activity);
		
		//change names
		ActivityTime.setText(time[position]);
		ActivityName.setText(activity[position]);

		return rowView;
	}
}