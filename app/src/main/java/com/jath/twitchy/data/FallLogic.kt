package com.jath.twitchy.data

import com.jath.twitchy.data.datasource.SensorData

class FallDetection {
    fun isFall(data: SensorData): Boolean {
        return data.totalAcceleration() <= 2 ||
            data.totalAcceleration() >= 13 ||
            data.totalAcceleration() >= 15 && data.altitude >= 5 ||
            data.totalRotation() >= 20

    }
}
fun fallSeverity(acceleration: Float): String {
    if (acceleration >= 30f) {
        return "High"
    } else if (acceleration >= 20f) {
        return "Medium"
    } else {
        return "Low"
    }
}