package com.yourcompanyname.calculator.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.yourcompanyname.calculator.MainActivity
import com.yourcompanyname.calculator.R

class LockScreenWidgetReceiver : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val views = RemoteViews(context.packageName, com.yourcompanyname.calculator.R.layout.widget_lock_screen)
    
    // Set up pending intent to launch the app
    val intent = Intent(context, MainActivity::class.java)
    val pendingIntent = android.app.PendingIntent.getActivity(
        context,
        0,
        intent,
        android.app.PendingIntent.FLAG_UPDATE_CURRENT or android.app.PendingIntent.FLAG_IMMUTABLE
    )
    
    views.setOnClickPendingIntent(R.id.widget_display, pendingIntent)
    views.setTextViewText(R.id.widget_display, "0")
    
    appWidgetManager.updateAppWidget(appWidgetId, views)
}
