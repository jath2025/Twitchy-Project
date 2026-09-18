package com.jath.twitchy.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        Routes.HISTORY,
        Routes.DASHBOARD,
        Routes.PROFILE
    )

    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    NavigationBar(containerColor = Color(0xFFC7C7C7)) {
        for (screen in screens) {
            NavigationBarItem(
                selected = currentDestination == screen.path,
                onClick = { navController.navigate(screen.path) },
                icon = { Icon(screen.icon, contentDescription = screen.title) },
                label = { Text(screen.title) }
            )
        }
    }
}