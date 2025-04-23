/*
 * LoginNavigationWrapper.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.start.ui.login.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.miventa.compose.mobile.presentation.start.ui.login.view.screen.login.LoginScreen
import com.miventa.compose.mobile.presentation.start.ui.login.view.screen.recover.RecoverScreen
import com.miventa.compose.mobile.presentation.start.ui.login.view.screen.recover.ValidateRecoverScreen
import com.miventa.compose.mobile.presentation.start.ui.login.view.screen.register.RegisterScreen
import com.miventa.compose.mobile.presentation.start.ui.login.view.screen.register.RegisterSuccessScreen
import com.miventa.compose.mobile.presentation.start.ui.login.view.screen.register.ValidateRegisterScreen
import com.miventa.compose.mobile.presentation.start.ui.login.view.screen.welcome.WelcomeScreen
import com.miventa.compose.mobile.presentation.start.viewmodel.login.LoginViewModel
import com.miventa.compose.mobile.presentation.start.viewmodel.login.state.LoginUiState

@Composable
fun LoginNavigationWrapper(
    navController: NavHostController,
    viewModel: LoginViewModel,
    loginUiState: LoginUiState,
) {
    NavHost(
        navController = navController,
        startDestination = Welcome,
    ) {
        composable<Welcome> {
            WelcomeScreen(
                welcomeInteractions = WelcomeInteractions(
                    navigateToLogin = {
                        viewModel.clearUiState()
                        navController.navigate(Login) {
                            popUpTo(Login) { inclusive = true }
                        }
                    },
                    navigateToRegister = {
                        viewModel.clearUiState()
                        navController.navigate(Register)
                    },
                )
            )
        }
        composable<Login> {
            LoginScreen(
                viewModel,
                loginUiState,
                loginInteractions = LoginInteractions(
                    navigateToWelcome = {
                        viewModel.clearUiState()
                        navController.popBackStack()
                    },
                    navigateToRecover = {
                        viewModel.clearUiState()
                        navController.navigate(Recover)
                    },
                )
            )
        }
        composable<Recover> {
            RecoverScreen(
                viewModel,
                loginUiState,
                recoverInteractions = RecoverInteractions(
                    navigateToLogin = {
                        viewModel.clearUiState()
                        navController.popBackStack()
                    },
                ),
            )
        }
        composable<ValidateRecover> {
            ValidateRecoverScreen(
                navigateToLogin = {
                    viewModel.clearUiState()
                    navController.navigate(Login) {
                        popUpTo(Login) { inclusive = true }
                    }
                },
            )
        }
        composable<Register> {
            RegisterScreen(
                viewModel,
                loginUiState,
                registerInteractions = RegisterInteractions(
                    navigateToWelcome = {
                        viewModel.clearUiState()
                        navController.popBackStack()
                    },
                )
            )
        }
        composable<ValidateRegister> {
            ValidateRegisterScreen(
                loginUiState,
                validateRegisterInteractions = ValidateRegisterInteractions(
                    navigateToRegister = {
                        viewModel.clearUiState()
                        navController.popBackStack()
                    },
                    navigateToRegisterSuccess = {
                        viewModel.clearUiState()
                        navController.navigate(RegisterSuccess) {
                            popUpTo(ValidateRegister) { inclusive = true }
                        }
                    }
                )
            )
        }
        composable<RegisterSuccess> {
            RegisterSuccessScreen(
                navigateToWelcome = {
                    viewModel.clearUiState()
                    navController.navigate(Welcome) {
                        popUpTo(Welcome) { inclusive = false }
                    }
                },
            )
        }
    }
}
