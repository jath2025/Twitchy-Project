package com.jath.twitchy.data

import java.time.Instant
import java.time.format.DateTimeFormatter

fun formatTime(millis: Long): String {
    val timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    return (Instant
        .ofEpochMilli(millis)
        .atZone(java.time.ZoneId.systemDefault())
        .format(timeFormat))
}