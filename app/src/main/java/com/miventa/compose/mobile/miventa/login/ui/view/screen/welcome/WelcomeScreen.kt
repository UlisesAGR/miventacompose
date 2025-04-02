/*
 * WelcomeScreen.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.miventa.login.ui.view.screen.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.miventa.login.ui.navigation.WelcomeInteractions
import com.miventa.compose.mobile.theme.Screen
import com.miventa.compose.mobile.widget.ButtonPrimary
import com.miventa.compose.mobile.widget.ButtonSecondary

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    welcomeInteractions: WelcomeInteractions,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_big))
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.padding_big)),
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            painter = painterResource(id = R.drawable.il_welcome_cats),
            contentDescription = stringResource(R.string.welcome),
        )
        Text(
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            text = stringResource(R.string.welcome_to_the_app_that_helps_you_manage_your_sales),
        )
        ButtonPrimary(
            text = stringResource(R.string.sign_in),
            onClick = {
                welcomeInteractions.navigateToLogin()
            },
        )
        ButtonSecondary(
            text = stringResource(R.string.register),
            onClick = {
                welcomeInteractions.navigateToRegister()
            },
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.PIXEL_7,
)
@Composable
private fun WelcomeScreenPreview() {
    Screen {
        WelcomeScreen(
            welcomeInteractions = WelcomeInteractions(
                navigateToLogin = {},
                navigateToRegister = {},
            )
        )
    }
}
