package com.app.kmp_app.di

import com.app.kmp_app.database.DriverFactory
import com.app.kmp_app.interfaces.AndroidPlatformAction
import com.app.kmp_app.interfaces.PlatformAction
import org.koin.dsl.module

actual fun platformModule() = module {
    single { DriverFactory(get()) }
    single<PlatformAction> { AndroidPlatformAction(get()) }
}
