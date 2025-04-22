/*
 * PreferencesModule.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.di.preferences

import com.miventa.compose.mobile.data.local.preference.PreferencesManager
import com.miventa.compose.mobile.data.local.preference.PreferencesManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {

    @Binds
    @Singleton
    abstract fun providePreferencesManager(preferencesManagerImpl: PreferencesManagerImpl): PreferencesManager
}
