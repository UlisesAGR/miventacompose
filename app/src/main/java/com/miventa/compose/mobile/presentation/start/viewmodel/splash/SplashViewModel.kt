/*
 * SplashViewModel.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.start.viewmodel.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miventa.compose.mobile.domain.usecase.login.auth.EmailVerifiedUseCase
import com.miventa.compose.mobile.domain.usecase.login.auth.ValidateCurrentUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val validateCurrentUserUseCase: ValidateCurrentUserUseCase,
    private val emailVerifiedUseCase: EmailVerifiedUseCase,
) : ViewModel() {

    private val _splashUiEvent = MutableSharedFlow<SplashUiEvent>(replay = 1)
    val splashUiEvent: SharedFlow<SplashUiEvent> = _splashUiEvent

    init {
        verifyCurrentUser()
    }

    private fun verifyCurrentUser() = viewModelScope.launch {
        validateCurrentUserUseCase()
            .onSuccess { email ->
                if (email.isNotEmpty()) {
                    isEmailVerified()
                } else {
                    _splashUiEvent.emit(SplashUiEvent.GoToLogin)
                }
            }
            .onFailure { exception ->
                _splashUiEvent.emit(SplashUiEvent.Error(exception))
            }
    }

    @VisibleForTesting
    fun isEmailVerified() = viewModelScope.launch {
        emailVerifiedUseCase()
            .onSuccess { isEmailVerified ->
                if (isEmailVerified) {
                    _splashUiEvent.emit(SplashUiEvent.GoToOrder)
                } else {
                    _splashUiEvent.emit(SplashUiEvent.GoToLogin)
                }
            }
            .onFailure { exception ->
                _splashUiEvent.emit(SplashUiEvent.Error(exception))
            }
    }
}
