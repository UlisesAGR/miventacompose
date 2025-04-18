/*
 * PreferencesManager.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.data.local.preference

interface PreferencesManager {

    suspend fun readInfoValue(): Boolean
    suspend fun updateInfoValue(value: Boolean)
}
