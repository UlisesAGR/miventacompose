/*
 * RegisterForm.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.miventa.login.ui.view.screen.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.miventa.compose.mobile.miventa.login.ui.navigation.RegisterInteractions
import com.miventa.compose.mobile.miventa.login.ui.viewmodel.LoginViewModel
import com.miventa.compose.mobile.miventa.login.ui.viewmodel.state.LoginUiState
import com.miventa.compose.mobile.widget.ButtonCircular
import com.miventa.compose.mobile.widget.ButtonPrimary
import com.miventa.compose.mobile.widget.EmailTextField
import com.miventa.compose.mobile.widget.PasswordTextField

@Composable
fun RegisterForm(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    state: LoginUiState,
    registerInteractions: RegisterInteractions,
) = with(state) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val textModifier = modifier.fillMaxWidth()

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.padding_big)),
    ) {
        ButtonCircular(
            modifier = Modifier,
            image = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
            contentDescription = stringResource(id = R.string.back),
            onClick = {
                keyboardController?.hide()
                registerInteractions.navigateToWelcome()
                viewModel.clearUiState()
            },
        )
        Text(
            modifier = textModifier,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            text = stringResource(R.string.register_account),
        )
        Text(
            modifier = textModifier,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall,
            text = stringResource(R.string.here_you_can_register_a_new_account),
        )
        Text(
            modifier = textModifier,
            style = MaterialTheme.typography.titleSmall,
            text = stringResource(R.string.enter_your_email),
        )
        EmailTextField(
            email = email,
            hint = stringResource(R.string.email),
            imeAction = ImeAction.Next,
            onTextFieldChanged = { email ->
                viewModel.onEmailChanged(email)
            },
        )
        Text(
            modifier = textModifier,
            style = MaterialTheme.typography.titleSmall,
            text = stringResource(R.string.enter_your_password),
        )
        PasswordTextField(
            password = password,
            passwordHidden = passwordHidden,
            hint = stringResource(R.string.password),
            imeAction = ImeAction.Next,
            onTextFieldChanged = { password ->
                viewModel.onPasswordChanged(password)
            },
            onClickPasswordHidden = {
                viewModel.onPasswordVisibilityChanged(passwordHidden)
            },
        )
        Text(
            modifier = textModifier,
            style = MaterialTheme.typography.titleSmall,
            text = stringResource(R.string.enter_your_password_to_confirm),
        )
        PasswordTextField(
            password = passwordConfirm,
            passwordHidden = passwordConfirmHidden,
            hint = stringResource(R.string.password),
            imeAction = ImeAction.Done,
            onTextFieldChanged = { password ->
                viewModel.onPasswordConfirmChanged(password)
            },
            onClickPasswordHidden = {
                viewModel.onPasswordConfirmVisibilityChanged(passwordConfirmHidden)
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
