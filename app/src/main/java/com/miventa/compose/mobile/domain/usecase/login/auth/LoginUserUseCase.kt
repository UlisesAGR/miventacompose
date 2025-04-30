/*
 * LoginUserUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.usecase.login.auth

import com.google.firebase.auth.AuthResult
import com.miventa.compose.mobile.domain.repository.login.LoginRepository
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {

    suspend operator fun invoke(
        email: String,
        password: String,
    ): Result<AuthResult> =
        runCatching {
            loginRepository.login(email, password)
        }
}
