/*
 * LoginForm.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.miventa.login.ui.view.screen.login

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
import com.miventa.compose.mobile.miventa.login.ui.navigation.LoginInteractions
import com.miventa.compose.mobile.miventa.login.ui.viewmodel.LoginViewModel
import com.miventa.compose.mobile.miventa.login.ui.viewmodel.state.LoginUiState
import com.miventa.compose.mobile.widget.ButtonCircular
import com.miventa.compose.mobile.widget.ButtonPrimary
import com.miventa.compose.mobile.widget.ButtonText
import com.miventa.compose.mobile.widget.EmailTextField
import com.miventa.compose.mobile.widget.PasswordTextField

@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    state: LoginUiState,
    interactions: LoginInteractions,
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
            contentDescription = stringResource(R.string.back),
            onClick = {
                keyboardController?.hide()
                interactions.navigateToWelcome()
            },
        )
        Text(
            modifier = textModifier,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            text = stringResource(R.string.welcome_come_back),
        )
        Text(
            modifier = textModifier,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall,
            text = stringResource(R.string.here_you_can_login_with_your_credentials),
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
            imeAction = ImeAction.Done,
            onTextFieldChanged = { password ->
                viewModel.onPasswordChanged(password)
            },
            onClickPasswordHidden = {
                viewModel.onPasswordVisibilityChanged(passwordHidden)
            },
        )
        ButtonText(
            modifier = Modifier.align(alignment = androidx.compose.ui.Alignment.End),
            text = stringResource(R.string.forgot_password),
            onClick = {
                keyboardController?.hide()
                interactions.navigateToRecover()
            },
        )
        ButtonPrimary(
            text = stringResource(R.string.sign_in),
            onClick = {
                keyboardController?.hide()
                viewModel.onLoginChanged(email, password)
            },
        )
    }
}
