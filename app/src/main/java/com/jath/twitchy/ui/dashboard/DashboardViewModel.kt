package com.jath.twitchy.ui.dashboard
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jath.twitchy.data.datasource.SensorDataSource
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

// Android developer's documentation was used as a reference for building the viewmodel and state.
// sources: https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-savedstate,
// https://developer.android.com/topic/libraries/architecture/viewmodel/viewmodel-factories

class DashboardState(
    val acceleration: Float = EARTH_GRAVITY,
    val rotation: Float = 0f,
    val altitude: Float = BELT_HEIGHT
)

class DashboardViewModel(
    dataSource: SensorDataSource
) : ViewModel() {
    val state: StateFlow<DashboardState> = dataSource.getSensorData().map {
        DashboardState(
            acceleration = it.totalAcceleration(),
            rotation = it.totalRotation(),
            altitude = it.altitude
        )
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DashboardState()
        )
}