/*
 * LoginInteractions.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.start.ui.login.navigation

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

data class RegisterInteractions(
    val navigateToWelcome: () -> Unit,
    val navigateToValidateRegister: () -> Unit,
)

data class ValidateRegisterInteractions(
    val navigateToRegister: () -> Unit,
    val navigateToRegisterSuccess: () -> Unit,
)
