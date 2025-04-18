/*
 * LoginRepositoryImpl.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.data.repository.login

import com.google.firebase.auth.AuthResult
import com.miventa.compose.mobile.data.datasource.login.LoginNetworkDataSource
import com.miventa.compose.mobile.domain.repository.login.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginNetworkDataSource: LoginNetworkDataSource,
    private val dispatcher: CoroutineDispatcher,
) : LoginRepository {

    override suspend fun verifyCurrentUser(): String = withContext(dispatcher) {
        loginNetworkDataSource.verifyCurrentUser()
    }

    override suspend fun isEmailVerified(): Boolean = withContext(dispatcher) {
        loginNetworkDataSource.isEmailVerified()
    }

    override suspend fun login(
        email: String,
        password: String,
    ): AuthResult = withContext(dispatcher) {
        loginNetworkDataSource.login(email, password)
    }

    override suspend fun emailHasBenVerified(): Flow<Boolean> =
        loginNetworkDataSource.emailHasBenVerified().flowOn(dispatcher)

    override suspend fun recoverPassword(email: String): Void = withContext(dispatcher) {
        loginNetworkDataSource.recoverPassword(email)
    }

    override suspend fun registerUser(
        email: String,
        password: String,
    ): AuthResult = withContext(dispatcher) {
        loginNetworkDataSource.registerUser(email, password)
    }

    override suspend fun sendVerificationEmail() = withContext(dispatcher) {
        loginNetworkDataSource.sendVerificationEmail()
    }
}
