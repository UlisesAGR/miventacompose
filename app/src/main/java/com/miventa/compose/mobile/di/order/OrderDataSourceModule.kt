/*
 * OrderDataSourceModule.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.di.order

import com.miventa.compose.mobile.data.datasource.order.local.OrderLocalDataSource
import com.miventa.compose.mobile.data.datasource.order.local.OrderLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class OrderDataSourceModule {

    @Binds
    @Singleton
    abstract fun provideOrderLocalDataSource(orderLocalDataSourceImpl: OrderLocalDataSourceImpl): OrderLocalDataSource
}
