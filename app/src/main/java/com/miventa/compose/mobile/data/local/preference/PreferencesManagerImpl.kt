/*
 * PreferencesManagerImpl.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.data.local.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.miventa.compose.mobile.util.Constants.INFO
import com.miventa.compose.mobile.util.Constants.SETTINGS
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = SETTINGS)

class PreferencesManagerImpl @Inject constructor(
    @ApplicationContext context: Context
) : PreferencesManager {

    private val dataStore = context.dataStore
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
