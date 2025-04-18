/*
 * ValidateCurrentUserUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.usecase.login.auth

import com.miventa.compose.mobile.domain.repository.login.LoginRepository
import javax.inject.Inject

class ValidateCurrentUserUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {

    suspend operator fun invoke(): Result<String> =
        runCatching {
            loginRepository.verifyCurrentUser()
        }
}
