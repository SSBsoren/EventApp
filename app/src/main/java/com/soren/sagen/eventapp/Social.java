package com.YourPackage.EventApp;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.ListView;
import android.widget.TextView;

public class Social extends Activity {
	
	
	String[] Date = new String[] { ""};
	String[] Tweet = new String[] { "Cannot connect to twitter" };
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.social);

		//authenticate twitter keys
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		//CHANGE ME: its recomanded that you get your own keys from twitter. simply register your app on 'https://dev.twitter.com/apps/new'
		  .setOAuthConsumerKey("qmdGkyN3DWt5MLti3SCskQ")
		  .setOAuthConsumerSecret("aFhK29DsJDfMbMtRAbEIXx68kJIFwwypax7Vw9Ig")
		  .setOAuthAccessToken("607703967-YM8T12Etv4gDJvtTQtN31mIPx7C64qS8Xa9fGFxS")
		  .setOAuthAccessTokenSecret("CHIfrjZrtfg6AgHa09AqJvmVLi1B7XUASWkMSAsj8w");
		Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		List<Status> statuses;
		
		//get last tweets
		try {
			statuses = twitter.getUserTimeline(getString(R.string.TwitterName));
			Date = new String[statuses.size()];
			Tweet = new String[statuses.size()];
			for (int i = 0; i<statuses.size();i++){
				Tweet[i] = statuses.get(i).getText();
				Date[i] = (String) DateFormat.format("MMM dd", statuses.get(i).getCreatedAt());
				//Date[i] = (String) DateFormat.format("MMM dd, yyyy h:mmaa", statuses.get(i).getCreatedAt());
			}
			
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
		//show tweets on listview
		ListView TweetList = (ListView) findViewById(R.id.tweetlist);
		TweetList.setAdapter(new Twitter_ArrayAdapter(this, Date, Tweet));
		
		//set twitter name
		TextView twitter_name = (TextView) findViewById(R.id.twitter_name);
		twitter_name.setText("Tweets from @"+getString(R.string.TwitterName));


	}
}