package com.app.kmp_app.di

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

expect fun platformHttpClientEngineFactory(): HttpClientEngineFactory<*>

object NetworkModule {
    fun createHttpClient(engineFactory: HttpClientEngineFactory<*>? = null): HttpClient {
        val factory = engineFactory ?: platformHttpClientEngineFactory()

        return HttpClient(factory) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }

            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.INFO
            }
        }
    }
}
