/*
 * RegisterUiState.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.viewmodel.register

data class RegisterUiState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val passwordConfirm: String = "",
    val passwordHidden: Boolean = true,
    val passwordConfirmHidden: Boolean = true,
    val isEmailVerified: Boolean = false,
)
