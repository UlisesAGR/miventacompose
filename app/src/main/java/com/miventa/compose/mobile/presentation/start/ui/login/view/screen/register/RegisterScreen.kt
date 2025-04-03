/*
 * RegisterScreen.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.start.ui.login.view.screen.register

import android.content.Context
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.domain.model.RegisterStatus
import com.miventa.compose.mobile.presentation.start.ui.login.navigation.RegisterInteractions
import com.miventa.compose.mobile.presentation.start.viewmodel.login.LoginViewModel
import com.miventa.compose.mobile.presentation.start.viewmodel.login.state.LoginUiEvent
import com.miventa.compose.mobile.presentation.start.viewmodel.login.state.LoginUiState
import com.miventa.compose.mobile.theme.Screen
import com.miventa.compose.mobile.util.handleError
import com.miventa.compose.mobile.util.showToast
import com.miventa.compose.mobile.widget.ProgressIndicator

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    registerInteractions: RegisterInteractions,
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val loginUiState by viewModel.loginUiState.collectAsState(LoginUiState())

    val loginUiEvent by viewModel.loginUiEvent.collectAsState(LoginUiEvent.Empty)

    LaunchedEffect(key1 = loginUiEvent) {
        when (loginUiEvent) {
            is LoginUiEvent.NavigateToValidateRegister -> {
                registerInteractions.navigateToValidateRegister()
            }

            else -> {
                context.showEventToast(loginUiEvent)
            }
        }
    }

    Crossfade(targetState = loginUiState.isLoading) { content ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_big))
                .verticalScroll(scrollState),
        ) {
            if (content) {
                ProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                RegisterForm(
                    modifier = modifier,
                    viewModel = viewModel,
                    state = loginUiState,
                    registerInteractions = registerInteractions,
                )
            }
        }
    }
}

private fun Context.showEventToast(event: LoginUiEvent?) =
    when (event) {
        is LoginUiEvent.Error -> showToast(handleError(event.exception))
        is LoginUiEvent.ValidateRegisterForm -> showToast(getValidationMessage(event.status))
        else -> {}
    }

private fun Context.getValidationMessage(status: RegisterStatus): String =
    when (status) {
        RegisterStatus.EMPTY_EMAIL -> getString(R.string.enter_your_email)
        RegisterStatus.INVALID_EMAIL -> getString(R.string.email_is_invalid)
        RegisterStatus.EMPTY_PASSWORD -> getString(R.string.enter_your_password)
        RegisterStatus.PASSWORD_LENGTH -> getString(R.string.the_password_cannot_be_less_than_6_characters)
        RegisterStatus.CONFIRM_PASSWORD_LENGTH -> getString(R.string.please_confirm_your_password)
        RegisterStatus.PASSWORD_NOT_SAME -> getString(R.string.passwords_do_not_match)
        else -> ""
    }

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.PIXEL_7,
)
@Composable
private fun RegisterScreenPreview() {
    Screen {
        RegisterScreen(
            viewModel = hiltViewModel(),
            registerInteractions = RegisterInteractions(
                navigateToWelcome = {},
                navigateToValidateRegister = {},
            )
        )
    }
}
