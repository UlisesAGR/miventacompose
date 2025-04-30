/*
 * AuthInteractions.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.ui.navigation

data class WelcomeInteractions(
    val navigateToLogin: () -> Unit,
    val navigateToRegister: () -> Unit,
)

data class LoginInteractions(
    val navigateToWelcome: () -> Unit,
    val navigateToRecover: () -> Unit,
    val navigateToOrder: () -> Unit,
)

data class RecoverInteractions(
    val navigateToLogin: () -> Unit,
    val navigateToValidateRecover: () -> Unit,
)

data class ValidateRecoverInteractions(
    val navigateToLogin: () -> Unit
)

data class RegisterInteractions(
    val navigateToWelcome: () -> Unit,
    val navigateToValidateRegister: () -> Unit,
)

data class ValidateRegisterInteractions(
    val navigateToRegister: () -> Unit,
    val navigateToRegisterSuccess: () -> Unit,
)

data class RegisterSuccessScreenInteractions(
    val navigateToWelcome: () -> Unit,
)
