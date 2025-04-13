/*
 * LoginActivity.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.start.ui.login.view

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.domain.model.LoginStatus
import com.miventa.compose.mobile.domain.model.RecoverStatus
import com.miventa.compose.mobile.domain.model.RegisterStatus
import com.miventa.compose.mobile.presentation.order.OrderActivity
import com.miventa.compose.mobile.presentation.start.ui.login.navigation.LoginNavigationWrapper
import com.miventa.compose.mobile.presentation.start.viewmodel.login.LoginViewModel
import com.miventa.compose.mobile.presentation.start.viewmodel.login.state.LoginUiEvent
import com.miventa.compose.mobile.theme.Screen
import com.miventa.compose.mobile.util.handleError
import com.miventa.compose.mobile.util.nextActivityEnd
import com.miventa.compose.mobile.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContentScreen()
        }
    }

    @Composable
    private fun ContentScreen() {
        Screen {
            val loginUiEvent by viewModel.loginUiEvent.collectAsState(LoginUiEvent.Empty)

            LaunchedEffect(loginUiEvent) {
                if (loginUiEvent is LoginUiEvent.Error) {
                    showEventToast(loginUiEvent)
                }
            }

            LoginNavigationWrapper(
                viewModel,
                navigateToOrder = {
                    goToOrder()
                },
            )
        }
    }

    private fun Context.showEventToast(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.Error -> showToast(handleError(event.exception))
            is LoginUiEvent.ValidateLoginForm -> showToast(getLoginValidationMessage(event.status))
            is LoginUiEvent.ValidateRecoverForm -> showToast(getRecoverValidationMessage(event.status))
            is LoginUiEvent.ValidateRegisterForm -> showToast(getRegisterValidationMessage(event.status))
            else -> {}
        }
    }

    private fun Context.getLoginValidationMessage(status: LoginStatus): String =
        when (status) {
            LoginStatus.EMPTY_EMAIL -> getString(R.string.enter_your_email)
            LoginStatus.INVALID_EMAIL -> getString(R.string.email_is_invalid)
            LoginStatus.PASSWORD_EMAIL -> getString(R.string.enter_your_password)
            else -> ""
        }

    private fun Context.getRecoverValidationMessage(status: RecoverStatus): String =
        when (status) {
            RecoverStatus.EMPTY_EMAIL -> getString(R.string.enter_your_email)
            RecoverStatus.INVALID_EMAIL -> getString(R.string.email_is_invalid)
            else -> ""
        }

    private fun Context.getRegisterValidationMessage(status: RegisterStatus): String =
        when (status) {
            RegisterStatus.EMPTY_EMAIL -> getString(R.string.enter_your_email)
            RegisterStatus.INVALID_EMAIL -> getString(R.string.email_is_invalid)
            RegisterStatus.EMPTY_PASSWORD -> getString(R.string.enter_your_password)
            RegisterStatus.PASSWORD_LENGTH -> getString(R.string.the_password_cannot_be_less_than_6_characters)
            RegisterStatus.CONFIRM_PASSWORD_LENGTH -> getString(R.string.please_confirm_your_password)
            RegisterStatus.PASSWORD_NOT_SAME -> getString(R.string.passwords_do_not_match)
            else -> ""
        }

    private fun goToOrder() {
        nextActivityEnd(
            destination = OrderActivity(),
            animIn = R.anim.slide_in_left,
            animOut = R.anim.slide_out_left,
        )
    }
}
