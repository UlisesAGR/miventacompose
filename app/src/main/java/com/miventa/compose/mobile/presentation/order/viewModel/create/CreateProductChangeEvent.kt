/*
 * CreateProductChangeEvent.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.viewModel.create

sealed class CreateProductChangeEvent {
    data class Name(val name: String) : CreateProductChangeEvent()
    data class Price(val price: String) : CreateProductChangeEvent()
}
