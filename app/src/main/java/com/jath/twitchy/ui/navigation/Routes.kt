package com.jath.twitchy.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ShowChart
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Person

enum class Routes(val path: String, val title: String, val icon: ImageVector) {
    DASHBOARD("home_screen", "Dashboard", Icons.AutoMirrored.Filled.ShowChart),
    HISTORY("history_screen", "History", Icons.Filled.History),
    PROFILE("profile_screen", "Profile", Icons.Filled.Person)
}