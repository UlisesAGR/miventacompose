/*
 * RegisterUiEvent.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.viewmodel.register

import com.miventa.compose.mobile.domain.model.login.RegisterStatus

sealed class RegisterUiEvent {
    internal data class Error(val exception: Throwable?) : RegisterUiEvent()
    internal data class ValidateRegisterForm(val status: RegisterStatus) : RegisterUiEvent()
    internal data object NavigateToValidateRegister : RegisterUiEvent()
}
