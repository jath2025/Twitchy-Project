package com.jath.twitchy.data.dataSource

import kotlin.math.sqrt

data class SensorData(
    val timestamp: Long,
    val accelerometerX: Float,
    val accelerometerY: Float,
    val accelerometerZ: Float,
    val gyroscopeX: Float,
    val gyroscopeY: Float,
    val gyroscopeZ: Float,
    val altitude: Float
) {
    fun totalAcceleration(): Float {
        return sqrt(accelerometerX * accelerometerX + accelerometerY * accelerometerY + accelerometerZ * accelerometerZ)
    }
    fun totalRotation(): Float {
        return sqrt(gyroscopeX * gyroscopeX + gyroscopeY * gyroscopeY + gyroscopeZ * gyroscopeZ)
    }
}