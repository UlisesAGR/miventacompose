/*
 * ValidateRegisterScreen.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.start.ui.login.view.screen.register

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.presentation.start.ui.login.navigation.ValidateRegisterInteractions
import com.miventa.compose.mobile.presentation.start.viewmodel.login.state.LoginUiState
import com.miventa.compose.mobile.util.enterToDown
import com.miventa.compose.mobile.widget.ButtonCircular
import com.miventa.compose.mobile.widget.ButtonPrimaryEnable

@Composable
fun ValidateRegisterScreen(
    loginUiState: LoginUiState,
    validateRegisterInteractions: ValidateRegisterInteractions,
    modifier: Modifier = Modifier,
) = with(loginUiState) {
    val scrollState = rememberScrollState()
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(loginUiState) {
        if (loginUiState.isEmailVerified) {
            visible = true
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.space)),
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_big))
            .verticalScroll(scrollState),
    ) {
        ButtonCircular(
            image = ImageVector.vectorResource(id = R.drawable.ic_arrow_back),
            contentDescription = stringResource(R.string.back),
            onClick = {
                validateRegisterInteractions.navigateToRegister()
            },
        )
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.space)),
            modifier = Modifier.wrapContentSize(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.il_message_sent),
                contentDescription = stringResource(R.string.email_sent),
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = stringResource(R.string.verify_your_email),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = stringResource(R.string.please_verify_and_return_to_the_application_to_continue_your_registration),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
        )
        AnimatedVisibility(
            visible = visible,
            enter = enterToDown(),
        ) {
            ButtonPrimaryEnable(
                text = stringResource(R.string.verified),
                enable = isEmailVerified,
                onClick = {
                    validateRegisterInteractions.navigateToRegisterSuccess()
                },
            )
        }
    }
}
