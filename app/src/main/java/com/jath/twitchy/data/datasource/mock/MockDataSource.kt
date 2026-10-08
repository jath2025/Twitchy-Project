package com.jath.twitchy.data.datasource.mock

import com.jath.twitchy.data.datasource.SensorData
import com.jath.twitchy.data.datasource.SensorDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

class MockDataSource : SensorDataSource {
    override fun getSensorData() : Flow<SensorData> = flow {
        while (true) {
            val currTime = System.currentTimeMillis()
            val spike = if (currTime % 20_000 < 250) {
                listOf(5f, 15f, 25f).random()
            } else {
                0f
            }
            emit(
            SensorData(
                timestamp = currTime,
                accelerometerX = static(),
                accelerometerY = static(),
                accelerometerZ = 9.81f + spike + static(),
                gyroscopeX = spike + static(),
                gyroscopeY = static(),
                gyroscopeZ = static(),
                altitude = 0.8f + static()
            )
            )
            delay(50.milliseconds)
        }
    }

    private fun static(): Float = Random.nextFloat() - 1f
}
// Android Studio's auto complete helped build this class