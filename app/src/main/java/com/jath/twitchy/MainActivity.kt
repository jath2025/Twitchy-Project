package com.jath.twitchy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.jath.twitchy.data.HistoryRepository
import com.jath.twitchy.ui.navigation.AppNavGraph
import com.jath.twitchy.ui.navigation.BottomBar

// To build the navigation modules Androids own documentation was used as a reference.
// Source: https://developer.android.com/develop/ui/compose/components/navigation-bar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TwitchyApp()
        }
    }
}

@Composable
fun TwitchyApp() {
    val navController = rememberNavController()
    val historyRepository = remember { HistoryRepository() }
    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            historyRepository = historyRepository,
            modifier = Modifier.padding(innerPadding)
        )
    }
}