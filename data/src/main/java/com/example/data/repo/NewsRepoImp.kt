package com.example.data.repo

import com.example.data.datasource.remote.RemoteDataSource
import com.example.data.mapper.toEntity
import com.example.domain.entity.NewsResponse
import com.example.domain.repo.NewsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepoImp @Inject constructor(private val remoteDataSource: RemoteDataSource): NewsRepo{

    override suspend fun getNews(query: String): Flow<NewsResponse> {
        return  remoteDataSource.getNews(query).map { dto ->
            dto.toEntity()
        }
    }
}
