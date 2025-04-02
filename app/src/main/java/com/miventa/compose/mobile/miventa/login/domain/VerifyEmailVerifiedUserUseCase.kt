/*
 * VerifyEmailVerifiedUserUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.miventa.login.domain

import com.miventa.compose.mobile.miventa.login.data.repository.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class VerifyEmailVerifiedUserUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(): Flow<Boolean> =
        loginRepository.verifyEmailIsVerified().flowOn(dispatcher)
}
