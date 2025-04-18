/*
 * OrderRepositoryImpl.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.data.repository.order

import com.miventa.compose.mobile.data.datasource.order.local.OrderLocalDataSource
import com.miventa.compose.mobile.data.datasource.order.remote.OrderNetworkDataSource
import com.miventa.compose.mobile.data.local.model.ProductEntity
import com.miventa.compose.mobile.domain.repository.order.OrderRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OrderRepositoryImpl @Inject constructor(
    private val orderNetworkDataSource: OrderNetworkDataSource,
    private val orderLocalDataSource: OrderLocalDataSource,
    private val dispatcher: CoroutineDispatcher,
) : OrderRepository {

    override suspend fun verifyCurrentUser(): String = withContext(dispatcher) {
        orderNetworkDataSource.verifyCurrentUser()
    }

    override suspend fun createProduct(product: ProductEntity) = withContext(dispatcher) {
        orderLocalDataSource.createProduct(product)
    }

    override fun readProducts(email: String): Flow<List<ProductEntity>> =
        orderLocalDataSource.readProducts(email).flowOn(dispatcher)

    override suspend fun updateProduct(product: ProductEntity) = withContext(dispatcher) {
        orderLocalDataSource.updateProduct(product)
    }

    override suspend fun deleteProduct(product: ProductEntity) = withContext(dispatcher) {
        orderLocalDataSource.deleteProduct(product)
    }

    override suspend fun productExist(
        email: String,
        productName: String,
    ): ProductEntity? = withContext(dispatcher) {
        orderLocalDataSource.productExist(email, productName)
    }

    override suspend fun readInfoValue(): Boolean = withContext(dispatcher) {
        orderLocalDataSource.readInfoValue()
    }

    override suspend fun updateInfoValue(value: Boolean) = withContext(dispatcher) {
        orderLocalDataSource.updateInfoValue(value)
    }
}
