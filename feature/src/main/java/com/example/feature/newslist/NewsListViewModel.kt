package com.example.feature.newslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.core.utils.ResponseStatus
import com.example.domain.entity.NewsResponse
import com.example.domain.usecases.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(private val getNewsUseCase: GetNewsUseCase) : ViewModel() {
    private val _mutableNewsList: MutableStateFlow<ResponseStatus<NewsResponse>> = MutableStateFlow(ResponseStatus.Loading)
    val newsList: StateFlow<ResponseStatus<NewsResponse>> = _mutableNewsList.asStateFlow()

    fun getNews(query: String) {
        viewModelScope.launch {
            _mutableNewsList.value = ResponseStatus.Loading
            getNewsUseCase(query).catch {
                    _mutableNewsList.value = ResponseStatus.Error(it)
                }
                .collect { news : NewsResponse ->
                    _mutableNewsList.value = ResponseStatus.Success(news)
                }
        }
    }


}

