package com.jath.twitchy.data


//todo logic for automatically adding fall to history

fun fallSeverity(acceleration: Float): String {
    if (acceleration >= 30f) {
        return "High"
    } else if (acceleration >= 15f) {
        return "Medium"
    } else {
        return "Low"
    }
}