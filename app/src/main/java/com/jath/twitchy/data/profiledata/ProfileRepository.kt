package com.jath.twitchy.data.profiledata

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

// Backing-property pattern _profile/profile were suggested by the Gen AI, (Claude, Anthropic).
// I myself implemented updateName and saveBaseline functions.

class ProfileRepository {
    private val _profile = MutableStateFlow(UserProfile())
    val profile: StateFlow<UserProfile> = _profile

    fun saveBaseline(baseline: Baseline) {
        _profile.update { it.copy(baseline = baseline) }
    }
    fun updateName(name: String) {
        _profile.update { it.copy(name = name) }
    }
}