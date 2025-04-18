/*
 * AuthModule.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.di.firebase

import com.miventa.compose.mobile.data.firebase.AuthManager
import com.miventa.compose.mobile.data.firebase.AuthManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    @Singleton
    abstract fun provideAuthManagerImpl(loginAuthManagerImpl: AuthManagerImpl): AuthManager
}
