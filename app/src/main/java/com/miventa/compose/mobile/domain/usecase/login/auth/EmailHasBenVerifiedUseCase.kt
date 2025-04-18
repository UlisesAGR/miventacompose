/*
 * EmailHasBenVerifiedUseCase.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.domain.usecase.login.auth

import com.miventa.compose.mobile.domain.repository.login.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EmailHasBenVerifiedUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {

    suspend operator fun invoke(): Flow<Boolean> =
        loginRepository.emailHasBenVerified()
}
