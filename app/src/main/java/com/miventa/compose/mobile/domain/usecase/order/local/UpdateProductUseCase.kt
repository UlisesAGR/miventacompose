/*
 * UpdateProductUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.usecase.order.local

import com.miventa.compose.mobile.domain.mapper.toEntity
import com.miventa.compose.mobile.domain.model.order.ProductModel
import com.miventa.compose.mobile.domain.repository.order.OrderRepository
import com.miventa.compose.mobile.util.ExistProductException
import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(
    private val orderRepository: OrderRepository,
) {

    suspend operator fun invoke(
        image: String,
        name: String,
        price: String,
    ): Result<Unit> =
        runCatching {
            val email = orderRepository.verifyCurrentUser()
            if (orderRepository.productExist(email, name) == null) {
                val product = ProductModel(
                    id = 0,
                    name,
                    price = price.toDouble(),
                    quantity = 0,
                    image,
                    email,
                )
                orderRepository.createProduct(product.toEntity())
            } else throw ExistProductException()
        }
}
