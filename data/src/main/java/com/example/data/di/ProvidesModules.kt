package com.example.data.di

import android.content.Context
import com.example.data.datasource.local.PreferenceManager
import com.example.data.datasource.local.UserLocalDataSource
import com.example.data.datasource.local.UserLocalDataSourceImpl
import com.example.data.datasource.remote.RemoteDataSource
import com.example.data.datasource.remote.RemoteDataSourceImp
import com.example.data.datasource.remote.Services
import com.example.data.repo.NewsRepoImp
import com.example.data.repo.UserPreferencesRepositoryImpl
import com.example.domain.repo.NewsRepo
import com.example.domain.repo.UserPreferencesRepository
import com.example.domain.usecases.GetNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvideModules {

    @Provides
    @Singleton
    fun providesNewsRemoteDataSource(services: Services): RemoteDataSource {
        return RemoteDataSourceImp(services)
    }

    @Provides
    @Singleton
     fun providesNewsRepo(remoteDataSource: RemoteDataSource): NewsRepo{
        return NewsRepoImp(remoteDataSource)
     }

  @Provides
  @Singleton
    fun provideGetNewsUseCase(newsRepo: NewsRepo): GetNewsUseCase {
        return GetNewsUseCase(newsRepo)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): PreferenceManager {
        return PreferenceManager(context)
    }

    @Provides
    @Singleton
    fun provideUserLocalDataSource(prefs: PreferenceManager): UserLocalDataSource {
        return UserLocalDataSourceImpl(prefs)
    }


    @Provides
    @Singleton
    fun provideUserPreferencesRepo( userLocalDataSource :UserLocalDataSource): UserPreferencesRepository {
       return UserPreferencesRepositoryImpl(userLocalDataSource)
    }

}