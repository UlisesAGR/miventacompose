/*
 * EmailVerifiedUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.usecase.login.auth

import com.miventa.compose.mobile.domain.repository.login.LoginRepository
import javax.inject.Inject

class EmailVerifiedUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {

    suspend operator fun invoke(): Result<Boolean> =
        runCatching {
            loginRepository.isEmailVerified()
        }
}
