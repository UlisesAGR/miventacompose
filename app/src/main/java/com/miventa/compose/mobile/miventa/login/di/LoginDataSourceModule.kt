/*
 * LoginDataSourceModule.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.miventa.login.di

import com.miventa.compose.mobile.miventa.login.data.datasource.LoginNetworkDataSource
import com.miventa.compose.mobile.miventa.login.data.datasource.LoginNetworkDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginDataSourceModule {

    @Binds
    @Singleton
    abstract fun provideLoginNetworkDataSource(loginNetworkDataSourceImpl: LoginNetworkDataSourceImpl): LoginNetworkDataSource
}
