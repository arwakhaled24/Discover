package com.example.data.di

import com.example.data.datasource.remote.RemoteDataSource
import com.example.data.datasource.remote.RemoteDataSourceImp
import com.example.data.datasource.remote.Services
import com.example.data.repo.NewsRepoImp
import com.example.domain.repo.NewsRepo
import com.example.domain.usecases.GetNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideModules {

    @Provides
    fun providesNewsRemoteDataSource(services: Services): RemoteDataSource {
        return RemoteDataSourceImp(services)
    }

    @Provides
     fun providesNewsRepo(remoteDataSource: RemoteDataSource): NewsRepo{
        return NewsRepoImp(remoteDataSource)
     }

 /* @Provides
    fun provideGetNewsUseCase(newsRepo: NewsRepo): GetNewsUseCase {
        return GetNewsUseCase(newsRepo)
    }*/

}