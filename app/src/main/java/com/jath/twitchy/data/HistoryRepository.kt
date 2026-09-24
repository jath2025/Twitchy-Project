package com.jath.twitchy.data

import androidx.compose.runtime.mutableStateListOf

class HistoryRepository() {
    private val historyItems = mutableStateListOf<HistoryItem>()

    fun addHistoryItem(item: HistoryItem) {
        historyItems.add(0, item)
    }

    fun getHistoryItems(): List<HistoryItem> {
        return historyItems.toList()
    }
    fun clearHistory() {
        historyItems.clear()
    }
}