/*
 * RecoverUiEvent.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.presentation.auth.viewmodel.recover

import com.miventa.compose.mobile.domain.model.login.RecoverStatus

sealed class RecoverUiEvent {
    internal data class Error(val exception: Throwable?) : RecoverUiEvent()
    internal data class ValidateRecoverForm(val status: RecoverStatus) : RecoverUiEvent()
    internal data object NavigateToValidateRecover : RecoverUiEvent()
}
