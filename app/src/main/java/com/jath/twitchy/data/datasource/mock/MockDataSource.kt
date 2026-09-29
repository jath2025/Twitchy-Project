package com.jath.twitchy.data.datasource.mock

import com.jath.twitchy.data.datasource.SensorData
import com.jath.twitchy.data.datasource.SensorDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.milliseconds

class MockDataSource : SensorDataSource {
    override fun getSensorData() : Flow<SensorData> = flow {
        while (true) {
            val sensorData = SensorData(
                timestamp = System.currentTimeMillis(),
                accelerometerX = (0..10).random().toFloat(),
                accelerometerY = (0..10).random().toFloat(),
                accelerometerZ = 9.81f + (0..10).random().toFloat(),
                gyroscopeX = (0..10).random().toFloat(),
                gyroscopeY = (0..10).random().toFloat(),
                gyroscopeZ = (0..10).random().toFloat(),
                altitude = 0.8f + (0..10).random().toFloat()
            )
            emit(sensorData)
            delay(50.milliseconds)
        }
    }
}
// Android Studio's auto complete helped build this class