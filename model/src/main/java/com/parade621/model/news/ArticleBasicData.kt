package com.parade621.model.news

import java.util.Date

data class ArticleBasicData(
    val title: String,
    val url: String,
    val originalUrl: String? = null,
    val thumbnailUrl: String,
    val dateTime: Date,
    val mediaType: ArticleCategory,
)

enum class ArticleCategory {

}