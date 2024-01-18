package com.example.data.repository

import com.example.data.model.Article
import com.example.data.source.remote.NewsSearchDataSource
import com.example.domain.model.ApiResult
import com.example.domain.repository.NewsSearchRopo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsSearchRepoImpl @Inject constructor(
    private val newsSearchDataSource: NewsSearchDataSource
) : NewsSearchRopo {
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
        TODO("Not yet implemented")
    }
}