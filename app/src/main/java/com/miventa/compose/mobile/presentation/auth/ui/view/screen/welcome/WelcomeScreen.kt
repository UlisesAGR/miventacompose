/*
 * WelcomeScreen.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.ui.view.screen.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.presentation.auth.ui.navigation.WelcomeInteractions
import com.miventa.compose.mobile.util.enterToDownClosest
import com.miventa.compose.mobile.util.enterToUpClosest
import com.miventa.compose.mobile.widget.AnimatedColoredText
import com.miventa.compose.mobile.widget.ButtonPrimary
import com.miventa.compose.mobile.widget.ButtonSecondary

@Composable
fun WelcomeScreen(
    welcomeInteractions: WelcomeInteractions,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.padding_big))
            .verticalScroll(scrollState),
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = enterToUpClosest(),
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
        ) {
            Image(
                painter = painterResource(id = R.drawable.il_welcome_cats),
                contentDescription = stringResource(R.string.welcome),
                modifier = Modifier.fillMaxSize(),
            )
        }
        AnimatedVisibility(
            visible = visible,
            enter = enterToDownClosest(),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_big)),
                modifier = Modifier.fillMaxWidth(),
            ) {
                AnimatedColoredText(
                    text = stringResource(R.string.welcome_to_the_app_that_helps_you_manage_your_sales),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth(),
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
    }
}
