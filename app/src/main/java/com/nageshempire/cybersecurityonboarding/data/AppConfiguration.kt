package com.nageshempire.cybersecurityonboarding.data

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.nageshempire.cybersecurityonboarding.util.MyApplication.Companion.configuration
import kotlinx.coroutines.flow.map

object AppConfiguration {

    private val ONBOARDING_ENABLED = booleanPreferencesKey("onboarding_enabled")

    suspend fun disableOnboarding() {
        configuration.edit { preferences ->
            preferences[ONBOARDING_ENABLED] = false
        }
    }

    fun getOnboardingEnabled() = configuration.data.map { preferences ->
        preferences[ONBOARDING_ENABLED] ?: true
    }
}