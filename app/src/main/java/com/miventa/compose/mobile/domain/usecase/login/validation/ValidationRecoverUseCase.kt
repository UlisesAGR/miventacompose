/*
 * ValidationRecoverUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.usecase.login.validation

import com.miventa.compose.mobile.domain.model.login.RecoverStatus
import com.miventa.compose.mobile.util.isValidEmail
import javax.inject.Inject

class ValidationRecoverUseCase @Inject constructor() {

    operator fun invoke(email: String): RecoverStatus =
        when {
            email.isEmpty() -> RecoverStatus.EMPTY_EMAIL
            !email.isValidEmail() -> RecoverStatus.INVALID_EMAIL
            else -> RecoverStatus.SUCCESS
        }
}
