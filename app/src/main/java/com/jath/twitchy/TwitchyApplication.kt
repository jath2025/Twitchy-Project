package com.jath.twitchy

import android.app.Application
import com.jath.twitchy.data.historydata.HistoryRepository
import com.jath.twitchy.data.datasource.SensorDataSource
import com.jath.twitchy.data.datasource.mock.MockDataSource
import com.jath.twitchy.data.profiledata.ProfileRepository

class TwitchyApplication : Application() {
    val sensorDataSource: SensorDataSource by lazy { MockDataSource() }
    val historyRepository: HistoryRepository by lazy { HistoryRepository() }
    val profileRepository: ProfileRepository by lazy { ProfileRepository() }
}