package com.example.domain.repo
import com.example.domain.entity.NewsResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepo {
  suspend fun getNews(query: String): Flow<NewsResponse>
}