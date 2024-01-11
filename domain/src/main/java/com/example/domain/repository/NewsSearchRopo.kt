package com.example.domain.repository

import com.example.domain.model.ApiResult
import kotlinx.coroutines.flow.Flow

interface NewsSearchRopo {

    fun getNewsDataResponse(
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
    ): Flow<ApiResult</* 데이터 타입 입력 */>>

}

enum class KakaoSearchSortType(val value: String) {
    ACCURACY("accuracy"),
    RECENCY("recency"),
}