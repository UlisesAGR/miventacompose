/*
 * ValidationRegisterUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.usecase.login.validation

import com.miventa.compose.mobile.domain.model.login.RegisterStatus
import com.miventa.compose.mobile.util.isValidEmail
import javax.inject.Inject

class ValidationRegisterUseCase @Inject constructor() {

    operator fun invoke(
        email: String,
        password: String,
        passwordConfirm: String,
    ): RegisterStatus =
        when {
            email.isEmpty() -> RegisterStatus.EMPTY_EMAIL
            !email.isValidEmail() -> RegisterStatus.INVALID_EMAIL
            password.isEmpty() -> RegisterStatus.EMPTY_PASSWORD
            password.length < 6 -> RegisterStatus.PASSWORD_LENGTH
            passwordConfirm.isEmpty() -> RegisterStatus.CONFIRM_PASSWORD_LENGTH
            password != passwordConfirm -> RegisterStatus.PASSWORD_NOT_SAME
            else -> RegisterStatus.SUCCESS
        }
}
