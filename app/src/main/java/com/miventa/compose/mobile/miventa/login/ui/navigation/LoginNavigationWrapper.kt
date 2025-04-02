/*
 * LoginNavigationWrapper.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.miventa.login.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.miventa.compose.mobile.miventa.login.ui.view.screen.login.LoginScreen
import com.miventa.compose.mobile.miventa.login.ui.view.screen.recover.RecoverScreen
import com.miventa.compose.mobile.miventa.login.ui.view.screen.recover.ValidateRecoverScreen
import com.miventa.compose.mobile.miventa.login.ui.view.screen.register.RegisterScreen
import com.miventa.compose.mobile.miventa.login.ui.view.screen.register.RegisterSuccessScreen
import com.miventa.compose.mobile.miventa.login.ui.view.screen.register.ValidateRegisterScreen
import com.miventa.compose.mobile.miventa.login.ui.view.screen.welcome.WelcomeScreen
import com.miventa.compose.mobile.miventa.login.ui.viewmodel.LoginViewModel

@Composable
fun LoginNavigationWrapper(
    viewModel: LoginViewModel,
    navigateToOrder: () -> Unit = {},
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Welcome,
    ) {
        composable<Welcome> {
            WelcomeScreen(
                welcomeInteractions = WelcomeInteractions(
                    navigateToLogin = {
                        navController.navigate(Login)
                    },
                    navigateToRegister = {
                        navController.navigate(Register)
                    },
                )
            )
        }
        composable<Login> {
            viewModel.clearUiState()
            LoginScreen(
                viewModel = viewModel,
                loginInteractions = LoginInteractions(
                    navigateToWelcome = {
                        navController.popBackStack()
                    },
                    navigateToRecover = {
                        navController.navigate(Recover)
                    },
                    navigateToOrder = {
                        navigateToOrder()
                    },
                )
            )
        }
        composable<Recover> {
            viewModel.clearUiState()
            RecoverScreen(
                viewModel = viewModel,
                recoverInteractions = RecoverInteractions(
                    navigateToLogin = {
                        navController.popBackStack()
                    },
                    navigateToValidateRecover = {
                        navController.navigate(ValidateRecover)
                    },
                ),
            )
        }
        composable<ValidateRecover> {
            viewModel.clearUiState()
            ValidateRecoverScreen(
                navigateToLogin = {
                    navController.navigate(Login) {
                        popUpTo(Login) { inclusive = true }
                    }
                },
            )
        }
        composable<Register> {
            viewModel.clearUiState()
            RegisterScreen(
                viewModel = viewModel,
                registerInteractions = RegisterInteractions(
                    navigateToWelcome = {
                        navController.popBackStack()
                    },
                    navigateToValidateRegister = {
                        navController.navigate(ValidateRegister)
                    },
                )
            )
        }
        composable<ValidateRegister> {
            viewModel.clearUiState()
            viewModel.verifyEmailVerified()
            ValidateRegisterScreen(
                viewModel = viewModel,
                validateRegisterInteractions = ValidateRegisterInteractions(
                    navigateToRegister = {
                        navController.popBackStack()
                    },
                    navigateToRegisterSuccess = {
                        navController.navigate(RegisterSuccess)
                    },
                )
            )
        }
        composable<RegisterSuccess> {
            viewModel.clearUiState()
            RegisterSuccessScreen(
                navigateToWelcome = {
                    navController.navigate(Welcome) {
                        popUpTo(Welcome) { inclusive = false }
                    }
                },
            )
        }
    }
}
