/*
 * OrderUiState.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.viewModel.state

data class OrderUiState(
    val isLoading: Boolean = false,
    val currentEmail: String = "",
)
