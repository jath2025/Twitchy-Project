package com.jath.twitchy.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
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

    NavigationBar(containerColor = Color(0xFF2E4050)) {
        for (screen in screens) {
            NavigationBarItem(
                selected = currentDestination == screen.path,
                onClick = { navController.navigate(screen.path) },
                icon = { Icon(screen.icon, contentDescription = screen.title) },
                label = { Text(screen.title) },
                colors =  NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFFFFFFF),
                    selectedTextColor = Color(0xFFFFFFFF),
                    indicatorColor = Color.DarkGray,
                    unselectedIconColor = Color(0xFFFFFFFF),
                    unselectedTextColor = Color(0xFFFFFFFF)
                )
            )
        }
    }
}