package com.example.ktorsample

data class NewsData(
    val status: String = "",
    val totalResults: Int = 0,
    val articles: List<Article> = listOf()
)
