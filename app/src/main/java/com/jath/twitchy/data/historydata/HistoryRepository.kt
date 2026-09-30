package com.jath.twitchy.data.historydata

import androidx.compose.runtime.mutableStateListOf

class HistoryRepository() {
    private val historyItems = mutableStateListOf<HistoryItem>()
    private var currId = 1
    fun addHistoryItem(item: HistoryItem) {
        historyItems.add(0, item)
    }
    fun createHistoryItem(timestamp: String, acceleration: Float, rotation: Float, altitude: Float): HistoryItem {
        val item = HistoryItem(currId, timestamp, acceleration, rotation, altitude)
        currId++
        historyItems.add(0, item)
        return item
    }
    fun getHistoryItems(): List<HistoryItem> {
        return historyItems.toList()
    }
    fun deleteHistoryItem(id: Int) {
        historyItems.removeAll { it.id == id }
    }
    fun clearHistory() {
        historyItems.clear()
    }
}