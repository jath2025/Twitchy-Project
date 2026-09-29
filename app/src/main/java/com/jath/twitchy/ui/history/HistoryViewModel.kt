package com.jath.twitchy.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jath.twitchy.data.HistoryRepository
import com.jath.twitchy.data.datasource.SensorDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val repo: HistoryRepository,
    private val dataSource: SensorDataSource
) : ViewModel() {
    val items get() = repo.getHistoryItems()
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