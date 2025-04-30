/*
 * UpdateProductChangeEvent.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.viewModel.update

sealed class UpdateProductChangeEvent {
    data class Name(val name: String) : UpdateProductChangeEvent()
    data class Price(val price: String) : UpdateProductChangeEvent()
}
