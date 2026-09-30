package com.jath.twitchy.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jath.twitchy.TwitchyApplication
import com.jath.twitchy.data.historydata.HistoryRepository
import com.jath.twitchy.data.datasource.SensorDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY

class HistoryViewModel(
    private val repo: HistoryRepository,
    private val dataSource: SensorDataSource
) : ViewModel() {
    val items get() = repo.getHistoryItems()
    val sortedItems get() = items.sortedByDescending { it.acceleration }
    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TwitchyApplication)
                HistoryViewModel(
                    application.historyRepository,
                    application.sensorDataSource
                )
            }
        }
    }
    fun addFall() = viewModelScope.launch {
        val data = dataSource.getSensorData().first()
        repo.createHistoryItem(
            data.timestamp.toString(),
            data.totalAcceleration(),
            data.totalRotation(),
            data.altitude
        )
    }
    fun deleteFall(id: Int) {
        repo.deleteHistoryItem(id)
    }
    fun clearHistory() {
        repo.clearHistory()
    }
}