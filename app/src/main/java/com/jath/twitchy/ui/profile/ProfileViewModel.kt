package com.jath.twitchy.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jath.twitchy.TwitchyApplication
import com.jath.twitchy.data.datasource.SensorDataSource
import com.jath.twitchy.data.profiledata.Baseline
import com.jath.twitchy.data.profiledata.ProfileRepository
import com.jath.twitchy.data.profiledata.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

// Backing-property pattern _profile/profile were suggested by the Gen AI, (Claude, Anthropic).

class ProfileViewModel(
    private val repo: ProfileRepository,
    private val dataSource: SensorDataSource
) : ViewModel() {

    val profile: StateFlow<UserProfile> = repo.profile
    private val _isRecording = MutableStateFlow(false)
    val isRecording: StateFlow<Boolean> = _isRecording

    fun recordBaseline() {
        viewModelScope.launch {
            _isRecording.value = true
            try {
                val recording = dataSource.getSensorData().take(100).toList()
                repo.saveBaseline(
                    Baseline(
                        recording.map { it.totalAcceleration() }.average().toFloat(),
                        recording.map { it.totalRotation() }.average().toFloat(),
                        recording.map { it.altitude }.average().toFloat()
                    )
                )
            } finally {
                _isRecording.value = false
            }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TwitchyApplication)
                ProfileViewModel(
                    application.profileRepository,
                    application.sensorDataSource
                )
            }
        }
    }

}