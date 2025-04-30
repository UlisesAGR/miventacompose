/*
 * OrderScreen.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.ui.view.screen.bottom.order

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.presentation.order.viewModel.order.OrderUiEvent
import com.miventa.compose.mobile.presentation.order.viewModel.order.OrderViewModel
import com.miventa.compose.mobile.util.handleError
import com.miventa.compose.mobile.util.showToast
import com.miventa.compose.mobile.widget.EmptyState
import com.miventa.compose.mobile.widget.ProductItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen() {
    val context = LocalContext.current
    val viewModel: OrderViewModel = hiltViewModel()
    val orderUiState by viewModel.orderUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.orderUiEvent.collect { orderUiEvent ->
            context.handleOrderEvent(orderUiEvent)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.order)) })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                },
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(R.string.add_product),
                )
            }
        },
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            if (orderUiState.productList.isEmpty()) {
                EmptyState(
                    title = stringResource(R.string.here_you_can_create_your_products_update_and_delete_them),
                    modifier = Modifier.fillMaxSize(),
                )
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(bottom = dimensionResource(id = R.dimen.padding_bottom)),
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(orderUiState.productList) { product ->
                        ProductItem(
                            product,
                            onEdit = {

                            },
                            onDelete = {
                                viewModel.deleteProduct(product)
                            },
                        )
                    }
                }
            }
        }
    }
}

private fun Context.handleOrderEvent(event: OrderUiEvent) {
    when (event) {
        is OrderUiEvent.Error -> {
            showToast(handleError(event.exception))
        }
        else -> Unit
    }
}
