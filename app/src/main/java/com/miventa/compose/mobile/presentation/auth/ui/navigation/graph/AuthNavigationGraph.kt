/*
 * AuthNavigationGraph.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.miventa.compose.mobile.presentation.auth.ui.navigation.Graph
import com.miventa.compose.mobile.presentation.auth.ui.navigation.LoginInteractions
import com.miventa.compose.mobile.presentation.auth.ui.navigation.LoginScreens
import com.miventa.compose.mobile.presentation.auth.ui.navigation.WelcomeInteractions
import com.miventa.compose.mobile.presentation.auth.ui.navigation.WelcomeScreens
import com.miventa.compose.mobile.presentation.auth.ui.view.screen.login.LoginScreen
import com.miventa.compose.mobile.presentation.auth.ui.view.screen.welcome.WelcomeScreen

@Composable
fun AuthNavigationGraph(navigateToOrder: () -> Unit) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        route = Graph.AUTH,
        startDestination = WelcomeScreens.Welcome.route,
    ) {
        composable(WelcomeScreens.Welcome.route) {
            WelcomeScreen(
                welcomeInteractions = WelcomeInteractions(
                    navigateToLogin = {
                        navController.navigate(LoginScreens.Login.route) {
                            launchSingleTop = true
                        }
                    },
                    navigateToRegister = {
                        navController.navigate(Graph.REGISTER) {
                            launchSingleTop = true
                        }
                    },
                )
            )
        }
        composable(LoginScreens.Login.route) {
            LoginScreen(
                loginInteractions = LoginInteractions(
                    navigateToWelcome = {
                        navController.popBackStack()
                    },
                    navigateToRecover = {
                        navController.navigate(Graph.RECOVER) {
                            launchSingleTop = true
                        }
                    },
                    navigateToOrder = navigateToOrder,
                )
            )
        }
        recoverNavGraph(navController)
        registerNavGraph(navController)
    }
}
