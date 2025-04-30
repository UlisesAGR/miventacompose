/*
 * LoginContent.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.ui.view.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.presentation.auth.ui.navigation.LoginInteractions
import com.miventa.compose.mobile.presentation.auth.viewmodel.login.LoginChangeEvent
import com.miventa.compose.mobile.presentation.auth.viewmodel.login.LoginUiState
import com.miventa.compose.mobile.presentation.auth.viewmodel.login.LoginViewModel
import com.miventa.compose.mobile.widget.ButtonCircular
import com.miventa.compose.mobile.widget.ButtonPrimary
import com.miventa.compose.mobile.widget.ButtonText
import com.miventa.compose.mobile.widget.EmailTextField
import com.miventa.compose.mobile.widget.PasswordTextField

@Composable
fun LoginContent(
    viewModel: LoginViewModel,
    loginUiState: LoginUiState,
    loginInteractions: LoginInteractions,
) {
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.space_big)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_big))
            .verticalScroll(scrollState),
    ) {
        ButtonCircular(
            image = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
            contentDescription = stringResource(R.string.back),
            onClick = {
                keyboardController?.hide()
                loginInteractions.navigateToWelcome()
            },
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.space_big)),
            modifier = Modifier.wrapContentSize(),
        ) {
            Text(
                text = stringResource(R.string.welcome_come_back),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = stringResource(R.string.here_you_can_login_with_your_credentials),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = stringResource(R.string.enter_your_email),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.fillMaxWidth(),
            )
            EmailTextField(
                email = loginUiState.email,
                hint = stringResource(R.string.email),
                imeAction = ImeAction.Next,
                onTextFieldChanged = { email ->
                    viewModel.onLoginChangeEvent(LoginChangeEvent.Email(email))
                },
            )
            Text(
                text = stringResource(R.string.enter_your_password),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.fillMaxWidth(),
            )
            PasswordTextField(
                password = loginUiState.password,
                passwordHidden = loginUiState.passwordHidden,
                hint = stringResource(R.string.password),
                imeAction = ImeAction.Done,
                onTextFieldChanged = { password ->
                    viewModel.onLoginChangeEvent(LoginChangeEvent.Password(password))
                },
                onClickPasswordHidden = {
                    viewModel.onLoginChangeEvent(
                        LoginChangeEvent.PasswordVisibility(
                            loginUiState.passwordHidden
                        )
                    )
                },
            )
            ButtonText(
                text = stringResource(R.string.forgot_password),
                modifier = Modifier.align(alignment = androidx.compose.ui.Alignment.End),
                onClick = {
                    keyboardController?.hide()
                    loginInteractions.navigateToRecover()
                },
            )
            ButtonPrimary(
                text = stringResource(R.string.sign_in),
                onClick = {
                    keyboardController?.hide()
                    viewModel.onLoginChanged(
                        email = loginUiState.email,
                        password = loginUiState.password,
                    )
                },
            )
        }
    }
}
