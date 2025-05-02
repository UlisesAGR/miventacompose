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

sealed class WelcomeScreens(val route: String) {
    data object Welcome : WelcomeScreens(route = "welcome")
}

sealed class LoginScreens(val route: String) {
    data object Login : LoginScreens(route = "login")
}

sealed class RecoverScreens(val route: String) {
    data object Recover : RecoverScreens(route = "recover")
    data object ValidateRecover : RecoverScreens(route = "validate_recover")
}

sealed class RegisterScreens(val route: String) {
    data object Register : RegisterScreens(route = "register")
    data object ValidateRegister : RegisterScreens(route = "validate_register")
    data object RegisterSuccess : RegisterScreens(route = "register_success")
}
