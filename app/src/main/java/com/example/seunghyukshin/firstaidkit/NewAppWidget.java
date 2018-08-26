package com.example.seunghyukshin.firstaidkit;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.TextView;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {
    static Context mContext;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        /*
        CharSequence widgetText =
                ((MainActivity) mContext).helper.getText().toString();
        */
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.example.seunghyukshin.firstaidkit.sharedPreferences",Context.MODE_PRIVATE);
        CharSequence widgetText = sharedPreferences.getString("widget_text","default");
        views.setImageViewResource(R.id.appwidget_image,sharedPreferences.getInt("widget_image",R.drawable.firstaid));
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {// There may be multiple widgets active, so update all of them
        //
        super.onUpdate(context,appWidgetManager,appWidgetIds);

        for (int appWidgetId : appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
            SharedPreferences sharedPreferences = context.getSharedPreferences("com.example.seunghyukshin.firstaidkit.sharedPreferences",Context.MODE_PRIVATE);
            remoteViews.setImageViewResource(R.id.appwidget_image,sharedPreferences.getInt("widget_image",R.drawable.firstaid));
            remoteViews.setTextViewText(R.id.appwidget_text,sharedPreferences.getString("widget_text","haha"));
            appWidgetManager.updateAppWidget(appWidgetId,remoteViews);
           // updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

