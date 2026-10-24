package com.jath.twitchy.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jath.twitchy.TwitchyApplication
import com.jath.twitchy.data.fallSeverity
import com.jath.twitchy.data.historydata.HistoryItem
import com.jath.twitchy.data.historydata.HistoryRepository

data class FallSummary(
    val count: Int = 0,
    val avgAcceleration: Float = 0f,
    val maxAcceleration: Float = 0f,
    val avgRotation: Float = 0f,
    val maxRotation: Float = 0f
)

class HistoryViewModel(
    private val repo: HistoryRepository
) : ViewModel() {
    val items get() = repo.getHistoryItems()

    val summary: FallSummary
        get() {
            val falls = items
            if (!falls.isEmpty()) {
                return FallSummary(
                    count = falls.size,
                    maxAcceleration = falls.maxOf { it.acceleration },
                    avgAcceleration = falls.map { it.acceleration }.average().toFloat(),
                    maxRotation = falls.maxOf { it.rotation },
                    avgRotation = falls.map { it.rotation }.average().toFloat()
                )
            }
            return FallSummary()
        }

    fun fallsBySeverity(severity: String): List<HistoryItem> {
        return items
            .filter { fallSeverity(it.acceleration) == severity }
            .sortedByDescending { it.timestamp.toLong() }
    }
    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as TwitchyApplication)
                HistoryViewModel(
                    application.historyRepository
                )
            }
        }
    }
    fun deleteFall(id: Int) {
        repo.deleteHistoryItem(id)
    }
    fun clearHistory() {
        repo.clearHistory()
    }
}