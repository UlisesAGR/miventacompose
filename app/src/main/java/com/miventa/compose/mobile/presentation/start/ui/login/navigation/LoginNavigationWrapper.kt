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
                        navController.navigate(Login) {
                            popUpTo(Login) { inclusive = true }
                        }
                        viewModel.clearUiState()
                    },
                    navigateToRegister = {
                        navController.navigate(Register)
                        viewModel.clearUiState()
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
                        navController.popBackStack()
                        viewModel.clearUiState()
                    },
                    navigateToRecover = {
                        navController.navigate(Recover)
                        viewModel.clearUiState()
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
                        navController.popBackStack()
                        viewModel.clearUiState()
                    },
                    navigateToValidateRecover = {
                        navController.navigate(ValidateRecover)
                        viewModel.clearUiState()
                    },
                ),
            )
        }
        composable<ValidateRecover> {
            ValidateRecoverScreen(
                navigateToLogin = {
                    navController.navigate(Login) {
                        popUpTo(Login) { inclusive = true }
                    }
                    viewModel.clearUiState()
                },
            )
        }
        composable<Register> {
            RegisterScreen(
                viewModel,
                loginUiState,
                registerInteractions = RegisterInteractions(
                    navigateToWelcome = {
                        navController.popBackStack()
                        viewModel.clearUiState()
                    },
                    navigateToValidateRegister = {
                        navController.navigate(ValidateRegister)
                        viewModel.clearUiState()
                    },
                )
            )
        }
        composable<ValidateRegister> {
            ValidateRegisterScreen(
                loginUiState,
                validateRegisterInteractions = ValidateRegisterInteractions(
                    navigateToRegister = {
                        navController.popBackStack()
                        viewModel.clearUiState()
                    },
                    navigateToRegisterSuccess = {
                        navController.navigate(RegisterSuccess) {
                            popUpTo(ValidateRegister) { inclusive = true }
                        }
                        viewModel.clearUiState()
                    }
                )
            )
        }
        composable<RegisterSuccess> {
            RegisterSuccessScreen(
                navigateToWelcome = {
                    navController.navigate(Welcome) {
                        popUpTo(Welcome) { inclusive = false }
                    }
                    viewModel.clearUiState()
                },
            )
        }
    }
}
