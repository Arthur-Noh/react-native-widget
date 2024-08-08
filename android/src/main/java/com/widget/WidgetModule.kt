package com.widget

import android.content.Intent
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise

class WidgetModule(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return NAME
  }

  @ReactMethod
  fun updateWidgetText(text: String, promise: Promise) {
    try {
      val intent = Intent(reactContext, WidgetProvider::class.java).apply {
        action = "com.widget.action.UPDATE_WIDGET"
        putExtra("widget_text", text)
      }
      reactContext.sendBroadcast(intent)
      promise.resolve(null)
    } catch (e: Exception) {
      promise.reject("Error", e)
    }
  }

  companion object {
    const val NAME = "Widget"
  }
}
