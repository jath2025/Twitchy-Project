package com.jath.twitchy.data.profiledata

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile (
    val name: String = "",
    val baseline: Baseline? = null
)