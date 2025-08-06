package com.example.kha.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.feature.search.SearchScreen


@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.Search.route
    ) {
        composable(NavigationRoutes.Search.route) {
            SearchScreen(
                onSearchClick = { query ->
                    navController.navigate(NavigationRoutes.NewsList.createRoute(query))
                }
            )
        }

       /* composable(
            route = NavigationRoutes.NewsList.route,
        ) { backStackEntry ->
            val query = backStackEntry.arguments?.getString("query") ?: ""
            val viewModel: NewsListViewModel = hiltViewModel()
            viewModel.getNews(query)
            NewsListScreen(viewModel = viewModel)
        }*/
    }
}
