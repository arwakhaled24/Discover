package com.example.data.datasource.local

interface UserLocalDataSource {
    fun isFirstTime(): Boolean
    fun setFirstTime(isFirstTime: Boolean)

    fun getLastSearch(): String
    fun setLastSearch(keyword: String)
}