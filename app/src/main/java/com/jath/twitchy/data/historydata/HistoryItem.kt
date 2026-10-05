package com.jath.twitchy.data.historydata

import kotlinx.serialization.Serializable

@Serializable
data class HistoryItem(
    val id: Int,
    val timestamp: String,
    val acceleration: Float,
    val rotation: Float,
    val altitude: Float
)