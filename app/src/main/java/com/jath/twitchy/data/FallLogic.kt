package com.jath.twitchy.data

import com.jath.twitchy.data.mock.MockDataSource
import kotlinx.coroutines.flow.first

private val dataSource = MockDataSource()

suspend fun addFall(repo: HistoryRepository) {
    val data = dataSource.getMockData().first()
    repo.createHistoryItem(
        data.timestamp.toString(),
        data.totalAcceleration(),
        data.totalRotation(),
        data.altitude
    )
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