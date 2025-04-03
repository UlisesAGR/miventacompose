/*
 * SplashViewModel.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.start.viewmodel.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miventa.compose.mobile.domain.usecase.auth.EmailVerifiedUseCase
import com.miventa.compose.mobile.domain.usecase.auth.ValidateCurrentUserUseCase
import com.miventa.compose.mobile.util.Constants.DELAY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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

    private var _splashUiEvent = MutableSharedFlow<SplashUiEvent>()
    val splashUiEvent: SharedFlow<SplashUiEvent> = _splashUiEvent

    init {
        verifyCurrentUser()
    }

    private fun verifyCurrentUser() = viewModelScope.launch {
        delay(DELAY)
        validateCurrentUserUseCase()
            .onSuccess { email ->
                if (email.isNotEmpty()) {
                    isEmailVerified()
                } else {
                    _splashUiEvent.emit(SplashUiEvent.GoToLogin)
                }
            }
            .onFailure {
                _splashUiEvent.emit(
                    SplashUiEvent.Error(
                        it
                    )
                )
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
            .onFailure {
                _splashUiEvent.emit(
                    SplashUiEvent.Error(
                        it
                    )
                )
            }
    }
}
