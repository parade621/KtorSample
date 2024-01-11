package com.example.data.source.remote

import com.example.data.api.NewsSearchService
import javax.inject.Inject

class NewsSearchDataSource @Inject constructor(
    private val newsSearchService: NewsSearchService
) {
    suspend fun getNewsSearchResponse(
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
    ) = newsSearchService.getNewsDataResponse(
        q,
        searchIn,
        sources,
        domains,
        excludeDomains,
        from,
        to,
        language,
        sortBy,
        pageSize,
        page
    )
}