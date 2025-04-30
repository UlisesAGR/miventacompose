/*
 * CreateProductScreen.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.ui.view.screen.create

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.domain.model.order.status.CreateStatus
import com.miventa.compose.mobile.presentation.order.ui.navigation.CreateInteractions
import com.miventa.compose.mobile.presentation.order.viewModel.create.CreateProductUiEvent
import com.miventa.compose.mobile.presentation.order.viewModel.create.CreateProductViewModel
import com.miventa.compose.mobile.util.handleError
import com.miventa.compose.mobile.util.showToast

@Composable
fun CreateProductScreen(
    viewModel: CreateProductViewModel = hiltViewModel(),
    createInteractions: CreateInteractions,
) {
    val context = LocalContext.current
    val createProductUiState by viewModel.createProductUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.createProductUiEvent.collect { createProductUiEvent ->
            context.handleCreateProductEvent(createProductUiEvent, createInteractions)
        }
    }

    CreateProductContent(
        viewModel,
        createProductUiState,
        createInteractions,
    )
}

private fun Context.handleCreateProductEvent(
    event: CreateProductUiEvent,
    createInteractions: CreateInteractions,
) {
    when (event) {
        is CreateProductUiEvent.Error -> {
            showToast(handleError(event.exception))
        }
        is CreateProductUiEvent.ValidateCreateStatus -> {
            showToast(
                when (event.status) {
                    CreateStatus.EMPTY_NAME -> getString(R.string.empty_name)
                    CreateStatus.EMPTY_PRICE -> getString(R.string.empty_price)
                    else -> ""
                }
            )
        }
        is CreateProductUiEvent.NavigateToOrder -> {
            createInteractions.navigateToOrder()
        }
    }
}
