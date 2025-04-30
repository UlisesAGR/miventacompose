/*
 * ValidateRecoverScreen.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.ui.view.screen.recover

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import com.miventa.compose.mobile.presentation.auth.ui.navigation.ValidateRecoverInteractions
import com.miventa.compose.mobile.widget.ButtonPrimary

@Composable
fun ValidateRecoverScreen(validateRecoverInteractions: ValidateRecoverInteractions) {
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
                contentDescription = stringResource(R.string.email_sent),
                painter = painterResource(id = R.drawable.il_message_sent),
                modifier = Modifier.aspectRatio(ratio = 1f),
            )
            Text(
                text = stringResource(R.string.the_email_has_been_sent),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = stringResource(R.string.the_email_has_been_sent_to_reset_your_password),
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
                validateRecoverInteractions.navigateToLogin()
            },
        )
    }
}
