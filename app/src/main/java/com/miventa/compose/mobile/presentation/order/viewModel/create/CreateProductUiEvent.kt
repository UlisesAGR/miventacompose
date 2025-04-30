/*
 * CreateProductUiEvent.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.viewModel.create

import com.miventa.compose.mobile.domain.model.order.status.CreateStatus

sealed class CreateProductUiEvent {
    internal data class Error(val exception: Throwable?) : CreateProductUiEvent()
    internal data class ValidateCreateStatus(val status: CreateStatus) : CreateProductUiEvent()
    internal data object NavigateToOrder : CreateProductUiEvent()
}
