package com.example.kha.navigations

import android.net.Uri

sealed class NavigationRoutes(val route: String) {
        object Search : NavigationRoutes("discover")
        object NewsList : NavigationRoutes("newsList/{query}") {
            fun createRoute(query: String) = "newsList/$query"
        }
    object WebView : NavigationRoutes("webview/{url}") {
        fun createRoute(url: String) = "webview/${Uri.encode(url)}"
    }
    object Onboarding : NavigationRoutes("onboarding")

}
