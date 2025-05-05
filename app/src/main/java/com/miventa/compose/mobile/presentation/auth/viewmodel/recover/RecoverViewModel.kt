/*
 * RecoverViewModel.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.viewmodel.recover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miventa.compose.mobile.domain.model.login.RecoverStatus
import com.miventa.compose.mobile.domain.usecase.login.auth.RecoverPasswordUseCase
import com.miventa.compose.mobile.domain.usecase.login.validation.ValidationRecoverUseCase
import com.miventa.compose.mobile.presentation.auth.viewmodel.recover.RecoverUiEvent.Error
import com.miventa.compose.mobile.presentation.auth.viewmodel.recover.RecoverUiEvent.NavigateToValidateRecover
import com.miventa.compose.mobile.presentation.auth.viewmodel.recover.RecoverUiEvent.ValidateRecoverForm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class RecoverViewModel @Inject constructor(
    private val validationRecoverUseCase: ValidationRecoverUseCase,
    private val recoverPasswordUseCase: RecoverPasswordUseCase,
) : ViewModel() {

    private val _recoverUiState = MutableStateFlow(RecoverUiState())
    val recoverUiState: StateFlow<RecoverUiState> = _recoverUiState

    private var _recoverUiEvent = MutableSharedFlow<RecoverUiEvent>(extraBufferCapacity = 1)
    val recoverUiEvent: SharedFlow<RecoverUiEvent> = _recoverUiEvent

    fun onRecoverChangeEvent(email: String) = viewModelScope.launch {
        _recoverUiState.update { state -> state.copy(email = email) }
    }

    fun onRecoverChanged(email: String) = viewModelScope.launch {
        when (validationRecoverUseCase(email)) {
            RecoverStatus.EMPTY_EMAIL ->
                _recoverUiEvent.emit(ValidateRecoverForm(status = RecoverStatus.EMPTY_EMAIL))

            RecoverStatus.INVALID_EMAIL ->
                _recoverUiEvent.emit(ValidateRecoverForm(status = RecoverStatus.INVALID_EMAIL))

            RecoverStatus.SUCCESS -> recoverPassword(email)
        }
    }

    @VisibleForTesting
    fun recoverPassword(email: String) = viewModelScope.launch {
        _recoverUiState.update { state -> state.copy(isLoading = true) }
        recoverPasswordUseCase(email)
            .onSuccess {
                _recoverUiState.update { state -> state.copy(isLoading = false) }
                _recoverUiEvent.emit(NavigateToValidateRecover)
            }
            .onFailure { exception ->
                _recoverUiState.update { state -> state.copy(isLoading = false) }
                _recoverUiEvent.emit(Error(exception))
            }
    }
}
