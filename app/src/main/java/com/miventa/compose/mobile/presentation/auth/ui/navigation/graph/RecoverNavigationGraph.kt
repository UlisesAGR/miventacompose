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
import com.miventa.compose.mobile.presentation.auth.ui.navigation.LoginScreens
import com.miventa.compose.mobile.presentation.auth.ui.navigation.RecoverInteractions
import com.miventa.compose.mobile.presentation.auth.ui.navigation.RecoverScreens
import com.miventa.compose.mobile.presentation.auth.ui.navigation.ValidateRecoverInteractions
import com.miventa.compose.mobile.presentation.auth.ui.view.screen.recover.RecoverScreen
import com.miventa.compose.mobile.presentation.auth.ui.view.screen.recover.ValidateRecoverScreen

fun NavGraphBuilder.recoverNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.RECOVER,
        startDestination = RecoverScreens.Recover.route,
    ) {
        composable(RecoverScreens.Recover.route) {
            RecoverScreen(
                recoverInteractions = RecoverInteractions(
                    navigateToLogin = {
                        navController.popBackStack()
                    },
                    navigateToValidateRecover = {
                        navController.navigate(RecoverScreens.ValidateRecover.route) {
                            launchSingleTop = true
                        }
                    },
                ),
            )
        }
        composable(RecoverScreens.ValidateRecover.route) {
            ValidateRecoverScreen(
                validateRecoverInteractions = ValidateRecoverInteractions(
                    navigateToLogin = {
                        navController.navigate(LoginScreens.Login.route) {
                            popUpTo(LoginScreens.Login.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    },
                )
            )
        }
    }
}
