/*
 * OrderUiEvent.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.viewModel.state

sealed class OrderUiEvent {
    internal data class Loading(val isLoading: Boolean) : OrderUiEvent()
    internal data class Error(val exception: Throwable?) : OrderUiEvent()
    internal data object NavigateToLogin : OrderUiEvent()
}
