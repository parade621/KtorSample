package com.example.data.repository

import com.example.data.BuildConfig
import com.example.data.model.Article
import com.example.data.model.NewsData
import com.example.data.source.remote.NewsSearchDataSource
import com.example.domain.model.ApiResult
import com.example.domain.repository.NewsSearchRopo
import com.example.network.NetworkType
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.headers
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import io.ktor.util.StringValues
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.internal.NopCollector.emit
import javax.inject.Inject

class NewsSearchRepoImpl @Inject constructor(
    private val newsSearchDataSource: NewsSearchDataSource,
    val httpClient: HttpClient
) : NewsSearchRopo {

    suspend fun HttpClient.requester(
        serverUrl: String = "https://newsapi.org/v2/top-headlines?country=kr",
        methodName: String? = null,
        params: StringValues = StringValues.Empty,
    ): NewsData {
        val serverURL = "$serverUrl/$methodName"
        val response: NewsData = request(serverURL) {
            this.method = HttpMethod.Get
            headers {
                append("apiKey", BuildConfig.API_KEY)
            }
            url.parameters.appendAll(params)
        }.body()
        return response
    }

    override fun getNewsDataResponse(
        q: String,
        searchIn: String,
        sources: String,
        domains: String,
        excludeDomains: String,
        from: String,
        to: String,
        language: String,
        sortBy: String,
        pageSize: Int,
        page: Int
    ): Flow<ApiResult<Article>> {
        val params = StringValues.build {
            append("q", q)
            append("searchIn", searchIn)
            append("sources", sources)
            append("domains", domains)
            append("excludeDomains", excludeDomains)
            append("from", from)
            append("to", to)
            append("language", language)
            append("sortBy", sortBy)
            append("pageSize", pageSize.toString())
            append("page", page.toString())
        }
        return flow {
            httpClient.requester(
                methodName = "LoginQXQuick",
                params = params
            )
            emit(ApiResult.Success(response))
        }
    }
}