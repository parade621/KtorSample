package com.example.domain.entities.news

data class NewsBasicEntity(
    val publisher: String = "",
    val author: String = "",
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val urlToImage: String = "",
    val publishedAt: String = "",
    val content: String = ""
)
