/*
 * ProductExistUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.usecase.order.local

import com.miventa.compose.mobile.domain.mapper.toDomain
import com.miventa.compose.mobile.domain.model.order.ProductModel
import com.miventa.compose.mobile.domain.repository.order.OrderRepository
import com.miventa.compose.mobile.util.NoExistProductException
import javax.inject.Inject

class ProductExistUseCase @Inject constructor(
    private val orderRepository: OrderRepository,
) {

    suspend operator fun invoke(name: String): Result<ProductModel> =
        runCatching {
            val email = orderRepository.verifyCurrentUser()
            orderRepository.productExist(email, name)?.toDomain()
                ?: run { throw NoExistProductException() }
        }
}
