package com.example.data.model

data class NewsResponseDto(
    val status: String,
    val totalResults: Long,
    val articles: List<ArticleDto>,
)
