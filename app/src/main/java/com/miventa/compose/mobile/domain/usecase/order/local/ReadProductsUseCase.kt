/*
 * ReadProductsUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.usecase.order.local

import com.miventa.compose.mobile.domain.mapper.toDomain
import com.miventa.compose.mobile.domain.model.order.ProductModel
import com.miventa.compose.mobile.domain.repository.order.OrderRepository
import com.miventa.compose.mobile.util.NoReadProductsException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReadProductsUseCase @Inject constructor(
    private val orderRepository: OrderRepository,
) {

    suspend operator fun invoke(): Flow<List<ProductModel>> {
        val email = orderRepository.verifyCurrentUser()
        return if (email.isNotEmpty()) {
            orderRepository.readProducts(email).map { list ->
                list.map { product ->
                    product.toDomain()
                }
            }
        } else throw NoReadProductsException()
    }
}
