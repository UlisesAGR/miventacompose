/*
 * LoginUiEvent.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.start.viewmodel.login.state

import com.miventa.compose.mobile.domain.model.login.LoginStatus
import com.miventa.compose.mobile.domain.model.login.RecoverStatus
import com.miventa.compose.mobile.domain.model.login.RegisterStatus

sealed class LoginUiEvent {
    internal data object Initial : LoginUiEvent()
    internal data class Error(val exception: Throwable?) : LoginUiEvent()

    internal data class ValidateRegisterForm(val status: RegisterStatus) : LoginUiEvent()
    internal data class ValidateRecoverForm(val status: RecoverStatus) : LoginUiEvent()
    internal data class ValidateLoginForm(val status: LoginStatus) : LoginUiEvent()

    internal data object NavigateToValidateRegister : LoginUiEvent()
    internal data object NavigateToValidateRecover : LoginUiEvent()
    internal data object NavigateToOrder : LoginUiEvent()
}
