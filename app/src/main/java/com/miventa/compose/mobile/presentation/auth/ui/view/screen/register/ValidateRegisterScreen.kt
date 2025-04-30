/*
 * ValidateRegisterScreen.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.ui.view.screen.register

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.miventa.compose.mobile.presentation.auth.ui.navigation.ValidateRegisterInteractions
import com.miventa.compose.mobile.presentation.auth.viewmodel.register.RegisterUiEvent
import com.miventa.compose.mobile.presentation.auth.viewmodel.register.RegisterViewModel
import com.miventa.compose.mobile.util.handleError
import com.miventa.compose.mobile.util.showToast

@Composable
fun ValidateRegisterScreen(validateRegisterInteractions: ValidateRegisterInteractions) {
    val context = LocalContext.current
    val viewModel: RegisterViewModel = hiltViewModel()
    val registerUiState by viewModel.registerUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.emailHasBenVerified()
    }

    LaunchedEffect(Unit) {
        viewModel.registerUiEvent.collect { registerUiEvent ->
            context.handleValidateRegisterEvent(registerUiEvent)
        }
    }

    ValidateRegisterContent(
        registerUiState,
        validateRegisterInteractions,
    )
}

private fun Context.handleValidateRegisterEvent(event: RegisterUiEvent) {
    when (event) {
        is RegisterUiEvent.Error -> {
            showToast(handleError(event.exception))
        }
        else -> Unit
    }
}
