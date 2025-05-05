/*
 * RegisterScreen.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.ui.view.screen.register

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
import com.miventa.compose.mobile.domain.model.login.RegisterStatus
import com.miventa.compose.mobile.presentation.auth.ui.navigation.RegisterInteractions
import com.miventa.compose.mobile.presentation.auth.viewmodel.register.RegisterUiEvent
import com.miventa.compose.mobile.presentation.auth.viewmodel.register.RegisterViewModel
import com.miventa.compose.mobile.util.handleError
import com.miventa.compose.mobile.util.showToast
import com.miventa.compose.mobile.widget.ProgressIndicator

@Composable
fun RegisterScreen(registerInteractions: RegisterInteractions) {
    val context = LocalContext.current
    val viewModel: RegisterViewModel = hiltViewModel()
    val registerUiState by viewModel.registerUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.registerUiEvent.collect { event ->
            context.handleRegisterEvent(event, registerInteractions)
        }
    }

    if (registerUiState.isLoading) {
        ProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        )
    } else {
        RegisterContent(
            viewModel,
            registerUiState,
            registerInteractions,
        )
    }
}

private fun Context.handleRegisterEvent(
    event: RegisterUiEvent,
    interactions: RegisterInteractions,
) {
    when (event) {
        is RegisterUiEvent.Error -> {
            showToast(handleError(event.exception))
        }
        is RegisterUiEvent.ValidateRegisterForm -> {
            showToast(
                when (event.status) {
                    RegisterStatus.EMPTY_EMAIL -> getString(R.string.enter_your_email)
                    RegisterStatus.INVALID_EMAIL -> getString(R.string.email_is_invalid)
                    RegisterStatus.EMPTY_PASSWORD -> getString(R.string.enter_your_password)
                    RegisterStatus.PASSWORD_LENGTH -> getString(R.string.the_password_cannot_be_less_than_6_characters)
                    RegisterStatus.CONFIRM_PASSWORD_LENGTH -> getString(R.string.please_confirm_your_password)
                    RegisterStatus.PASSWORD_NOT_SAME -> getString(R.string.passwords_do_not_match)
                    else -> ""
                }
            )
        }
        is RegisterUiEvent.NavigateToValidateRegister -> {
            interactions.navigateToValidateRegister()
        }
    }
}
