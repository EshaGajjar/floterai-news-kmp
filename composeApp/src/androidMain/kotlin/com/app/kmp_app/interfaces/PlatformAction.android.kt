package com.app.kmp_app.interfaces

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

class AndroidPlatformAction(private val context: Context) : PlatformAction {
    override fun openUrl(url: String) {
        try {
            val intent = CustomTabsIntent.Builder().build()
            intent.launchUrl(context, Uri.parse(url))
        } catch (e: Exception) {
            val browserIntent = android.content.Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url))
            browserIntent.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(browserIntent)
        }
    }
}
