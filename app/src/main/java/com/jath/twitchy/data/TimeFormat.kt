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
fun formatTimeSinceFall(millis: Long?): String = when {
    millis == null -> "N/A"
    millis/1000 < 60 -> "${millis/1000} sec"
    millis/1000 < 3600 -> "${millis/1000/60} min ${millis/1000%60} sec"
    else -> "${millis/1000/60/60} hr ${millis/1000/60%60} min"

}