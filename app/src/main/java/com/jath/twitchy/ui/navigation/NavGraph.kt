package com.jath.twitchy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jath.twitchy.ui.dashboard.DashboardScreen
import com.jath.twitchy.ui.history.HistoryScreen
import com.jath.twitchy.ui.profile.ProfileScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.DASHBOARD.path) {
        composable(Routes.DASHBOARD.path) {
            DashboardScreen()
        }
        composable(Routes.HISTORY.path) {
            HistoryScreen()
        }
        composable(Routes.PROFILE.path) {
            ProfileScreen()
        }
    }
}