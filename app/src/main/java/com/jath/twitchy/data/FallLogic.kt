package com.jath.twitchy.data

import com.jath.twitchy.data.datasource.SensorData

class FallDetection {
    fun isFall(data: SensorData): Boolean {
        return data.totalAcceleration() <= 2 ||
            data.totalAcceleration() >= 30 ||
            data.totalRotation() >= 20 ||
            data.altitude >= 10
    }
}
fun fallSeverity(acceleration: Float): String {
    if (acceleration >= 30f) {
        return "High"
    } else if (acceleration >= 15f) {
        return "Medium"
    } else {
        return "Low"
    }
}