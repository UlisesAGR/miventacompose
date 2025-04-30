/*
 * ReadCurrentUserUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.usecase.order.local

import com.miventa.compose.mobile.domain.repository.order.OrderRepository
import javax.inject.Inject

class ReadCurrentUserUseCase @Inject constructor(
    private val orderRepository: OrderRepository,
) {

    suspend operator fun invoke(): Result<String> =
        runCatching {
            orderRepository.verifyCurrentUser()
        }
}
