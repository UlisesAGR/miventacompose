/*
 * LoginNetworkDataSourceImpl.kt
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
package com.miventa.compose.mobile.data.datasource.login

import com.google.firebase.auth.AuthResult
import com.miventa.compose.mobile.data.firebase.AuthManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginNetworkDataSourceImpl @Inject constructor(
    private val firebaseAuth: AuthManager,
) : LoginNetworkDataSource {

    override suspend fun verifyCurrentUser(): String =
        runCatching {
            firebaseAuth.verifyCurrentUser()
        }.getOrDefault(defaultValue = "")

    override suspend fun registerUser(
        email: String,
        password: String,
    ): AuthResult =
        firebaseAuth.registerUser(email, password).await()

    override suspend fun sendVerificationEmail() {
        runCatching {
            firebaseAuth.sendVerificationEmail()
        }
    }

    override suspend fun emailHasBenVerified(): Flow<Boolean> =
        firebaseAuth.emailHasBenVerified()

    override suspend fun login(
        email: String,
        password: String,
    ): AuthResult =
        firebaseAuth.login(email, password).await()

    override suspend fun isEmailVerified(): Boolean =
        runCatching {
            firebaseAuth.isEmailVerified()
        }.getOrDefault(false)

    override suspend fun recoverPassword(email: String): Void =
        firebaseAuth.recoverPassword(email).await()
}
