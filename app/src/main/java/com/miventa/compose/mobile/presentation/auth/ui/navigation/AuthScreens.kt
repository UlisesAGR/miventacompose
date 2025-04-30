/*
 * AuthScreens.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.ui.navigation

object Graph {
    const val AUTH = "auth_graph"
    const val RECOVER = "recover_graph"
    const val REGISTER = "register_graph"
}

sealed class WelcomeScreen(val route: String) {
    data object Welcome : WelcomeScreen(route = "welcome")
}

sealed class LoginScreen(val route: String) {
    data object Login : WelcomeScreen(route = "login")
}

sealed class RecoverScreen(val route: String) {
    data object Recover : WelcomeScreen(route = "recover")
    data object ValidateRecover : WelcomeScreen(route = "validate_recover")
}

sealed class RegisterScreen(val route: String) {
    data object Register : WelcomeScreen(route = "register")
    data object ValidateRegister : WelcomeScreen(route = "validate_register")
    data object RegisterSuccess : WelcomeScreen(route = "register_success")
}
