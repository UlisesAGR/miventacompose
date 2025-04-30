/*
 * UpdateProductViewModel.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.viewModel.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miventa.compose.mobile.domain.model.order.status.UpdateStatus
import com.miventa.compose.mobile.domain.usecase.order.local.UpdateProductUseCase
import com.miventa.compose.mobile.domain.usecase.order.validation.ValidationUpdateProductUseCase
import com.miventa.compose.mobile.presentation.order.viewModel.update.UpdateProductUiEvent.Error
import com.miventa.compose.mobile.presentation.order.viewModel.update.UpdateProductUiEvent.NavigateToOrder
import com.miventa.compose.mobile.presentation.order.viewModel.update.UpdateProductUiEvent.ValidateUpdateStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class UpdateProductViewModel @Inject constructor(
    private val validationUpdateProductUseCase: ValidationUpdateProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
) : ViewModel() {

    private val _updateProductUiState = MutableStateFlow(UpdateProductUiState())
    val updateProductUiState: StateFlow<UpdateProductUiState> = _updateProductUiState

    private var _updateProductUiEvent = MutableSharedFlow<UpdateProductUiEvent>(extraBufferCapacity = 1)
    val updateProductUiEvent: SharedFlow<UpdateProductUiEvent> = _updateProductUiEvent

    fun onUpdateProductChangeEvent(event: UpdateProductChangeEvent) = viewModelScope.launch {
        _updateProductUiState.value = when (event) {
            is UpdateProductChangeEvent.Name ->
                _updateProductUiState.value.copy(name = event.name)

            is UpdateProductChangeEvent.Price ->
                _updateProductUiState.value.copy(price = event.price)
        }
    }

    fun onUpdateProductChanged(
        image: String,
        name: String,
        price: String,
    ) = viewModelScope.launch {
        when (validationUpdateProductUseCase(name, price)) {
            UpdateStatus.EMPTY_NAME ->
                _updateProductUiEvent.emit(ValidateUpdateStatus(status = UpdateStatus.EMPTY_NAME))

            UpdateStatus.EMPTY_PRICE ->
                _updateProductUiEvent.emit(ValidateUpdateStatus(status = UpdateStatus.EMPTY_PRICE))

            UpdateStatus.SUCCESS -> updateProduct(image, name, price)
        }
    }

    @VisibleForTesting
    fun updateProduct(
        image: String,
        name: String,
        price: String,
    ) = viewModelScope.launch {
        updateProductUseCase(image, name, price)
            .onSuccess {
                _updateProductUiEvent.emit(NavigateToOrder)
            }
            .onFailure { exception ->
                _updateProductUiEvent.emit(Error(exception))
            }
    }
}
