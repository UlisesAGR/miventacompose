/*
 * LoginUiEven.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.viewmodel.login

import com.miventa.compose.mobile.domain.model.login.LoginStatus

sealed class LoginUiEvent {
    internal data class Error(val exception: Throwable?) : LoginUiEvent()
    internal data class ValidateLoginForm(val status: LoginStatus) : LoginUiEvent()
    internal data object NavigateToOrder : LoginUiEvent()
}
