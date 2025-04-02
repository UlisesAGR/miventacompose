/*
 * AuthenticationModule.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.miventa.login.di

import com.miventa.compose.mobile.miventa.login.data.network.AuthenticationManager
import com.miventa.compose.mobile.miventa.login.data.network.AuthenticationManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthenticationModule {

    @Binds
    @Singleton
    abstract fun provideAuthenticationManager(authenticationManagerImpl: AuthenticationManagerImpl): AuthenticationManager
}
