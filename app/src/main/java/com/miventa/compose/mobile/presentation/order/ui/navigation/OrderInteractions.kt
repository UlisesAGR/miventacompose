/*
 * OrderInteractions.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.ui.navigation

data class EditProductsInteractions(
    val navigateToCreateProduct: () -> Unit,
    val navigateToUpdateProduct: (String) -> Unit,
)

data class CreateInteractions(
    val navigateToOrder: () -> Unit,
)

data class UpdateInteractions(
    val navigateToOrder: () -> Unit,
)

data class ProfileInteractions(
    val navigateToLogin: () -> Unit,
)
