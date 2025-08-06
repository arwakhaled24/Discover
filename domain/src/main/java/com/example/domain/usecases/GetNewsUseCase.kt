package com.example.domain.usecases
import com.example.domain.entity.NewsResponse
import com.example.domain.repo.NewsRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetNewsUseCase @Inject constructor(private val newsRepo: NewsRepo) {
    suspend operator fun invoke(query: String): Flow<NewsResponse> {
        return newsRepo.getNews(query)
    }
}

