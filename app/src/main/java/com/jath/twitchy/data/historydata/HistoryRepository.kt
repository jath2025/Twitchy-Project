package com.jath.twitchy.data.historydata

import androidx.compose.runtime.mutableStateListOf
import com.jath.twitchy.data.EncryptedStorage
import kotlinx.serialization.json.Json

private const val HISTORY_FILE = "history.enc"

class HistoryRepository(private val storage: EncryptedStorage) {
    private val historyItems = mutableStateListOf<HistoryItem>()
    private var currId = 1
    init {
        historyItems.addAll(load())
        val maxId = historyItems.maxOfOrNull { it.id } ?: 0
        currId = maxId + 1
    }

    fun addHistoryItem(item: HistoryItem) {
        historyItems.add(0, item)
        save()
    }
    fun createHistoryItem(timestamp: String, acceleration: Float, rotation: Float, altitude: Float): HistoryItem {
        val item = HistoryItem(currId, timestamp, acceleration, rotation, altitude)
        currId++
        historyItems.add(0, item)
        save()
        return item
    }
    fun getHistoryItems(): List<HistoryItem> {
        return historyItems.toList()
    }
    fun deleteHistoryItem(id: Int) {
        historyItems.removeAll { it.id == id }
        save()
    }
    fun clearHistory() {
        historyItems.clear()
        save()
    }

    private fun save() {
        storage.save(HISTORY_FILE, Json.encodeToString(historyItems.toList()))
    }
    private fun load(): List<HistoryItem> {
        val text = storage.load(HISTORY_FILE) ?: return emptyList()
        return try {
            Json.decodeFromString<List<HistoryItem>>(text)
        } catch (e: Exception) {
            emptyList()
        }
    }
}