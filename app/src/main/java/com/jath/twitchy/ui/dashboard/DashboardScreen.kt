package com.jath.twitchy.ui.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jath.twitchy.data.mock.MockDataSource

private const val EARTH_GRAVITY = 9.81f
private const val BELT_HEIGHT = 0.8f

@Composable
fun DashboardScreen() {
    val dataSource = remember { MockDataSource() }
    val sensorData by dataSource.getMockData().collectAsStateWithLifecycle(initialValue = null)

    val accelData = sensorData?.totalAcceleration() ?: EARTH_GRAVITY
    val gyroData = sensorData?.totalRotation() ?: 0f
    val baroData = sensorData?.altitude ?: BELT_HEIGHT

    Column (
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text("Live Data",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text("Accelerometer: %.2f m/s^2".format(accelData),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        MotionGraph(
            newValue = accelData,
            graphColor = Color.Red,
            modifier = Modifier.weight(1f).fillMaxWidth(),
            minValue = EARTH_GRAVITY,
            maxValue = 25f
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text("Gyrometer: %.2f rad/s".format(gyroData),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        MotionGraph(
            newValue = gyroData,
            graphColor = Color.Blue,
            modifier = Modifier.weight(1f).fillMaxWidth(),
            minValue = 0f,
            maxValue = 20f
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text("Barometer: %.2f m".format(baroData),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        MotionGraph(
            newValue = baroData,
            graphColor = Color.Yellow,
            modifier = Modifier.weight(1f).fillMaxWidth(),
            minValue = 0f,
            maxValue = 14f
        )
    }
}