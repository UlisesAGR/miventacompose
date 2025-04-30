/*
 * ValidationUpdateProductUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.usecase.order.validation

import com.miventa.compose.mobile.domain.model.order.status.UpdateStatus
import javax.inject.Inject

class ValidationUpdateProductUseCase @Inject constructor() {

    operator fun invoke(
        name: String,
        price: String,
    ): UpdateStatus =
        when {
            name.isEmpty() -> UpdateStatus.EMPTY_NAME
            price.isEmpty() -> UpdateStatus.EMPTY_PRICE
            else -> UpdateStatus.SUCCESS
        }
}
