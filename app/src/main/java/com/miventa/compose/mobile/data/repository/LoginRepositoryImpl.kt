/*
 * LoginRepositoryImpl.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.data.repository

import com.google.firebase.auth.AuthResult
import com.miventa.compose.mobile.data.datasource.LoginNetworkDataSource
import com.miventa.compose.mobile.domain.repository.LoginRepository
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

    override suspend fun registerUser(
        email: String,
        password: String,
    ): AuthResult = withContext(dispatcher) {
        loginNetworkDataSource.registerUser(email, password)
    }

    override suspend fun sendVerificationEmail() = withContext(dispatcher) {
        loginNetworkDataSource.sendVerificationEmail()
    }

    override suspend fun verifyEmailIsVerified(): Flow<Boolean> =
        loginNetworkDataSource.verifyEmailIsVerified().flowOn(dispatcher)

    override suspend fun login(
        email: String,
        password: String,
    ): AuthResult = withContext(dispatcher) {
        loginNetworkDataSource.login(email, password)
    }

    override suspend fun isEmailVerified(): Boolean = withContext(dispatcher) {
        loginNetworkDataSource.isEmailVerified()
    }

    override suspend fun recoverPassword(email: String): Void = withContext(dispatcher) {
        loginNetworkDataSource.recoverPassword(email)
    }
}
