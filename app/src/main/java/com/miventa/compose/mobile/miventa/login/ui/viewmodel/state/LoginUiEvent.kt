/*
 * LoginUiEvent.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.miventa.login.ui.viewmodel.state

import com.miventa.compose.mobile.util.LoginStatus
import com.miventa.compose.mobile.util.RecoverStatus
import com.miventa.compose.mobile.util.RegisterStatus

sealed class LoginUiEvent {
    internal data object Empty : LoginUiEvent()

    internal data class Error(val exception: Throwable?) : LoginUiEvent()

    internal data class ValidateLoginForm(val status: LoginStatus) : LoginUiEvent()
    internal data class ValidateRecoverForm(val status: RecoverStatus) : LoginUiEvent()
    internal data class ValidateRegisterForm(val status: RegisterStatus) : LoginUiEvent()

    internal data object NavigateToOrder : LoginUiEvent()
    internal data object NavigateToValidateRecover : LoginUiEvent()
    internal data object NavigateToValidateRegister : LoginUiEvent()
}
