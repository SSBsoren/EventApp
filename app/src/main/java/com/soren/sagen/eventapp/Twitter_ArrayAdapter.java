package com.YourPackage.EventApp;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Twitter_ArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[] date, tweet;

	public Twitter_ArrayAdapter(Context context, String[] date, String[] tweet) {
		super(context, R.layout.twitter_row, tweet);
		this.context = context;
		this.date = date;
		this.tweet = tweet;
		
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//set fonts
		Typeface arial = Typeface.createFromAsset(context.getAssets(),"ARIAL.TFF");
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		//get view and textview from xml
		View rowView = inflater.inflate(R.layout.twitter_row, parent, false);
		TextView TweetDate = (TextView) rowView.findViewById(R.id.date);
		TextView TweetContent = (TextView) rowView.findViewById(R.id.tweet);
		
		//change tweet row
		TweetContent.setText(tweet[position]);
		TweetDate.setText(date[position]);
		
		TweetContent.setTypeface(arial);
		TweetDate.setTypeface(arial);
		
		
		return rowView;
	}
}