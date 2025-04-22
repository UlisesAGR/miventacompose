/*
 * RegisterScreen.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.start.ui.login.view.screen.register

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.presentation.start.ui.login.navigation.RegisterInteractions
import com.miventa.compose.mobile.presentation.start.viewmodel.login.LoginViewModel
import com.miventa.compose.mobile.presentation.start.viewmodel.login.state.LoginChangeEvent
import com.miventa.compose.mobile.presentation.start.viewmodel.login.state.LoginUiState
import com.miventa.compose.mobile.util.enterToDownClosest
import com.miventa.compose.mobile.util.enterToLeft
import com.miventa.compose.mobile.widget.ButtonCircular
import com.miventa.compose.mobile.widget.ButtonPrimary
import com.miventa.compose.mobile.widget.EmailTextField
import com.miventa.compose.mobile.widget.PasswordTextField

@Composable
fun RegisterScreen(
    viewModel: LoginViewModel,
    loginUiState: LoginUiState,
    registerInteractions: RegisterInteractions,
    modifier: Modifier = Modifier,
) = with(loginUiState) {
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.space)),
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_big))
            .verticalScroll(scrollState),
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = enterToLeft(),
        ) {
            ButtonCircular(
                image = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
                contentDescription = stringResource(id = R.string.back),
                onClick = {
                    keyboardController?.hide()
                    registerInteractions.navigateToWelcome()
                },
            )
        }
        AnimatedVisibility(
            visible = visible,
            enter = enterToDownClosest(),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.space)),
                modifier = Modifier.wrapContentSize(),
            ) {
                Text(
                    text = stringResource(R.string.register_account),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
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
                    email = email,
                    hint = stringResource(R.string.email),
                    imeAction = ImeAction.Next,
                    onTextFieldChanged = { email ->
                        viewModel.onChangeEvent(LoginChangeEvent.Email(email))
                    },
                )
                Text(
                    text = stringResource(R.string.enter_your_password),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.fillMaxWidth(),
                )
                PasswordTextField(
                    password = password,
                    passwordHidden = passwordHidden,
                    hint = stringResource(R.string.password),
                    imeAction = ImeAction.Next,
                    onTextFieldChanged = { password ->
                        viewModel.onChangeEvent(LoginChangeEvent.Password(password))
                    },
                    onClickPasswordHidden = {
                        viewModel.onChangeEvent(LoginChangeEvent.PasswordVisibility(passwordHidden))
                    },
                )
                Text(
                    text = stringResource(R.string.enter_your_password_to_confirm),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.fillMaxWidth(),
                )
                PasswordTextField(
                    password = passwordConfirm,
                    passwordHidden = passwordConfirmHidden,
                    hint = stringResource(R.string.password),
                    imeAction = ImeAction.Done,
                    onTextFieldChanged = { password ->
                        viewModel.onChangeEvent(LoginChangeEvent.PasswordConfirm(password))
                    },
                    onClickPasswordHidden = {
                        viewModel.onChangeEvent(
                            LoginChangeEvent.PasswordConfirmVisibility(
                                passwordConfirmHidden
                            )
                        )
                    },
                )
                ButtonPrimary(
                    text = stringResource(R.string.register),
                    onClick = {
                        keyboardController?.hide()
                        viewModel.onRegisterChanged(email, password, passwordConfirm)
                    },
                )
            }
        }
    }
}
