/*
 * LoginViewModel.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.start.viewmodel.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miventa.compose.mobile.domain.model.LoginStatus
import com.miventa.compose.mobile.domain.model.RecoverStatus
import com.miventa.compose.mobile.domain.model.RegisterStatus
import com.miventa.compose.mobile.domain.usecase.auth.LoginUserUseCase
import com.miventa.compose.mobile.domain.usecase.auth.RecoverPasswordUseCase
import com.miventa.compose.mobile.domain.usecase.auth.RegisterUserUseCase
import com.miventa.compose.mobile.domain.usecase.auth.SendVerificationEmailUserUseCase
import com.miventa.compose.mobile.domain.usecase.auth.VerifyEmailVerifiedUserUseCase
import com.miventa.compose.mobile.domain.usecase.validation.ValidationLoginUseCase
import com.miventa.compose.mobile.domain.usecase.validation.ValidationRecoverUseCase
import com.miventa.compose.mobile.domain.usecase.validation.ValidationRegisterUseCase
import com.miventa.compose.mobile.presentation.start.viewmodel.login.state.LoginChangeEvent
import com.miventa.compose.mobile.presentation.start.viewmodel.login.state.LoginUiEvent
import com.miventa.compose.mobile.presentation.start.viewmodel.login.state.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validationLoginUseCase: ValidationLoginUseCase,
    private val validationRecoverUseCase: ValidationRecoverUseCase,
    private val validationRegisterUseCase: ValidationRegisterUseCase,
    private val loginUserUseCase: LoginUserUseCase,
    private val recoverPasswordUseCase: RecoverPasswordUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    private val sendVerificationEmailUserUseCase: SendVerificationEmailUserUseCase,
    private val verifyEmailVerifiedUserUseCase: VerifyEmailVerifiedUserUseCase,
) : ViewModel() {

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState: StateFlow<LoginUiState> = _loginUiState

    private var _loginUiEvent = MutableSharedFlow<LoginUiEvent>()
    val loginUiEvent: SharedFlow<LoginUiEvent> = _loginUiEvent

    fun onChangeEvent(event: LoginChangeEvent) = viewModelScope.launch {
        _loginUiState.value = when (event) {
            is LoginChangeEvent.Email ->
                _loginUiState.value.copy(email = event.email)

            is LoginChangeEvent.Password ->
                _loginUiState.value.copy(password = event.password)

            is LoginChangeEvent.PasswordVisibility ->
                _loginUiState.value.copy(passwordHidden = !event.passwordHidden)

            is LoginChangeEvent.PasswordConfirm ->
                _loginUiState.value.copy(passwordConfirm = event.passwordConfirm)

            is LoginChangeEvent.PasswordConfirmVisibility ->
                _loginUiState.value.copy(passwordConfirmHidden = !event.passwordConfirmHidden)
        }
    }

    fun onLoginChanged(
        email: String,
        password: String,
    ) = viewModelScope.launch {
        when (validationLoginUseCase(email, password)) {
            LoginStatus.EMPTY_EMAIL ->
                _loginUiEvent.emit(LoginUiEvent.ValidateLoginForm(status = LoginStatus.EMPTY_EMAIL))

            LoginStatus.INVALID_EMAIL ->
                _loginUiEvent.emit(LoginUiEvent.ValidateLoginForm(status = LoginStatus.INVALID_EMAIL))

            LoginStatus.PASSWORD_EMAIL ->
                _loginUiEvent.emit(LoginUiEvent.ValidateLoginForm(status = LoginStatus.PASSWORD_EMAIL))

            LoginStatus.SUCCESS ->
                login(email, password)
        }
    }

    @VisibleForTesting
    fun login(
        email: String,
        password: String,
    ) = viewModelScope.launch {
        _loginUiState.value = _loginUiState.value.copy(isLoading = true)
        loginUserUseCase(email, password)
            .onSuccess {
                _loginUiState.value = _loginUiState.value.copy(isLoading = false)
                _loginUiEvent.emit(LoginUiEvent.NavigateToOrder)
            }.onFailure { exception ->
                _loginUiState.value = _loginUiState.value.copy(isLoading = false)
                _loginUiEvent.emit(LoginUiEvent.Error(exception))
            }
    }

    fun onRecoverChanged(email: String) = viewModelScope.launch {
        when (validationRecoverUseCase(email)) {
            RecoverStatus.EMPTY_EMAIL ->
                _loginUiEvent.emit(LoginUiEvent.ValidateRecoverForm(status = RecoverStatus.EMPTY_EMAIL))

            RecoverStatus.INVALID_EMAIL ->
                _loginUiEvent.emit(LoginUiEvent.ValidateRecoverForm(status = RecoverStatus.INVALID_EMAIL))

            RecoverStatus.SUCCESS ->
                recoverPassword(email)
        }
    }

    @VisibleForTesting
    fun recoverPassword(email: String) = viewModelScope.launch {
        _loginUiState.value = _loginUiState.value.copy(isLoading = true)
        recoverPasswordUseCase(email)
            .onSuccess {
                _loginUiState.value = _loginUiState.value.copy(isLoading = false)
                _loginUiEvent.emit(LoginUiEvent.NavigateToValidateRecover)
            }
            .onFailure { exception ->
                _loginUiState.value = _loginUiState.value.copy(isLoading = false)
                _loginUiEvent.emit(LoginUiEvent.Error(exception))
            }
    }

    fun onRegisterChanged(
        email: String,
        password: String,
        passwordConfirm: String,
    ) = viewModelScope.launch {
        when (validationRegisterUseCase(email, password, passwordConfirm)) {
            RegisterStatus.EMPTY_EMAIL ->
                _loginUiEvent.emit(LoginUiEvent.ValidateRegisterForm(status = RegisterStatus.EMPTY_EMAIL))

            RegisterStatus.INVALID_EMAIL ->
                _loginUiEvent.emit(LoginUiEvent.ValidateRegisterForm(status = RegisterStatus.INVALID_EMAIL))

            RegisterStatus.EMPTY_PASSWORD ->
                _loginUiEvent.emit(LoginUiEvent.ValidateRegisterForm(status = RegisterStatus.EMPTY_PASSWORD))

            RegisterStatus.PASSWORD_LENGTH ->
                _loginUiEvent.emit(LoginUiEvent.ValidateRegisterForm(status = RegisterStatus.PASSWORD_LENGTH))

            RegisterStatus.CONFIRM_PASSWORD_LENGTH ->
                _loginUiEvent.emit(LoginUiEvent.ValidateRegisterForm(status = RegisterStatus.CONFIRM_PASSWORD_LENGTH))

            RegisterStatus.PASSWORD_NOT_SAME ->
                _loginUiEvent.emit(LoginUiEvent.ValidateRegisterForm(status = RegisterStatus.PASSWORD_NOT_SAME))

            RegisterStatus.SUCCESS ->
                registerUser(email, password)
        }
    }

    @VisibleForTesting
    fun registerUser(
        email: String,
        password: String,
    ) = viewModelScope.launch {
        _loginUiState.value = _loginUiState.value.copy(isLoading = true)
        registerUserUseCase(email, password)
            .onSuccess {
                sendVerificationEmail()
            }
            .onFailure { exception ->
                _loginUiState.value = _loginUiState.value.copy(isLoading = false)
                _loginUiEvent.emit(LoginUiEvent.Error(exception))
            }
    }

    @VisibleForTesting
    fun sendVerificationEmail() = viewModelScope.launch {
        sendVerificationEmailUserUseCase()
            .onSuccess {
                _loginUiState.value = _loginUiState.value.copy(isLoading = false)
                _loginUiEvent.emit(LoginUiEvent.NavigateToValidateRegister)
            }
            .onFailure { exception ->
                _loginUiState.value = _loginUiState.value.copy(isLoading = false)
                _loginUiEvent.emit(LoginUiEvent.Error(exception))

            }
    }

    fun verifyEmailVerified() = viewModelScope.launch {
        verifyEmailVerifiedUserUseCase().catch { exception ->
            _loginUiEvent.emit(LoginUiEvent.Error(exception))
        }.collect { isEmailVerified ->
            _loginUiState.value = _loginUiState.value.copy(isEmailVerified = isEmailVerified)
        }
    }

    fun clearUiState() = viewModelScope.launch {
        _loginUiState.value = LoginUiState()
    }
}
