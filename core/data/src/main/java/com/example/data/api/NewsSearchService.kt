package com.example.data.api

import com.example.data.model.Article
import com.example.network.provideHttpClient
import io.ktor.client.HttpClient
import io.ktor.util.StringValues
import javax.inject.Inject

abstract class NewsSearchService {
    suspend fun getNewsDataResponse(
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
    ): Article {

        val client = provideHttpClient()
        val params = StringValues.Companion.build {
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
        return client.requester(
            methodName = "LoginQXQuick",
            params = params
        ).also {
            client.close()
        }
    }
}