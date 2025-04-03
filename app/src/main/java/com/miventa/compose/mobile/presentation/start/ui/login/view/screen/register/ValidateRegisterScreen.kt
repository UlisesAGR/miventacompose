/*
 * ValidateRegisterScreen.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.start.ui.login.view.screen.register

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.presentation.start.ui.login.navigation.ValidateRegisterInteractions
import com.miventa.compose.mobile.presentation.start.viewmodel.login.LoginViewModel
import com.miventa.compose.mobile.presentation.start.viewmodel.login.state.LoginUiEvent
import com.miventa.compose.mobile.presentation.start.viewmodel.login.state.LoginUiState
import com.miventa.compose.mobile.theme.Screen
import com.miventa.compose.mobile.util.handleError
import com.miventa.compose.mobile.util.showToast
import com.miventa.compose.mobile.widget.ButtonCircular
import com.miventa.compose.mobile.widget.ButtonPrimaryEnable

@Composable
fun ValidateRegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    validateRegisterInteractions: ValidateRegisterInteractions,
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    val loginUiState by viewModel.loginUiState.collectAsState(LoginUiState())

    val loginUiEvent by viewModel.loginUiEvent.collectAsState(LoginUiEvent.Empty)

    LaunchedEffect(key1 = loginUiEvent) {
        context.handleEvent(loginUiEvent)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_big))
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.padding_big)),
    ) {
        ButtonCircular(
            modifier = Modifier,
            image = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
            contentDescription = stringResource(R.string.back),
            onClick = {
                validateRegisterInteractions.navigateToRegister()
            },
        )
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        )
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.il_message_sent),
            contentDescription = stringResource(R.string.email_sent),
        )
        Text(
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            text = stringResource(R.string.verify_your_email),
        )
        Text(
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall,
            text = stringResource(R.string.please_verify_and_return_to_the_application_to_continue_your_registration),
        )
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        )
        ButtonPrimaryEnable(
            text = stringResource(R.string.verified),
            enable = loginUiState.isEmailVerified,
            onClick = {
                validateRegisterInteractions.navigateToRegisterSuccess()
            },
        )
    }
}

private fun Context.handleEvent(event: LoginUiEvent?) {
    when (event) {
        is LoginUiEvent.Error -> {
            showToast(handleError(event.exception))
        }

        else -> {}
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.PIXEL_7,
)
@Composable
private fun ValidateRegisterScreenPreview() {
    Screen {
        ValidateRegisterScreen(
            viewModel = hiltViewModel(),
            validateRegisterInteractions = ValidateRegisterInteractions(
                navigateToRegister = {},
                navigateToRegisterSuccess = {},
            )
        )
    }
}
