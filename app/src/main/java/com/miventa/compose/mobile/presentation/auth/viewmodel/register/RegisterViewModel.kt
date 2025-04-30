/*
 * RegisterViewModel.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.viewmodel.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miventa.compose.mobile.domain.model.login.RegisterStatus
import com.miventa.compose.mobile.domain.usecase.login.auth.EmailHasBenVerifiedUseCase
import com.miventa.compose.mobile.domain.usecase.login.auth.RegisterUserUseCase
import com.miventa.compose.mobile.domain.usecase.login.auth.SendVerificationEmailUserUseCase
import com.miventa.compose.mobile.domain.usecase.login.validation.ValidationRegisterUseCase
import com.miventa.compose.mobile.presentation.auth.viewmodel.register.RegisterUiEvent.Error
import com.miventa.compose.mobile.presentation.auth.viewmodel.register.RegisterUiEvent.NavigateToValidateRegister
import com.miventa.compose.mobile.presentation.auth.viewmodel.register.RegisterUiEvent.ValidateRegisterForm
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
class RegisterViewModel @Inject constructor(
    private val validationRegisterUseCase: ValidationRegisterUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    private val sendVerificationEmailUserUseCase: SendVerificationEmailUserUseCase,
    private val emailHasBenVerifiedUseCase: EmailHasBenVerifiedUseCase,
) : ViewModel() {

    private val _registerUiState = MutableStateFlow(RegisterUiState())
    val registerUiState: StateFlow<RegisterUiState> = _registerUiState

    private var _registerUiEvent = MutableSharedFlow<RegisterUiEvent>(extraBufferCapacity = 1)
    val registerUiEvent: SharedFlow<RegisterUiEvent> = _registerUiEvent

    fun onRegisterChangeEvent(event: RegisterChangeEvent) = viewModelScope.launch {
        when (event) {
            is RegisterChangeEvent.Email ->
                _registerUiState.update { it.copy(email = event.email) }

            is RegisterChangeEvent.Password ->
                _registerUiState.update { it.copy(password = event.password) }

            is RegisterChangeEvent.PasswordVisibility ->
                _registerUiState.update { it.copy(passwordHidden = !event.passwordHidden) }

            is RegisterChangeEvent.PasswordConfirm ->
                _registerUiState.update { it.copy(passwordConfirm = event.passwordConfirm) }

            is RegisterChangeEvent.PasswordConfirmVisibility ->
                _registerUiState.update { it.copy(passwordConfirmHidden = !event.passwordConfirmHidden) }
        }
    }

    fun onRegisterChanged(
        email: String,
        password: String,
        passwordConfirm: String,
    ) = viewModelScope.launch {
        when (validationRegisterUseCase(email, password, passwordConfirm)) {
            RegisterStatus.EMPTY_EMAIL ->
                _registerUiEvent.emit(ValidateRegisterForm(status = RegisterStatus.EMPTY_EMAIL))

            RegisterStatus.INVALID_EMAIL ->
                _registerUiEvent.emit(ValidateRegisterForm(status = RegisterStatus.INVALID_EMAIL))

            RegisterStatus.EMPTY_PASSWORD ->
                _registerUiEvent.emit(ValidateRegisterForm(status = RegisterStatus.EMPTY_PASSWORD))

            RegisterStatus.PASSWORD_LENGTH ->
                _registerUiEvent.emit(ValidateRegisterForm(status = RegisterStatus.PASSWORD_LENGTH))

            RegisterStatus.CONFIRM_PASSWORD_LENGTH ->
                _registerUiEvent.emit(ValidateRegisterForm(status = RegisterStatus.CONFIRM_PASSWORD_LENGTH))

            RegisterStatus.PASSWORD_NOT_SAME ->
                _registerUiEvent.emit(ValidateRegisterForm(status = RegisterStatus.PASSWORD_NOT_SAME))

            RegisterStatus.SUCCESS -> registerUser(email, password)
        }
    }

    @VisibleForTesting
    fun registerUser(
        email: String,
        password: String,
    ) = viewModelScope.launch {
        _registerUiState.update { it.copy(isLoading = true) }
        registerUserUseCase(email, password)
            .onSuccess {
                sendVerificationEmail()
            }
            .onFailure { exception ->
                _registerUiState.update { it.copy(isLoading = false) }
                _registerUiEvent.emit(Error(exception))
            }
    }

    @VisibleForTesting
    fun sendVerificationEmail() = viewModelScope.launch {
        sendVerificationEmailUserUseCase()
            .onSuccess {
                _registerUiState.update { it.copy(isLoading = false) }
                _registerUiEvent.emit(NavigateToValidateRegister)
            }
            .onFailure { exception ->
                _registerUiState.update { it.copy(isLoading = false) }
                _registerUiEvent.emit(Error(exception))

            }
    }

    fun emailHasBenVerified() = viewModelScope.launch {
        emailHasBenVerifiedUseCase().catch { exception ->
            _registerUiEvent.emit(Error(exception))
        }.collect { isEmailVerified ->
            _registerUiState.update { it.copy(isEmailVerified = isEmailVerified) }
        }
    }
}
