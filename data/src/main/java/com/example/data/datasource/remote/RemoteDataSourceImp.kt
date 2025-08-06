package com.example.data.datasource.remote
import com.example.data.model.NewsResponseDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(val apiService: Services) : RemoteDataSource{
    override suspend fun getNews(query: String): Flow<NewsResponseDto> {
        return flowOf( apiService.getNews(query))
    }


}