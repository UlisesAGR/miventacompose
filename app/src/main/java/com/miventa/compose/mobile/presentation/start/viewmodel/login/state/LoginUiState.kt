/*
 * LoginUiState.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.start.viewmodel.login.state

data class LoginUiState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val passwordHidden: Boolean = true,
    val passwordConfirm: String = "",
    val passwordConfirmHidden: Boolean = true,
    val isEmailVerified: Boolean = false,
)
