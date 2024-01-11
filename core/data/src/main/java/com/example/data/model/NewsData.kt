package com.example.data.model

import com.example.data.model.Article

data class NewsData(
    val status: String = "",
    val totalResults: Int = 0,
    val articles: List<Article> = listOf()
)
