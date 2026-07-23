package com.app.kmp_app.di

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

actual fun platformHttpClientEngineFactory(): HttpClientEngineFactory<*> = OkHttp