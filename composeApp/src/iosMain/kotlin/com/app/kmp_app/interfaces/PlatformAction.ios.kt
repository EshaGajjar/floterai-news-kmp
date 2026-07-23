package com.app.kmp_app.interfaces

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

class IosPlatformAction : PlatformAction {
    override fun openUrl(url: String) {
        val nsUrl = NSURL.URLWithString(url)
        if (nsUrl != null) {
            UIApplication.sharedApplication.openURL(nsUrl)
        }
    }
}
