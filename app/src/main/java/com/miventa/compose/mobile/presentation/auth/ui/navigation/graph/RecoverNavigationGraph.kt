/*
 * RecoverNavigationGraph.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.ui.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.miventa.compose.mobile.presentation.auth.ui.navigation.Graph
import com.miventa.compose.mobile.presentation.auth.ui.navigation.LoginScreen
import com.miventa.compose.mobile.presentation.auth.ui.navigation.RecoverInteractions
import com.miventa.compose.mobile.presentation.auth.ui.navigation.ValidateRecoverInteractions
import com.miventa.compose.mobile.presentation.auth.ui.view.screen.recover.RecoverScreen
import com.miventa.compose.mobile.presentation.auth.ui.view.screen.recover.ValidateRecoverScreen

fun NavGraphBuilder.recoverNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.RECOVER,
        startDestination = com.miventa.compose.mobile.presentation.auth.ui.navigation.RecoverScreen.Recover.route,
    ) {
        composable(com.miventa.compose.mobile.presentation.auth.ui.navigation.RecoverScreen.Recover.route) {
            RecoverScreen(
                recoverInteractions = RecoverInteractions(
                    navigateToLogin = {
                        navController.popBackStack()
                    },
                    navigateToValidateRecover = {
                        navController.navigate(com.miventa.compose.mobile.presentation.auth.ui.navigation.RecoverScreen.ValidateRecover.route) {
                            launchSingleTop = true
                        }
                    },
                ),
            )
        }
        composable(com.miventa.compose.mobile.presentation.auth.ui.navigation.RecoverScreen.ValidateRecover.route) {
            ValidateRecoverScreen(
                validateRecoverInteractions = ValidateRecoverInteractions(
                    navigateToLogin = {
                        navController.navigate(LoginScreen.Login.route) {
                            popUpTo(LoginScreen.Login.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                )
            )
        }
    }
}
