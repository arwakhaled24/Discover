package com.example.kha.navigations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.feature.details.WebViewScreen
import com.example.feature.newslist.screens.NewsListScreen
import com.example.feature.onboarding.OnboardingScreen
import com.example.feature.search.screen.SearchScreen
import com.example.kha.NavigationViewModel


@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    val viewModel: NavigationViewModel = hiltViewModel()
    val startDestination = viewModel.startDestination.collectAsState()
    NavHost(
        navController = navController,
        startDestination = startDestination.value.toString()
    ) {
        composable(NavigationRoutes.Onboarding.route) {
            OnboardingScreen(
                onGetStartedClick = {
                    navController.navigate(NavigationRoutes.Search.route) {
                        popUpTo(NavigationRoutes.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(NavigationRoutes.Search.route) {
            SearchScreen(
                onSearchClick = { query ->
                    navController.navigate(NavigationRoutes.NewsList.createRoute(query)) {
                        popUpTo(NavigationRoutes.Search.route) { inclusive = true }
                    }                }
            )
        }
        composable(
            route = NavigationRoutes.NewsList.route,
        ) { backStackEntry ->
            val query = backStackEntry.arguments?.getString("query") ?: ""
            NewsListScreen(
                first = query,
                onNavigateToWeb = { url ->
                    navController.navigate(NavigationRoutes.WebView.createRoute(url))
                }
            )
        }
        composable(
            route = NavigationRoutes.WebView.route
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: ""
            WebViewScreen(url = url)
        }
    }


}
