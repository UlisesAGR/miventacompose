/*
 * RecoverContent.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.ui.view.screen.recover

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
import com.miventa.compose.mobile.presentation.auth.ui.navigation.RecoverInteractions
import com.miventa.compose.mobile.presentation.auth.viewmodel.recover.RecoverUiState
import com.miventa.compose.mobile.presentation.auth.viewmodel.recover.RecoverViewModel
import com.miventa.compose.mobile.widget.ButtonCircular
import com.miventa.compose.mobile.widget.ButtonPrimary
import com.miventa.compose.mobile.widget.EmailTextField

@Composable
fun RecoverContent(
    viewModel: RecoverViewModel,
    recoverUiState: RecoverUiState,
    recoverInteractions: RecoverInteractions,
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
            contentDescription = stringResource(R.string.back),
            onClick = {
                keyboardController?.hide()
                recoverInteractions.navigateToLogin()
            },
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.space_big)),
            modifier = Modifier.wrapContentSize(),
        ) {
            Text(
                text = stringResource(R.string.recover_password),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = stringResource(R.string.here_you_can_recover_your_password),
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
                email = recoverUiState.email,
                hint = stringResource(R.string.email),
                imeAction = ImeAction.Done,
                onTextFieldChanged = { email ->
                    viewModel.onRecoverChangeEvent(email)
                },
            )
            ButtonPrimary(
                text = stringResource(R.string.recover),
                onClick = {
                    keyboardController?.hide()
                    viewModel.onRecoverChanged(
                        email = recoverUiState.email,
                    )
                },
            )
        }
    }
}
