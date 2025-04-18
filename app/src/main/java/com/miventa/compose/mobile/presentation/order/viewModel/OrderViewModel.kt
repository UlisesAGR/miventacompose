/*
 * OrderViewModel.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.viewModel

import androidx.lifecycle.ViewModel
import com.miventa.compose.mobile.presentation.order.viewModel.state.OrderUiEvent
import com.miventa.compose.mobile.presentation.order.viewModel.state.OrderUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
) : ViewModel() {

    private val _orderUiState = MutableStateFlow(OrderUiState())
    val orderUiState: StateFlow<OrderUiState> = _orderUiState

    private var _orderUiEvent = MutableSharedFlow<OrderUiEvent>()
    val orderUiEvent: SharedFlow<OrderUiEvent> = _orderUiEvent
}
