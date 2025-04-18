/*
 * OrderLocalDataSource.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.data.datasource.order.local

import com.miventa.compose.mobile.data.local.model.ProductEntity
import kotlinx.coroutines.flow.Flow

interface OrderLocalDataSource {
    suspend fun createProduct(product: ProductEntity)
    fun readProducts(email: String): Flow<List<ProductEntity>>
    suspend fun updateProduct(product: ProductEntity)
    suspend fun deleteProduct(product: ProductEntity)
    suspend fun productExist(
        email: String,
        productName: String,
    ): ProductEntity?

    suspend fun readInfoValue(): Boolean
    suspend fun updateInfoValue(value: Boolean)
}
