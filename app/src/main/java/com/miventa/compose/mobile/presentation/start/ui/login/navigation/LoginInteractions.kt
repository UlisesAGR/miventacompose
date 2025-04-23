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
)

data class RecoverInteractions(
    val navigateToLogin: () -> Unit,
)

data class RegisterInteractions(
    val navigateToWelcome: () -> Unit,
)

data class ValidateRegisterInteractions(
    val navigateToRegister: () -> Unit,
    val navigateToRegisterSuccess: () -> Unit,
)
