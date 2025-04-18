/*
 * ValidationLoginUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.usecase.login.validation

import com.miventa.compose.mobile.domain.model.login.LoginStatus
import com.miventa.compose.mobile.util.isValidEmail
import javax.inject.Inject

class ValidationLoginUseCase @Inject constructor() {

    operator fun invoke(
        email: String,
        password: String,
    ): LoginStatus =
        when {
            email.isEmpty() -> LoginStatus.EMPTY_EMAIL
            !email.isValidEmail() -> LoginStatus.INVALID_EMAIL
            password.isEmpty() -> LoginStatus.PASSWORD_EMAIL
            else -> LoginStatus.SUCCESS
        }
}
