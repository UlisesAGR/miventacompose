/*
 * AppDatabase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.miventa.compose.mobile.data.local.database.dao.ProductsDao
import com.miventa.compose.mobile.data.local.model.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductsDao
}
