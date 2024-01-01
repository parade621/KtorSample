package com.example.ktorsample.server

import com.android.volley.NetworkError
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.isSuccess
import io.ktor.serialization.gson.gson
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
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
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
                cause is NetworkError
            }
            delayMillis { retry ->
                500L
            }
        }
    }
}

//private val BASE_URL by lazy {
//    setServerUrl()
//}



//suspend fun HttpClient.dynamicRequest(
//    serverUrl: String = BASE_URL,
//    methodName: String? = null,
//    params: StringValues = StringValues.Empty,
//): APIModel {
//    val serverURL = "$serverUrl/$methodName"
//    val response: APIModel = request(serverURL) {
//        this.method = HttpMethod.Post
//        headers {
//            append("User-Agent", DataStoreManager.userAgent)
//        }
//        url.parameters.appendAll(params)
//    }.body()
//    return response
//}


