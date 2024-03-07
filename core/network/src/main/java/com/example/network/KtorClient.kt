package com.example.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton


fun provideHttpClient(): HttpClient {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    return HttpClient(OkHttp) {
        engine {
            preconfigured = okHttpClient
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        // Timeout 설정
        install(HttpTimeout) {
            requestTimeoutMillis = 10_000 // 10초.
            connectTimeoutMillis = 10_000
            socketTimeoutMillis = 10_000
        }
        // 자동 재시도
        install(HttpRequestRetry) {
            maxRetries = 2
            retryIf { _, response ->
                !response.status.isSuccess()
            }
            retryOnExceptionIf { _, cause ->
                true
            }
            delayMillis { _ ->
                500L
            }
        }
    }
}


