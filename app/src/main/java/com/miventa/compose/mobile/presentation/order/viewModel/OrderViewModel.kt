/*
 * OrderViewModel.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miventa.compose.mobile.domain.usecase.order.CreateProductUseCase
import com.miventa.compose.mobile.domain.usecase.order.DeleteProductUseCase
import com.miventa.compose.mobile.domain.usecase.order.ReadCurrentUserUseCase
import com.miventa.compose.mobile.domain.usecase.order.ReadInfoValueUseCase
import com.miventa.compose.mobile.domain.usecase.order.ReadProductsUseCase
import com.miventa.compose.mobile.domain.usecase.order.SingOutUseCase
import com.miventa.compose.mobile.domain.usecase.order.UpdateInfoValueUseCase
import com.miventa.compose.mobile.domain.usecase.order.UpdateProductUseCase
import com.miventa.compose.mobile.presentation.order.viewModel.state.OrderUiEvent
import com.miventa.compose.mobile.presentation.order.viewModel.state.OrderUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val readInfoValueUseCase: ReadInfoValueUseCase,
    private val updateInfoValueUseCase: UpdateInfoValueUseCase,
    private val readCurrentUserUseCase: ReadCurrentUserUseCase,
    private val createProductUseCase: CreateProductUseCase,
    private val readProductsUseCase: ReadProductsUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val singOutUseCase: SingOutUseCase,
) : ViewModel() {

    private val _orderUiState = MutableStateFlow(OrderUiState())
    val orderUiState: StateFlow<OrderUiState> = _orderUiState

    private var _orderUiEvent = MutableSharedFlow<OrderUiEvent>()
    val orderUiEvent: SharedFlow<OrderUiEvent> = _orderUiEvent

    init {
        readInfoValue()
        readCurrentEmail()
    }

    @VisibleForTesting
    fun readInfoValue() = viewModelScope.launch {
        _orderUiState.value = _orderUiState.value.copy(isLoading = true)
        readInfoValueUseCase()
            .onSuccess {
                _orderUiState.value = _orderUiState.value.copy(isLoading = false)
                _orderUiEvent.emit(OrderUiEvent.ShowInfoDialog)
            }
            .onFailure { exception ->
                _orderUiState.value = _orderUiState.value.copy(isLoading = false)
                _orderUiEvent.emit(OrderUiEvent.Error(exception))
            }
    }

    fun updateInfo() = viewModelScope.launch {
        updateInfoValueUseCase(value = true)
            .onFailure { exception ->
                _orderUiEvent.emit(OrderUiEvent.Error(exception))
            }
    }

    @VisibleForTesting
    fun readCurrentEmail() = viewModelScope.launch {
        readCurrentUserUseCase()
            .onSuccess { email ->
                _orderUiState.value = _orderUiState.value.copy(currentEmail = email)
            }
            .onFailure { exception ->
                _orderUiEvent.emit(OrderUiEvent.Error(exception))
            }
    }

    fun shareUrl() = viewModelScope.launch {
        _orderUiEvent.emit(OrderUiEvent.ShareUrl)
    }

    fun singOut() = viewModelScope.launch {
        _orderUiState.value = _orderUiState.value.copy(isLoading = true)
        singOutUseCase()
            .onSuccess {
                _orderUiState.value = _orderUiState.value.copy(isLoading = false)
                _orderUiEvent.emit(OrderUiEvent.NavigateToLogin)
            }
            .onFailure { exception ->
                _orderUiState.value = _orderUiState.value.copy(isLoading = false)
                _orderUiEvent.emit(OrderUiEvent.Error(exception))
            }
    }
}
