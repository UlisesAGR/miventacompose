/*
 * OrderViewModel.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.viewModel.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miventa.compose.mobile.domain.model.order.ProductModel
import com.miventa.compose.mobile.domain.usecase.order.local.DeleteProductUseCase
import com.miventa.compose.mobile.domain.usecase.order.local.ReadCurrentUserUseCase
import com.miventa.compose.mobile.domain.usecase.order.local.ReadProductsUseCase
import com.miventa.compose.mobile.domain.usecase.order.remote.SingOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val readCurrentUserUseCase: ReadCurrentUserUseCase,
    private val readProductsUseCase: ReadProductsUseCase,
    private val deleteProductUseCase: DeleteProductUseCase,
    private val singOutUseCase: SingOutUseCase,
) : ViewModel() {

    private val _orderUiState = MutableStateFlow(OrderUiState())
    val orderUiState: StateFlow<OrderUiState> = _orderUiState

    private var _orderUiEvent = MutableSharedFlow<OrderUiEvent>(extraBufferCapacity = 1)
    val orderUiEvent: SharedFlow<OrderUiEvent> = _orderUiEvent

    init {
        readProducts()
        readCurrentEmail()
    }

    @VisibleForTesting
    fun readProducts() = viewModelScope.launch {
        readProductsUseCase().catch { exception ->
            _orderUiEvent.emit(OrderUiEvent.Error(exception))
        }.collect { products ->
            _orderUiState.update { it.copy(productList = products) }
        }
    }

    fun deleteProduct(product: ProductModel) = viewModelScope.launch {
        deleteProductUseCase(product)
            .onFailure { exception ->
                _orderUiEvent.emit(OrderUiEvent.Error(exception))
            }
    }

    @VisibleForTesting
    fun readCurrentEmail() = viewModelScope.launch {
        readCurrentUserUseCase()
            .onSuccess { email ->
                _orderUiState.update { it.copy(currentEmail = email) }
            }
            .onFailure { exception ->
                _orderUiEvent.emit(OrderUiEvent.Error(exception))
            }
    }

    fun singOut() = viewModelScope.launch {
        _orderUiState.update { it.copy(isLoading = true) }
        singOutUseCase()
            .onSuccess {
                _orderUiState.update { it.copy(isLoading = false) }
                _orderUiEvent.emit(OrderUiEvent.NavigateToLogin)
            }
            .onFailure { exception ->
                _orderUiState.update { it.copy(isLoading = false) }
                _orderUiEvent.emit(OrderUiEvent.Error(exception))
            }
    }
}
