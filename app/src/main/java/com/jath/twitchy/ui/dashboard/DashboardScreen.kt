package com.jath.twitchy.ui.dashboard

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

internal const val EARTH_GRAVITY = 9.81f
internal const val BELT_HEIGHT = 0.8f

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val statusText = when (state.status) {
        DashboardStatus.MONITORING -> "Monitoring"
        DashboardStatus.FALL_DETECTED -> "Fall Detected"
    }
    val statusColor = when (state.status) {
        DashboardStatus.MONITORING -> Color.Green
        DashboardStatus.FALL_DETECTED -> Color.Red
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text("Live Data",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
                .height(IntrinsicSize.Max)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Text("Accel: %.2f m/s^2".format(state.acceleration),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                MotionGraph(
                    newValue = state.acceleration,
                    graphColor = Color.Red,
                    modifier = Modifier
                        .height(75.dp)
                        .fillMaxWidth()
                        .border(width = 2.dp, color = Color.Black),
                    minValue = EARTH_GRAVITY,
                    maxValue = 25f
                )
            }
            Spacer(modifier = Modifier.width(4.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Text("Gyro: %.2f rad/s".format(state.rotation),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                MotionGraph(
                    newValue = state.rotation,
                    graphColor = Color.Blue,
                    modifier = Modifier
                        .height(75.dp)
                        .fillMaxWidth()
                        .border(width = 2.dp, color = Color.Black),
                    minValue = 0f,
                    maxValue = 20f
                )
            }
            Spacer(modifier = Modifier.width(4.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                Text("Baro: %.2f m".format(state.altitude),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                MotionGraph(
                    newValue = state.altitude,
                    graphColor = Color.Yellow,
                    modifier = Modifier
                        .height(75.dp)
                        .fillMaxWidth()
                        .border(width = 2.dp, color = Color.Black),
                    minValue = 0f,
                    maxValue = 14f
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Status:",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = statusColor)
            ) {
                Text(statusText,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}