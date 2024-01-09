package com.example.data.api

import com.example.data.Article

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
            append("X-Api-Key", "")
        }
        url.parameters.appendAll(params)
    }.body()
    return response
}