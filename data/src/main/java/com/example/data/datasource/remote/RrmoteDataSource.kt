package com.example.data.datasource.remote

import com.example.data.model.NewsResponseDto
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getNews(query: String): Flow<NewsResponseDto>
}
