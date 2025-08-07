package com.example.data.repo

import com.example.data.datasource.local.UserLocalDataSource
import com.example.domain.repo.UserPreferencesRepository
import jakarta.inject.Inject

class UserPreferencesRepositoryImpl @Inject constructor (private val localDataSource: UserLocalDataSource) : UserPreferencesRepository {

    override fun isFirstTime() = localDataSource.isFirstTime()
    override fun setFirstTime(isFirstTime: Boolean) = localDataSource.setFirstTime(isFirstTime)
    override fun getLastSearch() = localDataSource.getLastSearch()
    override fun setLastSearch(keyword: String) = localDataSource.setLastSearch(keyword)
}