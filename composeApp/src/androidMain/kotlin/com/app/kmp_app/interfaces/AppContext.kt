package com.app.kmp_app.interfaces

import android.app.Application
import android.content.Context

object AppContext {
    lateinit var app: Application
        private set

    fun init(app: Application) { this.app = app }

    val context: Context
        get() = app.applicationContext
}