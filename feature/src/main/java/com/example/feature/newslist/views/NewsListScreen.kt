package com.example.feature.newslist.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.core.utils.ResponseStatus
import com.example.domain.entity.NewsResponse
import com.example.feature.newslist.NewsListViewModel
import androidx.compose.runtime.getValue


@Composable
fun NewsListScreen(viewModel: NewsListViewModel) {
    viewModel.getNews("android")
    val newsListState by viewModel.newsList.collectAsState()

    when (newsListState) {
        is ResponseStatus.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is ResponseStatus.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Failed to load news ")
            }
        }

        is ResponseStatus.Success<*> -> {
            val newsList = (newsListState as ResponseStatus.Success<NewsResponse>).result.articles
            LazyColumn {
                items(newsList.size) { article ->
                    NewsCard(
                       article= newsList[article],
                        onClick = { print("clicked")
                        }
                    )
                }
            }
        }
    }
}
