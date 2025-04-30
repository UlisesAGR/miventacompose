/*
 * ValidationCreateProductUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.usecase.order.validation

import com.miventa.compose.mobile.domain.model.order.status.CreateStatus
import javax.inject.Inject

class ValidationCreateProductUseCase @Inject constructor() {

    operator fun invoke(
        name: String,
        price: String,
    ): CreateStatus =
        when {
            name.isEmpty() -> CreateStatus.EMPTY_NAME
            price.isEmpty() -> CreateStatus.EMPTY_PRICE
            else -> CreateStatus.SUCCESS
        }
}
