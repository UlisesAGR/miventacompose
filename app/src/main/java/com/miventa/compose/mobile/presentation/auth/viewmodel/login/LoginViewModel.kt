/*
 * LoginViewModel.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.viewmodel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miventa.compose.mobile.domain.model.login.LoginStatus
import com.miventa.compose.mobile.domain.usecase.login.auth.LoginUserUseCase
import com.miventa.compose.mobile.domain.usecase.login.validation.ValidationLoginUseCase
import com.miventa.compose.mobile.presentation.auth.viewmodel.login.LoginUiEvent.Error
import com.miventa.compose.mobile.presentation.auth.viewmodel.login.LoginUiEvent.NavigateToOrder
import com.miventa.compose.mobile.presentation.auth.viewmodel.login.LoginUiEvent.ValidateLoginForm
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
class LoginViewModel @Inject constructor(
    private val validationLoginUseCase: ValidationLoginUseCase,
    private val loginUserUseCase: LoginUserUseCase,
) : ViewModel() {

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    private var _loginUiEvent = MutableSharedFlow<LoginUiEvent>(extraBufferCapacity = 1)
    val loginUiEvent: SharedFlow<LoginUiEvent> = _loginUiEvent

    fun onLoginChangeEvent(event: LoginChangeEvent) = viewModelScope.launch {
        when (event) {
            is LoginChangeEvent.Email ->
                _loginUiState.update { it.copy(email = event.email) }

            is LoginChangeEvent.Password ->
                _loginUiState.update { it.copy(password = event.password) }

            is LoginChangeEvent.PasswordVisibility ->
                _loginUiState.update { it.copy(passwordHidden = !event.passwordHidden) }
        }
    }

    fun onLoginChanged(
        email: String,
        password: String,
    ) = viewModelScope.launch {
        when (validationLoginUseCase(email, password)) {
            LoginStatus.EMPTY_EMAIL ->
                _loginUiEvent.emit(ValidateLoginForm(status = LoginStatus.EMPTY_EMAIL))

            LoginStatus.INVALID_EMAIL ->
                _loginUiEvent.emit(ValidateLoginForm(status = LoginStatus.INVALID_EMAIL))

            LoginStatus.PASSWORD_EMAIL ->
                _loginUiEvent.emit(ValidateLoginForm(status = LoginStatus.PASSWORD_EMAIL))

            LoginStatus.SUCCESS -> login(email, password)
        }
    }

    @VisibleForTesting
    fun login(
        email: String,
        password: String,
    ) = viewModelScope.launch {
        _loginUiState.update { it.copy(isLoading = true) }
        loginUserUseCase(email, password)
            .onSuccess {
                _loginUiState.update { it.copy(isLoading = false) }
                _loginUiEvent.emit(NavigateToOrder)
            }.onFailure { exception ->
                _loginUiState.update { it.copy(isLoading = false) }
                _loginUiEvent.emit(Error(exception))
            }
    }
}
