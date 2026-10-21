package com.jath.twitchy.ui.theme

import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val TwitchyBlue = Color(0xFF415260) // 0xFF2E4050
val TwitchyBlueLight = Color(0xFF415260) // 0xFF415260

@Composable
fun appButtonColors() = ButtonDefaults.buttonColors(
    containerColor = Color.DarkGray,
    contentColor = Color.White
)