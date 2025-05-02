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
import com.miventa.compose.mobile.presentation.auth.ui.navigation.RegisterScreens
import com.miventa.compose.mobile.presentation.auth.ui.navigation.RegisterSuccessScreenInteractions
import com.miventa.compose.mobile.presentation.auth.ui.navigation.ValidateRegisterInteractions
import com.miventa.compose.mobile.presentation.auth.ui.navigation.WelcomeScreens
import com.miventa.compose.mobile.presentation.auth.ui.view.screen.register.RegisterScreen
import com.miventa.compose.mobile.presentation.auth.ui.view.screen.register.RegisterSuccessScreen
import com.miventa.compose.mobile.presentation.auth.ui.view.screen.register.ValidateRegisterScreen

fun NavGraphBuilder.registerNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.REGISTER,
        startDestination = RegisterScreens.Register.route,
    ) {
        composable(RegisterScreens.Register.route) {
            RegisterScreen(
                registerInteractions = RegisterInteractions(
                    navigateToWelcome = {
                        navController.popBackStack()
                    },
                    navigateToValidateRegister = {
                        navController.navigate(RegisterScreens.ValidateRegister.route) {
                            launchSingleTop = true
                        }
                    },
                )
            )
        }
        composable(RegisterScreens.ValidateRegister.route) {
            ValidateRegisterScreen(
                validateRegisterInteractions = ValidateRegisterInteractions(
                    navigateToRegister = {
                        navController.popBackStack()
                    },
                    navigateToRegisterSuccess = {
                        navController.navigate(RegisterScreens.RegisterSuccess.route) {
                            launchSingleTop = true
                        }
                    },
                )
            )
        }
        composable(RegisterScreens.RegisterSuccess.route) {
            RegisterSuccessScreen(
                registerSuccessScreenInteractions = RegisterSuccessScreenInteractions(
                    navigateToWelcome = {
                        navController.navigate(WelcomeScreens.Welcome.route) {
                            popUpTo(WelcomeScreens.Welcome.route) { inclusive = false }
                            launchSingleTop = true
                        }
                    },
                )
            )
        }
    }
}
