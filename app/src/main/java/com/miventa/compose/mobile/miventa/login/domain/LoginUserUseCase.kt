/*
 * LoginUserUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.miventa.login.domain

import com.google.firebase.auth.AuthResult
import com.miventa.compose.mobile.miventa.login.data.repository.LoginRepository
import com.miventa.compose.mobile.util.ValidateYourEmail
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(
        email: String,
        password: String,
    ): Result<AuthResult> = with(dispatcher) {
        runCatching {
            if (loginRepository.isEmailVerified()) {
                loginRepository.login(email, password)
            } else {
                throw ValidateYourEmail()
            }
        }
    }
}
