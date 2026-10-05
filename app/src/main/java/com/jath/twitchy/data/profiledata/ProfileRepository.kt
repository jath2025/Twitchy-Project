package com.jath.twitchy.data.profiledata

import com.jath.twitchy.data.EncryptedStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.json.Json

// Backing-property pattern _profile/profile were suggested by the Gen AI, (Claude, Anthropic).
// I myself implemented updateName and saveBaseline functions.

private const val PROFILE_FILE = "profile.enc"

class ProfileRepository(private val storage: EncryptedStorage) {
    private val _profile = MutableStateFlow(load())
    val profile: StateFlow<UserProfile> = _profile

    fun saveBaseline(baseline: Baseline) {
        _profile.update { it.copy(baseline = baseline) }
        save()
    }
    fun updateName(name: String) {
        _profile.update { it.copy(name = name) }
        save()
    }

    private fun save() {
        storage.save(PROFILE_FILE, Json.encodeToString(_profile.value))
    }
    private fun load(): UserProfile {
        val text = storage.load(PROFILE_FILE) ?: return UserProfile()
        return try {
            Json.decodeFromString<UserProfile>(text)
        } catch (e: Exception) {
            UserProfile()
        }
    }
}