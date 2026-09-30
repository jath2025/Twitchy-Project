package com.jath.twitchy.data.historydata

data class HistoryItem(
    val id: Int,
    val timestamp: String,
    val acceleration: Float,
    val rotation: Float,
    val altitude: Float
)