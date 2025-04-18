/*
 * UpdateProductUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.usecase.order

import com.miventa.compose.mobile.domain.mapper.toEntity
import com.miventa.compose.mobile.domain.model.order.ProductModel
import com.miventa.compose.mobile.domain.repository.order.OrderRepository
import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(
    private val orderRepository: OrderRepository,
) {

    suspend operator fun invoke(product: ProductModel): Result<Unit> =
        runCatching {
            orderRepository.updateProduct(product.toEntity())
        }
}
