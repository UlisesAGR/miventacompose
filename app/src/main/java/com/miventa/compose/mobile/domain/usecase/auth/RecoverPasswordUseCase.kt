/*
 * RecoverPasswordUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.usecase.auth

import com.miventa.compose.mobile.domain.repository.LoginRepository
import javax.inject.Inject

class RecoverPasswordUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {

    suspend operator fun invoke(email: String): Result<Void> =
        runCatching {
            loginRepository.recoverPassword(email)
        }
}
