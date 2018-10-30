package com.YourPackage.EventApp;

import java.util.Calendar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class Welcome extends Activity implements ViewFactory {
	Context context = this;
	private int index;
	private int[] images = new int[] { R.drawable.gallery1, R.drawable.gallery2, R.drawable.gallery3, R.drawable.gallery4, R.drawable.gallery5, R.drawable.gallery6, R.drawable.gallery7, R.drawable.gallery8 };
	ImageSwitcher switcher;
	Handler Handler = new Handler();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);

		//set image switcher
		switcher = (ImageSwitcher) this.findViewById(R.id.imageSwitcher1);
		switcher.setFactory(this);

		switcher.setImageResource(images[index]);
		switcher.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				index++;
				if (index >= images.length) {
					index = 0;
				}
				switcher.setImageResource(images[index]);
			}
		});
		switcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
		switcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));

		//auto change image
		Handler.post(UpdateImage);

		//set font
		Typeface arial = Typeface.createFromAsset(context.getAssets(), "ARIALBD.TTF");
		TextView title = (TextView) findViewById(R.id.title);
		TextView countdown = (TextView) findViewById(R.id.countdown);
		title.setTypeface(arial);
		countdown.setTypeface(arial);

		//compute difference
		Calendar now = Calendar.getInstance();
		Calendar event_date = Calendar.getInstance();
		event_date.set(getResources().getIntArray(R.array.event_time)[0], getResources().getIntArray(R.array.event_time)[1] - 1, getResources().getIntArray(R.array.event_time)[2], getResources().getIntArray(R.array.event_time)[3], getResources().getIntArray(R.array.event_time)[4], 0);

		long diff = event_date.getTimeInMillis() - now.getTimeInMillis();
		if (diff > 0) {

			long days = diff / (24 * 60 * 60 * 1000);
			diff -= (days * 24 * 60 * 60 * 1000);
			long hours = diff / (60 * 60 * 1000);
			diff -= (hours * 60 * 60 * 1000);
			long minutes = diff / (60 * 1000);
			diff -= (minutes * 60 * 1000);
			countdown.setText("");
			if (days > 0)
				countdown.setText(countdown.getText().toString() + days + "days ");
			if (hours > 0)
				countdown.setText(countdown.getText().toString() + hours + "hours ");
			countdown.setText(countdown.getText().toString() + minutes + "minutes ");
			countdown.setText(countdown.getText().toString() + "left to " + getString(R.string.event_title));
			countdown.setVisibility(View.VISIBLE);
		} else {
			countdown.setVisibility(View.GONE);
		}

		//set welcome text
		WebView webView = (WebView) findViewById(R.id.welcometext);
		String customHtml = "<html> " +
				"<head>" +
				"<style type=\"text/css\"> " +
				"body{ text-align:justify; background-color:#fff;}" +
				"p1 { font-size: 1.2em; font-family: Arial;color: #888; }" +
				"</style>" +
				"</head>" +

				"<body>" +
				"<p1>" + getString(R.string.welcome_text) + "</p1>" +
				"</body>" +
				"</html>";
		webView.setBackgroundColor(0);
		webView.loadData(customHtml, "text/html", "UTF-8");

		//handle contact clicks
		ImageView mailButton = (ImageView) findViewById(R.id.mailButton);
		mailButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent email = new Intent(Intent.ACTION_SEND);
				email.putExtra(Intent.EXTRA_EMAIL, new String[] { getString(R.string.email) });
				email.setType("message/rfc822");
				startActivity(email);
			}

		});
		ImageView webButton = (ImageView) findViewById(R.id.webButton);
		webButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.website)));
				startActivity(intent);
			}

		});
		ImageView telButton = (ImageView) findViewById(R.id.telButton);
		telButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				try {
					Intent intent = new Intent(Intent.ACTION_CALL);
					intent.setData(Uri.parse("tel:" + getString(R.string.phone_number)));
					startActivity(intent);
				} catch (Exception e) {
				}
			}

		});

		

	}

	Runnable UpdateImage = new Runnable() {
		public void run() {
			// Increment index
			index++;
			if (index > (images.length - 1)) {
				index = 0;
			}
			switcher.setImageResource(images[index]);
			// Set the execution after 5 seconds
			Handler.postDelayed(this, (5 * 1000));
		}
	};

	public View makeView() {
		ImageView i = new ImageView(this);

		i.setScaleType(ImageView.ScaleType.CENTER_CROP);
		i.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		i.setBackgroundColor(0xFF000000);
		return i;
	}

}
