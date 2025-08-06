package com.example.data.mapper
import com.example.data.model.*
import com.example.domain.entity.*

fun SourceDto.toEntity(): Source {
    return Source(
        name = this.name
    )
}

fun ArticleDto.toEntity(): Article {
    return Article(
        source = this.source.toEntity(),
        author = this.author,
        title = this.title,
        description = this.description,
        url = this.url,
        urlToImage = this.urlToImage,
        publishedAt = this.publishedAt,
        content = this.content
    )
}

fun NewsResponseDto.toEntity(): NewsResponse {
    return NewsResponse(
        articles = this.articles.map { it.toEntity() }
    )
}
