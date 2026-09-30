package com.jath.twitchy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jath.twitchy.ui.dashboard.DashboardScreen
import com.jath.twitchy.ui.history.HistoryScreen
import com.jath.twitchy.ui.history.HistoryViewModel
import com.jath.twitchy.ui.dashboard.DashboardViewModel
import com.jath.twitchy.ui.profile.ProfileScreen
import com.jath.twitchy.ui.profile.ProfileViewModel

// Helpful documentation for using viewmodels from android developers:
// Source: https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-factories
@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController,
        startDestination = Routes.DASHBOARD.path,
        modifier = modifier
        ) {
        composable(Routes.DASHBOARD.path) {
            DashboardScreen(viewModel(factory = DashboardViewModel.Factory))
        }
        composable(Routes.HISTORY.path) {
            HistoryScreen(viewModel(factory = HistoryViewModel.Factory))
        }
        composable(Routes.PROFILE.path) {
            ProfileScreen(viewModel(factory = ProfileViewModel.Factory))
        }
    }
}