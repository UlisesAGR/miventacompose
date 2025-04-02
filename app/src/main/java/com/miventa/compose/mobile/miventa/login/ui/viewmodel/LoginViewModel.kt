/*
 * LoginViewModel.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.miventa.login.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miventa.compose.mobile.miventa.login.domain.LoginUserUseCase
import com.miventa.compose.mobile.miventa.login.domain.RecoverPasswordUseCase
import com.miventa.compose.mobile.miventa.login.domain.RegisterUserUseCase
import com.miventa.compose.mobile.miventa.login.domain.SendVerificationEmailUserUseCase
import com.miventa.compose.mobile.miventa.login.domain.VerifyEmailVerifiedUserUseCase
import com.miventa.compose.mobile.miventa.login.ui.viewmodel.state.LoginUiEvent
import com.miventa.compose.mobile.miventa.login.ui.viewmodel.state.LoginUiState
import com.miventa.compose.mobile.util.LoginStatus
import com.miventa.compose.mobile.util.RecoverStatus
import com.miventa.compose.mobile.util.RegisterStatus
import com.miventa.compose.mobile.util.isValidEmail
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

    fun onLoginChanged(
        email: String,
        password: String,
    ) = viewModelScope.launch {
        when {
            email.isEmpty() ->
                _loginUiEvent.emit(LoginUiEvent.ValidateLoginForm(status = LoginStatus.EMPTY_EMAIL))

            !email.isValidEmail() ->
                _loginUiEvent.emit(LoginUiEvent.ValidateLoginForm(status = LoginStatus.INVALID_EMAIL))

            password.isEmpty() ->
                _loginUiEvent.emit(LoginUiEvent.ValidateLoginForm(status = LoginStatus.PASSWORD_EMAIL))

            else -> login(email, password)
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
        when {
            email.isEmpty() ->
                _loginUiEvent.emit(LoginUiEvent.ValidateRecoverForm(status = RecoverStatus.EMPTY_EMAIL))

            !email.isValidEmail() ->
                _loginUiEvent.emit(LoginUiEvent.ValidateRecoverForm(status = RecoverStatus.INVALID_EMAIL))

            else -> recoverPassword(email)
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
        when {
            email.isEmpty() ->
                _loginUiEvent.emit(LoginUiEvent.ValidateRegisterForm(status = RegisterStatus.EMPTY_EMAIL))

            !email.isValidEmail() ->
                _loginUiEvent.emit(LoginUiEvent.ValidateRegisterForm(status = RegisterStatus.INVALID_EMAIL))

            password.isEmpty() ->
                _loginUiEvent.emit(LoginUiEvent.ValidateRegisterForm(status = RegisterStatus.EMPTY_PASSWORD))

            password.length < 6 ->
                _loginUiEvent.emit(LoginUiEvent.ValidateRegisterForm(status = RegisterStatus.PASSWORD_LENGTH))

            passwordConfirm.isEmpty() ->
                _loginUiEvent.emit(LoginUiEvent.ValidateRegisterForm(status = RegisterStatus.CONFIRM_PASSWORD_LENGTH))

            password != passwordConfirm ->
                _loginUiEvent.emit(LoginUiEvent.ValidateRegisterForm(status = RegisterStatus.PASSWORD_NOT_SAME))

            else -> registerUser(email, password)
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

    fun onEmailChanged(email: String) = viewModelScope.launch {
        _loginUiState.value = _loginUiState.value.copy(email = email)
    }

    fun onPasswordChanged(password: String) = viewModelScope.launch {
        _loginUiState.value = _loginUiState.value.copy(password = password)
    }

    fun onPasswordVisibilityChanged(passwordHidden: Boolean) {
        _loginUiState.value = _loginUiState.value.copy(passwordHidden = !passwordHidden)
    }

    fun onPasswordConfirmChanged(password: String) = viewModelScope.launch {
        _loginUiState.value = _loginUiState.value.copy(passwordConfirm = password)
    }

    fun onPasswordConfirmVisibilityChanged(passwordHidden: Boolean) {
        _loginUiState.value = _loginUiState.value.copy(passwordConfirmHidden = !passwordHidden)
    }

    fun clearUiState() {
        _loginUiState.value = LoginUiState()
    }
}
