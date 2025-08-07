package com.example.domain.repo

interface UserPreferencesRepository {
    fun isFirstTime(): Boolean
    fun setFirstTime(isFirstTime: Boolean)
    fun getLastSearch(): String
    fun setLastSearch(keyword: String)
}