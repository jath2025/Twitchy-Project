package com.jath.twitchy.ui.dashboard

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import com.jath.twitchy.data.formatTime
import com.jath.twitchy.data.formatTimeSinceFall
import com.jath.twitchy.data.historydata.HistoryItem

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
        DashboardStatus.MONITORING -> Color(0xFF4DBB50)
        DashboardStatus.FALL_DETECTED -> Color(0xFFC22727)
    }
    val animatedColorSwitch by animateColorAsState(
        targetValue = statusColor,
        animationSpec = tween(durationMillis = 400)
    )


    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Card {
            Text(
                "Live Data",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp, top = 8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .padding(start = 8.dp, bottom = 8.dp, top = 8.dp, end = 8.dp)

            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Text(
                        "Accel: %.1f m/s²".format(state.acceleration),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    MotionGraph(
                        newValue = state.acceleration,
                        graphColor = Color.Red,
                        modifier = Modifier
                            .height(90.dp)
                            .fillMaxWidth(),
                        minValue = EARTH_GRAVITY / 2,
                        maxValue = 25f
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Text(
                        "Rotation: %.1f rad/s".format(state.rotation),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    MotionGraph(
                        newValue = state.rotation,
                        graphColor = Color.Blue,
                        modifier = Modifier
                            .height(90.dp)
                            .fillMaxWidth(),
                        minValue = -5f,
                        maxValue = 20f
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                ) {
                    Text(
                        "Height: %.1f m".format(state.altitude),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    MotionGraph(
                        newValue = state.altitude,
                        graphColor = Color.Yellow,
                        modifier = Modifier
                            .height(90.dp)
                            .fillMaxWidth(),
                        minValue = 0f,
                        maxValue = 4f
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(150.dp),
                    colors = CardDefaults.cardColors(containerColor = animatedColorSwitch),
                    elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Text(statusText,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 40.dp)
                )
                Text("Time since fall: " + formatTimeSinceFall(state.timeSinceFall),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }
        }
        Card {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Recent Falls",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 8.dp)
            )
            RecentFallsCard(state.recentFalls)
        }
            }
    }
}
@Composable
fun RecentFallsCard(falls: List<HistoryItem>) {
    if(falls.isEmpty()) {
        Text("No recent falls",
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp, end = 8.dp)
        )
    } else {
        falls.forEach { fall ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 8.dp, end = 8.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.LightGray),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text(formatTime(fall.timestamp.toLong()))
                        Text("Acceleration %.1f m/s²  Rotation %.1f rad/s".format(fall.acceleration, fall.rotation))
                        Text("Height %.1f m".format(fall.altitude))
                    }
                }
            }
        }
    }
}