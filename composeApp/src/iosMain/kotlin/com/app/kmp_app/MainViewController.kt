package com.app.kmp_app

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.compose.KoinApplication
import org.koin.core.KoinApplication.Companion.init
import platform.UIKit.UIViewController

fun mainViewController(): UIViewController {
    return ComposeUIViewController {
        KoinApplication(application = { init() }) {
            App()
        }
    }
}
