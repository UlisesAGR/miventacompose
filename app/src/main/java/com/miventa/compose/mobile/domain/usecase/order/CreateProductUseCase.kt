/*
 * CreateProductUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.usecase.order

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
            if (orderRepository.productExist(email, product.name) == null) {
                product.userEmail = email
                orderRepository.createProduct(product.toEntity())
            } else throw ExistProductException()
        }
}
