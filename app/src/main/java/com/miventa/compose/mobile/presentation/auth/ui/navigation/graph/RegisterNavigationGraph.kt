/*
 * RegisterNavigationGraph.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.ui.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.miventa.compose.mobile.presentation.auth.ui.navigation.Graph
import com.miventa.compose.mobile.presentation.auth.ui.navigation.RegisterInteractions
import com.miventa.compose.mobile.presentation.auth.ui.navigation.RegisterSuccessScreenInteractions
import com.miventa.compose.mobile.presentation.auth.ui.navigation.ValidateRegisterInteractions
import com.miventa.compose.mobile.presentation.auth.ui.navigation.WelcomeScreen
import com.miventa.compose.mobile.presentation.auth.ui.view.screen.register.RegisterScreen
import com.miventa.compose.mobile.presentation.auth.ui.view.screen.register.RegisterSuccessScreen
import com.miventa.compose.mobile.presentation.auth.ui.view.screen.register.ValidateRegisterScreen

fun NavGraphBuilder.registerNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.REGISTER,
        startDestination = com.miventa.compose.mobile.presentation.auth.ui.navigation.RegisterScreen.Register.route,
    ) {
        composable(com.miventa.compose.mobile.presentation.auth.ui.navigation.RegisterScreen.Register.route) {
            RegisterScreen(
                registerInteractions = RegisterInteractions(
                    navigateToWelcome = {
                        navController.popBackStack()
                    },
                    navigateToValidateRegister = {
                        navController.navigate(com.miventa.compose.mobile.presentation.auth.ui.navigation.RegisterScreen.ValidateRegister.route) {
                            launchSingleTop = true
                        }
                    },
                )
            )
        }
        composable(com.miventa.compose.mobile.presentation.auth.ui.navigation.RegisterScreen.ValidateRegister.route) {
            ValidateRegisterScreen(
                validateRegisterInteractions = ValidateRegisterInteractions(
                    navigateToRegister = {
                        navController.popBackStack()
                    },
                    navigateToRegisterSuccess = {
                        navController.navigate(com.miventa.compose.mobile.presentation.auth.ui.navigation.RegisterScreen.RegisterSuccess.route) {
                            launchSingleTop = true
                        }
                    },
                )
            )
        }
        composable(com.miventa.compose.mobile.presentation.auth.ui.navigation.RegisterScreen.RegisterSuccess.route) {
            RegisterSuccessScreen(
                registerSuccessScreenInteractions = RegisterSuccessScreenInteractions(
                    navigateToWelcome = {
                        navController.navigate(WelcomeScreen.Welcome.route) {
                            popUpTo(WelcomeScreen.Welcome.route) { inclusive = false }
                            launchSingleTop = true
                        }
                    },
                )
            )
        }
    }
}
