package com.YourPackage.EventApp;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityDetails extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activitydetails);
		
		//get extra
		int index = getIntent().getIntExtra("index", 0);
		//get font
		Typeface arial = Typeface.createFromAsset(getAssets(), "ARIALBD.TTF");
		//get views
		ImageView image = (ImageView) findViewById(R.id.ActivityImage);
		WebView webView = (WebView) findViewById(R.id.activity_webview);
		TextView item_title = (TextView) findViewById(R.id.item_title);


		//get ActivityName and ActivityDetails from strings.xml
		String[] ActivityName = getResources().getStringArray(R.array.schedule_title);
		String[] ActivityDetails = getResources().getStringArray(R.array.schedule_details);
		TypedArray ActivityImage = getResources().obtainTypedArray(R.array.schedule_image);

		//set image
		image.setImageDrawable(ActivityImage.getDrawable(index));
		//set title
		item_title.setText(ActivityName[index]);
		item_title.setTypeface(arial);
		
		//set webview
		String customHtml = "<html> " +
				"<head>" +
				"<style type=\"text/css\"> " +
				"body{ text-align:justify;}" +
				"p1 { font-size: 1.2em; font-family: Arial;color: #555; }" +
				"</style>" +
				"</head>" +

				"<body>" +
				"<p1>" + ActivityDetails[index] + "</p1>" +
				"</body>" +
				"</html>";
		webView.loadData(customHtml, "text/html", "UTF-8");

	}
}