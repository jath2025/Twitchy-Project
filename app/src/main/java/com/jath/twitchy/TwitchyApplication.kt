package com.jath.twitchy

import android.app.Application
import com.jath.twitchy.data.FallDetection
import com.jath.twitchy.data.historydata.HistoryRepository
import com.jath.twitchy.data.datasource.SensorDataSource
import com.jath.twitchy.data.datasource.mock.MockDataSource
import com.jath.twitchy.data.profiledata.ProfileRepository
import com.jath.twitchy.data.EncryptedStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val FALL_COOLDOWN_MS = 10_000L

class TwitchyApplication : Application() {
    val sensorDataSource: SensorDataSource by lazy { MockDataSource() }
    val historyRepository: HistoryRepository by lazy { HistoryRepository(encryptedStorage) }
    val profileRepository: ProfileRepository by lazy { ProfileRepository(encryptedStorage) }

    private val fallDetection = FallDetection()
    private val encryptedStorage by lazy { EncryptedStorage(this) }

    // Gen AI (Claude) suggested that I give the app its own scope,
    // so that falls are being tracked in the background.
    private val appScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        fallListener()
    }

    // I used Gen AI (Claude) for help with this function, it suggested that I use collect
    // and a var for the last time a fall has happened, instead of using delay().
    private fun fallListener() {
        appScope.launch {
            var lastFall = 0L
            sensorDataSource.getSensorData().collect { data ->
                val coolDown = data.timestamp - lastFall > FALL_COOLDOWN_MS
                if (coolDown && fallDetection.isFall(data, profileRepository.profile.value.baseline)) {
                    lastFall = data.timestamp
                    historyRepository.createHistoryItem(
                        data.timestamp.toString(),
                        data.totalAcceleration(),
                        data.totalRotation(),
                        data.altitude
                    )
                }
            }
        }
    }
}