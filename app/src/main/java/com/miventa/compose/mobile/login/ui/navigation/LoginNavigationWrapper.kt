package com.miventa.compose.mobile.login.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.miventa.compose.mobile.login.ui.view.screen.login.LoginScreen
import com.miventa.compose.mobile.login.ui.view.screen.recover.RecoverScreen
import com.miventa.compose.mobile.login.ui.view.screen.recover.ValidateRecoverScreen
import com.miventa.compose.mobile.login.ui.view.screen.register.RegisterScreen
import com.miventa.compose.mobile.login.ui.view.screen.register.RegisterSuccessScreen
import com.miventa.compose.mobile.login.ui.view.screen.register.ValidateRegisterScreen
import com.miventa.compose.mobile.login.ui.view.screen.welcome.WelcomeScreen

@Composable
fun NavigationWrapper() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Welcome) {
        composable<Welcome> {
            WelcomeScreen(
                navigateToLogin = {
                    navController.navigate(Login)
                },
                navigateToRegister = {
                    navController.navigate(Register)
                },
            )
        }
        composable<Login> {
            LoginScreen(
                navigateToWelcome = {
                    navController.popBackStack()
                },
                navigateToRecover = {
                    navController.navigate(Recover)
                },
            )
        }
        composable<Recover> {
            RecoverScreen(
                navigateToLogin = {
                    navController.popBackStack()
                },
            )
        }
        composable<ValidateRecover> {
            ValidateRecoverScreen(
                navigateToLogin = {
                    navController.navigate(Login) {
                        popUpTo(Login) { inclusive = true }
                    }
                },
            )
        }
        composable<Register> {
            RegisterScreen(
                navigateToWelcome = {
                    navController.popBackStack()
                },
                navigateToValidateRegister = {
                    navController.navigate(ValidateRegister)
                },
            )
        }
        composable<ValidateRegister> {
            ValidateRegisterScreen(
                navigateToRegister = {
                    navController.popBackStack()
                },
                navigateToRegisterSuccess = {
                    navController.navigate(RegisterSuccess)
                },
            )
        }
        composable<RegisterSuccess> {
            RegisterSuccessScreen(
                navigateToWelcome = {
                    navController.navigate(Welcome) {
                        popUpTo(Welcome) { inclusive = true }
                    }
                },
            )
        }
    }
}
