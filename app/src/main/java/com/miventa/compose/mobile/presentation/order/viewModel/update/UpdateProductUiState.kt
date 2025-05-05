/*
 * UpdateProductUiState.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.viewModel.update

import com.miventa.compose.mobile.domain.model.order.ProductModel

data class UpdateProductUiState(
    val product: ProductModel = ProductModel(
        id = 0,
        name = "",
        price = "",
        quantity = "",
        image = "",
        userEmail = "",
    ),
)
