package com.example.kha.navigations

sealed class NavigationRoutes(val route: String) {
        object Search : NavigationRoutes("discover")
        object NewsList : NavigationRoutes("newsList/{query}") {
            fun createRoute(query: String) = "newsList/$query"
        }

}
