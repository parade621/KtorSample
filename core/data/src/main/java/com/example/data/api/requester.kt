package com.example.data.api

import com.example.data.model.Article
import com.example.data.BuildConfig.API_KEY
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.http.headers
import io.ktor.util.StringValues

private const val BASE_URL = "https://newsapi.org"

// 다양한 형태로 응용 가능한 Requester 모듈
suspend fun HttpClient.requester(
    serverUrl: String = BASE_URL,
    methodName: String? = null,
    params: StringValues = StringValues.Empty,
): Article {
    val serverURL = "$serverUrl/$methodName"
    val response: Article = request(serverURL) {
        this.method = HttpMethod.Post
        headers {
            append("X-Api-Key", API_KEY)
        }
        url.parameters.appendAll(params)
    }.body()
    return response
}