/*
 * RegisterContent.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.ui.view.screen.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.miventa.compose.mobile.presentation.auth.ui.navigation.RegisterInteractions
import com.miventa.compose.mobile.presentation.auth.viewmodel.register.RegisterChangeEvent
import com.miventa.compose.mobile.presentation.auth.viewmodel.register.RegisterUiState
import com.miventa.compose.mobile.presentation.auth.viewmodel.register.RegisterViewModel
import com.miventa.compose.mobile.widget.ButtonCircular
import com.miventa.compose.mobile.widget.ButtonPrimary
import com.miventa.compose.mobile.widget.EmailTextField
import com.miventa.compose.mobile.widget.PasswordTextField

@Composable
fun RegisterContent(
    viewModel: RegisterViewModel,
    registerUiState: RegisterUiState,
    registerInteractions: RegisterInteractions,
) {
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.space_big)),
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_big))
            .verticalScroll(scrollState),
    ) {
        ButtonCircular(
            image = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
            contentDescription = stringResource(id = R.string.back),
            onClick = {
                keyboardController?.hide()
                registerInteractions.navigateToWelcome()
            },
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.space_big)),
            modifier = Modifier.wrapContentSize(),
        ) {
            Text(
                text = stringResource(R.string.register_account),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = stringResource(R.string.here_you_can_register_a_new_account),
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
                email = registerUiState.email,
                hint = stringResource(R.string.email),
                imeAction = ImeAction.Next,
                onTextFieldChanged = { email ->
                    viewModel.onRegisterChangeEvent(RegisterChangeEvent.Email(email))
                },
            )
            Text(
                text = stringResource(R.string.enter_your_password),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.fillMaxWidth(),
            )
            PasswordTextField(
                password = registerUiState.password,
                passwordHidden = registerUiState.passwordHidden,
                hint = stringResource(R.string.password),
                imeAction = ImeAction.Next,
                onTextFieldChanged = { password ->
                    viewModel.onRegisterChangeEvent(RegisterChangeEvent.Password(password))
                },
                onClickPasswordHidden = {
                    viewModel.onRegisterChangeEvent(
                        RegisterChangeEvent.PasswordVisibility(
                            registerUiState.passwordHidden
                        )
                    )
                },
            )
            Text(
                text = stringResource(R.string.enter_your_password_to_confirm),
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.fillMaxWidth(),
            )
            PasswordTextField(
                password = registerUiState.passwordConfirm,
                passwordHidden = registerUiState.passwordConfirmHidden,
                hint = stringResource(R.string.password),
                imeAction = ImeAction.Done,
                onTextFieldChanged = { password ->
                    viewModel.onRegisterChangeEvent(
                        RegisterChangeEvent.PasswordConfirm(
                            password
                        )
                    )
                },
                onClickPasswordHidden = {
                    viewModel.onRegisterChangeEvent(
                        RegisterChangeEvent.PasswordConfirmVisibility(
                            registerUiState.passwordConfirmHidden
                        )
                    )
                },
            )
            ButtonPrimary(
                text = stringResource(R.string.register),
                onClick = {
                    keyboardController?.hide()
                    viewModel.onRegisterChanged(
                        email = registerUiState.email,
                        password = registerUiState.password,
                        passwordConfirm = registerUiState.passwordConfirm,
                    )
                },
            )
        }
    }
}
