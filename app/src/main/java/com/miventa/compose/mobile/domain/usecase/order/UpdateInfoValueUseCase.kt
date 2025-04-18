/*
 * UpdateInfoValueUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.usecase.order

import com.miventa.compose.mobile.domain.repository.order.OrderRepository
import javax.inject.Inject

class UpdateInfoValueUseCase @Inject constructor(
    private val orderRepository: OrderRepository,
) {

    suspend operator fun invoke(value: Boolean): Result<Unit> =
        runCatching {
            orderRepository.updateInfoValue(value)
        }
}
