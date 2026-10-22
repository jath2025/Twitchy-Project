package com.jath.twitchy.ui.theme

import android.R
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
val TwitchyCard = Color(0xFF263341)
val TwitchyRed: Color = Color(0xFFC22727)
val TwitchyGreen: Color = Color(0xFF4DBB50)
val TwitchyYellow: Color = Color(0xFFEECC1A)
@Composable
fun appButtonColors() = ButtonDefaults.buttonColors(
    containerColor = Color(0xFF41509B),
    contentColor = Color.White
)
@Composable
fun appButtonColorsDanger() = ButtonDefaults.buttonColors(
    containerColor = TwitchyRed,
    contentColor = Color.White
)