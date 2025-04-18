/*
 * DaoModule.kt
 * Created by Ulises Gonzalez on 20/02/25
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.di.local

import com.miventa.compose.mobile.data.local.database.AppDatabase
import com.miventa.compose.mobile.data.local.database.dao.ProductsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideProductsDao(database: AppDatabase): ProductsDao =
        database.productDao()
}
