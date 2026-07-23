package com.app.kmp_app.di

import com.app.kmp_app.database.DriverFactory
import com.app.kmp_app.interfaces.IosPlatformAction
import com.app.kmp_app.interfaces.PlatformAction
import org.koin.dsl.module

actual fun platformModule() = module {
    single { DriverFactory() }
    single<PlatformAction> { IosPlatformAction() }
}
