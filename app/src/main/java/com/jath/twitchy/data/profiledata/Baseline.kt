package com.jath.twitchy.data.profiledata

import kotlinx.serialization.Serializable

@Serializable
data class Baseline (
    val acceleration: Float,
    val rotation: Float,
    val altitude: Float
)