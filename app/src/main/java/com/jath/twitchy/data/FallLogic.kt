package com.jath.twitchy.data

import com.jath.twitchy.data.datasource.SensorData
import com.jath.twitchy.data.profiledata.Baseline

private const val ACCELERATION_THRESHOLD = 4f
private const val ROTATION_THRESHOLD = 10f
private const val ALTITUDE_THRESHOLD = 0.5f


// I discussed my approach for fall detection with Gen AI (Claude): compare sensor data against the baseline + a const,
// and using the altitude as a confirmation and not as a separate condition. I wrote the code without Gen AI.
class FallDetection {
    fun isFall(data: SensorData, baseline: Baseline?): Boolean {
        val accelLimit = (baseline?.acceleration ?: 9.81f) + ACCELERATION_THRESHOLD
        val rotationLimit = (baseline?.rotation ?: 0f) + ROTATION_THRESHOLD
        val altitudeDiff = data.altitude <= (baseline?.altitude ?: 0.8f) - ALTITUDE_THRESHOLD

        return data.totalAcceleration() >= accelLimit ||
            data.totalAcceleration() <= 2f ||
            data.totalRotation() >= rotationLimit && altitudeDiff
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