/*
 * LoginRepositoryModule.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.miventa.login.di

import com.miventa.compose.mobile.miventa.login.data.repository.LoginRepository
import com.miventa.compose.mobile.miventa.login.data.repository.LoginRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository
}
