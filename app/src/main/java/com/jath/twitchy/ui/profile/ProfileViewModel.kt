package com.jath.twitchy.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jath.twitchy.TwitchyApplication
import com.jath.twitchy.data.datasource.SensorDataSource
import com.jath.twitchy.data.profiledata.ProfileRepository

class ProfileViewModel(
    private val repo: ProfileRepository,
    private val dataSource: SensorDataSource
) : ViewModel() {

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