/*
 * OrderUiState.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.viewModel.order

import com.miventa.compose.mobile.domain.model.order.ProductModel

data class OrderUiState(
    val isLoading: Boolean = false,
    val productList: List<ProductModel> = emptyList(),
    val currentEmail: String = "",
)
