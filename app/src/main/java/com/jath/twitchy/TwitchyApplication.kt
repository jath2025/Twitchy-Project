package com.jath.twitchy

import android.app.Application
import com.jath.twitchy.data.HistoryRepository
import com.jath.twitchy.data.datasource.SensorDataSource
import com.jath.twitchy.data.datasource.mock.MockDataSource

class TwitchyApplication : Application() {
    val sensorDataSource: SensorDataSource by lazy { MockDataSource() }
    val historyRepository: HistoryRepository by lazy { HistoryRepository() }
}