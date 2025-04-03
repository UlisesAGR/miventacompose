/*
 * RecoverForm.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.start.ui.login.view.screen.recover

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
import com.miventa.compose.mobile.presentation.start.ui.login.navigation.RecoverInteractions
import com.miventa.compose.mobile.presentation.start.viewmodel.login.LoginViewModel
import com.miventa.compose.mobile.presentation.start.viewmodel.login.state.LoginChangeEvent
import com.miventa.compose.mobile.presentation.start.viewmodel.login.state.LoginUiState
import com.miventa.compose.mobile.widget.ButtonCircular
import com.miventa.compose.mobile.widget.ButtonPrimary
import com.miventa.compose.mobile.widget.EmailTextField

@Composable
fun RecoverForm(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    state: LoginUiState,
    recoverInteractions: RecoverInteractions,
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
                recoverInteractions.navigateToLogin()
            },
        )
        Text(
            modifier = textModifier,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            text = stringResource(R.string.recover_password),
        )
        Text(
            modifier = textModifier,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall,
            text = stringResource(R.string.here_you_can_recover_your_password),
        )
        Text(
            modifier = textModifier,
            style = MaterialTheme.typography.titleSmall,
            text = stringResource(R.string.enter_your_email),
        )
        EmailTextField(
            email = email,
            hint = stringResource(R.string.email),
            imeAction = ImeAction.Done,
            onTextFieldChanged = { email ->
                viewModel.onChangeEvent(LoginChangeEvent.Email(email))
            },
        )
        ButtonPrimary(
            text = stringResource(R.string.recover),
            onClick = {
                keyboardController?.hide()
                viewModel.onRecoverChanged(email)
            },
        )
    }
}
