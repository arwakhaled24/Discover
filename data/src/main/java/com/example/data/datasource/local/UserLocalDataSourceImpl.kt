package com.example.data.datasource.local

import jakarta.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(private val prefs: PreferenceManager) : UserLocalDataSource {

    override fun isFirstTime(): Boolean = prefs.isFirstTime()

    override fun setFirstTime(isFirstTime: Boolean) {
        prefs.setFirstTime(isFirstTime)
    }

    override fun getLastSearch(): String = prefs.getLastSearch()

    override fun setLastSearch(keyword: String) {
        prefs.setLastSearch(keyword)
    }
}