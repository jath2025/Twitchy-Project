package com.jath.twitchy.data.dataSource

import kotlinx.coroutines.flow.Flow

interface SensorDataSource {
    fun getSensorData(): Flow<SensorData>
}