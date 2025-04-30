/*
 * LoginScreen.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.ui.view.screen.login

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.domain.model.login.LoginStatus
import com.miventa.compose.mobile.presentation.auth.ui.navigation.LoginInteractions
import com.miventa.compose.mobile.presentation.auth.viewmodel.login.LoginUiEvent
import com.miventa.compose.mobile.presentation.auth.viewmodel.login.LoginViewModel
import com.miventa.compose.mobile.util.handleError
import com.miventa.compose.mobile.util.showToast
import com.miventa.compose.mobile.widget.ProgressIndicator

@Composable
fun LoginScreen(loginInteractions: LoginInteractions) {
    val context = LocalContext.current
    val viewModel: LoginViewModel = hiltViewModel()
    val loginUiState by viewModel.loginUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loginUiEvent.collect { loginUiEvent ->
            context.handleLoginEvent(loginUiEvent, loginInteractions)
        }
    }

    if (loginUiState.isLoading) {
        ProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        )
    } else {
        LoginContent(
            viewModel,
            loginUiState,
            loginInteractions,
        )
    }
}

private fun Context.handleLoginEvent(
    event: LoginUiEvent,
    interactions: LoginInteractions,
) {
    when (event) {
        is LoginUiEvent.Error -> {
            showToast(handleError(event.exception))
        }
        is LoginUiEvent.ValidateLoginForm -> {
            showToast(
                when (event.status) {
                    LoginStatus.EMPTY_EMAIL -> getString(R.string.enter_your_email)
                    LoginStatus.INVALID_EMAIL -> getString(R.string.email_is_invalid)
                    LoginStatus.PASSWORD_EMAIL -> getString(R.string.enter_your_password)
                    else -> ""
                }
            )
        }
        is LoginUiEvent.NavigateToOrder -> {
            interactions.navigateToOrder()
        }
    }
}
