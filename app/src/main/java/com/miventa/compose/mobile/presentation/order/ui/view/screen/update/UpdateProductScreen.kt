/*
 * UpdateProductScreen.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.ui.view.screen.update

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.domain.model.order.status.UpdateStatus
import com.miventa.compose.mobile.presentation.order.ui.navigation.UpdateInteractions
import com.miventa.compose.mobile.presentation.order.viewModel.update.UpdateProductUiEvent
import com.miventa.compose.mobile.presentation.order.viewModel.update.UpdateProductViewModel
import com.miventa.compose.mobile.util.handleError
import com.miventa.compose.mobile.util.showToast

@Composable
fun UpdateProductScreen(
    productName: String,
    updateInteractions: UpdateInteractions,
) {
    val context = LocalContext.current
    val viewModel: UpdateProductViewModel = hiltViewModel()
    val updateProductUiState by viewModel.updateProductUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getProductByName(productName)
    }

    LaunchedEffect(Unit) {
        viewModel.updateProductUiEvent.collect { event ->
            context.handleUpdateProductEvent(event, updateInteractions)
        }
    }

    UpdateProductContent(
        viewModel,
        updateProductUiState,
        updateInteractions,
    )
}

private fun Context.handleUpdateProductEvent(
    event: UpdateProductUiEvent,
    updateInteractions: UpdateInteractions,
) {
    when (event) {
        is UpdateProductUiEvent.Error -> {
            showToast(handleError(event.exception))
        }
        is UpdateProductUiEvent.ValidateUpdateStatus -> {
            showToast(
                when (event.status) {
                    UpdateStatus.EMPTY_NAME -> getString(R.string.empty_name)
                    UpdateStatus.EMPTY_PRICE -> getString(R.string.empty_price)
                    else -> ""
                }
            )
        }
        is UpdateProductUiEvent.NavigateToOrder -> {
            updateInteractions.navigateToOrder()
        }
    }
}
