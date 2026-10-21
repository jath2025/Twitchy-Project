package com.jath.twitchy.data.datasource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

enum class ConnectionStatus {
    DISCONNECTED,
    CONNECTING,
    CONNECTED
}
interface SensorDataSource {
    val connectionStatus: StateFlow<ConnectionStatus>
    fun getSensorData(): Flow<SensorData>
}