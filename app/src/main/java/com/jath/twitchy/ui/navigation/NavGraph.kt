package com.jath.twitchy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jath.twitchy.data.HistoryRepository
import com.jath.twitchy.ui.dashboard.DashboardScreen
import com.jath.twitchy.ui.history.HistoryScreen
import com.jath.twitchy.ui.profile.ProfileScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    historyRepository: HistoryRepository,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController,
        startDestination = Routes.DASHBOARD.path,
        modifier = modifier
        ) {
        composable(Routes.DASHBOARD.path) {
            DashboardScreen()
        }
        composable(Routes.HISTORY.path) {
            HistoryScreen(historyRepository)
        }
        composable(Routes.PROFILE.path) {
            ProfileScreen()
        }
    }
}