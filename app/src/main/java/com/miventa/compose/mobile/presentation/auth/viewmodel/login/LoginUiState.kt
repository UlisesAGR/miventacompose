/*
 * LoginUiState.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.viewmodel.login

data class LoginUiState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val passwordHidden: Boolean = false,
)
