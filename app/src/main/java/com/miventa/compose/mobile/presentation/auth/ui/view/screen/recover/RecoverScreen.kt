/*
 * RecoverScreen.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.ui.view.screen.recover

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.domain.model.login.RecoverStatus
import com.miventa.compose.mobile.presentation.auth.ui.navigation.RecoverInteractions
import com.miventa.compose.mobile.presentation.auth.viewmodel.recover.RecoverUiEvent
import com.miventa.compose.mobile.presentation.auth.viewmodel.recover.RecoverViewModel
import com.miventa.compose.mobile.util.handleError
import com.miventa.compose.mobile.util.showToast
import com.miventa.compose.mobile.widget.ProgressIndicator

@Composable
fun RecoverScreen(recoverInteractions: RecoverInteractions) {
    val context = LocalContext.current
    val viewModel: RecoverViewModel = hiltViewModel()
    val recoverUiState by viewModel.recoverUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.recoverUiEvent.collect { event ->
            context.handleRecoverEvent(event, recoverInteractions)
        }
    }

    if (recoverUiState.isLoading) {
        ProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        )
    } else {
        RecoverContent(
            viewModel,
            recoverUiState,
            recoverInteractions,
        )
    }
}

private fun Context.handleRecoverEvent(
    event: RecoverUiEvent,
    interactions: RecoverInteractions,
) {
    when (event) {
        is RecoverUiEvent.Error -> {
            showToast(handleError(event.exception))
        }
        is RecoverUiEvent.ValidateRecoverForm -> {
            showToast(
                when (event.status) {
                    RecoverStatus.EMPTY_EMAIL -> getString(R.string.enter_your_email)
                    RecoverStatus.INVALID_EMAIL -> getString(R.string.email_is_invalid)
                    else -> ""
                }
            )
        }
        is RecoverUiEvent.NavigateToValidateRecover -> {
            interactions.navigateToValidateRecover()
        }
    }
}
