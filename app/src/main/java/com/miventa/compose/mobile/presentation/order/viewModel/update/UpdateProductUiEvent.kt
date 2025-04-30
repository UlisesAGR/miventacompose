/*
 * UpdateProductUiEvent.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.viewModel.update

import com.miventa.compose.mobile.domain.model.order.status.UpdateStatus

sealed class UpdateProductUiEvent {
    internal data class Error(val exception: Throwable?) : UpdateProductUiEvent()
    internal data class ValidateUpdateStatus(val status: UpdateStatus) : UpdateProductUiEvent()
    internal data object NavigateToOrder : UpdateProductUiEvent()
}
