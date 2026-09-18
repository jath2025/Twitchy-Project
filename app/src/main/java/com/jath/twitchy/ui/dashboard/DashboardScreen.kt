package com.jath.twitchy.ui.dashboard
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

@Composable
fun DashboardScreen() {
    Surface(color = Color(0xFFFFFFFF), modifier = Modifier.fillMaxSize()) {
        Text("DashboardScreen", fontFamily = FontFamily.Serif, fontSize = 50.sp)
    }
}