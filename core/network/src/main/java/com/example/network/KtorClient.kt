package com.example.ktorsample

import android.net.http.HttpResponseCache.install
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.isSuccess
import io.ktor.serialization.gson.gson
import io.ktor.util.StringValues
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

fun createHttpClient(): HttpClient {
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
            gson()
        }
        // Timeout 설정
        install(HttpTimeout) {
            requestTimeoutMillis = 10_000 // 10초.
            connectTimeoutMillis = 10_000
            socketTimeoutMillis = 10_000
        }
        // 자동 재시도
        install(HttpRequestRetry) {
            maxRetries = 1
            retryIf { request, response ->
                !response.status.isSuccess()
            }
            retryOnExceptionIf { request, cause ->
                true
            }
            delayMillis { retry ->
                500L
            }
        }
    }
}

private const val BASE_URL = "https://newsapi.org"

// 다양한 형태로 응용 가능한 Requester 모듈
suspend fun HttpClient.requester(
    serverUrl: String = com.example.ktorsample.BASE_URL,
    methodName: String? = null,
    params: StringValues = StringValues.Empty,
): Article {
    val serverURL = "$serverUrl/$methodName"
    val response: Article = request(serverURL) {
        this.method = HttpMethod.Post
        headers {
            append("X-Api-Key", "")
        }
        url.parameters.appendAll(params)
    }.body()
    return response
}


