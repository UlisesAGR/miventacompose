/*
 * RegisterSuccessScreen.kt
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.util.enterToDown
import com.miventa.compose.mobile.widget.ButtonPrimary

@Composable
fun RegisterSuccessScreen(
    modifier: Modifier = Modifier,
    navigateToWelcome: () -> Unit = {},
) {
    val scrollState = rememberScrollState()
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
                painter = painterResource(id = R.drawable.il_confirmed),
                contentDescription = stringResource(R.string.account_created_successfully),
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = stringResource(R.string.congratulations_account_created_successfully),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier.fillMaxWidth(),
            )
            Text(
                text = stringResource(R.string.sign_in_and_start_making_your_sales),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelSmall,
                modifier = modifier.fillMaxWidth(),
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
            ButtonPrimary(
                text = stringResource(R.string.accept),
                onClick = {
                    navigateToWelcome()
                },
            )
        }
    }
}
