/*
 * SendVerificationEmailUserUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.miventa.login.domain

import com.miventa.compose.mobile.miventa.login.data.repository.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SendVerificationEmailUserUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(): Result<Unit> = with(dispatcher) {
        runCatching {
            loginRepository.sendVerificationEmail()
        }
    }
}
