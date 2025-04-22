/*
 * PreferencesManagerImpl.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.data.local.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.miventa.compose.mobile.util.Constants.INFO
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesManagerImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : PreferencesManager {

    private val info = booleanPreferencesKey(INFO)

    override suspend fun readInfoValue(): Boolean =
        dataStore.data.map { preferences ->
            preferences[info] ?: false
        }.first()

    override suspend fun updateInfoValue(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[info] = value
        }
    }
}
