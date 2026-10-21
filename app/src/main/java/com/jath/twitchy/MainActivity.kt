package com.jath.twitchy

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.jath.twitchy.ui.navigation.AppNavGraph
import com.jath.twitchy.ui.navigation.BottomBar

// To build the navigation modules Androids own documentation was used as a reference.
// Source: https://developer.android.com/develop/ui/compose/components/navigation-bar

// Android developer's documentation was helpful for requesting permissions.
// Source: https://developer.android.com/training/permissions/requesting

class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()) { results ->
        if (results.all { it.value }) {
            // TODO: start BLE source here
        }
    }
    private val permissions = arrayOf(
        Manifest.permission.BLUETOOTH_SCAN,
        Manifest.permission.BLUETOOTH_CONNECT
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestPermissionLauncher.launch((permissions))
        setContent {
            TwitchyApp()
        }
    }
}

@Composable
fun TwitchyApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        AppNavGraph(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}