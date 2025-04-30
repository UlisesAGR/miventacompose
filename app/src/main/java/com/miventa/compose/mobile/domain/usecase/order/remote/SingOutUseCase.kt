/*
 * SingOutUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.usecase.order.remote

import com.miventa.compose.mobile.domain.repository.order.OrderRepository
import javax.inject.Inject

class SingOutUseCase @Inject constructor(
    private val orderRepository: OrderRepository,
) {

    suspend operator fun invoke(): Result<Unit> =
        runCatching {
            orderRepository.signOut()
        }
}
