/*
 * ProductsDao.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.data.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.miventa.compose.mobile.data.local.model.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Insert
    suspend fun createProduct(product: ProductEntity)

    @Query("SELECT * FROM product_table WHERE userEmail = :email")
    fun readProducts(email: String): Flow<List<ProductEntity>>

    @Update
    suspend fun updateProduct(product: ProductEntity)

    @Delete
    suspend fun deleteProduct(product: ProductEntity)

    @Query("SELECT * FROM product_table WHERE LOWER(name) = LOWER(:productName) AND userEmail = :email")
    suspend fun productExist(
        email: String,
        productName: String,
    ): ProductEntity?
}
