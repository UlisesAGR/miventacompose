/*
 * CreateProductContent.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.ui.view.screen.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.miventa.compose.mobile.R
import com.miventa.compose.mobile.presentation.order.ui.navigation.CreateInteractions
import com.miventa.compose.mobile.presentation.order.viewModel.create.CreateProductChangeEvent
import com.miventa.compose.mobile.presentation.order.viewModel.create.CreateProductUiState
import com.miventa.compose.mobile.presentation.order.viewModel.create.CreateProductViewModel
import com.miventa.compose.mobile.widget.ButtonPrimary
import com.miventa.compose.mobile.widget.PriceTextField
import com.miventa.compose.mobile.widget.TextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateProductContent(
    viewModel: CreateProductViewModel,
    createProductUiState: CreateProductUiState,
    createInteractions: CreateInteractions,
) {
    val scrollState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.modify)) },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            createInteractions.navigateToOrder()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back),
                        )
                    }
                },
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(dimensionResource(id = R.dimen.padding_big)),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(space = dimensionResource(id = R.dimen.space)),
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState),
            ) {
                Text(
                    text = stringResource(R.string.give_your_product_a_name),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.fillMaxWidth(),
                )
                TextField(
                    text = createProductUiState.name,
                    hint = stringResource(R.string.name),
                    imeAction = ImeAction.Next,
                    onTextFieldChanged = { name ->
                        viewModel.onCreateProductChangeEvent(CreateProductChangeEvent.Name(name))
                    },
                )
                Text(
                    text = stringResource(R.string.put_a_price_on_your_product),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.fillMaxWidth(),
                )
                PriceTextField(
                    text = createProductUiState.price,
                    hint = stringResource(R.string.price),
                    imeAction = ImeAction.Done,
                    onTextFieldChanged = { price ->
                        viewModel.onCreateProductChangeEvent(CreateProductChangeEvent.Price(price))
                    },
                )
                ButtonPrimary(
                    text = stringResource(R.string.create),
                    onClick = {
                        keyboardController?.hide()
                        viewModel.onCreateProductChanged(
                            image = "",
                            name = createProductUiState.name,
                            price = createProductUiState.price,
                        )
                    },
                )
            }
        }
    }
}
