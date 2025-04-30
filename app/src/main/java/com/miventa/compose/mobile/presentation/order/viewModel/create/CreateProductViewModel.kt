/*
 * CreateProductViewModel.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.order.viewModel.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miventa.compose.mobile.domain.model.order.status.CreateStatus
import com.miventa.compose.mobile.domain.usecase.order.local.CreateProductUseCase
import com.miventa.compose.mobile.domain.usecase.order.validation.ValidationCreateProductUseCase
import com.miventa.compose.mobile.presentation.order.viewModel.create.CreateProductUiEvent.Error
import com.miventa.compose.mobile.presentation.order.viewModel.create.CreateProductUiEvent.NavigateToOrder
import com.miventa.compose.mobile.presentation.order.viewModel.create.CreateProductUiEvent.ValidateCreateStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class CreateProductViewModel @Inject constructor(
    private val validationCreateProductUseCase: ValidationCreateProductUseCase,
    private val createProductUseCase: CreateProductUseCase,
) : ViewModel() {

    private val _createProductUiState = MutableStateFlow(CreateProductUiState())
    val createProductUiState: StateFlow<CreateProductUiState> = _createProductUiState

    private var _createProductUiEvent = MutableSharedFlow<CreateProductUiEvent>(extraBufferCapacity = 1)
    val createProductUiEvent: SharedFlow<CreateProductUiEvent> = _createProductUiEvent

    fun onCreateProductChangeEvent(event: CreateProductChangeEvent) = viewModelScope.launch {
        _createProductUiState.value = when (event) {
            is CreateProductChangeEvent.Name ->
                _createProductUiState.value.copy(name = event.name)

            is CreateProductChangeEvent.Price ->
                _createProductUiState.value.copy(price = event.price)
        }
    }

    fun onCreateProductChanged(
        image: String,
        name: String,
        price: String,
    ) = viewModelScope.launch {
        when (validationCreateProductUseCase(name, price)) {
            CreateStatus.EMPTY_NAME ->
                _createProductUiEvent.emit(ValidateCreateStatus(status = CreateStatus.EMPTY_NAME))

            CreateStatus.EMPTY_PRICE ->
                _createProductUiEvent.emit(ValidateCreateStatus(status = CreateStatus.EMPTY_PRICE))

            CreateStatus.SUCCESS -> createProduct(image, name, price)
        }
    }

    @VisibleForTesting
    fun createProduct(
        image: String,
        name: String,
        price: String,
    ) = viewModelScope.launch {
        createProductUseCase(image, name, price)
            .onSuccess {
                _createProductUiEvent.emit(NavigateToOrder)
            }
            .onFailure { exception ->
                _createProductUiEvent.emit(Error(exception))
            }
    }
}
