package com.widget

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.Toast

class WidgetProvider : AppWidgetProvider() {

  override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
    super.onUpdate(context, appWidgetManager, appWidgetIds)

    for (appWidgetId in appWidgetIds) {
      val views = RemoteViews(context.packageName, R.layout.widget_layout)

      val intent = Intent(context, WidgetProvider::class.java).apply {
        action = "com.widget.action.UPDATE_WIDGET"
      }
      val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
      views.setOnClickPendingIntent(R.id.widget_button, pendingIntent)

      appWidgetManager.updateAppWidget(appWidgetId, views)
    }
  }

  override fun onReceive(context: Context, intent: Intent) {
    super.onReceive(context, intent)

    if (intent.action == "com.widget.action.UPDATE_WIDGET") {
      val text = intent.getStringExtra("widget_text") ?: "Default Text"
      val appWidgetManager = AppWidgetManager.getInstance(context)
      val thisWidget = ComponentName(context, WidgetProvider::class.java)
      val appWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget)

      for (appWidgetId in appWidgetIds) {
        val views = RemoteViews(context.packageName, R.layout.widget_layout)
        views.setTextViewText(R.id.widget_text, text)

        appWidgetManager.updateAppWidget(appWidgetId, views)
      }
      Toast.makeText(context, "Widget Updated", Toast.LENGTH_SHORT).show()
    }
  }
}
