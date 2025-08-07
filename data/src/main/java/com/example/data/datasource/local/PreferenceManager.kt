package com.example.data.datasource.local

import android.content.Context
import android.content.SharedPreferences
import jakarta.inject.Inject

class PreferenceManager @Inject constructor (context: Context) {

    companion object {
        private const val PREF_NAME = "user_prefs"
        private const val KEY_IS_FIRST_TIME = "is_first_time"
        private const val KEY_LAST_SEARCH = "last_search"
    }

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)


    fun isFirstTime(): Boolean = prefs.getBoolean(KEY_IS_FIRST_TIME, true)

    fun setFirstTime(isFirstTime: Boolean) {
        prefs.edit().putBoolean(KEY_IS_FIRST_TIME, isFirstTime).apply()
    }

    fun getLastSearch(): String = prefs.getString(KEY_LAST_SEARCH, "") ?: ""

    fun setLastSearch(keyword: String) {
        prefs.edit().putString(KEY_LAST_SEARCH, keyword).apply()
    }
}
