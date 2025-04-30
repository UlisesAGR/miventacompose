/*
 * RegisterSuccessScreen.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.ui.view.screen.register

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.presentation.auth.ui.navigation.RegisterSuccessScreenInteractions
import com.miventa.compose.mobile.widget.ButtonPrimary

@Composable
fun RegisterSuccessScreen(registerSuccessScreenInteractions: RegisterSuccessScreenInteractions) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
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
            verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.space_big)),
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
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = stringResource(R.string.sign_in_and_start_making_your_sales),
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
        ButtonPrimary(
            text = stringResource(R.string.accept),
            onClick = {
                registerSuccessScreenInteractions.navigateToWelcome()
            },
        )
    }
}
