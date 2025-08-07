package com.example.feature.newslist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.core.utils.ResponseStatus
import com.example.domain.entity.NewsResponse
import com.example.domain.usecases.GetLastSearchUseCase
import com.example.domain.usecases.GetNewsUseCase
import com.example.domain.usecases.SetLastSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val setLastSearchUseCase: SetLastSearchUseCase,
    private val getLastSearchUseCase: GetLastSearchUseCase
    ) : ViewModel() {
    private val _mutableNewsList: MutableStateFlow<ResponseStatus<NewsResponse>> = MutableStateFlow(ResponseStatus.Loading)
    val newsList: StateFlow<ResponseStatus<NewsResponse>> = _mutableNewsList.asStateFlow()

    private val _lastSearch = MutableStateFlow("")
    val lastSearch: StateFlow<String> = _lastSearch.asStateFlow()

    fun getNews(query: String) {
        viewModelScope.launch {
            setLastSearchUseCase(query)
            _lastSearch.value = query
            Log.i("TAGG", "getNews: viewmodel $query ")
            _mutableNewsList.value = ResponseStatus.Loading
            getNewsUseCase(query).catch {
                    _mutableNewsList.value = ResponseStatus.Error(it)
                print(it.message)
                }
                .collect { news : NewsResponse ->

                    _mutableNewsList.value = ResponseStatus.Success(news)
                }
        }
    }

    fun getLastSearch() {
        viewModelScope.launch {
            val lastSearch = getLastSearchUseCase()
            _lastSearch.value = lastSearch ?: ""
        }
    }

}



