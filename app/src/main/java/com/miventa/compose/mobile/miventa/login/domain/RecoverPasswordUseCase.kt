/*
 * RecoverPasswordUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.miventa.login.domain

import com.miventa.compose.mobile.miventa.login.data.repository.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RecoverPasswordUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(email: String): Result<Void> = with(dispatcher) {
        runCatching {
            loginRepository.recoverPassword(email)
        }
    }
}
