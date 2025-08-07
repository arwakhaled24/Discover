package com.example.feature.newslist.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.core.utils.ResponseStatus
import com.example.domain.entity.NewsResponse
import com.example.feature.newslist.NewsListViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsListScreen(
    viewModel: NewsListViewModel = hiltViewModel(),
    first: String,
    onNavigateToWeb: (String) -> Unit
) {
    var query by remember { mutableStateOf("") }
    var searchQuery by remember { mutableStateOf<String?>(null) }

    val lastSearch by viewModel.lastSearch.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getLastSearch()
    }

    LaunchedEffect(lastSearch) {
        if (searchQuery == null) {
            val initial = when {
                !first.isNullOrBlank() -> first
                !lastSearch.isNullOrBlank() -> lastSearch
                else -> null
            }
            if (!initial.isNullOrBlank()) {
                query = initial
                searchQuery = initial
            }
        }
    }

    LaunchedEffect(searchQuery) {
        searchQuery?.let { viewModel.getNews(it) }
    }
    val newsListState by viewModel.newsList.collectAsState()
    when (newsListState) {
        is ResponseStatus.Loading -> LoadingScreen()

        is ResponseStatus.Error -> ErrorScreen()

        is ResponseStatus.Success<*> -> {
            val newsList = (newsListState as ResponseStatus.Success<NewsResponse>).result.articles
            NewsSuccessScreen(
                newsList = newsList,
                query = query,
                onQueryChange = { query = it },
                onItemClick = onNavigateToWeb,
                onSearch = {
                    searchQuery = query
                }
            )
        }
    }
}





