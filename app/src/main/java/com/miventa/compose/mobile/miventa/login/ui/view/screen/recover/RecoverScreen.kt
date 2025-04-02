/*
 * RecoverScreen.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.miventa.login.ui.view.screen.recover

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
import com.miventa.compose.mobile.miventa.login.ui.navigation.RecoverInteractions
import com.miventa.compose.mobile.miventa.login.ui.viewmodel.LoginViewModel
import com.miventa.compose.mobile.miventa.login.ui.viewmodel.state.LoginUiEvent
import com.miventa.compose.mobile.miventa.login.ui.viewmodel.state.LoginUiState
import com.miventa.compose.mobile.theme.Screen
import com.miventa.compose.mobile.util.RecoverStatus
import com.miventa.compose.mobile.util.handleError
import com.miventa.compose.mobile.util.showToast
import com.miventa.compose.mobile.widget.ProgressIndicator

@Composable
fun RecoverScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    recoverInteractions: RecoverInteractions,
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val loginUiState by viewModel.loginUiState.collectAsState(LoginUiState())

    val loginUiEvent by viewModel.loginUiEvent.collectAsState(LoginUiEvent.Empty)

    LaunchedEffect(key1 = loginUiEvent) {
        when (loginUiEvent) {
            is LoginUiEvent.NavigateToValidateRecover -> {
                recoverInteractions.navigateToValidateRecover()
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
                RecoverForm(
                    modifier = modifier,
                    viewModel = viewModel,
                    state = loginUiState,
                    recoverInteractions = recoverInteractions,
                )
            }
        }
    }
}

private fun Context.showEventToast(event: LoginUiEvent?) =
    when (event) {
        is LoginUiEvent.Error -> showToast(handleError(event.exception))
        is LoginUiEvent.ValidateRecoverForm -> showToast(getValidationMessage(event.status))
        else -> {}
    }

private fun Context.getValidationMessage(status: RecoverStatus): String =
    when (status) {
        RecoverStatus.EMPTY_EMAIL -> getString(R.string.enter_your_email)
        RecoverStatus.INVALID_EMAIL -> getString(R.string.email_is_invalid)
    }

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.PIXEL_7,
)
@Composable
private fun RecoverScreenPreview() {
    Screen {
        RecoverScreen(
            viewModel = hiltViewModel(),
            recoverInteractions = RecoverInteractions(
                navigateToLogin = {},
                navigateToValidateRecover = {},
            ),
        )
    }
}
