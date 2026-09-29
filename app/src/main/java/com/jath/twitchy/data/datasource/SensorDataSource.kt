package com.jath.twitchy.data.datasource

import kotlinx.coroutines.flow.Flow

interface SensorDataSource {
    fun getSensorData(): Flow<SensorData>
}