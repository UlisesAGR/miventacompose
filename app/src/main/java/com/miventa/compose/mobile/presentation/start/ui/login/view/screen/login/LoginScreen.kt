/*
 * LoginScreen.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.start.ui.login.view.screen.login

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
import com.miventa.compose.mobile.domain.model.LoginStatus
import com.miventa.compose.mobile.presentation.start.ui.login.navigation.LoginInteractions
import com.miventa.compose.mobile.presentation.start.viewmodel.login.LoginViewModel
import com.miventa.compose.mobile.presentation.start.viewmodel.login.state.LoginUiEvent
import com.miventa.compose.mobile.presentation.start.viewmodel.login.state.LoginUiState
import com.miventa.compose.mobile.theme.Screen
import com.miventa.compose.mobile.util.handleError
import com.miventa.compose.mobile.util.showToast
import com.miventa.compose.mobile.widget.ProgressIndicator

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    loginInteractions: LoginInteractions,
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val loginUiState by viewModel.loginUiState.collectAsState(LoginUiState())
    val loginUiEvent by viewModel.loginUiEvent.collectAsState(LoginUiEvent.Empty)

    LaunchedEffect(loginUiEvent) {
        when (loginUiEvent) {
            is LoginUiEvent.NavigateToOrder -> {
                loginInteractions.navigateToOrder()
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
                LoginForm(
                    viewModel = viewModel,
                    state = loginUiState,
                    interactions = loginInteractions,
                )
            }
        }
    }
}

private fun Context.showEventToast(event: LoginUiEvent) {
    when (event) {
        is LoginUiEvent.Error -> showToast(handleError(event.exception))
        is LoginUiEvent.ValidateLoginForm -> showToast(getValidationMessage(event.status))
        else -> {}
    }
}

private fun Context.getValidationMessage(status: LoginStatus): String =
    when (status) {
        LoginStatus.EMPTY_EMAIL -> getString(R.string.enter_your_email)
        LoginStatus.INVALID_EMAIL -> getString(R.string.email_is_invalid)
        LoginStatus.PASSWORD_EMAIL -> getString(R.string.enter_your_password)
        else -> ""
    }

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.PIXEL_7,
)
@Composable
private fun LoginScreenPreview() {
    Screen {
        LoginScreen(
            viewModel = hiltViewModel(),
            loginInteractions = LoginInteractions(
                navigateToWelcome = {},
                navigateToRecover = {},
                navigateToOrder = {},
            )
        )
    }
}
