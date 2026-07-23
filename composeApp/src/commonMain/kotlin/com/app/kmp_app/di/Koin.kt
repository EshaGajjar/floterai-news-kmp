package com.app.kmp_app.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(appModule(), platformModule())
    }

// For iOS initialization
fun initKoin() = initKoin {}
