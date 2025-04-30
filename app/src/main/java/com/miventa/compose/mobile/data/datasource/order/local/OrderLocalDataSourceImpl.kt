/*
 * OrderLocalDataSourceImpl.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.data.datasource.order.local

import com.miventa.compose.mobile.data.local.database.dao.ProductsDao
import com.miventa.compose.mobile.data.local.model.ProductEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OrderLocalDataSourceImpl @Inject constructor(
    private val productsDao: ProductsDao,
) : OrderLocalDataSource {

    override suspend fun createProduct(product: ProductEntity) {
        runCatching {
            productsDao.createProduct(product)
        }
    }

    override fun readProducts(email: String): Flow<List<ProductEntity>> =
        productsDao.readProducts(email)

    override suspend fun updateProduct(product: ProductEntity) {
        runCatching {
            productsDao.updateProduct(product)
        }
    }

    override suspend fun deleteProduct(product: ProductEntity) {
        runCatching {
            productsDao.deleteProduct(product)
        }
    }

    override suspend fun productExist(
        email: String,
        productName: String,
    ): ProductEntity? =
        runCatching {
            productsDao.productExist(email, productName)
        }.getOrNull()
}
