/*
 * LoginRepositoryImpl.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.data.repository

import com.google.firebase.auth.AuthResult
import com.miventa.compose.mobile.data.datasource.LoginNetworkDataSource
import com.miventa.compose.mobile.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginNetworkDataSource: LoginNetworkDataSource,
) : LoginRepository {

    override suspend fun verifyCurrentUser(): String =
        loginNetworkDataSource.verifyCurrentUser()

    override suspend fun isEmailVerified(): Boolean =
        loginNetworkDataSource.isEmailVerified()

    override suspend fun login(
        email: String,
        password: String,
    ): AuthResult =
        loginNetworkDataSource.login(email, password)

    override suspend fun emailHasBenVerified(): Flow<Boolean> =
        loginNetworkDataSource.emailHasBenVerified()

    override suspend fun recoverPassword(email: String): Void =
        loginNetworkDataSource.recoverPassword(email)

    override suspend fun registerUser(
        email: String,
        password: String,
    ): AuthResult =
        loginNetworkDataSource.registerUser(email, password)

    override suspend fun sendVerificationEmail() =
        loginNetworkDataSource.sendVerificationEmail()
}
