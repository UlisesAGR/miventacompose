/*
 * CreateProductUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.usecase.order.local

import com.miventa.compose.mobile.domain.mapper.toEntity
import com.miventa.compose.mobile.domain.model.order.ProductModel
import com.miventa.compose.mobile.domain.repository.order.OrderRepository
import com.miventa.compose.mobile.util.ExistProductException
import javax.inject.Inject

class CreateProductUseCase @Inject constructor(
    private val orderRepository: OrderRepository,
) {

    suspend operator fun invoke(product: ProductModel): Result<Unit> =
        runCatching {
            val email = orderRepository.verifyCurrentUser()
            orderRepository.productExist(email, productName = product.name)?.let {
                throw ExistProductException()
            } ?: product.copy(userEmail = email)
                .toEntity()
                .also { product ->
                    orderRepository.createProduct(product)
                }
        }
}
