package com.YourPackage.EventApp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

public class Widget extends AppWidgetProvider {
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

		//create new intent to open when widget is clicked
		Intent intent = new Intent(context, EventApp.class);
		intent.putExtra("widget", 1234);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
		PendingIntent pendIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		//set widget
		ComponentName Widget = new ComponentName(context, Widget.class);
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);

		remoteViews.setTextViewText(R.id.widget_title, context.getString(R.string.SheduleWidget_Title));
		remoteViews.setTextViewText(R.id.widget_text, context.getString(R.string.SheduleWidget_Contents));

		remoteViews.setOnClickPendingIntent(R.id.widget_text, pendIntent);
		appWidgetManager.updateAppWidget(Widget, remoteViews);
	}
}